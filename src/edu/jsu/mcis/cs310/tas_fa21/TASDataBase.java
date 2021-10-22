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
import java.time.*;


public class TASDataBase {
    
    Connection conn;
    ResultSetMetaData metadata;
    ResultSet resultset = null;        
    PreparedStatement pstSelect = null, pstUpdate = null;
    
    String server = ("jdbc:mysql://localhost/tas_fa21_v1");
    
    String username = "TeamF";
    String password = "R259";
    
    //Create and store database connection
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
        
        String badgeid = null;
        String punchtypeid = null;
        String terminalid = null;
            
        try{
           
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(id));
            boolean hasresults = pstmt.execute();
            
            
            
            if(hasresults){
                
                resultset = pstmt.getResultSet();
                resultset.next();
                
                punchtypeid = resultset.getString("punchtypeid");
                badgeid = resultset.getString("badgeid");
                terminalid = resultset.getString("terminalid");
                
                //terminal id, badge id, punchtype id <-- Punch constructor param
                //Call punch constructor and return to caller
                
                //Test Output Block
                System.out.print("Punch type: " + punchtypeid + '\n');
                System.out.print("Badge ID: " + badgeid + '\n');
                System.out.print("Terminal Id: " + terminalid);
                
                
                
            }
            
        } catch (Exception e) { e.printStackTrace(); }
        
        Punch punch = new Punch(Integer.parseInt(terminalid), getBadge(badgeid), Integer.parseInt(punchtypeid));
        
        return punch ;
    }
    
    
    public Badge getBadge(String id){
        
        String query = "SELECT * FROM punch WHERE id=?";
        String description = null;
        
        try{
           
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(id));
            boolean hasresults = pstmt.execute();
            
            if(hasresults){
                
                resultset = pstmt.getResultSet();
                resultset.next();
                
                description = resultset.getString("description");
            }
        } catch (Exception e) {e.printStackTrace(); }
                
        Badge badge = new Badge(id, description);
        return  badge;
    }
    
    
    public Shift getShift(int shiftID){
        
        //public Shift(int interval, int id, int gracePeriod, Time begin, int dock, Time lunchBegin, int lunchDeduct, Time lunchEnd, Time end, String description) 
        
        Shift s = null;
        
        try {
            pstSelect = conn.prepareStatement("SELECT * from shift where id = ?");
            pstSelect.setInt(1, shiftID);
            
            boolean hasresult = pstSelect.execute();
            if(hasresult) {
                System.err.println("Getting shift data. ");
                ResultSet resultset = pstSelect.getResultSet();
                resultset.first();
                
                //getting the results?
                String description = resultset.getString("description");
                LocalTime shiftStart = LocalTime.parse(resultset.getString("start"));
                LocalTime shiftStop = LocalTime.parse(resultset.getString("stop"));
                int interval = resultset.getInt("interval");
                int gracePeriod = resultset.getInt("graceperiod");
                int dock = resultset.getInt("dock");
                
                LocalTime lunchStart = LocalTime.parse(resultset.getString("lunchstart"));
                LocalTime lunchStop = LocalTime.parse(resultset.getString("lunchstop"));
                
                int lunchDeduct = resultset.getInt("lunchdeduct");

                //public Shift(int interval, int id, int gracePeriod, Time begin, int dock, Time lunchBegin, 
                //int lunchDeduct, Time lunchEnd, Time end, String description)
                Shift s = new Shift(shiftID, description, shiftStart, shiftStop, interval, 
                              gracePeriod, dock, lunchStart, lunchStop, lunchDeduct);
            }
        }
        catch(Exception e) {
            System.err.println("** getShift: " + e.toString());
    }
    return s;
    
    }
    
    
    public void close(){
        
        /* Close Database Connection */
      
        try{
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }

    }   
    
    
}
