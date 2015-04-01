package com.wdxxl.wechat.util;

public class Profiles {

	public static final String ACTIVE_PROFILE = "spring.profiles.active";
	public static final String DEFAULT_PROFILE = "spring.profiles.default";

	//those profiles is used to make sure which database is active during unit test。
	//please check test utils class SpringTreansactionalTestCase
	public static final String DEVELOPMENT = "dev";
	
	public static void setProfileAsSystemProperty(String profile) {
		System.setProperty(ACTIVE_PROFILE, profile);
	}
	
}
