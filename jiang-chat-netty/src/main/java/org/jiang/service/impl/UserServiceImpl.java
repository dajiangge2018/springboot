package org.jiang.service.impl;

import java.util.List;

import org.jiang.mapper.UsersMapper;
import org.jiang.pojo.ChatMsg;
import org.jiang.pojo.Users;
import org.jiang.pojo.vo.FriendRequestVO;
import org.jiang.pojo.vo.MyFriendsVO;

import org.jiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersMapper userMapper;
	

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

	@Override
	public Users queryUserForLogin(String username, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users saveUser(Users user) {
		// TODO Auto-generated method stub
		return null;
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
