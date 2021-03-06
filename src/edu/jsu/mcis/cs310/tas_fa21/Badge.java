package edu.jsu.mcis.cs310.tas_fa21;

public class Badge {
    
    private String id, description;
    //id is employee's id number
    //description is employee's name 
    
    public Badge(String id, String description) {
        
        this.id = id;
        this.description = description;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString(){
    
        StringBuilder s = new StringBuilder();
        s.append("#").append(id).append(" (").append(description).append(")");
        
        return s.toString();
    }
}