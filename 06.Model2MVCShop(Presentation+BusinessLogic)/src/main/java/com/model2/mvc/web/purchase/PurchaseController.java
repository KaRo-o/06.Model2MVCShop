package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;


@Controller
public class PurchaseController {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@RequestMapping("/addPurchaseView.do")
	public String AddPurchase(@RequestParam("prodNo") int prodNo
										,HttpServletRequest req, Model model
										) throws Exception {
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		
		Product product = productService.getProduct(prodNo);
		
		Purchase purchase = new Purchase();
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		
		model.addAttribute("purchase", purchase);
		
		
		return "forward:/purchase/addPurchase.jsp";
	}
	
	@RequestMapping("/addPurchase.do")
	public String AddPurchaseResult(@ModelAttribute("purchase") Purchase purchase
												) throws Exception {
		
		System.out.println("purchase : "+ purchase);
		
		
		
		purchaseService.addPurchase(purchase);
		
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	
}
