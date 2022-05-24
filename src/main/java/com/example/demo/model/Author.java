package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @JsonIgnore
    @OneToMany(mappedBy="author")
    private Set<Book> books;

    private String name;

}
