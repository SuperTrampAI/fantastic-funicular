/*
 * Copyright (c) 2017- 2019 SuperTrampAI.github All Rights Reserved.
 */

package com.github.supertrampai.fantasticfunicular.utils;

import java.util.UUID;

public class Guid {

	public static String NewGuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
