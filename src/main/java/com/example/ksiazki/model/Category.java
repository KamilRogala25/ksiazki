package com.example.ksiazki.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

}
