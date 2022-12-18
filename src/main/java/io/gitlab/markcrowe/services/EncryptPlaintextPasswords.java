/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package io.gitlab.markcrowe.services;

import io.gitlab.markcrowe.User;
import io.gitlab.markcrowe.repositories.UserRepository;
import io.gitlab.markcrowe.utilities.HashUtility;

public final class EncryptPlaintextPasswords
{

	/**
	 * Only run once! Otherwise, you create a hash of a hash
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)
	{
		hashUsersPasswords(new UserRepository("default"));
	}

	public static void hashUsersPasswords(UserRepository userRepository)
	{
		for(User user : userRepository.getAllUsers())
		{
			String clearTextPassword = user.getPlainTextPassword();
			String hash = HashUtility.hashToBase64(clearTextPassword);

			user.setHashedPassword(hash);
			userRepository.updateUser(user);
		}
	}

	/**
	 * Private constructor to prevent instantiation
	 */
	private EncryptPlaintextPasswords()
	{
		throw new IllegalStateException("Utility class");
	}
}
