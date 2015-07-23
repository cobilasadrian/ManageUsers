package users;

import java.util.Date;

public class User {
     
    private String userName,userLoginName,userPassword;
    private Date creationDate;
    private int idUser;
    
    
    public int getIdUser() 
    {
    	return idUser;
    }
    
    public String getUserName()
    {
    	return userName;
    }
    
    public String getUserLoginName()
    {
    	return userLoginName;
    }
    
    public String getUserPassword() 
    {

    	return userPassword;
    } 
    
    public Date getCreationDate() 
    {
    	return creationDate;
    }
    
    public void setIdUser(int idUser)
    {
    	this.idUser=idUser;
    }
    
    public void setUserName(String userName)
    {
    	this.userName=userName;
    }
    
    public void setUserLoginName(String userLoginName)
    {
    	this.userLoginName=userLoginName;
    }
    
    public void setUserPassword(String userPassword)
    {
    	this.userPassword=userPassword;
    }
    
    
    public void setCreationDate(Date creationDate)
    {
    	this.creationDate=creationDate;
    }
    
}

