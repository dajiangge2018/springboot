package org.jiang.controller;

import org.apache.commons.lang3.StringUtils;
import org.jiang.pojo.Users;
import org.jiang.pojo.bo.UsersBO;
import org.jiang.pojo.vo.UsersVO;
import org.jiang.service.impl.UserServiceImpl;
import org.jiang.utils.FastDFSClient;
import org.jiang.utils.FileUtils;
import org.jiang.utils.JiangJSONResult;
import org.jiang.utils.MD5Utils;
import org.joang.enums.SearchFriendsStatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@RestController
@RequestMapping("/u")
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private FastDFSClient fastDFSClient;
	/**
	 * 用户注册/登陆
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/registOrLogin")
	public JiangJSONResult registOrLogin(@RequestBody Users user) throws Exception {
		
		// 0. 判断用户名和密码不能为空
		if (StringUtils.isBlank(user.getUsername()) 
				|| StringUtils.isBlank(user.getPassword())) {
			return JiangJSONResult.errorMsg("用户名或密码不能为空...");
		}
		//调用服务层接口，查询该用户是否存在
		boolean isExistFlag = userService.queryUsernameIsExist(user.getUsername());
		
		Users userResult = null;
		//如果存在
		if(isExistFlag) {
			//登陆，需要校验密码是否正确,密码需要转换
			userResult =	userService.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
			if(userResult == null) {
				return JiangJSONResult.errorMsg("用户名或密码不正确...");
			}
		}else {
			//注册
			user.setNickname(user.getUsername());
			user.setFaceImage(null);
			user.setFaceImageBig(null);
			user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
			userResult =	userService.saveUser(user);
		}
		UsersVO userVO = new UsersVO();
		BeanUtils.copyProperties(userResult, userVO);
		return JiangJSONResult.ok(userVO);
	} 
	/**
	 * @Description: 上传用户头像
	 */
	@PostMapping("/uploadFaceBase64")
	public JiangJSONResult uploadFaceBase64(@RequestBody UsersBO userBO) throws Exception{
		// 获取前端传过来的base64字符串, 然后转换为文件对象再上传
				String base64Data = userBO.getFaceData();
				String userFacePath = "D:\\" + userBO.getUserId() + "userface64.png";
				FileUtils.base64ToFile(userFacePath, base64Data);
				
				// 上传文件到fastdfs
				MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
				String url = fastDFSClient.uploadBase64(faceFile);
				System.out.println(url);
				
//				"dhawuidhwaiuh3u89u98432.png"
//				"dhawuidhwaiuh3u89u98432_80x80.png"
				
				// 获取缩略图的url
				String thump = "_80x80.";
				String arr[] = url.split("\\.");
				String thumpImgUrl = arr[0] + thump + arr[1];
				
				// 更细用户头像
				Users user = new Users();
				user.setId(userBO.getUserId());
				user.setFaceImage(thumpImgUrl);
				user.setFaceImageBig(url);
				
				Users result = userService.updateUserInfo(user);
				
				return JiangJSONResult.ok(result);
	}
	
	/**
	 * @Description: 修改名字
	 */
	@PostMapping("/setNickname")
	public JiangJSONResult setNickname(@RequestBody UsersBO userBO) throws Exception{
		
		Users user = new Users();
		
		user.setId(userBO.getUserId());
		user.setNickname(userBO.getNickname());
		
		Users result = userService.updateUserInfo(user);
		return JiangJSONResult.ok(result);
	}

	/**
	 * @Description: 根据账号做匹配查询
	 */
	@PostMapping("/search")
	public JiangJSONResult searchUser(String myUserId, String friendUsername) throws Exception{
		
		// 0. 判断 myUserId friendUsername 不能为空
				if (StringUtils.isBlank(myUserId) 
						|| StringUtils.isBlank(friendUsername)) {
					return JiangJSONResult.errorMsg("");
				}
				
				// 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
				// 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
				// 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
				Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
				if (status == SearchFriendsStatusEnum.SUCCESS.status) {
					Users user = userService.queryUserInfoByUsername(friendUsername);
					UsersVO userVO = new UsersVO();
					BeanUtils.copyProperties(user, userVO);
					return JiangJSONResult.ok(userVO);
				} else {
					String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
					return JiangJSONResult.errorMsg(errorMsg);
				}
	}
}
