package com.example.seminarskaemt.Implementation;

import com.example.seminarskaemt.Entity.Category;
import com.example.seminarskaemt.Exception.CategoryNotFoundException;
import com.example.seminarskaemt.Repository.CategoryRepository;
import com.example.seminarskaemt.Service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new
                CategoryNotFoundException(id));
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category updatedCategory = this.findById(id);
        updatedCategory.setName(category.getName());
        updatedCategory.setDescription(category.getDescription());
        return this.categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteById(Long id) {
        Category category = this.findById(id);
        this.categoryRepository.removeById(id);
    }
}
