package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demospringboot.entity.Bus;
public interface BusRepository extends JpaRepository<Bus, Long> {}
