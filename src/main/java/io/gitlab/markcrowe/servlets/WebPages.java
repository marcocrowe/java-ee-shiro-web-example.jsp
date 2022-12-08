package io.gitlab.markcrowe.servlets;

public final class WebPages
{
	static final String ACCOUNT_DETAILS_JSP = "/WEB-INF/account-details.jsp";
	static final String HOME_JSP = "/WEB-INF/home.jsp";
	static final String SIGN_IN_JSP = "/WEB-INF/sign-in.jsp";
	static final String TEST_SECURITY_JSP = "/WEB-INF/test-security.jsp";

	/**
	 * Private constructor to prevent instantiation
	 */
	private WebPages()
	{
		throw new IllegalStateException("Utility class");
	}
}
