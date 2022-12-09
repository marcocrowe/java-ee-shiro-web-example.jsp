/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package io.gitlab.markcrowe.utilities;

import io.gitlab.markcrowe.User;
import io.gitlab.markcrowe.repositories.UserRepository;

public final class SecureUserPasswordUtility
{
	public static final String PERSISTENCE_UNIT_NAME = "default";

	public static void main(String[] args)
	{
		hashUserPasswords(new UserRepository(PERSISTENCE_UNIT_NAME));
		hashAndSaltUserPasswords(new UserRepository(PERSISTENCE_UNIT_NAME));
		confirmUserHashAndSaltPasswords(new UserRepository(PERSISTENCE_UNIT_NAME));
	}

	public static void hashUserPasswords(UserRepository userRepository)
	{
		for(User user : userRepository.getAllUsers())
		{
			String hash = hash(user.getPlainTextPassword());

			user.setHashedPassword(hash);

			userRepository.updateUser(user);
		}
	}

	public static void hashAndSaltUserPasswords(UserRepository userRepository)
	{
		for(User user : userRepository.getAllUsers())
		{
			String salt = HashUtility.generateSalt();
			String saltHash = saltHash(user.getPlainTextPassword(), salt);

			user.setSaltHashedPassword(saltHash);
			user.setSalt(salt);

			userRepository.updateUser(user);
		}
	}

	public static void confirmUserHashAndSaltPasswords(UserRepository userRepository)
	{
		for(User user : userRepository.getAllUsers())
		{
			String hash = hash(user.getPlainTextPassword());
			String salt = user.getSalt();
			String saltHash = saltHash(user.getPlainTextPassword(), salt);

			if(!user.getHashedPassword().equals(hash))
				System.err.println(user.getUsername() + " hash is not valid");
			else
				System.out.println(user.getUsername() + " hash is valid");

			if(!user.getSaltHashedPassword().equals(saltHash))
				System.err.println(user.getUsername() + " salted hash is not valid");
			else
				System.out.println(user.getUsername() + " salted hash is valid");
		}
	}

	public static String hash(String plainText)
	{
		return HashUtility.hashToHex(plainText);
	}
	public static String saltHash(String plainText, String salt)
	{
		return HashUtility.hashToHex(plainText, salt);
	}

	/**
	 * Private constructor to prevent instantiation
	 */
	private SecureUserPasswordUtility()
	{
		throw new IllegalStateException("Utility class");
	}
}
