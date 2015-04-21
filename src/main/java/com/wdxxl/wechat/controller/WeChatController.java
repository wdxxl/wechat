package com.wdxxl.wechat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.model.ChatUser;
import com.wdxxl.wechat.service.IChatUserService;
import com.wdxxl.wechat.service.ITextMsgRecordService;
import com.wdxxl.wechat.utils.WeChatNewsItem;
import com.wdxxl.wechat.utils.WeChatNewsResponse;
import com.wdxxl.wechat.utils.WeChatTextResponse;
import com.wdxxl.wechat.utils.WeChatUtils;

@Controller
public class WeChatController {
	Logger logger = Logger.getLogger(WeChatController.class);
	
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
		logger.debug("Call /api GET.");
		//use account id to get account information (include token)
		String token = "13858180812";
		
		//use account related token to validate
		if(WeChatUtils.checkSignature(signature,timestamp,nonce,token)){
			return echostr;
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/api", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String getReply(HttpServletRequest request,
			@RequestParam(value="signature", required = true) String signature, //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
			@RequestParam(value="timestamp", required = true) String timestamp, //时间戳  
			@RequestParam(value="nonce", required = true) String nonce) throws Exception{ //随机数
		logger.debug("Call /api POST.");
		String resultXML = null;
		
		//XML Parse From Request content
        Map<String, String> requestMap = WeChatUtils.parseXml(request);  
        
        String fromUserName = requestMap.get("FromUserName");  
        String toUserName = requestMap.get("ToUserName");  
        
        if(requestMap != null){
        	String msgType = requestMap.get("MsgType");
        	if(msgType!=null && msgType.equalsIgnoreCase("event")){//消息类型，event(菜单点击/订阅)
        		String event = requestMap.get("Event");
        		 if(event!=null && event.equalsIgnoreCase("subscribe")){//订阅
					WeChatTextResponse textRes = new WeChatTextResponse();
					textRes.setToUserName(toUserName);
					textRes.setFromUserName(fromUserName);
					textRes.setMsgType("text");
					textRes.setCreateTime(System.currentTimeMillis());
					textRes.setContent("请回复输入:注册 XXX,完成初始化用户注册。例如:注册 王科学...");
					resultXML = textRes.getResponseXML();
        		 }else if(event!=null && event.equalsIgnoreCase("click")){//菜单点击事件
        			 String eventKey = requestMap.get("EventKey");
        			 if(eventKey!=null && eventKey.equalsIgnoreCase("你好")){
        				 WeChatTextResponse textRes = new WeChatTextResponse();
        				 textRes.setToUserName(toUserName);
        				 textRes.setFromUserName(fromUserName);
        				 textRes.setMsgType("text");
        				 textRes.setCreateTime(System.currentTimeMillis());
        				 textRes.setContent("您点击了: "+eventKey);
        				 resultXML = textRes.getResponseXML();
        			 }else{
           				String newTitle3 = "吉利汽车编年史...";
           				String newDescription3 = "(Wikipedia抠的)";
           				String newPicUrl3 = "http://124.160.42.240/wechat/resource/image/evun/evun.png";
           				String newUrl3 = "http://mp.weixin.qq.com/s?__biz=MzA5NDUyMTUxNA==&mid=203446829&idx=2&sn=2c4053fa1dfb7fdb6493c417fef1fb7e#rd";
           				WeChatNewsItem weChatNewsItem3 = new WeChatNewsItem(newTitle3, newDescription3, newPicUrl3, newUrl3);
           				List<WeChatNewsItem> weChatNewsItems= new ArrayList<WeChatNewsItem>();
           				weChatNewsItems.add(weChatNewsItem3);
           				WeChatNewsResponse newsRes =  new WeChatNewsResponse();
           	        	newsRes.setToUserName(toUserName);
           	        	newsRes.setFromUserName(fromUserName);
           	        	newsRes.setMsgType("news");
           	        	newsRes.setCreateTime(System.currentTimeMillis());
           	        	newsRes.setArticleCount(weChatNewsItems.size());
           	        	newsRes.setWeChatNewsItems(weChatNewsItems);
           	        	resultXML = newsRes.getResponseXML();
        			 }
        		 }else{
        			 logger.debug("Event Else.");
        		 }
        	}else if(msgType!=null && msgType.equalsIgnoreCase("text")){//消息类型，text(文本消息)
        		 String content = requestMap.get("Content");
        		 if(content.indexOf("注册")>=0){
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
                	String newTitle1 = "春晓整车制造基地MES项目";
      				String newDescription1 = "“零缺陷，零干预，零浪费”，助力企业打造精益智能工厂！";
      				String newPicUrl1 = "http://124.160.42.240/wechat/resource/image/mes/chunxiao/mes.png";
      				String newUrl1 = "http://www.evun.cn/index.php/case/detail/27?ctype=6";
      				WeChatNewsItem weChatNewsItem1 = new WeChatNewsItem(newTitle1, newDescription1, newPicUrl1, newUrl1);
      				String newTitle2 = "杭州吉利易云科技有限公司";
      				String newDescription2 = "汽车行业领先的信息化解决方案提供商和运营商";
      				String newPicUrl2 = "http://124.160.42.240/wechat/resource/image/evun/evun.png";
      				String newUrl2 = "http://www.evun.cn";
      				WeChatNewsItem weChatNewsItem2 = new WeChatNewsItem(newTitle2, newDescription2, newPicUrl2, newUrl2);
      				String newTitle3 = "吉利汽车编年史...";
      				String newDescription3 = "(Wikipedia抠的)";
      				String newPicUrl3 = "http://124.160.42.240/wechat/resource/image/evun/evun.png";
      				String newUrl3 = "http://mp.weixin.qq.com/s?__biz=MzA5NDUyMTUxNA==&mid=203446829&idx=2&sn=2c4053fa1dfb7fdb6493c417fef1fb7e#rd";
      				WeChatNewsItem weChatNewsItem3 = new WeChatNewsItem(newTitle3, newDescription3, newPicUrl3, newUrl3);
      				List<WeChatNewsItem> weChatNewsItems= new ArrayList<WeChatNewsItem>();
      				weChatNewsItems.add(weChatNewsItem1);
      				weChatNewsItems.add(weChatNewsItem2);
      				weChatNewsItems.add(weChatNewsItem3);
      				WeChatNewsResponse newsRes =  new WeChatNewsResponse();
      	        	newsRes.setToUserName(toUserName);
      	        	newsRes.setFromUserName(fromUserName);
      	        	newsRes.setMsgType("news");
      	        	newsRes.setCreateTime(System.currentTimeMillis());
      	        	newsRes.setArticleCount(weChatNewsItems.size());
      	        	newsRes.setWeChatNewsItems(weChatNewsItems);
      	        	resultXML = newsRes.getResponseXML();//JaxbUtils.convertToXml(newsRes);  
                  }
        	}else{
        		logger.debug("Not Event&Text Else.");
        	}
        }
        
		return resultXML;
	}

}
