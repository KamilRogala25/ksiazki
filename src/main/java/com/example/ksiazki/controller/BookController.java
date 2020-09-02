package com.example.ksiazki.controller;

import com.example.ksiazki.model.Book;
import com.example.ksiazki.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("books")
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    // dodajemy możliwość dodania książki
    @PostMapping("books")
    public Book createBook(@RequestParam String title, @RequestParam String author){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @PutMapping("books/{id}")
    public ResponseEntity updateBook(@PathVariable Integer id, @RequestParam String title, @RequestParam(required = false) String author){
        Optional bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = (Book) bookOptional.get();
            book.setTitle(title);
            book.setAuthor(author);
            return new ResponseEntity<>(bookRepository.save(book),HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("books/{id}")
    public Book getBook(@PathVariable Integer id){
        return bookRepository.findById(id).get();
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id){
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            bookRepository.delete(book.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
