/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package io.gitlab.markcrowe.utilities;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;

public final class ShiroPasswordServices
{
	static final int HASH_ITERATIONS = 1_024;

	public static DefaultPasswordService buildHashPasswordService()
	{
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);

		DefaultPasswordService passwordService = new DefaultPasswordService();
		passwordService.setHashService(hashService);
		return passwordService;
	}
	public static DefaultPasswordService buildSaltHashPasswordService()
	{
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
		hashService.setHashIterations(HASH_ITERATIONS);
		hashService.setGeneratePublicSalt(true);

		DefaultPasswordService passwordService = new DefaultPasswordService();
		passwordService.setHashService(hashService);
		return passwordService;
	}
	public static DefaultPasswordService buildSaltHashPasswordService(ByteSource privateSalt)
	{
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
		hashService.setHashIterations(HASH_ITERATIONS);
		hashService.setPrivateSalt(privateSalt);

		DefaultPasswordService passwordService = new DefaultPasswordService();
		passwordService.setHashService(hashService);
		return passwordService;
	}
	public static DefaultPasswordService buildSaltHashPasswordService(String base64Salt)
	{
		return buildSaltHashPasswordService(ByteSource.Util.bytes(Base64.decode(base64Salt)));
	}

	public static Hash hashWithSalt(String plainText, String salt)
	{
		return new SimpleHash(Sha256Hash.ALGORITHM_NAME, plainText, salt, HASH_ITERATIONS);
	}

	/**
	 * Private constructor to prevent instantiation
	 */
	private ShiroPasswordServices()
	{
		throw new IllegalStateException("Utility class");
	}
}
