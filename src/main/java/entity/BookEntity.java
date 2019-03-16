package entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BOOK")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String name;
    private int year;
    private int pages;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
}


