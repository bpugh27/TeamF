/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_fa21;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class TASDataBase {
    
    Connection conn;
    ResultSetMetaData metadata;
    ResultSet resultset = null;        
    PreparedStatement pstSelect = null, pstUpdate = null;
    
    String server = ("jdbc:mysql://localhost/tas_fa21_v1");
    
    String username = "TeamF";
    String password = "R259";
    
    String query;
    
    
    public TASDataBase(){

         try {
             /* Open Connection*/
             conn = DriverManager.getConnection(server, username, password);
             System.out.println("Connection Successful");
         }

         catch (Exception e) { e.printStackTrace(); }  
    }
    
    
    //Change to punch type once in project
    public Punch getPunch(int id){
        /*Prepare query for punch with provided id*/
        query = "SELECT * FROM punch WHERE id=" + id;
        
        try{
           
            PreparedStatement pstmt = conn.prepareStatement(query);
            boolean hasresults = pstmt.execute();
            
            if(hasresults){
                
                resultset = pstmt.getResultSet();
                resultset.next();
                String punchid = resultset.getString("id");
                String desc = resultset.getString("description");
                
                System.out.print(punchid);
                System.out.print(desc);
            }
            
        } catch (Exception e) { e.printStackTrace(); }

    }
    
    public void close(){
        
        /* Close Database Connection */
      
        try{
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }

    }   
    
    
}
