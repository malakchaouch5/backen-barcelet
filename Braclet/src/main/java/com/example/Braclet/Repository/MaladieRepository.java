package com.example.Braclet.Repository;

import com.example.Braclet.Entity.Maladie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaladieRepository extends JpaRepository<Maladie, Long> {
}
