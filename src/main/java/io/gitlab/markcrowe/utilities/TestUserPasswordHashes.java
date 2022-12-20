/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package io.gitlab.markcrowe.utilities;

import io.gitlab.markcrowe.User;
import io.gitlab.markcrowe.repositories.UserRepository;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.*;

public final class TestUserPasswordHashes
{
	public static final String PERSISTENCE_UNIT_NAME = "default";

	public static void main(String[] args)
	{
		UserRepository userRepository = new UserRepository(PERSISTENCE_UNIT_NAME);
		if(isUsersPasswordsHashedValid(userRepository))
			System.out.println("Hashed Passwords Valididated");

		if(isUsersPasswordsSaltHashValid256(userRepository))
			System.out.println("Salt-Hashed 256 Passwords Valididated");
	}

	public static boolean isUsersPasswordsHashedValid(UserRepository userRepository)
	{
		for(User user : userRepository.getAllUsers())
		{
			DefaultPasswordService passwordService = ShiroPasswordServices.buildHashPasswordService();
			Hash hash = passwordService.hashPassword(user.getPlainTextPassword());
			if(!user.getHashedPassword().equals(hash.toHex()))
			{
				System.err.println(user.getUsername() + " hash is not valid");
				return false;
			}
		}
		return true;
	}
	public static boolean isUsersPasswordsSaltHashValidNotWorking(UserRepository userRepository)
	{
		for(User user : userRepository.getAllUsers())
		{
			DefaultPasswordService passwordService = ShiroPasswordServices.buildSaltHashPasswordService(user.getSalt());
			Hash saltHash = passwordService.hashPassword(user.getPlainTextPassword());

			if(!user.getSaltHashedPassword().equals(saltHash.toHex()))
			{
				System.err.println("Salt Hash is NOT valid");
				System.err.println(user.getSalt());
				System.err.println(saltHash.getSalt().toBase64());
				System.err.println(user.getSaltHashedPassword());
				System.err.println(saltHash.toHex());

				return false;
			}
		}
		return true;
	}
	public static boolean isUsersPasswordsSaltHashValid256(UserRepository userRepository)
	{
		for(User user : userRepository.getAllUsers())
		{
			Sha256Hash saltSha256Hash = Sha256Hash.fromHexString(user.getSaltHashedPassword());

			if(!user.getSaltHashedPassword().equals(saltSha256Hash.toHex()))
			{
				System.err.println("Salt Hash is NOT valid");
				System.err.println(user.getSalt());
				System.err.println(saltSha256Hash.getSalt().toHex());
				System.err.println(user.getSaltHashedPassword());
				System.err.println(saltSha256Hash.toHex());

				return false;
			}
		}
		return true;
	}

	/**
	 * Private constructor to prevent instantiation
	 */
	private TestUserPasswordHashes()
	{
		throw new IllegalStateException("Utility class");
	}
}
