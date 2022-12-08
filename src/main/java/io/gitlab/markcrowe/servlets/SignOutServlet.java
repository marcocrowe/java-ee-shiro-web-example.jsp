package io.gitlab.markcrowe.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@WebServlet(WebPaths.SIGN_OUT)
public class SignOutServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated())
		{
			log("Attempt to log out by a not authenticated user");
		}
		log(subject.getPrincipal() + " has logged out");
		subject.logout();
		response.sendRedirect(request.getContextPath()+WebPaths.SIGN_IN);
	}
}
