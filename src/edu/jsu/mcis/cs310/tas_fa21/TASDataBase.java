
package edu.jsu.mcis.cs310.tas_fa21;


import java.sql.*;
import java.sql.Connection;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


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

         catch (SQLException e) {}  
    }
    
    //Change to punch type once in project
    //get punch
    public Punch getPunch(int id){
        
        Punch punch = null;
        
        /*Prepare query for punch with provided id*/
        String query = "SELECT * FROM punch WHERE id=?";
            
        try{
           
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            boolean hasresults = pstmt.execute();
            
            if(hasresults){
                
                resultset = pstmt.getResultSet();
                resultset.first();
                
                int punchtypeid = resultset.getInt("punchtypeid");
                String badgeid = resultset.getString("badgeid");
                int terminalid = resultset.getInt("terminalid");
                LocalDateTime originaltimestamp = resultset.getTimestamp("originaltimestamp").toLocalDateTime();
                
              //Punch(int id, int terminalid, Badge badge, PunchType punchtypeid, LocalDateTime originaltimestamp)  
                punch = new Punch(id, terminalid, getBadge(badgeid), punchtypeid, originaltimestamp);
                
            }
            
        } catch (SQLException e) {}
        
        return punch;
    }
    //get badge
    public Badge getBadge(String id){
        
        String query = "SELECT * FROM badge WHERE id=?";
        
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
        } catch (SQLException e) {}
                
        Badge badge = new Badge(id, description);
        return badge;
    }
    //get shift
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
                
                s = new Shift(interval, shiftID, gracePeriod, shiftStart, dock, lunchStart, lunchDeduct, lunchStop, shiftStop, description);
            }
        }
        catch(SQLException e) {
            System.err.println("** getShift: " + e.toString());
    }
    return s;
    
    }
    //get shift
    public Shift getShift(Badge b) {
        
        Shift s = null;
        
        try {
            pstSelect = conn.prepareStatement("SELECT employee.shiftid, shift.* FROM employee, shift WHERE employee.shiftid = shift.id AND employee.badgeid = ?");
            pstSelect.setString(1, b.getId());

            boolean hasresult = pstSelect.execute();
             
            if (hasresult) {
                
                ResultSet resultset = pstSelect.getResultSet();
                resultset.first();

                //results
                int shiftID = resultset.getInt("shiftid");
                
                String description = resultset.getString("description");
                LocalTime shiftStart = LocalTime.parse(resultset.getString("start"));
                LocalTime shiftStop = LocalTime.parse(resultset.getString("stop"));
                int interval = resultset.getInt("interval");
                int gracePeriod = resultset.getInt("graceperiod");
                int dock = resultset.getInt("dock");
                LocalTime lunchStart = LocalTime.parse(resultset.getString("lunchstart"));
                LocalTime lunchStop = LocalTime.parse(resultset.getString("lunchstop"));
                int lunchDeduct = resultset.getInt("lunchdeduct");

                s = new Shift(interval, shiftID, gracePeriod, shiftStart, dock, lunchStart, lunchDeduct, lunchStop, shiftStop, description);
                }
              
            }

        catch(SQLException e) {
            System.err.println("** getShift: " + e.toString());
        }
        
        
        return s;
    }
    
    public int insertPunch(Punch p) {
        String query;
        
        int updateCount;
        
        //get the punch data?
        int results = 0;
        //results = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime originalTime = p.getOriginaltimestamp();
        //get
        String badgeid = p.getBadge().getId();
        //not working?? think its right though
                
        int terminalid = p.getTerminalid(); 
        PunchType punchtypeid = p.getPunchtype();
        
        try {
            //prepare for the query
            query = "INSERT INTO punch (terminalid, badgeid, originaltimestamp, punchtypeid) VALUES (?, ?, ?, ?)"; 

            pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstUpdate.setInt(1, terminalid);
            pstUpdate.setString(2, badgeid);
            pstUpdate.setString(3, dtf.format(originalTime));
            pstUpdate.setInt(4, punchtypeid.ordinal());
        
            updateCount = pstUpdate.executeUpdate();

            if(updateCount > 0){

                ResultSet resultset = pstUpdate.getGeneratedKeys(); 

                if (resultset.next()){
                    results = resultset.getInt(1);
                }
            }
        }
        catch(SQLException e) {
        }
        System.err.println("New Punch ID: " + results);
        return results;              
    }
    
    
    public ArrayList<Punch> getDailyPunchList(Badge badge, LocalDate date) {

        ArrayList<Punch> output = null;
        Punch obj; 
        output = new ArrayList<>(); 
        String strbadge = badge.getId();
        try {
            String query = "SELECT * FROM punch WHERE badgeid=? AND DATE(originalTimeStamp)=?";
            PreparedStatement pstSelect = conn.prepareStatement(query);
            pstSelect.setString(1, strbadge);
            pstSelect.setDate(2, java.sql.Date.valueOf(date));
            pstSelect.setString(1, badge.getId());
            pstSelect.setDate(2, java.sql.Date.valueOf(date));
            
            boolean hasResults = pstSelect.execute();
            
            
            if(hasResults){
                
                //output = new ArrayList<>();
                
                ResultSet resultsSet = pstSelect.getResultSet();
                while(resultsSet.next()) {
                    
                    int id = resultsSet.getInt("id");
                    int terminalid = resultsSet.getInt("terminalid");
                    String badgeid = resultsSet.getString("badgeid");
                    LocalDateTime originalTimeStamp = resultsSet.getTimestamp("originalTimeStamp").toLocalDateTime();
                    int punchTypeid = resultsSet.getInt("punchTypeId");
                    int punchid = resultsSet.getInt("id");
                    obj = getPunch(punchid);
                    output.add(obj);
                }
            }
                  
        }
        catch (SQLException e) {}
        return output;
    }

    public void close(){
        
        /*close the database*/
        try{
            conn.close();
        } catch (SQLException e) {}

    }   
}
