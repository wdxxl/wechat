package com.wdxxl.wechat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.model.ChatUser;
import com.wdxxl.wechat.model.TextMsgRecord;
import com.wdxxl.wechat.service.IChatUserService;
import com.wdxxl.wechat.service.ITextMsgRecordService;
import com.wdxxl.wechat.utils.WeChatNewsItem;
import com.wdxxl.wechat.utils.WeChatNewsResponse;
import com.wdxxl.wechat.utils.WeChatTextResponse;
import com.wdxxl.wechat.utils.WeChatUtils;

@Controller
public class WeChatController {

	@Autowired
	private IChatUserService chatUserService;
	@Autowired
	private ITextMsgRecordService textMsgRecordService;
	
	@RequestMapping(value="/api", method=RequestMethod.GET)
	@ResponseBody
	public String validateToken(
			@RequestParam(value="signature", required = true) String signature,//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
			@RequestParam(value="timestamp", required = true) String timestamp,//时间戳  
			@RequestParam(value="nonce", required = true) String nonce,//随机数  
			@RequestParam(value="echostr", required = true) String echostr,
			@RequestParam(value="accountId",required = true) String accountId){//随机字符串  
		//use account id to get account information (include token)
		String token = "13858180812";
		
		//use account related token to validate
		if(WeChatUtils.checkSignature(signature,timestamp,nonce,token)){
			return echostr;
		}
		
		return null;
	}
	
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/api", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getReply(HttpServletRequest request,
			@RequestParam(value="signature", required = true) String signature, //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
			@RequestParam(value="timestamp", required = true) String timestamp, //时间戳  
			@RequestParam(value="nonce", required = true) String nonce) throws Exception{ //随机数
		String resultXML = null;
		
		//XML Parse From Request content
        Map<String, String> requestMap = WeChatUtils.parseXml(request);  
        
        String fromUserName = requestMap.get("FromUserName");  
        String toUserName = requestMap.get("ToUserName");  
        String createTime = requestMap.get("CreateTime");  
        String msgType = requestMap.get("MsgType");
        String msgId = requestMap.get("MsgId");
        //MsgType = text
        String content = requestMap.get("Content");
        //MsgType = image
        String picUrl = requestMap.get("PicUrl");
        String mediaId = requestMap.get("MediaId"); //MsgType = voice //MsgType = video
        //MsgType = voice
        String format = requestMap.get("Format");        
        //MsgType = video
        String thumbMediaId = requestMap.get("ThumbMediaId");
        //MsgType = location
        String location_X = requestMap.get("Location_X");
        String location_Y = requestMap.get("Location_Y");
        String scale = requestMap.get("Scale");
        String label = requestMap.get("Label");
        //MsgType = link
        String title = requestMap.get("Title");
        String description = requestMap.get("Description");
        String url = requestMap.get("Url");
     
        //MsgType = event
        String event = requestMap.get("Event"); //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
        if(event!=null && event.equalsIgnoreCase("subscribe")){
        	WeChatTextResponse textRes = new WeChatTextResponse();
            textRes.setToUserName(toUserName);
            textRes.setFromUserName(fromUserName);
            textRes.setMsgType("text");
            textRes.setCreateTime(System.currentTimeMillis());
            textRes.setContent("请回复输入:注册 XXX,完成初始化用户注册。例如:注册 王科学..");
            resultXML = textRes.getResponseXML();
        }else{
        	
        	TextMsgRecord textMsgRecord = new TextMsgRecord();
        	textMsgRecord.setContent(content);
        	textMsgRecord.setCreateTime(new Date());
        	textMsgRecord.setFromUserName(fromUserName);
        	textMsgRecord.setMsgId(msgId);
        	textMsgRecord.setMsgType(msgType);
        	textMsgRecord.setToUserName(toUserName);
        	textMsgRecordService.insertTextMsgRecord(textMsgRecord);
        	
        	 if(content.indexOf("注册")>=0){//Text
             	WeChatTextResponse textRes = new WeChatTextResponse();
				textRes.setToUserName(toUserName);
				textRes.setFromUserName(fromUserName);
				textRes.setMsgType("text");
				textRes.setCreateTime(System.currentTimeMillis());
				textRes.setContent("用户:"+content+",注册成功!");
				resultXML = textRes.getResponseXML();
				
				ChatUser chatUser = new ChatUser();
				chatUser.setName(content);
				chatUser.setOpenid(fromUserName);
				chatUserService.insertChatUser(chatUser);
				
             }else{
				String newTitle1 = "测试图文信息1";
				String newDescription1 = "信息<H1>asdads</H1>";
				String newPicUrl1 = "http://mp.weixin.qq.com/wiki/skins/common/images/weixin_wiki_logo.png";
				String newUrl1 = "";
				WeChatNewsItem weChatNewsItem1 = new WeChatNewsItem(newTitle1, newDescription1, newPicUrl1, newUrl1);
				String newTitle2 = "测试图文信息1";
				String newDescription2 = "信息<H1>asdads</H1>";
				String newPicUrl2 = "http://mp.weixin.qq.com/wiki/skins/common/images/weixin_wiki_logo.png";
				String newUrl2 = "www.baidu.com";
				WeChatNewsItem weChatNewsItem2 = new WeChatNewsItem(newTitle2, newDescription2, newPicUrl2, newUrl2);
				List<WeChatNewsItem> weChatNewsItems= new ArrayList<WeChatNewsItem>();
				weChatNewsItems.add(weChatNewsItem1);
				weChatNewsItems.add(weChatNewsItem2);
				WeChatNewsResponse newsRes =  new WeChatNewsResponse();
	        	newsRes.setToUserName(toUserName);
	        	newsRes.setFromUserName(fromUserName);
	        	newsRes.setMsgType("news");
	        	newsRes.setCreateTime(System.currentTimeMillis());
	        	newsRes.setArticleCount(weChatNewsItems.size());
	        	newsRes.setWeChatNewsItems(weChatNewsItems);
	        	resultXML = newsRes.getResponseXML();//JaxbUtils.convertToXml(newsRes);  
             }
        }
      
		return resultXML;
	}

}
