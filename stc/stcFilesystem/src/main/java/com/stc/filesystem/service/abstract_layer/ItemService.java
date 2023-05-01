package com.stc.filesystem.service.abstract_layer;

import java.util.List;
import java.util.Optional;

import com.stc.filesystem.entity.Item;

public interface ItemService {

	public List<Item> findAll();

	public Optional<Item> findById(Long id);

	public Item save(Item item,String user_email);

}
