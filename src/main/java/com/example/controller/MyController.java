package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.model.MyCart;
import com.example.model.Orders;
import com.example.model.Products;
import com.example.repo.CustomerRepo;
import com.example.repo.MyCartRepo;
import com.example.repo.OrdersRepo;
import com.example.repo.ProductRepo;

@RestController
@CrossOrigin(origins = "*")
public class MyController {
	
	
	@Autowired
	CustomerRepo crepo;
	
	@Autowired
	MyCartRepo mcrepo;
	
	@Autowired
	OrdersRepo orepo;
	
	@Autowired
	ProductRepo prepo;
	
	@GetMapping("/order/{oid}")
	public Orders fetchOrderById(@PathVariable(value="oid") int oid) {
		Orders o = orepo.getById(oid);
		return o;
	}
	
	
	@GetMapping("/cart/{mcid}")
	public MyCart fetchCartById(@PathVariable(value="mcid") int mcid) {
		MyCart mc = mcrepo.getById(mcid);
//		System.out.println(mc);
		return mc;
	}
	
	@GetMapping("/customers")
	public List<Customer> fetchAllCustomers() {
		List<Customer> customerList=crepo.findAll();
		
		return customerList;
	}
	@GetMapping("/customer/{cid}")
	public Optional<Customer> fetchCustomerById(@PathVariable(value="cid") int cid) {
		Optional<Customer> customerList=crepo.findById(cid);
		
		return customerList;
	}
	@GetMapping("/products")
	public List<Products> fetchAllProducts() {
		List<Products> customerList=prepo.findAll();
		
		return customerList;
	}
	
	@GetMapping("/customer/cart/{cid}")
	public List<MyCart> fetchAllCustomerCart(@PathVariable(value="cid") int cid) {
		Customer c=crepo.getById(cid);
		return c.getMyCartList();
	}
	@GetMapping("/customer/order/{cid}")
	public List<Orders> fetchAllCustomerOrder(@PathVariable(value="cid") int cid) {
		MyCart c=mcrepo.getById(cid);
		System.out.println(c.getOrderList());
	//	System.out.println();
		return c.getOrderList();
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                        //	posting
	
	
	@PostMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
	public int createProduct(@RequestBody Products product) {
		product=prepo.save(product);
		return product.getPid();
	}
	
	@PostMapping(path="/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public int createCustomer(@RequestBody Customer customer) {
		customer=crepo.save(customer);
		return customer.getCid();
		
	}
	
	
	@PostMapping(value="/order/{cid}")
	public int placeOrder(@PathVariable(value="cid") int cid, @RequestBody Orders order) {
		MyCart mc=mcrepo.getById(cid);
		
		// 1st way
		// get the orderList from customer, add this order object and save customer
		List<Orders> orderList=mc.getOrderList();
		orderList.add(order);
		order.setMycart(mc);
		// retrieve itemList from order and add order object to each item
//				List<Item> itemList=order.getItemList();
//				for(Item item:itemList) {
//					item.setOrder(order);
//				}
//		mcrepo.save(mc);
		orepo.save(order);
		// 2nd way
		// add customer object into order object and save order object
		/* 
		order.setCustomer(customer);
		ojpaRepo.save(order); 
		*/
		
		return order.getOid();
	}
	
	@PostMapping(value="/cart/{cid}")
	public int placeOrder(@PathVariable(value="cid") int cid, @RequestBody MyCart cart) {
		Customer customer=crepo.getById(cid);
		
		// 1st way
		// get the orderList from customer, add this order object and save customer
		List<MyCart> cartList=customer.getMyCartList();
		cartList.add(cart);
		cart.setCustomer(customer);
		// retrieve itemList from order and add order object to each item
//				List<Item> itemList=order.getItemList();
//				for(Item item:itemList) {
//					item.setOrder(order);
//				}
//		crepo.save(customer);
		mcrepo.save(cart);
		
		System.out.println(cart);
		System.out.println(cart.getMcid());
		return cart.getMcid();
	}
	
	
	

}
