package com.example.seminarskaemt.Controller.RestController;


import com.example.seminarskaemt.Entity.Brand;
import com.example.seminarskaemt.Service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandRestController {

    private final BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping
    public List<Brand> findAll() {
        return this.brandService.findAll();
    }


    @GetMapping("/{id}")
    public Brand findById(@PathVariable Long id) {
        return this.brandService.findById(id);
    }

    @PostMapping
    public Brand save(@Valid Brand brand) {
        return this.brandService.save(brand);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Long id, @Valid Brand brand) {
        return this.brandService.update(id, brand);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.brandService.deleteById(id);
    }

}
