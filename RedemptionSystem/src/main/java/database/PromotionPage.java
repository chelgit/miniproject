/*
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.  U.S.
 * Government Rights - Commercial software.  Government users are subject
 * to the Sun Microsystems, Inc. standard license agreement and
 * applicable provisions of the FAR and its supplements.  Use is subject
 * to license terms.
 *
 * This distribution may include materials developed by third parties.
 * Sun, Sun Microsystems, the Sun logo, Java and J2EE are trademarks
 * or registered trademarks of Sun Microsystems, Inc. in the U.S. and
 * other countries.
 *
 * Copyright (c) 2006 Sun Microsystems, Inc. Tous droits reserves.
 *
 * Droits du gouvernement americain, utilisateurs gouvernementaux - logiciel
 * commercial. Les utilisateurs gouvernementaux sont soumis au contrat de
 * licence standard de Sun Microsystems, Inc., ainsi qu'aux dispositions
 * en vigueur de la FAR (Federal Acquisition Regulations) et des
 * supplements a celles-ci.  Distribue par des licences qui en
 * restreignent l'utilisation.
 *
 * Cette distribution peut comprendre des composants developpes par des
 * tierces parties. Sun, Sun Microsystems, le logo Sun, Java et J2EE
 * sont des marques de fabrique ou des marques deposees de Sun
 * Microsystems, Inc. aux Etats-Unis et dans d'autres pays.
 */


package database;

import java.sql.*;
import javax.sql.*;

import SMTP.EmailFunc_VoucherCode;

import javax.naming.*;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

// The instance of BookDBAO gets created when the application
// is deployed. It maintains the Connection object to the
// database. The Connection object is created from DataSource
// object, which is retrieved through JNDI.
// For more information on DataSource, please see
// http://java.sun.com/j2se/1.4.2/docs/api/javax/sql/DataSource.html.
public class PromotionPage {

    Connection con;
    private boolean conFree = true;
    
    // Database configuration
    public static String url = "jdbc:mysql://localhost:3306/REDEMPTION_DB";
    public static String dbdriver = "com.mysql.jdbc.Driver";
    public static String username = "root";
    public static String password = "Passw0rd";
    
    public PromotionPage() throws Exception {
        try {
            /*
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/BookDB");
            con = ds.getConnection();
            */
            Class.forName(dbdriver);
            con = DriverManager.getConnection(url, username, password);
            
        } catch (Exception ex) {
            System.out.println("Exception in AccountDBAO: " + ex);
            throw new Exception("Couldn't open connection to database: " +
                    ex.getMessage());
        }
    }
    
