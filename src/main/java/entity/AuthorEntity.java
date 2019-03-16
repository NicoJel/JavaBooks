package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "AUTHOR")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String firstName;
    private String lastName;
    private String nationality;
    @OneToMany(mappedBy="author")
    private List<BookEntity> books;

}

