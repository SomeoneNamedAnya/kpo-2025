package org.homework.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "analysis")
public class FileAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int idFile;
    int words;
    int paragraphs;
    int letters;
    String location;

    public FileAnalysis(int idFile, int paragraphs, int words, int letters, String location) {
        this.idFile = idFile;
        this.words = words;
        this.letters = letters;
        this.paragraphs = paragraphs;
        this.location = location;
    }
}
