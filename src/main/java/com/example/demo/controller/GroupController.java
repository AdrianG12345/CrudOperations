package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.GrupuriDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Grupuri;
import com.example.demo.repository.GrupRepository;

@RestController
public class GroupController {
	@Autowired
	private GrupRepository grupRepository;


	///handler for putting data in DB
	@PostMapping("/saveGroup")
	public Grupuri saveGroup(@RequestBody Grupuri grup)
	{
		grupRepository.save(grup);
		return grup;
	}
	//handler for getting all data from DB
	@GetMapping("/getAllGroup")
	public List<Grupuri> getAllGroup(){
		return grupRepository.findAll();
	}
	
	@DeleteMapping("/deleteGroup/{id}")
	public String deleteGroup(@PathVariable int id)
	{
		Grupuri foundById = grupRepository.findById(id).get();
		if (foundById != null)
		{///daca exista
			grupRepository.delete(foundById);
		}
		return "Deleted with success.";
	}
	///handler for update object
	@PutMapping("/updateGroup")
	public Grupuri updateGroup(@RequestBody GrupuriDTO grupDTO)
	{
		int id = grupDTO.getGroup_id();
		Grupuri foundById = grupRepository.findById(id).get();
		Grupuri grup = new Grupuri(grupDTO.getGroup_id(), grupDTO.getGroupNr());
		if (foundById != null)
			grupRepository.save(grup);

		return grup;
	}
	///handler for fetch a single row of data
	@GetMapping("/getGroup/{id}")
	public Grupuri getGroup(@PathVariable int id)
	{
		Grupuri foundById = grupRepository.findById(id).get();
		return foundById;
	}
	
	
}
