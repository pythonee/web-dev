package com.mashup.search;

import com.mashup.base.BaseDependencyInjectionTests;
import com.mashup.domain.Category;
import com.mashup.domain.Product;
import com.mashup.search.CategoryMapFromDB;

public class TestCategoryMapFromDB extends BaseDependencyInjectionTests {
	
	CategoryMapFromDB categoryMapFromDB;

	/**
	 * @throws java.lang.Exception
	 */


	/**
	 * Test method for {@link com.mashup.service.impl.UserService#getUserById()}.
	 * @throws Throwable 
	 */
	public void testAddProductToDB()throws Throwable{

		for(int i=0;i<30;i++){
			Product product = new Product();
			product.setProductName("浙江义乌衣服商场服装衣服女装"+i);
			product.setProductDesc("衣服 女装 服装 西装");
			product.setLevelClick(6);
			product.setProductImg("images/item.bmp");
			product.setScore(3.1+i*0.05);
			product.setPrice(5.6+i);
			product.setUrl("http://youa.baidu.com");
			product.setSource("有啊");
			Category category = new Category();
			category.setCategoryId(3);
			product.setCategory(category);
			categoryMapFromDB.addProductToDB(product);
		}
		categoryMapFromDB.finalize();
	}

	public CategoryMapFromDB getCategoryMapFromDB() {
		return categoryMapFromDB;
	}

	public void setCategoryMapFromDB(CategoryMapFromDB categoryMapFromDB) {
		this.categoryMapFromDB = categoryMapFromDB;
	}

	@Override
	protected void onSetUp() throws Exception {
		// TODO Auto-generated method stub
		super.onSetUp();
		categoryMapFromDB.initTopNode();
	}

	@Override
	protected void onTearDown() throws Exception {
		// TODO Auto-generated method stub
		super.onTearDown();
	}

}



