/**
 * 
 */
package com.mashup.service.impl;

import java.util.List;

import org.junit.Test;

import com.mashup.base.BaseTransactionalDataSourceTests;
import com.mashup.domain.Role;
import com.mashup.domain.User;
import com.mashup.service.IUserService;

/**
 * @author Administrator
 * 
 */
public class TestUserService extends BaseTransactionalDataSourceTests {
	IUserService userService;
	User user;

	/**
	 * Test method for {@link com.mashup.service.impl.UserService#getUserById()}.
	 */

	@Test
	public void testLogin() {
		// 暂时未测
	}

	@Test
	public void testFindAll() {
		// 查看user表，保证总记录数与查询的总数相等
		log.debug("finish find all test " + userService.findAll().size());
		assertEquals(13, userService.findAll().size());
		log.debug("findAll()完成测试");
	}

	@Test
	public void testGetUserById() {
		// 查看数据表，保证某记录的用户名与实际查到的用户名是否相等
		assertEquals("guofengzai", userService.getUserById(1).getUsername());
		log.debug("getUserById()完成测试");
	}

	@Test
	public void testUpdateUser() {
		// 更新之前先保存总记录数，更新，保证更新之后的记录数与更新后的记录数相等
		User u = userService.getUserById(5);
		u.setUsername("laymu");
		int pre = userService.findAll().size();
		userService.updateUser(u);
		assertEquals(pre, userService.findAll().size());
		log.debug("updateUser()完成测试");
	}

	@Test
	public void testInsertUser() {
		// 保证插入用户之后，用户信息出现在数据表中
		User user = new User();
		user.setPassword("this");
		user.setEmail("pythonee@gmail.com");
		user.setStatus(1);
		user.setUsername("pythonee");
		userService.insertUser(user);
		log.debug("insertUser()完成测试");
	}

	@Test
	public void testRemoveUserById() {
		// 保证删除某一记录之后，总记录数减一
		int pre = userService.findAll().size();
		userService.removeUserById(10);
		assertEquals(pre - 1, userService.findAll().size());
		log.debug("removeUserById()完成测试");
	}

	@Test
	public void testBatchRemoveUser() {
		// 未实现
	}

	@Test
	public void testForgotPasswd() {
		// 通过实际是否收到忘记密码的邮件来测试
	}

	@Test
	public void testLogoff() {
		// 暂未测
	}

	@Test
	public void testFindByExample() {
		// 根据某一个User类的实例，来找出所有与该实例类似的记录
		User u = new User();
		u.setStatus(1);
		List ls = userService.findByExample(u);
		log.debug("Finish find by example: " + ls.size());
	}

	public void testAddUserRole() {
		// 根据某一个用户实例user和角色实例role，来保证UserRole的实例成功添加到数据库
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("ROLE_ADMIN");
		role.setDesc("管理员");
		userService.addUserRole(user, role);
		log.debug("addUserRole()完成测试");
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;

		user = new User();
		user.setUserId(5);
		user.setUsername("haoshuang");
		user.setPassword("123");
		user.setEmail("laymuhao@gmail.com");
		user.setStatus(1);
	}

	public IUserService getUserService() {
		return userService;
	}

}
