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
import java.util.ArrayList;

/**
 *
 * @author Esthefany
 */
public class CompaniaRepository {

    public static int save(Compania c) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO Compania (RFC, NumTotalAcciones, AccionesDisponibles, ValorActualAccion) values(?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, c.getRfc());
            pstmt.setInt(2, c.getNumTA());
            pstmt.setInt(3, c.getNumAD());
            pstmt.setFloat(4, c.getvTA());
            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static int update(Compania c) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "UPDATE Compania SET NumTotalAcciones=?, AccionesDisponibles=?,ValorActualAccion=? WHERE RFC=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, c.getNumTA());
            pstmt.setInt(2, c.getNumAD());
            pstmt.setFloat(3, c.getvTA());
            pstmt.setString(4, c.getRfc());

            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static int delete(Compania c) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "DELETE FROM Compania WHERE RFC=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, c.getRfc());

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
            String SQL = "DELETE FROM Compania";
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
            String QRY = "SELECT * FROM Compania ORDER BY RFC";
            Connection con = DBManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QRY);

            while (rs.next()) {
                Compania c = new Compania();
                c.setRfc(rs.getString("RFC"));
                c.setNumTA(rs.getInt("NumTotalAcciones"));
                c.setNumAD(rs.getInt("AccionesDisponibles"));
                c.setvTA(rs.getFloat("ValorActualAccion"));
                arr.add(c);
            }

            stmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }
    
    public static ArrayList findByRFC(String rfcu) {
        ArrayList arr = new ArrayList();

        try {
            String QRY = "SELECT * FROM Compania WHERE RFC=(?) ORDER BY rfc";
            Connection con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(QRY);
            pstmt.setString(1,rfcu);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Compania c = new Compania();
                c.setRfc(rs.getString("RFC"));
                c.setNumTA(rs.getInt("NumTotalAcciones"));
                c.setNumAD(rs.getInt("AccionesDisponibles"));
                c.setvTA(rs.getFloat("ValorActualAccion"));
                arr.add(c);
            }

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }
    
    
}
