package org.jiang.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jiang.pojo.Users;
import org.jiang.pojo.vo.UsersVO;
import org.jiang.service.impl.UserServiceImpl;
import org.jiang.utils.JiangJSONResult;
import org.jiang.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/u")
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
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
		boolean isExistFlag = userService.queryUserNameIsExist(user.getUsername());
		
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
			userResult =	userService.saveUser(userResult);
		}
		UsersVO userVO = new UsersVO();
		BeanUtils.copyProperties(userResult, userVO);
		return JiangJSONResult.ok();
	} 

}
