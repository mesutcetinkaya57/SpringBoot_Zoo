package com.cetinkaya.zoo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cetinkaya.zoo.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long>{

}
