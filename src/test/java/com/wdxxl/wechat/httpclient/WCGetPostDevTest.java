package com.wdxxl.wechat.httpclient;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.wdxxl.wechat.utils.WeChatGetPostUtils;

public class WCGetPostDevTest {

	@Test
	@Ignore
	public void test() throws IOException {
		WeChatGetPostUtils.getInstance().retrieveAccessToken();
		System.out.println(WeChatGetPostUtils.getInstance().getAccessToken());
	}

}
