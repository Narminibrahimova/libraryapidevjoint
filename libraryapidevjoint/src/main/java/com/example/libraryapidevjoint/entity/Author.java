package com.example.libraryapidevjoint.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authors")
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String fullName;
    String email;
    String bio;
    @Builder.Default
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Book> books=new ArrayList<>();

}
