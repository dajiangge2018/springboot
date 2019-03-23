package org.jiang.mapper;

import java.util.List;

import org.jiang.pojo.Users;
import org.jiang.pojo.vo.FriendRequestVO;
import org.jiang.pojo.vo.MyFriendsVO;
import org.jiang.utils.MyMapper;

public interface UsersMapperCustom extends MyMapper<Users> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}