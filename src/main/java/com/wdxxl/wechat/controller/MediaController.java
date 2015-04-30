package com.wdxxl.wechat.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wdxxl.wechat.http.MediaUtil;
import com.wdxxl.wechat.service.ITokenService;

/**
 * 自定义菜单管理
 * 
 * @author wdxxl
 *
 */
@Controller
public class MediaController {
	Logger logger = Logger.getLogger(MediaController.class);
	
	@Autowired
	private ITokenService tokenService;
	
	@RequestMapping(method = RequestMethod.POST, value="/uploadMedia")
	public @ResponseBody String uploadMedia(@RequestParam(value="media",required=true) MultipartFile file,
			@RequestParam(value="type", required = true) String type){
		 File tempFile = null; 
		 if (!file.isEmpty()) {
	            FileOutputStream fos = null;
	            try {
	                byte[] bytes = file.getBytes();
	                tempFile = new File(file.getOriginalFilename());
	                fos = new FileOutputStream(tempFile);
	                fos.write(bytes);
	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    fos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		
		String result = MediaUtil.uploadMedia(tokenService.getCurrentAccessToken(),type, tempFile);
		 // 输入要删除的文件位置
		if(tempFile.exists())
			tempFile.delete();
		return result;
	}
	
	
	
}
