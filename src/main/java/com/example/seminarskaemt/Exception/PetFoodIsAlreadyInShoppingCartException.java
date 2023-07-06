package com.example.seminarskaemt.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class PetFoodIsAlreadyInShoppingCartException extends RuntimeException {
    public PetFoodIsAlreadyInShoppingCartException(String name) {
        super(String.format("Pet food %s is already in active shopping cart", name));
    }
}
