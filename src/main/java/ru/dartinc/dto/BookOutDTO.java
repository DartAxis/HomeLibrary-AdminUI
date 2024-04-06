package ru.dartinc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookOutDTO {
    private String title;
    private String originalTitle;
    private String yearEdition;
    private String yearOfEditionTranslate;
    private String isbnOriginal;
    private String isbnTranslate;
    private String fileFormatBook;
    private String genre;
    private String pubHouse;
    private String pubHouseTranslate;
    private Set<String> tags;
    private Set<String> authors;
    private String file;
    private String picture;
    private String seria;
}
