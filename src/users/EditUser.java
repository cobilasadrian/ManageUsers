package users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditUser() {
        super();
    }

    public boolean notIsNUllAndNotIsEmpty(String element){
    	
    	if(element != null && !element.isEmpty())
    		return true; 
    	
    	return false;
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		if(request.getParameter("deleteUserButton") != null)
		{
			if(notIsNUllAndNotIsEmpty(request.getParameter("idUser")))
			{ 
				int idUser = Integer.parseInt(request.getParameter("idUser"));
			
				UserServices us = new UserServices();
				if(us.deleteUser(idUser) == true) 
					response.sendRedirect("index.jsp");		
			}
			else {	response.sendRedirect("index.jsp"); }
			
		}
		
		if(request.getParameter("editUserButton") != null)
		{
			int idUser = 0;
			if(notIsNUllAndNotIsEmpty(request.getParameter("idUser")))
			{
				idUser = Integer.parseInt((String) request.getParameter("idUser"));
				response.sendRedirect("editUser.jsp?idUser="+idUser);
			}
			else {	response.sendRedirect("index.jsp");}
		}
		
		if(request.getParameter("saveEditedUserButton") != null)
		{
			
			int editedIdUser = 0;
			String editedUserName = null;
			String editedUserLoginName = null;
			String editedUserPassword = null;
			
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/html");
			
			if(notIsNUllAndNotIsEmpty(request.getParameter("editedIdUser")))
			{
				editedIdUser = Integer.parseInt((String) request.getParameter("editedIdUser"));
				
				if(notIsNUllAndNotIsEmpty(request.getParameter("editedUserName")))
				{
					editedUserName = request.getParameter("editedUserName");
				
					if(notIsNUllAndNotIsEmpty(request.getParameter("editedUserLoginName")))
					{
						editedUserLoginName = request.getParameter("editedUserLoginName");
					
						if(notIsNUllAndNotIsEmpty(request.getParameter("editedUserPassword")))
						{
							editedUserPassword = request.getParameter("editedUserPassword");
						
							UserServices us = new UserServices();
							if(us.checkUserForEdit(editedIdUser,editedUserName, editedUserLoginName))
							{
							
								if(us.editUser(editedIdUser,editedUserName,editedUserLoginName,editedUserPassword))
									response.sendRedirect("index.jsp");
							}
							else {	out.println("<p>Utilizator cu asa date deja exista.<a href='editUser.jsp?idUser="+editedIdUser+"'> Introduceti alte date.</a></p>");}
						}
						else {	out.println("<p>Eroare! Nu ati introdus parola. <a href='editUser.jsp?idUser="+editedIdUser+"'> Inapoi.</a></p>");}
					}
					else {	out.println("<p>Eroare! Nu ati introdus numele de logare. <a href='editUser.jsp?idUser="+editedIdUser+"'> Inapoi.</a></p>");}
				}
				else {	out.println("<p>Eroare! Nu ati introdus numele utilizatorului. <a href='editUser.jsp?idUser="+editedIdUser+"'> Inapoi.</a></p>");}
				
			}		
		}
		
		
	}

}
