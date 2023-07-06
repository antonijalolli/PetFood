package com.example.seminarskaemt.Repository;

import com.example.seminarskaemt.Entity.Enum.CartStatus;
import com.example.seminarskaemt.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUserId(Long userId);
}

