/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytailor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AutoIncreament {
    
public int auto_increment()
{

int id=1;
PreparedStatement ps=null;
ResultSet rs=null;
DBconnection db=new DBconnection();
 try
 {
	ps=db.getConnection().prepareStatement("SELECT MAX(ordno) FROM cust_info");
	rs=ps.executeQuery();
	while(rs.next())
	{
		id=rs.getInt(1)+1;
			
	}
  }catch(SQLException e)
  {
	System.out.println("Error"+e.getMessage());
	
  }
  finally
  {
	try
	{
		ps.close();
		rs.close();
		//db.destroy();//calling for distructor
	}catch(Exception e)
  {
  
  }
  }
return id;
}
//AUTO INCREMENT IN STAFF ID 
public int auto_incrementForStaff()
{

    int id=1;
    PreparedStatement ps=null;
    ResultSet rs=null;
    DBconnection db=new DBconnection();
 try
 {
	ps=db.getConnection().prepareStatement("SELECT MAX(staff_id) FROM staff");
	rs=ps.executeQuery();
	while(rs.next())
	{
		id=rs.getInt(1)+1;
			
	}
  }catch(SQLException e)
  {
	System.out.println("Error"+e.getMessage());
	
  }
  finally
  {
	try
	{
		ps.close();
		rs.close();
		db.destroy();//calling for distructor
	}catch(Exception e)
  {
  
  }
  }
return id;
}






public int auto_BillnoForPayment()
{

    int id=2010;
    PreparedStatement ps=null;
    ResultSet rs=null;
    DBconnection db=new DBconnection();
    
 try
 {
        ps=db.getConnection().prepareStatement("SELECT MAX(billno) FROM payment");
        rs=ps.executeQuery();
        
           
       
	while(rs.next())
	{
		id=rs.getInt(1)+1;
			
	}
         
       
        
  }catch(SQLException e)
  {
	System.out.println("Error"+e.getMessage());
	
  }
  finally
  {
	try
	{
		ps.close();
		rs.close();
		db.destroy();//calling for distructor
	}catch(SQLException e)
  {
  
  }
  }
return id;

}




public int auto_forTransactionID()
{

    int id=2010;
    PreparedStatement ps=null;
    ResultSet rs=null;
    DBconnection db=new DBconnection();
    
 try
 {
        ps=db.getConnection().prepareStatement("SELECT MAX(tranID) FROM paymentHistory");
        rs=ps.executeQuery();
        
           
       
	while(rs.next())
	{
		id=rs.getInt(1)+1;
			
	}
         
       
        
  }catch(SQLException e)
  {
	System.out.println("Error"+e.getMessage());
	
  }
  finally
  {
	try
	{
		ps.close();
		rs.close();
		db.destroy();//calling for distructor
	}catch(SQLException e)
  {
  
  }
  }
return id;

}

}
