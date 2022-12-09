/*
 * Copyright (c) 2022 Mark Crowe <https://github.com/markcrowe-com>. All rights reserved.
 */
package io.gitlab.markcrowe.services;

import java.util.Map;
import org.apache.shiro.config.Ini;

public class ShiroConfigReader
{
	public static void main(String[] args)
	{
		Ini ini = new Ini();
		ini.load(ShiroConfigReader.class.getClassLoader().getResourceAsStream("shiro.ini"));

		for(Ini.Section section : ini.getSections())
		{
			System.out.printf("[%s]\n", section.getName());
			for(Map.Entry<String, String> entry : section.entrySet())
				System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
