package com.example.seminarskaemt.Implementation;

import com.example.seminarskaemt.Entity.ShoppingCart;
import com.example.seminarskaemt.Exception.PetFoodIsAlreadyInShoppingCartException;
import com.example.seminarskaemt.Repository.ShoppingCartRepository;
import com.example.seminarskaemt.Service.PetFoodService;
import com.example.seminarskaemt.Service.ShoppingCartService;
import com.example.seminarskaemt.Service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {

    private final UserService userService;
    private final PetFoodService petFoodService;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImplementation(
            UserService userService,
            PetFoodService petFoodService,
            ShoppingCartRepository shoppingCartRepository) {

        this.userService = userService;
        this.petFoodService = petFoodService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart getShoppingCartForUserId(Long userId) {
        var shoppingCart = this.shoppingCartRepository.findByUserId(userId);
        if(shoppingCart == null) {
            return this.createNewShoppingCart(userId);
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart createNewShoppingCart(Long userId) {
        var user = this.userService.findById(userId);
        var shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart = this.shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    @Transactional
    public ShoppingCart addPetFoodToShoppingCart(Long shoppingCartId, Long petFoodId) {
        var shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();
        var petFood = this.petFoodService.findById(petFoodId);

        for (var p : shoppingCart.getPetFoods()) {
            if (p.getId().equals(petFoodId)) {
                throw new PetFoodIsAlreadyInShoppingCartException(petFood.getName());
            }
        }
        shoppingCart.getPetFoods().add(petFood);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public void removePetFoodFromShoppingCart(Long shoppingCartId, Long petFoodId) {
        var shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow();
        shoppingCart.setPetFoods(
                shoppingCart.getPetFoods()
                        .stream()
                        .filter(petFood -> !petFood.getId().equals(petFoodId))
                        .collect(Collectors.toList())
        );

        this.shoppingCartRepository.save(shoppingCart);
    }
}
