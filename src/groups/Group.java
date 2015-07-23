package groups;

import java.util.Date;

public class Group {
     
    private String groupName,groupDisplayName;
    private Date creationDate;
    private int idGroup;

    public int getIdGroup() {
    	return idGroup;
    }
    
    public String getGroupName()
    {
    	return groupName;
    }
    public String getGroupDisplayName() {
    	return groupDisplayName;
    }
 
    public Date getCreationDate() {
    	return creationDate;
    }
    
    public void setIdGroup(int idGroup)
    {
    	this.idGroup=idGroup;
    }
    
    public void setGroupName(String groupName)
    {
    	this.groupName=groupName;
    }
    
    public void setGroupDisplayName(String groupDisplayName)
    {
    	this.groupDisplayName=groupDisplayName;
    }
    
    public void setCreationDate(Date creationDate)
    {
    	this.creationDate=creationDate;
    }
    
    public Group(int idGroup, String groupName,String groupDisplayName, Date creationDate)
    {	
    	this.idGroup=idGroup;
    	this.groupName=groupName;
    	this.groupDisplayName=groupDisplayName;
    	this.creationDate=creationDate;
    }
    
    public Group(){}
}

