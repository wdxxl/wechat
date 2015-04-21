package com.wdxxl.wechat.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;

/**
 * 分组管理接口
 * 开发者可以使用接口，对公众平台的分组进行查询、创建、修改操作，也可以使用接口在需要时移动用户到某个分组。
 * http://mp.weixin.qq.com/wiki/0/56d992c605a97245eb7e617854b169fc.html
 * 
 * @author wdxxl
 * @date 2015-4-21 09:46:07
 * 
 */
public class GroupUtil {
	private static final String GROUPS_CREATE_URI_POST = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=";							//创建分组
	private static final String GROUPS_GET_URI_GET = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=";									//查询所有分组
	private static final String GROUPS_GETID_URI_POST = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=";								//查询用户所在分组
	private static final String GROUPS_UPDATE_URI_POST = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=";							//修改分组名
	private static final String GROUPS_MEMBER_UPDATE_URI_POST = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=";			//移动用户分组
	private static final String GROUPS_MEMBER_BATCHUPDATE_URI_POST = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=";	//批量移动用户分组
	private static final String GROUPS_DELETE_POST = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=";								//删除分组
	
	
	/**
	 * 创建分组, 一个公众账号,最多支持创建100个分组。
	 * 	发送 {"group":{"name":"test"}}
	 * 	返回 {"group": {"id": 107, "name": "test"}}
	 * @param accessToken
	 * @param groupName 分组名字（30个字符以内）
	 * @return
	 */
	public static String createGroup(String accessToken, String groupName){
		String result = null;
		
		Map<String,Object> group = new HashMap<String,Object>();
        Map<String,Object> nameObj = new HashMap<String,Object>();
        nameObj.put("name", groupName);
        group.put("group", nameObj);
        String reqJson = JSONObject.toJSONString(group);
        
		try {
			result = HttpClientUtils.Post(GROUPS_CREATE_URI_POST+accessToken, reqJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询所有分组
	 * @param accessToken
	 * @return
	 */
	public static String getGroups(String accessToken){
		String result = null;
		try {
			result = HttpClientUtils.Get(GROUPS_GET_URI_GET+accessToken);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询用户所在分组
	 * 通过用户的OpenID查询其所在的GroupID。
	 * 	发送  {"openid":"od8XIjsmk6QdVTETa9jLtGWA6KBc"}
	 *  返回  {"groupid": 102}
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static String getIdGroups(String accessToken,String openId){
		String result = null;
		
		Map<String,Object> openIdMap = new HashMap<String,Object>();
		openIdMap.put("openid", openId);
        String reqJson = JSONObject.toJSONString(openIdMap);
        
		try {
			result = HttpClientUtils.Post(GROUPS_GETID_URI_POST+accessToken, reqJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改分组名
	 * 发送  {"group":{"id":108,"name":"test2_modify2"}}
	 * 返回{"errcode": 0, "errmsg": "ok"}
	 * 
	 * @param accessToken
	 * @param groupId
	 * @param groupName
	 * @return
	 */
	public static String updateGroups(String accessToken,String groupId,String groupName){
		String result = null;
		
		Map<String,Object> group = new HashMap<String,Object>();
        Map<String,Object> nameObj = new HashMap<String,Object>();
        nameObj.put("name", groupName);
        nameObj.put("id", groupId);
        group.put("group", nameObj);
        String reqJson = JSONObject.toJSONString(group);
        
		try {
			result = HttpClientUtils.Post(GROUPS_UPDATE_URI_POST+accessToken, reqJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * 移动用户分组
	 *  发送 {"openid":"oDF3iYx0ro3_7jD4HFRDfrjdCM58","to_groupid":108}
	 *  返回 {"errcode": 0, "errmsg": "ok"}
	 * @param accessToken
	 * @param openId
	 * @param toGroupId
	 * @return
	 */
	public static String updateMembersGroups(String accessToken,String openId,String toGroupId){
		String result = null;
		
        Map<String,Object> nameObj = new HashMap<String,Object>();
        nameObj.put("openid", openId);
        nameObj.put("to_groupid", toGroupId);
        String reqJson = JSONObject.toJSONString(nameObj);
        
		try {
			result = HttpClientUtils.Post(GROUPS_MEMBER_UPDATE_URI_POST+accessToken, reqJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 批量移动用户分组
	 * 发送 {"openid_list":["oDF3iYx0ro3_7jD4HFRDfrjdCM58","oDF3iY9FGSSRHom3B-0w5j4jlEyY"],"to_groupid":108}
	 * 返回 {"errcode": 0, "errmsg": "ok"}
	 * @param accessToken
	 * @param openidList
	 * @param toGroupId
	 * @return
	 */
	public static String batchUpdateMembersGroups(String accessToken,List<String> openidList,String toGroupId){
		String result = null;
		
        Map<String,Object> nameObj = new HashMap<String,Object>();
        nameObj.put("openid_list", openidList);
        nameObj.put("to_groupid", toGroupId);
        String reqJson = JSONObject.toJSONString(nameObj);
        
		try {
			result = HttpClientUtils.Post(GROUPS_MEMBER_BATCHUPDATE_URI_POST+accessToken, reqJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除分组
	 * 注意本接口是删除一个用户分组，删除分组后，所有该分组内的用户自动进入默认分组
	 * 发送 {"group":{"id":108}}
	 * 返回  {"errcode":-1,"errmsg":"system error"}
	 * @param accessToken
	 * @param groupId
	 * @return
	 */
	@Deprecated
	public static String deleteGroups(String accessToken, Integer groupId){
		String result = null;
		
		Map<String,Object> group = new HashMap<String,Object>();
        Map<String,Object> nameObj = new HashMap<String,Object>();
        nameObj.put("id", groupId);
        group.put("group", nameObj);
        String reqJson = JSONObject.toJSONString(group);
        
		try {
			result = HttpClientUtils.Post(GROUPS_DELETE_POST+accessToken, reqJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
