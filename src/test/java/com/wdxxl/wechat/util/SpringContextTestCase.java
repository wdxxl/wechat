package com.wdxxl.wechat.util;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ActiveProfiles(value=Profiles.DEVELOPMENT)
public abstract class SpringContextTestCase extends AbstractJUnit4SpringContextTests {

}
