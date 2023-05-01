package com.stc.filesystem.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stc.filesystem.dto.ItemInRsDTO;
import com.stc.filesystem.entity.Item;
import com.stc.filesystem.exception.ItemNotFoundException;
import com.stc.filesystem.service.ItemServiceImpl;

import mapper.ItemMapper;


@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	ItemServiceImpl service;
	
	@PostMapping
	public ResponseEntity<Item> save(@RequestBody @Valid ItemInRsDTO itemInRsDto) {
		String user_email=itemInRsDto.getUser_email();
		Item item=ItemMapper.getItemDto(itemInRsDto);
		Item addedItem=service.save(item,user_email);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(addedItem.getId()).toUri();
		 return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/employees/id/{id}")
	public Item find(@PathVariable long id) {
		Item item = service.findById(id).orElse(null);
         //making bundle for exception Message
		if (item == null)
			throw new ItemNotFoundException("no employees found ");
			
		return item;
	}
}
