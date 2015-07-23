package groups;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddNewGroup")
public class AddNewGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddNewGroup() {
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

		if(request.getParameter("addNewGroupButton") != null)
		{
			String newGroupName = null;
			String newGroupDisplayName = null;
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/html");
			
			if(notIsNUllAndNotIsEmpty(request.getParameter("newGroupName")))
			{	newGroupName = request.getParameter("newGroupName");
	
			
				if(notIsNUllAndNotIsEmpty(request.getParameter("newGroupDisplayName")))	
				{	newGroupDisplayName = request.getParameter("newGroupDisplayName");
						
					GroupServices gs = new GroupServices();
					if(gs.checkGroup(newGroupName))
					{
						if(gs.addNewGroup(newGroupName, newGroupDisplayName))
							response.sendRedirect("index.jsp");
					}
					else {	out.println("<p>Grupul cu asa nume deja exista.<a href='addNewGroup.jsp'> Introduceti alt nume.</a></p>");}
				
				}
				else {	out.println("<p>Eroare! Nu ati introdus numele de afisare a grupului. <a href='addNewGroup.jsp'> Inapoi.</a></p>");}
			}
			else {	out.println("<p>Eroare! Nu ati introdus numele grupului. <a href='addNewGroup.jsp'> Inapoi.</a></p>");}
		}
			
	}

}
