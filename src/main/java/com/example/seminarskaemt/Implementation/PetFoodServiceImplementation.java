package com.example.seminarskaemt.Implementation;

import com.example.seminarskaemt.Entity.Brand;
import com.example.seminarskaemt.Entity.Category;
import com.example.seminarskaemt.Entity.PetFood;
import com.example.seminarskaemt.Exception.PetFoodNotFoundException;
import com.example.seminarskaemt.Repository.PetFoodRepository;
import com.example.seminarskaemt.Service.BrandService;
import com.example.seminarskaemt.Service.CategoryService;
import com.example.seminarskaemt.Service.PetFoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Transactional
@Service
public class PetFoodServiceImplementation implements PetFoodService {

    private final PetFoodRepository petFoodRepository;

    private final CategoryService categoryService;

    private final BrandService brandService;


    public PetFoodServiceImplementation(PetFoodRepository petFoodRepository, CategoryService categoryService, BrandService brandService) {
        this.petFoodRepository = petFoodRepository;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }


    @Override
    public List<PetFood> findAllByCategoryId(Long id) {
        return null;
    }

    @Override
    public List<PetFood> findAll() {
        return petFoodRepository.findAll();
    }

    @Override
    public PetFood findById(Long id) {
        return petFoodRepository.findById(id).orElseThrow(() -> new PetFoodNotFoundException(id));
    }

    @Override
    public PetFood save(PetFood petFood, MultipartFile image) throws IOException {
        Category category = this.categoryService.findById(petFood.getCategory().getId());
        Brand brand = this.brandService.findById(petFood.getBrand().getId());
        petFood.setCategory(category);
        petFood.setBrand(brand);
        if (image != null) {
            byte[] imageBytes = image.getBytes();
            String test = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(imageBytes));
            petFood.setImage(test);
        }
        return this.petFoodRepository.save(petFood);
    }

    @Override
    public PetFood update(Long id, PetFood petFood, MultipartFile image) throws IOException {
        PetFood updatePetFood = this.findById(id);
        Category category = this.categoryService.findById(petFood.getCategory().getId());
        Brand brand = this.brandService.findById(petFood.getBrand().getId());
        updatePetFood.setCategory(category);
        updatePetFood.setBrand(brand);
        updatePetFood.setName(petFood.getName());
        updatePetFood.setEdition(petFood.getEdition());
        updatePetFood.setPrice(petFood.getPrice());

        if (image != null) {
            byte[] imageBytes = image.getBytes();
            String test = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(imageBytes));
            updatePetFood.setImage(test);
        }
        return this.petFoodRepository.save(updatePetFood);
    }


    @Override
    public void deleteById(Long id) {
        this.petFoodRepository.deleteById(id);
    }
}