    public void remove() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    protected synchronized Connection getConnection() {
        while (conFree == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        
        conFree = false;
        notify();
        
        return con;
    }
    
    protected synchronized void releaseConnection() {
        while (conFree == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        
        conFree = true;
        notify();
    }
    
    public String ExtractUserID(String id)  throws NoSuchAlgorithmException {
    	String user_id="-999";//dummy
    	try {//extract user-id
        	String selectStatement = "select user_id from user where email_address = ?";      	
            getConnection();           
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, id);                
            ResultSet rs = prepStmt.executeQuery();           
            rs.next();//need Move the cursor to the next row
			user_id = rs.getString("user_id");
            System.out.println("Tracing of User_ID done");
            prepStmt.close();
            releaseConnection();       
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return user_id ;
    }
    
    
    public int GetPointBalance(String id)  throws NoSuchAlgorithmException {
    	int balancepoints=-5;//dummy value. Lowest point is 0
        try {
        	String selectStatement = "select balancepoints from pointbalance where user_id=( select user_id from user where email_address = ?)";      	
            getConnection();           
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, id);
            ResultSet rs = prepStmt.executeQuery();          
            if (rs.next()) {        	
              	balancepoints = rs.getInt("balancepoints");
              	return balancepoints;	             
            }             
            prepStmt.close();
            releaseConnection();       
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return balancepoints ;
    }
    
    public List GetPromotionItems()  throws NoSuchAlgorithmException {
    	List<Map> list = new ArrayList<Map>();
    	try {
        	String selectStatement = "select name,points,imagename,stock_count from promotion where curdate() between startdate and enddate;";      	
            getConnection();           
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery(); 
            while (rs.next()) {// next row
    			System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
    			// }//column 1 2 and 3
    			String name = rs.getString("name");
    			String points = rs.getString("points");
    			String imagename = rs.getString("imagename");
    			String stock_count = rs.getString("stock_count");
    			// Get table information for receiving database by loop

    			Map map = new HashMap(); // new instance of map class
    			map.put("name", name);
    			map.put("points", points);
    			map.put("imagename", imagename);
    			map.put("stock_count", stock_count);
    			// System.out.println(map);
    			list.add(map);           
           // prepStmt.close(); will have error if include this
          //  releaseConnection(); 
            }
    	} catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return list ;
    }
    
    public List GetHistoricalTxn(String id)  throws NoSuchAlgorithmException {
    	List<Map> list = new ArrayList<Map>();
    	String user_id="-999";//dummy
    	try {
			PromotionPage account = new PromotionPage();
			user_id = account.ExtractUserID(id);			}
		catch (Exception e)
		{
		 e.printStackTrace();	
		}
    	
    	try {
        	String selectStatement = "with temp as(select b.name,b.points,a.user_id,a.created_date from redemption a left join promotion b on b.promotion_id=a.promotion_id) select name,points,created_date from temp where user_id= ? order by created_date desc limit 5;";      	
            getConnection();           
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, user_id); 
            ResultSet rs = prepStmt.executeQuery(); 
            while (rs.next()) {// next row
    			System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
    			// }//column 1 2 and 3
    			String name = rs.getString("name");
    			String points = rs.getString("points");
    			String created_date = rs.getString("created_date");
    			// Get table information for receiving database by loop

    			Map map = new HashMap(); // new instance of map class
    			map.put("name", name);
    			map.put("points", points);
    			map.put("created_date", created_date);
    			// System.out.println(map);
    			list.add(map);           
           // prepStmt.close(); will have error if include this
          //  releaseConnection(); 
            }
    	} catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return list ;
    }
    
    
    
 
    public int CheckEnoughPoint(String promotion,String id)  throws NoSuchAlgorithmException {
    	int booleanvalue=-5;//dummy value. Lowest point is 0
        try {
        	String selectStatement = "select (select points from promotion where name=?)<=(select balancepoints from pointbalance where user_id=( select user_id from user where email_address = ?)) as bool";
            getConnection();           
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, promotion);
            prepStmt.setString(2, id);
            ResultSet rs = prepStmt.executeQuery();          
            if (rs.next()) {        	
            	booleanvalue = rs.getInt("bool");
              	return booleanvalue;	             
            }             
            prepStmt.close();
            releaseConnection();       
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return booleanvalue ;
    }
    
    public int CheckEnoughStock(String promotion)  throws NoSuchAlgorithmException {
    	int booleanvalue=-5;//dummy value. Lowest point is 0
        try {
        	String selectStatement = "select stock_count from promotion where name=?";
            getConnection();           
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, promotion);
            ResultSet rs = prepStmt.executeQuery();          
            if (rs.next()) {        	
            	booleanvalue = rs.getInt("stock_count");
              	return booleanvalue;	             
            }             
            prepStmt.close();
            releaseConnection();       
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return booleanvalue ;
    }
    
    public boolean UpdateStock_TxnTable_PointBalance(String promotion,String id)  throws NoSuchAlgorithmException{
    	boolean status1 = false;
    	boolean status2 = false;
    	boolean status3 = false;
    	String VoucherCode="";
    	String user_id="-999";//dummy
        try {	
        	//Update stock value      	
            String sqlStatement = "update promotion set stock_count=stock_count-1 where name=?";  
            getConnection();            
            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setString(1, promotion);          
            int x = prepStmt.executeUpdate();
                      
            if (x == 1) {
            	status1 = true;   
            	System.out.println("Stock has been updated.");
            } 
            
            prepStmt.close();
            releaseConnection();
           
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        
        try {	
        	//Update Point Value	
            String sqlStatement = "update pointbalance set redeemedpoints=redeemedpoints+(select points from promotion where name=?) where user_id=(select user_id from user where email_address = ?)";  
            getConnection();            
            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setString(1, promotion);
            prepStmt.setString(2, id); 
            int x = prepStmt.executeUpdate();
                      
            if (x == 1) {
            	status2 = true;   
            	System.out.println("Point Balance has been updated.");
            } 
            
            prepStmt.close();
            releaseConnection();
           
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        
        try {
			PromotionPage account = new PromotionPage();
			VoucherCode = account.VoucherCode();			}
		catch (Exception e)
		{
		 e.printStackTrace();	
		}	
       
        try {	
        	//Update txn table redemption  
        	try {
    			PromotionPage account = new PromotionPage();
    			user_id = account.ExtractUserID(id);			}
    		catch (Exception e)
    		{
    		 e.printStackTrace();	
    		}
        	
    		EmailFunc_VoucherCode.main(id,VoucherCode,promotion);//send out notification email to user for voucher code. (String RECIPIENT, String VoucherCode, String promotion)
    		System.out.println("Voucher Code Email Triggered");
        	      	    	
            String sqlStatement = "INSERT INTO `redemption_db`.`Redemption`(`user_id`,`promotion_id`,`redemption_date`,`vouchercode`,`created_user`,`modified_user`) select ?,promotion_id,curdate(),?,'dbo','dbo'from promotion where name=?;";  
            getConnection();            
            PreparedStatement prepStmt = con.prepareStatement(sqlStatement);
            prepStmt.setString(1, user_id);
            prepStmt.setString(2, VoucherCode);  
            prepStmt.setString(3, promotion);          
            int x = prepStmt.executeUpdate();
                      
            if (x == 1) {
            	status3 = true;   
            	System.out.println("Redemption Record Inserted");
            } 
            
            prepStmt.close();
            releaseConnection();
           
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }

        
        
        return status1 && status2 &&status3;//db updated return true
    }
    
    
    public String VoucherCode()  
          	 throws NoSuchAlgorithmException {
          	 String salt = getSalt();
          	 return salt;
       }
       
       public static String getSalt() {
   		 Random r = new SecureRandom();
   		 byte[] saltBytes = new byte[16];
   		 r.nextBytes(saltBytes);
   		 return Base64.getEncoder().encodeToString(saltBytes);
   	}
    
    
    
    
}
    
///
    
    
