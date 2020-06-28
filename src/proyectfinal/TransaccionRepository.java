/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Esthefany
 */
public class TransaccionRepository {

    public static int save(Transaccion t) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO Transaccion (RFCU, RFC, FechaOp, AccionesOp, PrecioAOp) values(?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, t.getRfcU());
            pstmt.setString(2, t.getRfc());

            java.util.Date dt = t.getFechaOp();

            java.text.SimpleDateFormat sdf
                    = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(dt);
            pstmt.setString(3, currentTime);
            pstmt.setInt(4, t.getAccionesOp());
            pstmt.setFloat(5, t.getPrecioAOp());
            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static int update(Transaccion t) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "UPDATE Transaccion SET FechaOp=?, AccionesOp=?, PrecioAOp=? WHERE RFCU=? AND RFC=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            java.util.Date dt = t.getFechaOp();

            java.text.SimpleDateFormat sdf
                    = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(dt);
            pstmt.setString(1, currentTime);
            pstmt.setInt(2, t.getAccionesOp());
            pstmt.setFloat(3, t.getPrecioAOp());
            pstmt.setString(4, t.getRfcU());
            pstmt.setString(5, t.getRfc());

            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static int delete(Transaccion t) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "DELETE FROM Transaccion WHERE RFCU=? AND RFC=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, t.getRfcU());
            pstmt.setString(2, t.getRfc());
            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return iRet;
    }

    public static void deleteAll() {
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            String SQL = "DELETE FROM Transaccion";
            PreparedStatement pstmt = con.prepareStatement(SQL);

            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException se) {
            try {
                con.rollback();
            } catch (SQLException ise) {
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException fse) {
            }
        }
    }

    public static ArrayList findAll() {
        ArrayList arr = new ArrayList();

        try {
            String QRY = "SELECT * FROM Transaccion ORDER BY RFCU";
            Connection con = DBManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QRY);

            while (rs.next()) {
                Transaccion t = new Transaccion();
                t.setRfcU(rs.getString("RFCU"));
                t.setRfc(rs.getString("RFC"));
                Timestamp ts = Timestamp.valueOf(rs.getString("FechaOp"));
                Date date = new Date(ts.getTime());
                t.setFechaOp(date);
                t.setAccionesO(rs.getInt("AccionesOp"));
                t.setPrecioAOp(rs.getFloat("PrecioAOp"));

                arr.add(t);
            }

            stmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }

}
