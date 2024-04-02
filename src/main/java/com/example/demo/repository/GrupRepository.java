package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Grupuri;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupRepository extends JpaRepository<Grupuri, Integer> {

}
