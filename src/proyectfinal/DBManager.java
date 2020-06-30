/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectfinal;
import java.sql.*;

/**
 *
 * @author 52999
 */
public final class DBManager {
 
  private static DBManager _instance = null;
  private Connection _con = null;
 
  public DBManager() {
  
 	_con = getMySQLConnection();
		
  }
 
  //Thread safe instatiate method
  public static synchronized DBManager getInstance() {
    if (_instance == null) {
      _instance = new DBManager();
    }
    return _instance;
  }
 
  public Connection getConnection() {
    return _con;
  }
 
  /**
   * Connection to MySQL Database
   */
  private static Connection getMySQLConnection() {
    Connection con = null;
 
    try {
 
      String strCon = "jdbc:mysql://127.0.0.1:3308/test?user=root&password=";
      con = DriverManager.getConnection(strCon);
    } catch (SQLException se) {
      System.out.println(se);
    }
    return con;
  }
  
}
