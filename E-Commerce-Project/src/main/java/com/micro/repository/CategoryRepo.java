package com.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.model.Category;

public interface CategoryRepo  extends JpaRepository<Category, Integer>{

}
