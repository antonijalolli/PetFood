package com.example.seminarskaemt.Service;

import com.example.seminarskaemt.Entity.ShoppingCart;


public interface ShoppingCartService {
    ShoppingCart getShoppingCartForUserId(Long userId);
    ShoppingCart createNewShoppingCart(Long userId);
    ShoppingCart addPetFoodToShoppingCart(Long shoppingCartId, Long petFoodId);
    void removePetFoodFromShoppingCart(Long shoppingCartId, Long petFoodId);
}
