package com.model2.mvc.service.product.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.user.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception {
		Product product = new Product();
		product.setProdName("testProductName");
		product.setProdDetail("testProductDetail");
		product.setManuDate("24-03-04");
		product.setPrice(300000);
		product.setFileName("testFileName");
		
		productService.addProduct(product);		
		
	}
	
	@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		product = productService.getProduct(10001);
		System.out.println(productService.getProduct(10001));
		System.out.println(product.toString());
		
		Assert.assertEquals(10001,product.getProdNo());
		
		Assert.assertNotNull(product);
	
	}
	
	@Test
	public void testGetProductList() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("1000");
		
		productService.getProductList(search);
		
	}
	
	//@Test
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		
		product.setProdNo(10062);
		product.setProdName("updateTestProductName");
		product.setProdDetail("updateTestProductDetail");
		product.setManuDate("24-03-03");
		product.setPrice(200000);
		product.setFileName("updateTestFileName");
		
		productService.updateProduct(product);
		
	}
	
	@Test
	public void testGetTotalCount() throws Exception {
		Search search = new Search();
		search.setSearchCondition("0");
		search.setSearchKeyword("1000");
				
		
	}
	

}
