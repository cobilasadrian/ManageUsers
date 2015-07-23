package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

public class DbRequests {
	
	private static Connection newConnection(String usr,String pwd)
	{
		
		Connection conn = null;
		
		try{ 
			Locale enLocale = new Locale("en", "EN");
			Locale.setDefault(enLocale);
			
	        String url = "jdbc:oracle:thin:@localhost:1521:xe";
	        
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	      
	        conn = DriverManager.getConnection(url, usr, pwd);
        
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		}
		
		return conn; 				
	}
	
	public static ResultSet getAllGroups()
    {
                
        Statement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery("Select * from t_group");
		}
		catch( Exception e ){ 
            e.printStackTrace();         
		} 
        
       return rs;
    }
	 
	public static ResultSet deleteGroup(int idGroup)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("DELETE FROM t_group WHERE idGroup = ?");
        	stmt.setInt(1,idGroup);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet getGroup(int idGroup)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("SELECT * FROM t_group WHERE idGroup = ?");
        	stmt.setInt(1,idGroup);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet getUser(int idUser)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("SELECT * FROM t_user WHERE idUser = ?");
        	stmt.setInt(1,idUser);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet getUsersFromGroup(String groupName)
    {
                
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
	        stmt = conn.prepareStatement("SELECT idUser,userLoginName,creationDate FROM t_user INNER JOIN t_userMembership ON t_user.USERNAME=t_userMembership.USERNAME WHERE  t_userMembership.GROUPNAME = ?");
	        stmt.setString(1,groupName);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet getUsersFromOutsideGroup(int idGroup)
    {
                
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
	        stmt = conn.prepareStatement("SELECT idUser,userLoginName FROM t_user WHERE  userName NOT IN (SELECT userName FROM t_userMembership WHERE groupName = (SELECT groupName FROM t_group WHERE idGroup = ?))");
	        stmt.setInt(1,idGroup);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet getGroupsFromOutsideGroup(int idGroup)
    {
                
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
	        stmt = conn.prepareStatement("SELECT idGroup, groupDisplayName FROM t_group WHERE  groupName NOT IN (SELECT ChildGroupName FROM t_groupMembership WHERE parentGroupName = (SELECT groupName FROM t_group WHERE idGroup = ?)) AND groupName NOT IN (SELECT groupName FROM t_group WHERE idGroup = ?)");
	        stmt.setInt(1,idGroup);
	        stmt.setInt(2,idGroup);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	 
	public static ResultSet getChildrenGroupsFromGroup(String groupName)
    {
                
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
	        stmt = conn.prepareStatement("SELECT idGroup,groupDisplayName,creationDate FROM t_group INNER JOIN t_groupMembership ON t_group.GROUPNAME=t_groupMembership.CHILDGROUPNAME WHERE  t_groupMembership.PARENTGROUPNAME = ?");
	        stmt.setString(1,groupName);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet editGroup(int idGroup,String groupName,String groupDisplayName)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("UPDATE t_group SET groupName = ?, groupDisplayName = ?, creationDate = (Select TRUNC(SYSDATE) from DUAL) WHERE idGroup= ?");
        	stmt.setString(1,groupName);
        	stmt.setString(2,groupDisplayName);
        	stmt.setInt(3,idGroup);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet editUser(int idUser,String userName,String userLoginName,String userPassword)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("UPDATE t_user SET userName = ?, userLoginName = ?, userPassword = ?,creationDate = (Select TRUNC(SYSDATE) from DUAL) WHERE idUser= ?");
        	stmt.setString(1,userName);
        	stmt.setString(2,userLoginName);
        	stmt.setString(3,userPassword);
        	stmt.setInt(4,idUser);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet deleteUser(int idUser)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("DELETE FROM t_user WHERE idUser = ?");
        	stmt.setInt(1,idUser);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet deleteUserFromUserMembership(int idUser)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("DELETE FROM t_userMembership WHERE userName = (SELECT userName FROM t_user WHERE idUser = ?)");
        	stmt.setInt(1,idUser);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet deleteGroupFromGroupMembership(int idMGroup,int idEGroup)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("DELETE FROM t_groupMembership WHERE ChildGroupName = (SELECT groupName FROM t_group WHERE idGroup = ?) AND parentGroupName = (SELECT groupName FROM t_group WHERE idGroup = ?)");
        	stmt.setInt(1,idMGroup);
        	stmt.setInt(2,idEGroup);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	    
	 
	public static ResultSet addUserInUserMembership(int idGroup,int idUser)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("INSERT INTO t_userMembership VALUES ((SELECT groupName FROM t_group WHERE idGroup = ?),(Select userName FROM t_user WHERE idUser= ?))");
			stmt.setInt(1,idGroup);
			stmt.setInt(2,idUser);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet addNewGroup(String groupName,String groupDisplayName)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("INSERT INTO t_group (groupName,groupDisplayName,creationDate) VALUES (?,?,(Select TRUNC(SYSDATE) from DUAL))");
			stmt.setString(1,groupName);
			stmt.setString(2,groupDisplayName);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet addNewUser(String userName,String userLoginName,String userPassword)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("INSERT INTO t_user (userName,userLoginName,userPassword,creationDate) VALUES (?,?,?,(Select TRUNC(SYSDATE) from DUAL))");
			stmt.setString(1,userName);
			stmt.setString(2,userLoginName);
			stmt.setString(3,userPassword);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet addGroupInGroupMembership(int idParentGroup,int idChildGroup)
    {
	 	
	 	PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
			stmt = conn.prepareStatement("INSERT INTO t_groupMembership VALUES ((SELECT groupName FROM t_group WHERE idGroup = ?),(Select groupName FROM t_group WHERE idGroup= ?))");
			stmt.setInt(1,idParentGroup);
			stmt.setInt(2,idChildGroup);
	        rs = stmt.executeQuery();
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	public static ResultSet getAllUsers()
    {
                
        Statement stmt = null;
		ResultSet rs = null;
		Connection conn = newConnection("system","system");
		try{
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery("Select * from t_user");
		}
		catch( Exception e ){ 
            e.printStackTrace(); 
		} 
        
       return rs;
    }
	 
	 public static ResultSet checkUser(String userName, String userLoginName)
	 {

		 	PreparedStatement stmt = null;
			ResultSet rs = null;
			Connection conn = newConnection("system","system");
			try{
				stmt = conn.prepareStatement("SELECT idUser FROM t_user  WHERE userName = ? OR userLoginName = ?");
				stmt.setString(1,userName);
				stmt.setString(2,userLoginName);
		        rs = stmt.executeQuery();
			}
			catch( Exception e ){ 
	            e.printStackTrace(); 
			} 
	        
	       return rs; 
	 }
	 
	 public static ResultSet checkGroup(String groupName)
	 {

		 	PreparedStatement stmt = null;
			ResultSet rs = null;
			Connection conn = newConnection("system","system");
			try{
				stmt = conn.prepareStatement("SELECT idGroup FROM t_group  WHERE groupName = ?");
				stmt.setString(1,groupName);
		        rs = stmt.executeQuery();
			}
			catch( Exception e ){ 
	            e.printStackTrace(); 
			} 
	        
	       return rs; 
	 }
	 
	 public static ResultSet checkGroupForEdit(int idGroup,String groupName)
	 {

		 	PreparedStatement stmt = null;
			ResultSet rs = null;
			Connection conn = newConnection("system","system");
			try{
				stmt = conn.prepareStatement("SELECT idGroup FROM t_group  WHERE groupName = ? AND idGroup != ?");
				stmt.setString(1,groupName);
				stmt.setInt(2,idGroup);
		        rs = stmt.executeQuery();
			}
			catch( Exception e ){ 
	            e.printStackTrace(); 
			} 
	        
	       return rs; 
	 }
	 
	 public static ResultSet checkUserForEdit(int idUser,String userName, String userLoginName)
	 {

		 	PreparedStatement stmt = null;
			ResultSet rs = null;
			Connection conn = newConnection("system","system");
			try{
				stmt = conn.prepareStatement("SELECT idUser FROM t_user  WHERE (userName = ? OR userLoginName = ?) AND idUser != ?");
				stmt.setString(1,userName);
				stmt.setString(2,userLoginName);
				stmt.setInt(3,idUser);
		        rs = stmt.executeQuery();
			}
			catch( Exception e ){ 
	            e.printStackTrace(); 
			} 
	        
	       return rs; 
	 }

}
