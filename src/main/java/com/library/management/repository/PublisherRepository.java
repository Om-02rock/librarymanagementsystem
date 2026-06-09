package com.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}

