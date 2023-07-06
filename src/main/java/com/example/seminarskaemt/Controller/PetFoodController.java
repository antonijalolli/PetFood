package com.example.seminarskaemt.Controller;

import com.example.seminarskaemt.Entity.PetFood;
import com.example.seminarskaemt.Service.BrandService;
import com.example.seminarskaemt.Service.CategoryService;
import com.example.seminarskaemt.Service.PetFoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/petFoods")
public class PetFoodController {

    private final PetFoodService petFoodService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public PetFoodController(PetFoodService petFoodService, CategoryService categoryService, BrandService brandService) {
        this.petFoodService = petFoodService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @GetMapping
    public String listPetFoods(@RequestParam(required = false) String error, Model model) {
        var petFood = this.petFoodService.findAll();
        model.addAttribute("petFood", petFood);
        if(error != null) {
            model.addAttribute("error", "The pet food is already in shopping cart!");
        }
        return "petFoods";
    }

    @PostMapping
    public String saveOrUpdatePetFood(Model model, @Valid PetFood petFood, BindingResult bindingResult, MultipartFile image) throws IOException {
        if (petFood.getId() == null) {
            PetFood newPetFood = this.petFoodService.save(petFood, image);
        } else {
            this.petFoodService.update(petFood.getId(), petFood, image);
        }
        return "redirect:/petFoods";
    }

    @GetMapping("/add-new")
    public String addNewPetFood(Model model) {
        var categories = this.categoryService.findAll();
        var brands = this.brandService.findAll();
        model.addAttribute("petFood", new PetFood());
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        return "new-petFood";
    }

    @GetMapping("/{id}/edit")
    public String editPetFood(@PathVariable Long id, Model model) {
        try {
            var petFood = this.petFoodService.findById(id);
            var categories = this.categoryService.findAll();
            var brands = this.brandService.findAll();
            model.addAttribute("petFood", petFood);
            model.addAttribute("categories", categories);
            model.addAttribute("brands", brands);

            return "new-petFood";
        } catch (RuntimeException ex) {
            return "redirect:/petFoods?error=" + ex.getLocalizedMessage();
        }
    }

    @PostMapping(value = "/{id}/delete")
    public String deletePetFoodWithPost(@PathVariable Long id) {
        petFoodService.deleteById(id);
        return "redirect:/petFoods";
    }
}
