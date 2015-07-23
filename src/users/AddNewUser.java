package users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddNewUser")
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AddNewUser() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		if(request.getParameter("addNewUserButton") != null)
		{
			String NewUserName = null;
			String NewUserLoginName = null;
			String NewUserPassword = null;
			
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/html");
			
			if(notIsNUllAndNotIsEmpty(request.getParameter("NewUserName")))
			{	NewUserName = request.getParameter("NewUserName");
			
				if(notIsNUllAndNotIsEmpty(request.getParameter("NewUserLoginName")))
				{	NewUserLoginName = request.getParameter("NewUserLoginName");
				
					if(notIsNUllAndNotIsEmpty(request.getParameter("NewUserPassword")))
					{	NewUserPassword = request.getParameter("NewUserPassword");
					
						UserServices us = new UserServices();
						if(us.checkUser(NewUserName, NewUserLoginName))
						{
						
							if(us.addNewUser(NewUserName, NewUserLoginName, NewUserPassword))
								response.sendRedirect("index.jsp");
						}
						else 
						{
							out.println("<p>Utilizator cu asa date deja exista.<a href='addNewUser.jsp'> Introduceti alte date.</a></p>");
						}
					}
					else {	out.println("<p>Eroare! Nu ati introdus parola. <a href='addNewUser.jsp'> Inapoi.</a></p>");}
				}
				else {	out.println("<p>Eroare! Nu ati introdus numele de logare. <a href='addNewUser.jsp'> Inapoi.</a></p>");}
			}
			else {	out.println("<p>Eroare! Nu ati introdus numele utilizatorului. <a href='addNewUser.jsp'> Inapoi.</a></p>");}
		}
		
	}

}
