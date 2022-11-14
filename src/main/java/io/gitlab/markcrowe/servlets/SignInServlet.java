package io.gitlab.markcrowe.servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignInServlet", value = WebPaths.SIGN_IN)
public class SignInServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher(WebPages.SIGN_IN_JSP).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Subject currentUser = SecurityUtils.getSubject();

		doGet(request, response);
	}
}