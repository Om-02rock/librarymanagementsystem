package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

