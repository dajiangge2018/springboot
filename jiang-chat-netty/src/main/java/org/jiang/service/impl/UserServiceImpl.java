package org.jiang.service.impl;

import java.io.IOException;
import java.util.List;

import org.jiang.mapper.ChatMsgMapper;
import org.jiang.mapper.FriendsRequestMapper;
import org.jiang.mapper.MyFriendsMapper;
import org.jiang.mapper.UsersMapper;
import org.jiang.mapper.UsersMapperCustom;
import org.jiang.pojo.ChatMsg;
import org.jiang.pojo.MyFriends;
import org.jiang.pojo.Users;
import org.jiang.pojo.vo.FriendRequestVO;
import org.jiang.pojo.vo.MyFriendsVO;
import org.jiang.service.UserService;
import org.jiang.utils.FastDFSClient;
import org.jiang.utils.FileUtils;
import org.jiang.utils.QRCodeUtils;
import org.joang.enums.SearchFriendsStatusEnum;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersMapper userMapper;
	
	@Autowired
	private UsersMapperCustom usersMapperCustom;
	
	@Autowired
	private MyFriendsMapper myFriendsMapper;
	
	@Autowired
	private FriendsRequestMapper friendsRequestMapper;
	
	@Autowired
	private ChatMsgMapper chatMsgMapper;
	
	@Autowired
	private Sid sid;
	
	@Autowired
	private QRCodeUtils qrCodeUtils;
	
	@Autowired
	private FastDFSClient fastDFSClient;
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public boolean queryUsernameIsExist(String username) {
		Users user = new Users();
		user.setUsername(username);
		
		Users result = userMapper.selectOne(user);
		
		if(result!=null) {			
			return true;		
		}else {		
			return false;	
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserForLogin(String username, String pwd) {
		
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		
		criteria.andEqualTo("username", username);
		criteria.andEqualTo("password", pwd);
		
		Users result = userMapper.selectOneByExample(userExample);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Users saveUser(Users user) {
		
		String userId = sid.nextShort();
		// 为每个用户生成一个唯一的二维码
	    String qrCodePath = "D://user" + userId + "qrcode.png";
	
		qrCodeUtils.createQRCode(qrCodePath, "jiangChat_qrcode:" + user.getUsername());
		MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);
		
		String qrCodeUrl = "";
		
		try {
			qrCodeUrl = fastDFSClient.uploadQRCode(qrCodeFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		user.setQrcode(qrCodeUrl);
		
		user.setId(userId);
		userMapper.insert(user);
		return user;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Users updateUserInfo(Users user) {
		userMapper.updateByPrimaryKeySelective(user);
		return queryUserById(user.getId());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	private Users queryUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Integer preconditionSearchFriends(String myUserId, String friendUsername) {
		Users user = queryUserInfoByUsername(friendUsername);
		
		// 1. 搜索的用户如果不存在，返回[无此用户]
		if (user == null) {
			return SearchFriendsStatusEnum.USER_NOT_EXIST.status;
		}
		
		// 2. 搜索账号是你自己，返回[不能添加自己]
		if (user.getId().equals(myUserId)) {
			return SearchFriendsStatusEnum.NOT_YOURSELF.status;
		}
		
		// 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
		Example mfe = new Example(MyFriends.class);
		Criteria mfc = mfe.createCriteria();
		mfc.andEqualTo("myUserId", myUserId);
		mfc.andEqualTo("myFriendUserId", user.getId());
		MyFriends myFriendsRel = myFriendsMapper.selectOneByExample(mfe);
		if (myFriendsRel != null) {
			return SearchFriendsStatusEnum.ALREADY_FRIENDS.status;
		}
		
		return SearchFriendsStatusEnum.SUCCESS.status;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserInfoByUsername(String username) {
		Example ue = new Example(Users.class);
		Criteria uc = ue.createCriteria();
		uc.andEqualTo("username", username);
		return userMapper.selectOneByExample(ue);
	}

	@Override
	public void sendFriendRequest(String myUserId, String friendUsername) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFriendRequest(String sendUserId, String acceptUserId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passFriendRequest(String sendUserId, String acceptUserId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MyFriendsVO> queryMyFriends(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMsgSigned(List<String> msgIdList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<org.jiang.pojo.ChatMsg> getUnReadMsgList(String acceptUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveMsg(ChatMsg chatMsg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
