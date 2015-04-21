package com.wdxxl.wechat.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wdxxl.wechat.http.GroupUtil;
import com.wdxxl.wechat.service.ITokenService;

/**
 * 分组管理接口
 * 开发者可以使用接口，对公众平台的分组进行查询、创建、修改操作，也可以使用接口在需要时移动用户到某个分组。
 * 
 * @author wdxxl
 *
 */
@Controller
public class GroupController {
	Logger logger = Logger.getLogger(GroupController.class);
	
	@Autowired
	private ITokenService tokenService;
	
	@RequestMapping(method = RequestMethod.PUT, value="/createGroup")
	public @ResponseBody String createGroup(
			@RequestParam(value="groupName", required = true) String groupName){
		String result = GroupUtil.createGroup(tokenService.getCurrentAccessToken(), groupName);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getGroups")
	public @ResponseBody String getGroups(){
		String result = GroupUtil.getGroups(tokenService.getCurrentAccessToken());
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/getIdGroups")
	public @ResponseBody String getIdGroups(
			@RequestParam(value="openid", required = true) String openid){
		String result = GroupUtil.getIdGroups(tokenService.getCurrentAccessToken(), openid);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/updateGroups")
	public @ResponseBody String updateGroups(
			@RequestParam(value="groupId", required = true) String groupId,
			@RequestParam(value="groupName", required = true) String groupName){
		String result = GroupUtil.updateGroups(tokenService.getCurrentAccessToken(), groupId,groupName);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/updateMembersGroups")
	public @ResponseBody String updateMembersGroups(
			@RequestParam(value="openid", required = true) String openid,
			@RequestParam(value="groupId", required = true) String groupId){
		String result = GroupUtil.updateMembersGroups(tokenService.getCurrentAccessToken(),openid, groupId);
		return result;
	}
	
	/**
	 * 模拟List 提交 http://localhost:8080/wechat/batchUpdateMembersGroups?groupId=102&openidList=oqqZyuLXjSamIiF7mLbWtQHfADIs&openidList=oqqZyuLiVH39115jOCigxLMVSE-8
	 * 
	 * 分组不存在 {"errcode":-1,"errmsg":"system error"}
	 * @param openidList
	 * @param groupId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value="/batchUpdateMembersGroups")
	public @ResponseBody String batchUpdateMembersGroups(
			@RequestParam(value="openidList", required = true) List<String> openidList,
			@RequestParam(value="groupId", required = true) String groupId){
		String result = GroupUtil.batchUpdateMembersGroups(tokenService.getCurrentAccessToken(),openidList, groupId);
		return result;
	}
	
	/**
	 * 现阶段一直报错，无法删除分组
	 * {"errcode":-1,"errmsg":"system error"}
	 * @param groupId
	 * @return
	 */
	@Deprecated
	@RequestMapping(method = RequestMethod.DELETE, value="/deleteGroups")
	public @ResponseBody String deleteGroups(
			@RequestParam(value="groupId", required = true) Integer groupId){
		String result = GroupUtil.deleteGroups(tokenService.getCurrentAccessToken(), groupId);
		return result;
	}
	
	
}
