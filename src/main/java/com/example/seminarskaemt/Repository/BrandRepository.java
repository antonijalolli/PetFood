package com.example.seminarskaemt.Repository;

import com.example.seminarskaemt.Entity.Brand;
import com.example.seminarskaemt.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Transactional
    Integer removeById(Long id);
}
