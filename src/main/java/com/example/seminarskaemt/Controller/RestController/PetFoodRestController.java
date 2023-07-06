package com.example.seminarskaemt.Controller.RestController;

import com.example.seminarskaemt.Entity.PetFood;
import com.example.seminarskaemt.Service.PetFoodService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/petFoods")
public class PetFoodRestController {

    private final PetFoodService petFoodService;

    public PetFoodRestController(PetFoodService petFoodService) {
        this.petFoodService = petFoodService;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<PetFood> findAll() {
        return this.petFoodService.findAll();
    }

    @GetMapping("/{id}")
    public PetFood findById(@PathVariable Long id) {
        return this.petFoodService.findById(id);
    }

    @GetMapping("/by-category/{id}")
    public List<PetFood> findAllByCategoryId(@PathVariable Long id) {
        return this.petFoodService.findAllByCategoryId(id);
    }


    @PostMapping
    public PetFood save(@Valid PetFood petFood, @RequestParam(required = false) MultipartFile image) throws IOException {
        return this.petFoodService.save(petFood, image);
    }


    @PutMapping("/{id}")
    public PetFood update(@PathVariable Long id,
                       @Valid PetFood petFood,
                       @RequestParam(required = false) MultipartFile image) throws IOException {
        return this.petFoodService.update(id, petFood, image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.petFoodService.deleteById(id);
    }

}