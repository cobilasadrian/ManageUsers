package groups;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groups.GroupServices;
import users.UserServices;

@WebServlet("/EditGroup")
public class EditGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public EditGroup() {
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

		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html");

		
		if(request.getParameter("editGroupButton")!= null)
		{
			int idGroup = 0;
			if(notIsNUllAndNotIsEmpty(request.getParameter("idGroup")))
			{
				idGroup = Integer.parseInt((String) request.getParameter("idGroup"));
				response.sendRedirect("editGroup.jsp?idGroup="+idGroup);
			}
			else 
			{
				response.sendRedirect("index.jsp");
			}
		}
		
		if(request.getParameter("deleteGroupButton") != null)
		{
			if(notIsNUllAndNotIsEmpty(request.getParameter("idGroup")))
			{
				int idGroup = Integer.parseInt((String) request.getParameter("idGroup"));
				
				GroupServices gs = new GroupServices();
				if(gs.deleteGroup(idGroup) == true) 
					response.sendRedirect("index.jsp");
			}
			
			else 
			{
				response.sendRedirect("index.jsp");
			}
		}
		
		if(request.getParameter("saveEditedGroupButton") != null) 
		{
			if(notIsNUllAndNotIsEmpty(request.getParameter("editedIdGroup"))){
				int editedIdGroup = Integer.parseInt((String) request.getParameter("editedIdGroup"));
				
				if(notIsNUllAndNotIsEmpty(request.getParameter("editedGroupName"))){
					String editedGroupName=request.getParameter("editedGroupName");
					
					if(notIsNUllAndNotIsEmpty(request.getParameter("editedGroupDisplayName"))){
						String editedGroupDisplayName=request.getParameter("editedGroupDisplayName");
						
						GroupServices gs = new GroupServices();
						if(gs.checkGroupForEdit(editedIdGroup,editedGroupName))
						{
							if(gs.editGroup(editedIdGroup,editedGroupName,editedGroupDisplayName) == true) 
								response.sendRedirect("index.jsp");	
						}
						else {	out.println("<p>Grupul cu asa nume deja exista.<a href='editGroup.jsp?idGroup="+editedIdGroup+"'> Introduceti alt nume.</a></p>");}
					}
					else {	out.println("<p>Eroare! Nu ati introdus numele de afisare a grupului. <a href='editGroup.jsp?idGroup="+editedIdGroup+"'> Inapoi.</a></p>");}
				}
				else {	out.println("<p>Eroare! Nu ati introdus numele grupului. <a href='editGroup.jsp?idGroup="+editedIdGroup+"'> Inapoi.</a></p>");}
			}
			else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}
		}
		
		if(request.getParameter("deleteMemberButton") != null) 
		{		
			if(notIsNUllAndNotIsEmpty(request.getParameter("editedIdGroup")))
			{
				int editedIdGroup = Integer.parseInt((String) request.getParameter("editedIdGroup"));
			
				if(notIsNUllAndNotIsEmpty(request.getParameter("idGroupMember"))){
					
					int idGroupMember = Integer.parseInt(request.getParameter("idGroupMember"));	
						
					GroupServices gs = new GroupServices();
					if(gs.deleteGroupFromGroupMembership(idGroupMember,editedIdGroup))
						response.sendRedirect("editGroup.jsp?idGroup="+editedIdGroup);
					
				}
				
				else if(notIsNUllAndNotIsEmpty(request.getParameter("idUserMember")))
				{			
					int idUserMember = Integer.parseInt(request.getParameter("idUserMember"));
					
					UserServices us = new UserServices();
					if(us.deleteUserFromUserMembership(idUserMember))
						response.sendRedirect("editGroup.jsp?idGroup="+editedIdGroup);	
				}
				else 
				{
					response.sendRedirect("editGroup.jsp?idGroup="+editedIdGroup);
				}
			}
			else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}
		}
		
		if(request.getParameter("addGroupMemberButton") != null) 
		{
			if(notIsNUllAndNotIsEmpty(request.getParameter("editedIdGroup"))){
				int editedIdGroup = Integer.parseInt((String) request.getParameter("editedIdGroup"));
				response.sendRedirect("addGroupMember.jsp?idGroup="+editedIdGroup);
			}
			else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}
		}
		
		if(request.getParameter("addUserMemberButton") != null) 
		{
			if(notIsNUllAndNotIsEmpty(request.getParameter("editedIdGroup"))){
				int editedIdGroup = Integer.parseInt((String) request.getParameter("editedIdGroup"));
				response.sendRedirect("addUserMember.jsp?idGroup="+editedIdGroup);
			}
			else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}
		}
		
		if(request.getParameter("saveMemberGroupButton") != null) 
		{
			if(notIsNUllAndNotIsEmpty(request.getParameter("editedIdGroup"))){
				int editedIdGroup = Integer.parseInt((String) request.getParameter("editedIdGroup"));
				
				if(notIsNUllAndNotIsEmpty(request.getParameter("NewIdMemberGroup"))){
					int newIdMemberGroup = Integer.parseInt(request.getParameter("NewIdMemberGroup"));
					
					GroupServices gs = new GroupServices();
					if(gs.addGroupInGroupMembership(editedIdGroup,  newIdMemberGroup))
						response.sendRedirect("editGroup.jsp?idGroup="+editedIdGroup);
					
				}
				else {response.sendRedirect("editGroup.jsp?idGroup="+editedIdGroup);}
			}
			else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}
		}
		
		if(request.getParameter("saveMemberUserButton") != null) 
		{
			if(notIsNUllAndNotIsEmpty(request.getParameter("editedIdGroup"))){
				int editedIdGroup = Integer.parseInt((String) request.getParameter("editedIdGroup"));
				
				if(notIsNUllAndNotIsEmpty(request.getParameter("NewIdMemberUser"))){
					int newIdMemberUser = Integer.parseInt(request.getParameter("NewIdMemberUser"));
					
					GroupServices gs = new GroupServices();
					if(gs.addUserInUserMembership(editedIdGroup, newIdMemberUser))
						response.sendRedirect("editGroup.jsp?idGroup="+editedIdGroup);
					
				}
				else {response.sendRedirect("editGroup.jsp?idGroup="+editedIdGroup);}
			}
			else{ out.print("Eroare! Ati accesat pagina fara parametrii necesari. <a href='index.jsp'> Inapoi pe pagina principala</a>");}
		}

	}
}
