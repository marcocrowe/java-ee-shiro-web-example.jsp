/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package io.gitlab.markcrowe.utilities;

import io.gitlab.markcrowe.User;
import io.gitlab.markcrowe.repositories.UserRepository;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.Hash;

public final class SecureUserPasswordUtility
{
	public static final String PERSISTENCE_UNIT_NAME = "default";

	public static void main(String[] args)
	{
		UserRepository userRepository = new UserRepository(PERSISTENCE_UNIT_NAME);
		hashUserPasswords(userRepository);
		hashAndSaltUserPasswords(userRepository);
	}

	public static void hashUserPasswords(UserRepository userRepository)
	{
		DefaultPasswordService passwordService = ShiroPasswordServices.buildHashPasswordService();

		for(User user : userRepository.getAllUsers())
		{
			Hash passwordHash = passwordService.hashPassword(user.getPlainTextPassword());
			user.setHashedPassword(passwordHash.toHex());
			userRepository.updateUser(user);
		}
	}

	public static void hashAndSaltUserPasswords(UserRepository userRepository)
	{
		DefaultPasswordService passwordService = ShiroPasswordServices.buildSaltHashPasswordService();
		for(User user : userRepository.getAllUsers())
		{
			Hash saltedPasswordHash = passwordService.hashPassword(user.getPlainTextPassword());

			user.setSaltHashedPassword(saltedPasswordHash.toHex());
			user.setSalt(saltedPasswordHash.getSalt().toBase64());

			userRepository.updateUser(user);
		}
	}

	/**
	 * Private constructor to prevent instantiation
	 */
	private SecureUserPasswordUtility()
	{
		throw new IllegalStateException("Utility class");
	}
}
