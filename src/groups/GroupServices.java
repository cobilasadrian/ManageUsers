package groups;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DbRequests;



public class GroupServices {
	
	
	public List<Group> getAllGroups()
	{
		ResultSet rs = DbRequests.getAllGroups();
		
		List<Group> list = new ArrayList<Group>();
		int i=0;
	
		try{
		
	    	while(rs.next())
	         { 		
	    		Group g = new Group();
	    		g.setIdGroup(rs.getInt(1));
	    		g.setGroupName(rs.getString(2));
	    		g.setGroupDisplayName(rs.getString(3));
	    		g.setCreationDate(rs.getDate(4));
	            list.add(i,g);
	            i++;        
	         }
		}
		catch( Exception e ){ 
	        e.printStackTrace(); 
		} 
	  	
	   return list;
	}
	
	public Group getGroup(int idGroup)
	{
	    	
    	ResultSet rs = DbRequests.getGroup(idGroup);
    	
    	Group g = new Group();
    	
    	try{
    	
    		while(rs.next())
	         {
    			g.setIdGroup(rs.getInt(1));
	    		g.setGroupName(rs.getString(2));
	    		g.setGroupDisplayName(rs.getString(3));
	    		g.setCreationDate(rs.getDate(4));
	         }
    		
    	}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
    	
    	return g;
	}
	
	public List<Group> getChildrenGroupsFromGroup(String groupName)
    {
		ResultSet rs = DbRequests.getChildrenGroupsFromGroup(groupName);
		
		List<Group> list = new ArrayList<Group>();
    	int i=0;
 
    	try{
	    	while(rs.next())
	         {
	    		Group g = new Group();
	    		g.setIdGroup(rs.getInt(1));
	    		g.setGroupDisplayName(rs.getString(2));
	    		g.setCreationDate(rs.getDate(3));                  
	            list.add(i,g);
	            i++;
	         }	      
    	}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
      	
       return list;
    }
	
	public List<Group> getGroupsFromOutsideGroup(int idGroup)
    {
    	List<Group> list = new ArrayList<Group>();
    	int i=0;
    	
    	ResultSet rs = DbRequests.getGroupsFromOutsideGroup(idGroup);
    	
    	try{
    	
	    	while(rs.next())
	         {
	    		Group g = new Group();
	    		g.setIdGroup(rs.getInt(1));
	    		g.setGroupDisplayName(rs.getString(2));                    
	            list.add(i,g);
	            i++;
	         } 
	    	
    	}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
      	
       return list;
    }
	
	public boolean addNewGroup(String groupName,String groupDisplayName)
    {
    	ResultSet rs = DbRequests.addNewGroup(groupName,groupDisplayName);
    	
    	if(rs != null) return true;
		return false;
    
    }
	 
	public boolean editGroup(int idGroup,String groupName, String groupDisplayName)
    {
    	ResultSet rs = DbRequests.editGroup(idGroup,groupName,groupDisplayName);
    	
    	if(rs != null) return true;
		return false;
    
    }
	
	public boolean deleteGroup(int idGroup)
    {
    	ResultSet rs = DbRequests.deleteGroup(idGroup);
    	
    	if(rs != null) return true;
		return false;
    
    }
	
	public boolean addGroupInGroupMembership(int idParentGroup,int idChildGroup)
	{
		ResultSet rs = DbRequests.addGroupInGroupMembership(idParentGroup, idChildGroup);
		
		if(rs != null) return true;
		return false;
	
	}
	
	public boolean addUserInUserMembership(int idGroup, int idUser)
    {
    	ResultSet rs = DbRequests.addUserInUserMembership(idGroup,idUser);
    	
    	if(rs != null) return true;
		return false;
    
    }
	
	public boolean checkGroup(String groupName)
    {
    	ResultSet rs = DbRequests.checkGroup(groupName);
    	
    	int idGroup = 0 ;
    	
    	try {
			while(rs.next())
			{
			   idGroup = rs.getInt(1);                   
			}
		} 
    	catch( Exception e ){ 
            e.printStackTrace(); 
		} 
    	
    	if(idGroup == 0) return true;
		return false; 
    }
	
	
	public boolean checkGroupForEdit(int idGroup,String groupName)
    {
    	ResultSet rs = DbRequests.checkGroupForEdit(idGroup,groupName);
    	
    	int idSerchGroup = 0 ;
    	
    	try {
			while(rs.next())
			{
				idSerchGroup  = rs.getInt(1);                   
			}
		} 
    	catch (Exception e ) {

			e.printStackTrace();
		}
    	
    	if(idSerchGroup == 0) return true;
		return false;
    
    }
	
	public boolean deleteGroupFromGroupMembership(int idEGroup,int idMGroup)
    {
    	ResultSet rs = DbRequests.deleteGroupFromGroupMembership(idEGroup,idMGroup);
    	
    	if(rs != null) return true;
		return false;
    
    }
	
}

