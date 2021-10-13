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
        String query = "SELECT * FROM punch WHERE id=?";
        
        try{
           
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(id));
            boolean hasresults = pstmt.execute();
            
            if(hasresults){
                
                resultset = pstmt.getResultSet();
                resultset.next();
                
                String punchtypeid = resultset.getString("punchtypeid");
                String badgeid = resultset.getString("badgeid");
                String terminalid = resultset.getString("terminalid");
                
                //terminal id, badge id, punchtype id <-- Punch constructor param
                //Call punch constructor and return to caller
                
                System.out.print("Punch type: " + punchtypeid + '\n');
                System.out.print("Badge ID: " + badgeid + '\n');
                System.out.print("Terminal Id: " + terminalid);
                
                Punch punch = new Punch(terminalid, badgeid, punchtypeid);
                
            }
            
        } catch (Exception e) { e.printStackTrace(); }
        
        return punch ;
    }
    
    
    public void close(){
        
        /* Close Database Connection */
      
        try{
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }

    }   
    
    
}
