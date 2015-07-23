package users;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DbRequests;


public class UserServices {
	
	
	public List<User> getAllUsers()
    {
    	List<User> list = new ArrayList<User>();
    	int i=0;
    	
    	ResultSet rs = DbRequests.getAllUsers();
    	
    	try{
    	
	    	while(rs.next())
	         {
		    
		        User u = new User();
	    		
				u.setIdUser(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setUserLoginName(rs.getString(3));
				u.setUserPassword(rs.getString(4));
				u.setCreationDate(rs.getDate(5));                  
	            list.add(i,u);
	            i++;
	         }
    	}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
      	
       return list;
    }
	
	public User getUser(int idUser)
	{
	    	
		ResultSet rs = DbRequests.getUser(idUser);
		User u = new User();
		try{
		
			while(rs.next())
	         {
				u.setIdUser(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setUserLoginName(rs.getString(3));
				u.setUserPassword(rs.getString(4));
				u.setCreationDate(rs.getDate(5)); 
	         }
			
		}
		catch( Exception e ){ 
	        e.printStackTrace(); 
		} 
		
		return u;
	}
	
	
	public List<User> getUsersFromGroup(String groupName)
	{
		List<User> list = new ArrayList<User>();
		int i=0;
		
		ResultSet rs = DbRequests.getUsersFromGroup(groupName);
		
		try{
		
	    	while(rs.next())
	         {
	    		User u = new User();
	    		
				u.setIdUser(rs.getInt(1)); 
				u.setUserLoginName(rs.getString(2));
				u.setCreationDate(rs.getDate(3));                  
	            list.add(i,u);
	            i++;
	         }
	    	
		}
		catch( Exception e ){ 
	        e.printStackTrace(); 
		} 
	      	
	       return list;
	}
	 
	
	public List<User> getUsersFromOutsideGroup(int idGroup)
    {
    	List<User> list = new ArrayList<User>();
    	int i=0;
    	
    	ResultSet rs = DbRequests.getUsersFromOutsideGroup(idGroup);
    	
    	try{
    	
	    	while(rs.next())
	         {
	    		User u = new User();
	    		
				u.setIdUser(rs.getInt(1)); 
				u.setUserLoginName(rs.getString(2));
	            list.add(i,u);
	            i++;
	         }
	    	
    	}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
      	
       return list;
    }
	
	public boolean addNewUser(String userName,String userLoginName,String userPassword)
    {
    	ResultSet rs = DbRequests.addNewUser(userName,userLoginName,userPassword);
    	
    	if(rs != null) return true;
		return false;
    
    }
	
	public boolean checkUser(String userName, String userLoginName)
    {
    	ResultSet rs = DbRequests.checkUser(userName,userLoginName);
    	
    	int idUser = 0 ;
    	
    	try {
			while(rs.next())
			{
			   idUser = rs.getInt(1);                   
			}
		} 
    	catch( Exception e ){ 
            e.printStackTrace(); 
		} 
    	
    	if(idUser == 0) return true;
		return false; 
    }
	
	public boolean checkUserForEdit(int idUser,String userName, String userLoginName)
    {
    	ResultSet rs = DbRequests.checkUserForEdit(idUser,userName,userLoginName);
    	
    	int idSerchUser = 0 ;
    	
    	try {
			while(rs.next())
			{
				idSerchUser  = rs.getInt(1);                   
			}
		} 
    	catch (Exception e ) {

			e.printStackTrace();
		}
    	
    	if(idSerchUser == 0) return true;
		return false;
    
    }
	
	public boolean deleteUserFromUserMembership(int idUser)
    {
    	ResultSet rs = DbRequests.deleteUserFromUserMembership(idUser);
    	
    	if(rs != null) return true;
		return false;
    
    }
	
	public boolean editUser(int idUser,String userName,String userLoginName,String userPassword)
    {
    	ResultSet rs = DbRequests.editUser(idUser,userName,userLoginName,userPassword);
    	
    	if(rs != null) return true;
		return false;
    }
	
	public boolean deleteUser(int idUser)
    {
    	ResultSet rs = DbRequests.deleteUser(idUser);
    	
    	if(rs != null) return true;
		return false;
    }
	
}
