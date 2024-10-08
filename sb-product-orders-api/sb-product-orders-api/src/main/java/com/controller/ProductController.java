package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Product;
import com.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("products")	// http://localhost:9191/products/*
public class ProductController {

	@Autowired
	ProductService productService;

	//curl -X GET http://localhost:10992/products/zero/1
	
	@GetMapping(value = "zero/{num}")
	public String zeroDivide(@PathVariable("num") int num) {
		//try {
		int res = 100/num;
		return "Value of res is "+res;
		//}catch(Exception e) {
		//	return "Exception generated "+e.toString();
		//}
	}
	
	// create one method which responsible handle the exception 
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<String> handleGenericException(ArithmeticException e){
		return ResponseEntity.ok("Local Message Handler "+e.toString());
	}
	
//curl -X GET http://localhost:9191/products/findAll
	@GetMapping(value = "findAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> findAll() {
		return productService.findAll();
	}
// curl -X POST http://localhost:10992/products/store -H "Content-Type:application/json" -d '{"pid":100,"pname":"","price":56000,"qty":10}'
	
	@PostMapping(value = "store",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String storeProduct(@Valid @RequestBody Product product) {
		return productService.storeProduct(product);
	}
	// curl -X PUT http://localhost:9191/products/update -H "Content-Type:application/json" -d '{"pid":100,"price":59000,"qty":15}'	
	@PutMapping(value = "update",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateProduct(@RequestBody Product product) {
		return productService.updateProductDetails(product);
	}
 // curl -X DELETE http://localhost:9191/products/delete/1	
	@DeleteMapping(value = "delete/{pid}")
	public String updateProduct(@PathVariable("pid") int pid) {
		return productService.deletedProduct(pid);
	}
	 // curl -X GET http://localhost:10992/products/findbyid/101		
	@GetMapping(value = "findbyid/{pid}")
	public String findProducById(@PathVariable("pid") int pid) {
		return productService.findProduct(pid);
	}
	
	
	 // curl -X GET http://localhost:9191/products/findbyprice/45000		
		@GetMapping(value = "findbyprice/{price}",produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Product> findProducByprice(@PathVariable("price") float price) {
			return productService.findProductByPrice(price);
		}
	
		// curl -X PUT http://localhost:9191/products/update_qty -H "Content-Type:application/json" -d '{"price":45000,"qty":55}'	
		@PutMapping(value = "update_qty",consumes = MediaType.APPLICATION_JSON_VALUE)
		public String updateProductWithCustomQuery(@RequestBody Product product) {
			return productService.updateProductDetailsWithCustomQuery(product);
		}
}








