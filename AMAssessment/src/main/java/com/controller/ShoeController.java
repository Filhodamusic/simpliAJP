
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

import com.entity.Shoe;
import com.repository.ShoeRepo;
import com.service.ShoeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("shoes")	// http://localhost:9191/shoes/*
public class ShoeController {

	@Autowired
	ShoeService shoeService;

	
//curl -X GET http://localhost:9191/shoes/findAll
	@GetMapping(value = "findAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Shoe> findAll() {
		return shoeService.findAll();
	}
// curl -X POST http://localhost:10992/shoes/store -H "Content-Type:application/json" -d '{"pid":100,"pname":"","price":56000,"qty":10}'
	
	@PostMapping(value = "store",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String storeShoe(@Valid @RequestBody Shoe shoe) {
		return shoeService.storeShoe(shoe);
	}
	// curl -X PUT http://localhost:9191/shoes/update -H "Content-Type:application/json" -d '{"pid":100,"price":59000,"qty":15}'	
	@PutMapping(value = "update",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateShoe(@RequestBody Shoe shoe) {
		return shoeService.updateShoeDetails(shoe);
	}
 // curl -X DELETE http://localhost:9191/shoes/delete/1	
	@DeleteMapping(value = "delete/{pid}")
	public String updateShoe(@PathVariable("pid") int pid) {
		return shoeService.deletedShoe(pid);
	}
	 // curl -X GET http://localhost:10992/shoes/findbyid/101		
	@GetMapping(value = "findbyid/{pid}")
	public String findProducById(@PathVariable("pid") int pid) {
		return shoeService.findShoe(pid);
	}
	
	
	 // curl -X GET http://localhost:9191/shoes/findbyprice/45000		
		@GetMapping(value = "findbyprice/{price}",produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Shoe> findProducByprice(@PathVariable("price") float price) {
			return shoeService.findShoeByPrice(price);
		}
	
//		// curl -X PUT http://localhost:9191/shoes/update_qty -H "Content-Type:application/json" -d '{"price":45000,"qty":55}'	
//		@PutMapping(value = "update_qty",consumes = MediaType.APPLICATION_JSON_VALUE)
//		public String updateShoeWithCustomQuery(@RequestBody Shoe shoe) {
//			return shoeService.updateShoeDetailsWithCustomQuery(shoe);
//		}
}








