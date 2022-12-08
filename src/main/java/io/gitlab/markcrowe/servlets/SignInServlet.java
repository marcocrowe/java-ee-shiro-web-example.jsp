package io.gitlab.markcrowe.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

@WebServlet(WebPaths.SIGN_IN)
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
		UsernamePasswordToken usernamePasswordToken = getUsernamePasswordToken(request);
		signInUser(request, response, this, usernamePasswordToken);
	}

	static void addErrorMessageToRequest(HttpServletRequest request, String errorMessage)
	{
		request.setAttribute("errorMessage", errorMessage);
	}
	static UsernamePasswordToken getUsernamePasswordToken(HttpServletRequest request)
	{
		String password = request.getParameter("password");
		boolean rememberMe = request.getParameter("remember-me") != null;
		String username = request.getParameter("username");
		return new UsernamePasswordToken(username, password, rememberMe);
	}
	static void signInUser(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet, UsernamePasswordToken usernamePasswordToken) throws IOException, ServletException
	{
		String page = WebPages.SIGN_IN_JSP;
		try
		{
			Subject subject = SecurityUtils.getSubject();
			subject.login(usernamePasswordToken);
			servlet.log("principal " + subject.getPrincipal() + "logged in");
			page = WebPages.ACCOUNT_DETAILS_JSP;
		}
		catch(UnknownAccountException unknownAccountException)
		{
			servlet.log("Unknown Account", unknownAccountException);
			addErrorMessageToRequest(request, "Unknown username or password");
		}
		catch(IncorrectCredentialsException incorrectCredentialsException)
		{
			servlet.log("Incorrect Credentials", incorrectCredentialsException);
			addErrorMessageToRequest(request, "Unknown username or password");
		}
		catch(LockedAccountException lockedAccountException)
		{
			servlet.log("Locked Account", lockedAccountException);
			addErrorMessageToRequest(request, "Your Account is Locked.");
		}
		catch(ExcessiveAttemptsException excessiveAttemptsException)
		{
			servlet.log("Excessive Attempts", excessiveAttemptsException);
			addErrorMessageToRequest(request, "Excessive Attempts to login.");
		}
		catch(AuthenticationException authenticationException)
		{
			servlet.log("Authentication Error", authenticationException);
			addErrorMessageToRequest(request, "Authentication Error");
		}
		catch(UnavailableSecurityManagerException unavailableSecurityManagerException)
		{
			servlet.log("Unavailable Security Manager", unavailableSecurityManagerException);
			addErrorMessageToRequest(request, "Application Error");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}
}
