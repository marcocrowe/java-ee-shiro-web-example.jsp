/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package io.gitlab.markcrowe.utilities;

import io.gitlab.markcrowe.User;
import io.gitlab.markcrowe.repositories.UserRepository;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.*;

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
			String hash = hash(user.getPlainTextPassword()).toHex();

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
		DefaultPasswordService passwordService = buildPasswordService();
		for(User user : userRepository.getAllUsers())
		{
			String hash = hash(user.getPlainTextPassword()).toBase64();
			String salt = user.getSalt();
			String saltHash = saltHash(user.getPlainTextPassword(), salt);

			if(passwordService.passwordsMatch(user.getPlainTextPassword(), user.getHashedPassword())) //!user.getHashedPassword().equals(hash))
				System.err.println(user.getUsername() + " hash is not valid");
			else
				System.out.println(user.getUsername() + " hash is valid");

			if(!user.getSaltHashedPassword().equals(saltHash))
				System.err.println(user.getUsername() + " salted hash is not valid");
			else
				System.out.println(user.getUsername() + " salted hash is valid");
		}
	}

	public static Hash hash(String plainText)
	{
		DefaultPasswordService passwordService = buildPasswordService();
		return passwordService.hashPassword(plainText);

	}
	public static DefaultPasswordService buildPasswordService()
	{
		HashService hashService = buildHashService();
		DefaultPasswordService passwordService = new DefaultPasswordService();
		passwordService.setHashService(hashService);
		return passwordService;
	}
	public static HashService buildHashService()
	{
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
		return hashService;
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
