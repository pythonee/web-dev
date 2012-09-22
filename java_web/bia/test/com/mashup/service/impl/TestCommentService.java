/**
 * 
 */
package com.mashup.service.impl;

import java.util.Date;

import org.junit.Test;

import com.mashup.base.BaseTransactionalDataSourceTests;
import com.mashup.domain.Comment;
import com.mashup.domain.Product;
import com.mashup.domain.User;
import com.mashup.service.ICommentService;
import com.mashup.service.IProductService;
import com.mashup.service.IUserService;

/**
 * @author Administrator
 * 
 */
public class TestCommentService extends BaseTransactionalDataSourceTests {
	ICommentService commentService;
	IUserService userService;
	IProductService productService;

	/**
	 * {@link com.mashup.service.impl.CommentService#findAll()} 的测试方法。
	 */
	@Test
	public void testFindAll() {
		assertEquals(1, commentService.findAll().size());
		log.debug("findAll()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CommentService#getCommentById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testGetCommentById() {
		assertEquals("这厮欠揍！", commentService.getCommentById(1)
				.getCommentContent());
		log.debug("getCommentById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CommentService#updateComment(com.mashup.domain.Comment)}
	 * 的测试方法。
	 */
	@Test
	public void testUpdateComment() {
		Date dt = new Date();
		User user = userService.getUserById(6);
		Product product = productService.getProductById(10);
		Comment comment = new Comment();
		comment.setCommentId(1);
		comment.setCommentContent("这妞真漂亮！");
		comment.setUser(user);
		comment.setProduct(product);
		comment.setCommentTime(dt);

		commentService.updateComment(comment);
		assertEquals("这妞真漂亮！", commentService.getCommentById(1)
				.getCommentContent());
		log.debug("updateComment()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CommentService#insertComment(com.mashup.domain.Comment)}
	 * 的测试方法。
	 */
	@Test
	public void testInsertComment() {
		Date dt = new Date();
		User user = userService.getUserById(5);
		Product product = productService.getProductById(9);
		Comment comment = new Comment();
		comment.setCommentContent("这货有水平");
		comment.setUser(user);
		comment.setProduct(product);
		comment.setCommentTime(dt);

		int pre = commentService.findAll().size();
		commentService.insertComment(comment);
		assertEquals(pre + 1, commentService.findAll().size());
		log.debug("insertComment()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CommentService#removeCommentById(java.lang.Integer)}
	 * 的测试方法。
	 */
	@Test
	public void testRemoveCommentById() {
		int pre = commentService.findAll().size();
		commentService.removeCommentById(1);
		assertEquals(pre - 1, commentService.findAll().size());
		log.debug("removecommentById()完成测试");
	}

	/**
	 * {@link com.mashup.service.impl.CommentService#batchRemoveComment(java.util.List)}
	 * 的测试方法。
	 */
	@Test
	public void testBatchRemoveComment() {
		// 尚未实现
	}

	/**
	 * {@link com.mashup.service.impl.CommentService#getCommentByProduct(com.mashup.domain.Product)}
	 * 的测试方法。
	 */
	@Test
	public void testGetCommentByProduct() {
		Product product = productService.getProductById(10);
		assertEquals(1, commentService.getCommentByProduct(product).size());
		log.debug("getCommentByProduct()完成测试");
	}

	public ICommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

}
