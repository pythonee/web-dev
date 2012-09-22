package com.mashup.service.impl;

import com.mashup.base.BaseTransactionalDataSourceTests;
import com.mashup.domain.Friend;
import com.mashup.domain.User;
import com.mashup.service.IFriendService;

public class TestFriendService extends BaseTransactionalDataSourceTests {

	IFriendService friendService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.test.AbstractSingleSpringContextTests#onSetUp()
	 */
	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.test.AbstractSingleSpringContextTests#onTearDown()
	 */
	@Override
	protected void onTearDown() throws Exception {
		// TODO Auto-generated method stub
		super.onTearDown();
	}

	public void testFindAll() {
		// 保证expected等于查询所得的size大小
		assertEquals(8, friendService.findAll().size());
		log.debug("findAll()完成测试");
	}

	public void testGetFriendById() {
		// 制定一个userId，保证expected与查询所得的该userId的朋友数相等
		// Logger log = Logger.getLogger(this.getClass().getName());
		// log.debug(((Friend) friendService.findByUserId(4).get(0))
		// .getUserByFriendId().getUsername());
		assertEquals(5, friendService.findByUserId(4).size());
		log.debug("getFriendById()完成测试");
	}

	public void testUpdateFriend() {
		// 测试update一个朋友关系是否成功，直接比较update操作前后的数据表的变化
		User user1 = new User();
		user1.setUserId(5);
		User user2 = new User();
		user2.setUserId(4);

		Friend friend = new Friend(user1, user2);
		friend.setRelationId(7);
		friendService.updateFriend(friend);
		log.debug("updateFriend()完成测试");
	}

	public void testInsertFriend() {
		// 测试插入朋友关系能否插入成功，直接比较update操作前后的数据表的变化
		User user1 = new User();
		user1.setUserId(8);
		User user2 = new User();
		user2.setUserId(5);

		Friend friend = new Friend(user1, user2);
		friendService.insertFriend(friend);
		log.debug("insertFriend()完成测试");
	}

	public void testRemoveFriendById() {
		// 通过relationId来删除一个朋友关系
		int pre = friendService.findAll().size();
		friendService.removeFriendById(8);
		assertEquals(pre - 1, friendService.findAll().size());
		log.debug("removeFriendById()完成测试");
	}

	public void testBatchRemoveFriend() {
		// 方法未实现
	}

	public void testFindByUserId() {
		// 通过userId来找到这个user对应的所有friend
		assertEquals(5, friendService.findByUserId(4).size());
		log.debug("findByUserId()完成测试");
	}

	public void testFindByPageAndUserId() {
		// 给定page和userId，保证返回此用户page页的所有朋友
		// 以下测试返回用户Id为5的用户在page=1页的所有朋友数
		assertEquals(0, friendService.findByPageAndUserId(1, 5).size());
		log.debug("findByPageAndUserId()完成测试");
	}

	/**
	 * @return the friendService
	 */
	public IFriendService getFriendService() {
		return friendService;
	}

	/**
	 * @param friendService
	 *            the friendService to set
	 */
	public void setFriendService(IFriendService friendService) {
		this.friendService = friendService;
	}
}
