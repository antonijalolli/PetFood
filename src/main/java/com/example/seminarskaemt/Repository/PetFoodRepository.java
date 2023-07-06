package com.example.seminarskaemt.Repository;

import com.example.seminarskaemt.Entity.PetFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetFoodRepository extends JpaRepository<PetFood, Long> {
    boolean existsByCategoryId(Long id);
}
