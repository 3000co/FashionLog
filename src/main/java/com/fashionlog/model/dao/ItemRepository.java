package com.fashionlog.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashionlog.model.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
