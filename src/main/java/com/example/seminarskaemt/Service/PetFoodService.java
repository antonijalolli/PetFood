package com.example.seminarskaemt.Service;

import com.example.seminarskaemt.Entity.PetFood;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PetFoodService {

    List<PetFood> findAllByCategoryId(Long id);
    List<PetFood> findAll();
    PetFood findById(Long id);
    PetFood save(PetFood petFood, MultipartFile image) throws IOException;
    PetFood update(Long id, PetFood petFood, MultipartFile image) throws IOException;
    void deleteById(Long id);
}