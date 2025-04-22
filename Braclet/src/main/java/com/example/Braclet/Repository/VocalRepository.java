package com.example.Braclet.Repository;
import com.example.Braclet.Entity.Vocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VocalRepository extends JpaRepository<Vocal, Long> {
}
