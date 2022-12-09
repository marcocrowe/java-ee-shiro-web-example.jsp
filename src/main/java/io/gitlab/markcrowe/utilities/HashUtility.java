/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package io.gitlab.markcrowe.utilities;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;

public final class HashUtility
{
	public static final int BIT_LENGTH = 250;
	public static final int HASH_ITERATIONS = 1_024;

	/**
	 * Generate a random text as Salt to use for Hashing
	 *
	 * @return salt The salt text
	 */
	public static String generateSalt()
	{
		return generateSalt(BIT_LENGTH, new SecureRandom());
	}

	/**
	 * Generate a random text as Salt to use for Hashing
	 *
	 * @param random source of randomness to be used in computing the new BigInteger.
	 * @param bitLength maximum bit length of the BigInteger.
	 * @return salt The salt text
	 */
	public static String generateSalt(int bitLength, Random random)
	{
		return new BigInteger(bitLength, random).toString(Character.MAX_RADIX);
	}

	/**
	 * Generate a hash in Hex format
	 *
	 * @param plainText The plain-text to hash
	 * @return A hash of the plainText in Hex format
	 */
	public static String hashToHex(String plainText)
	{
		return new Sha256Hash(plainText).toHex();
	}

	/**
	 * Generate a hash using a salt in Hex format
	 *
	 * @param plainText The plain-text to hash
	 * @param salt The salt to hash plain-text with.
	 * @return A salted hash of the plain-text in Hex format.
	 */
	public static String hashToHex(String plainText, String salt)
	{
		return hashToHex(plainText, salt, HASH_ITERATIONS);
	}

	/**
	 * Generate a hash using a salt in Hex format
	 *
	 * @param plainText The plain-text to hash
	 * @param salt The salt to hash plain-text with.
	 * @param hashIterations The number of hash iterations.
	 * @return A salted hash of the plain-text in Hex format.
	 */
	public static String hashToHex(String plainText, String salt, int hashIterations)
	{
		return new Sha256Hash(plainText, new SimpleByteSource(salt).getBytes(), hashIterations).toHex();
	}

	/**
	 * Private constructor to prevent instantiation
	 */
	private HashUtility()
	{
		throw new IllegalStateException("Utility class");
	}
}
