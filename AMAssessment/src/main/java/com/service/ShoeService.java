
package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Shoe;
import com.repository.ShoeRepo;

@Service
public class ShoeService {

	@Autowired
	ShoeRepo shoeRepository;
	
	public String storeShoe(Shoe shoe) {
		if(shoeRepository.existsById(shoe.getPid())) {
			return "Shoe is must be unique";
		}else {
			shoeRepository.save(shoe);
			return "Shoe informaton stored successfully";
		}
	}
	
	public String updateShoeDetails(Shoe shoe) {
		Optional<Shoe> result = shoeRepository.findById(shoe.getPid());
		if(result.isPresent()) {
			Shoe s	= result.get();
			if(!shoe.getPbrand().isEmpty()) {
				s.setPbrand(shoe.getPbrand());
			}
			if(!shoe.getPname().isEmpty()) {
				s.setPname(shoe.getPname());
			}
			s.setPrice(shoe.getPrice());
			s.setQty(shoe.getQty());
			shoeRepository.saveAndFlush(s);
			return "Shoe details updated successfully";
		}else {
			return "Shoe not present";
		}
	}
	
	public String deletedShoe(int pid) {
		Optional<Shoe> result = shoeRepository.findById(pid);
		if(result.isPresent()) {
			shoeRepository.deleteById(pid);
		return "Shoe deleted successfully";
		}else {
			return "Shoe not present";
		}
	}
	
	public String findShoe(int pid) {
		Optional<Shoe> result = shoeRepository.findById(pid);
		if(result.isPresent()) {
		Shoe p	= result.get();
			return p.toString();
		}else {
			return "Shoe not present";
		}
	}
	public List<Shoe> findAll() {
		return shoeRepository.findAll();
	}
	
	public List<Shoe> findShoeByPrice(float price){
		return shoeRepository.findShoeByPrice(price);
	}
	
//	public String updateShoeDetailsWithCustomQuery(Shoe Shoe) {
//		if(shoeRepository.updateShoeQtyUsingPrice(Shoe.getQty(), Shoe.getPrice())>0) {
//			return "Shoe updated successfully";
//		}else {
//			return "Shoe didn't update";
//		}
//	}
}
