package br.com.codigojava.library.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {

	@Id
    @GeneratedValue
    private Long id;
 
    @NotBlank
    private String title;
 
    @NotBlank
    private String description;
 
    @Min(50)
    private Integer numOfPages;
 
    @Valid
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Author> authors = new HashSet<>();
 
    public void add(Author author) {
        authors.add(author);
    }
}
