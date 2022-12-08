package io.gitlab.markcrowe.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.shiro.authc.*;

@WebServlet(WebPaths.DEBUG_SIGN_IN)
public class DebugSignInServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("bart.simpson", "password", true);
		SignInServlet.signInUser(request, response, this, usernamePasswordToken);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
