/*
 * 
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
public class UsuarioRepository {

    public static int save(Usuario u) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO Usuario (RFCU, RFC, NumAcciones, UltPrecioCompra ) values(?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, u.getRfcU());
            pstmt.setString(2, u.getRfc());
            pstmt.setInt(3, u.getNumA());
            pstmt.setFloat(4, u.getUpc());

            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static int update(Usuario u) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "UPDATE Usuario SET RFC=?, NumAcciones=?, UltPrecioCompra=? WHERE RFCU=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, u.getRfc());
            pstmt.setInt(2, u.getNumA());
            pstmt.setFloat(3, u.getUpc());
            pstmt.setString(4, u.getRfcU());

            iRet = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return iRet;
    }

    public static int delete(Usuario u) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "DELETE FROM Usuario WHERE RFCU=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, u.getRfcU());

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
            String SQL = "DELETE FROM Usuario";
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
            String QRY = "SELECT * FROM Usuario ORDER BY RFCU";
            Connection con = DBManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QRY);

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setRfcU(rs.getString("RFCU"));
                u.setRfc(rs.getString("RFC"));
                u.setNumA(rs.getInt("NumAcciones"));
                u.setUpc(rs.getFloat("UltPrecioCompra"));
                arr.add(u);
            }

            stmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }

    public static ArrayList findByCompany(String rfc) {
        ArrayList arr = new ArrayList();

        try {
            String QRY = "SELECT * FROM Usuario WHERE rfc LIKE(?) ORDER BY rfcu";
            Connection con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(QRY);
            pstmt.setString(1, "%" + rfc + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setRfcU(rs.getString("RFCU"));
                u.setRfc(rs.getString("RFC"));
                u.setNumA(rs.getInt("NumAcciones"));
                u.setUpc(rs.getFloat("UltPrecioCompra"));
                arr.add(u);
            }

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }
    
    public static ArrayList findByRFCU(String rfcu) {
        ArrayList arr = new ArrayList();

        try {
            String QRY = "SELECT * FROM Usuario WHERE RFCU=(?) ORDER BY rfcu";
            Connection con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(QRY);
            pstmt.setString(1,rfcu);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setRfcU(rs.getString("RFCU"));
                u.setRfc(rs.getString("RFC"));
                u.setNumA(rs.getInt("NumAcciones"));
                u.setUpc(rs.getFloat("UltPrecioCompra"));
                arr.add(u);
            }

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }
    
     public static ArrayList findByCompanyAndRFCU(String company, String rfcu) {
        ArrayList arr = new ArrayList();

        try {
            String QRY = "SELECT * FROM Usuario WHERE RFCU=? AND RFC=? ORDER BY rfcu";
            Connection con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(QRY);
            pstmt.setString(1,rfcu);
            pstmt.setString(2, company);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setRfcU(rs.getString("RFCU"));
                u.setRfc(rs.getString("RFC"));
                u.setNumA(rs.getInt("NumAcciones"));
                u.setUpc(rs.getFloat("UltPrecioCompra"));
                arr.add(u);
            }

            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }
   
}
