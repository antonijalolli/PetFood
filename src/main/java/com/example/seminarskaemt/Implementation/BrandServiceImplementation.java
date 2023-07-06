package com.example.seminarskaemt.Implementation;

import com.example.seminarskaemt.Entity.Brand;
import com.example.seminarskaemt.Entity.Category;
import com.example.seminarskaemt.Exception.BrandNotFoundException;
import com.example.seminarskaemt.Repository.BrandRepository;
import com.example.seminarskaemt.Service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImplementation implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImplementation(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public Brand findById(Long id) {
        return this.brandRepository.findById(id).orElseThrow(() -> new
                BrandNotFoundException(id));
    }

    @Override
    public Brand save(Brand brand) {
        return this.brandRepository.save(brand);
    }

    @Override
    public Brand update(Long id, Brand brand) {
        Brand updatedBrand = this.findById(id);
        updatedBrand.setName(brand.getName());
        updatedBrand.setSpecification(brand.getSpecification());
        return this.brandRepository.save(updatedBrand);
    }
    @Override
    public void deleteById(Long id) {
        Brand brand = this.findById(id);
        this.brandRepository.removeById(id);
    }
}
