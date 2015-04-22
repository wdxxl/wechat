package com.wdxxl.wechat.http;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;


/**
 * 临时素材(原多媒体文件)
 * http://mp.weixin.qq.com/wiki/5/963fc70b80dc75483a271298a76a8d59.html
 * 	媒体文件在后台保存时间为3天，即3天后media_id失效。
 *  上传的临时多媒体文件有格式和大小限制，如下：
 *  	图片（image）: 1M，支持JPG格式
 *  	语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
 *  	视频（video）：10MB，支持MP4格式
 *  	缩略图（thumb）：64KB，支持JPG格式
 * 
 * @author wdxxl
 * @date 2015-4-22 10:29:21
 * 
 */
public class MediaUtil {
	private static final String MEDIA_UPLOAD_POST = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";	//新增临时素材
	private static final String MEDIA_GET_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";	//获取临时素材

	/**
	 * 新增临时素材
	 * 公众号经常有需要用到一些临时性的多媒体素材的场景，例如在使用接口特别是发送消息时，对多媒体文件、多媒体消息的获取和调用等操作，
	 * 是通过media_id来进行的。素材管理接口对所有认证的订阅号和服务号开放。通过本接口，公众号可以新增临时素材（即上传临时多媒体文件）。
	 * @param accessToken
	 * @param reqJson (菜单的json结果)
	 * @return
	 */
	public static String uploadMedia(String accessToken,String type, File file){
		String result = null;
		try {
			result = HttpClientUtils.PostFile(MEDIA_UPLOAD_POST+accessToken+"&type="+type, file);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
