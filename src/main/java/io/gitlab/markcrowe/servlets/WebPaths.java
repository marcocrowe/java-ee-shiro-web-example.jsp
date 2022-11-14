package io.gitlab.markcrowe.servlets;

public class WebPaths
{
	static final String DEBUG_SIGN_IN = "/debug-sign-in";
	static final String HOME = "/";
	static final String SIGN_IN = "/sign-in";
	static final String USER_ACCOUNT_DETAILS = "/user/account-details";

	/**
	 * Private constructor to prevent instantiation
	 */
	private WebPaths()
	{
		throw new IllegalStateException("Utility class");
	}
}
