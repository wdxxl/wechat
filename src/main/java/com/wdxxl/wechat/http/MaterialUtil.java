package com.wdxxl.wechat.http;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;


/**
 * 永久素材
 * http://mp.weixin.qq.com/wiki/14/7e6c03263063f4813141c3e17dd4350a.html
 *  开发者有时需要永久保存一些素材，届时就可以通过本接口新增永久素材。
 *  上传的临时多媒体文件有格式和大小限制，如下：
 *  	1、新增的永久素材也可以在公众平台官网素材管理模块中看到
 *  	2、永久素材的数量是有上限的，请谨慎新增。图文消息素材和图片素材的上限为5000，其他类型为1000
 *  	3、素材的格式大小等要求与公众平台官网一致。具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
 *  	4、调用该接口需https协议
 * 
 * @author wdxxl
 * @date 2015-4-23 09:26:00
 * 
 */
public class MaterialUtil {
	private static final String MATERIAL_ADD_MATERIAL_POST = "http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=";	//新增其他类型永久素材

	
}
