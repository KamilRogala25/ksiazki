package com.example.ksiazki.controller;


import com.example.ksiazki.model.Book;
import com.example.ksiazki.model.Category;
import com.example.ksiazki.repository.BookRepository;
import com.example.ksiazki.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("categories") // ADRES URL
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @PostMapping("categories")
    public Category createCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id,
                                           @RequestParam String name) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            if (name != null) {
                category.setName(name);
            }

            return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            categoryRepository.delete(categoryOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("categories/{id}/books")
    public Iterable<Book> getBooksFromCategory(@PathVariable Integer id){
        Category category = categoryRepository.findById(id).get();
        return category.getBooks();
    }

}
