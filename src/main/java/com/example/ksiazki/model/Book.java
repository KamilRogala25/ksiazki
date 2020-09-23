package com.example.ksiazki.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
//nie uzywamy polecen Column i nie nadajemy nazwy, bo nie mamy juz gotowej bazy,
//wiec SQL moze sobie nadawac nazwy dla kolumn takie jak bedzoe chcial na podstawie kodu
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonIgnore
    private String isbn;

    @ManyToOne
    @JsonProperty("category")
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Category category;

}
