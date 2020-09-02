package com.example.ksiazki.controller;

import com.example.ksiazki.model.Book;
import com.example.ksiazki.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/*
    To nasze api dostępne dla innych
 */
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {
    private BookRepository bookRepository;

    @Autowired /* nie wymagane */
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /*
        ŻĄDANIE HTTP = ARES URL + METODA HTTP
     */
    @GetMapping("books") // ADRES URL
    public Iterable<Book> findAll() {
        return bookRepository.findAll(); //
        // REPOSITORY, KTÓRE WYŚWIETLA NAM WSZYSTKIE KSIĄŻKI
    }    /*
        @RequestParam do parametrów
        /books?title=ksiazka&author=Super%Author
     */

    @PostMapping("books")
    public Book createBook(@RequestParam String title,
                           @RequestParam String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    // @PathVariable sprawia, że w ścieżce mamy /books/{id}
// required false daje opcje niepodawania parametru
    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id,
                                           @RequestParam String title,
                                           @RequestParam(required = false) String author) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (title != null) {
                book.setTitle(title);
            }
            if (author != null) {
                book.setAuthor(author);
            }
            return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}