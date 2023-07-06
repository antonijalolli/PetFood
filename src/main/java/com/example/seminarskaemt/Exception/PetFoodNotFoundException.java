package com.example.seminarskaemt.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PetFoodNotFoundException extends RuntimeException {
    public PetFoodNotFoundException(Long id)
    {
        super(String.format("Pet food with id %d is not found!", id));
    }
}
