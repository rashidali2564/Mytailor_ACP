/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytailor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aalib
 */
public class DBconnection {
    Connection conn=null;
	DBconnection () {
            
		//String url="jdbc:mysql://localhost:3306/";
                //String dbName="mytailor";
                //String driver="com.mysql.cj.jdbc.Driver";
                //String userName="root";
                //String password="";
                try{
                Class.forName("com.mysql.jdbc.Driver");
                this.conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mytailor","root","");
                System.out.println("coneected");
                }catch(Exception ex){
                    System.out.println("not coneected" + ex);
                }
	}
	public Connection getConnection(){
		return conn;
		
	}
	public void destroy(){
		try{
			conn.close();
		}catch(SQLException ex){
			
		}
	}

    
    
}
