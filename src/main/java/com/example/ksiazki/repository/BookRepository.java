package com.example.ksiazki.repository;

import com.example.ksiazki.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
