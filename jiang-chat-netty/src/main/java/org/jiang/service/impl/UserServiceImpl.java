package org.jiang.service.impl;

import java.util.List;

import org.jiang.mapper.ChatMsgMapper;
import org.jiang.mapper.FriendsRequestMapper;
import org.jiang.mapper.MyFriendsMapper;
import org.jiang.mapper.UsersMapper;
import org.jiang.pojo.ChatMsg;
import org.jiang.pojo.Users;
import org.jiang.pojo.vo.FriendRequestVO;
import org.jiang.pojo.vo.MyFriendsVO;
import org.jiang.service.UserService;
import org.jiang.utils.QRCodeUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersMapper userMapper;
	
//	@Autowired
//	private UsersMapperCustom usersMapperCustom;
	
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
//		String qrCodePath = "C://user" + userId + "qrcode.png";
//		
//		qrCodeUtils.createQRCode(qrCodePath, "jiangChat_qrcode:" + user.getUsername());
//		MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);
		
		String qrCodeUrl = "";
		
//		try {
//			qrCodeUrl = fastDFSClient.uploadQRCode(qrCodeFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		user.setQrcode(qrCodeUrl);
		
		user.setId(userId);
		userMapper.insert(user);
		return user;
	}

	@Override
	public Users updateUserInfo(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer preconditionSearchFriends(String myUserId, String friendUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users queryUserInfoByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
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
