package org.homework.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Files")
@NoArgsConstructor
@Setter
@Getter
public class StorageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String filename;
    String location;
    int hash;

    public StorageFile(String filename, String location, int hash) {
        this.filename = filename;
        this.location = location;
        this.hash = hash;
    }

}
