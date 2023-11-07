package com.example.bookstore.model.entity;

import com.example.bookstore.model.entity.enums.LiteraturePeriods;
import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity{
    private String name;
    private LiteraturePeriods eraOfWriting;

    public Author() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public LiteraturePeriods getEraOfWriting() {
        return eraOfWriting;
    }

    public void setEraOfWriting(LiteraturePeriods eraOfWriting) {
        this.eraOfWriting = eraOfWriting;
    }
}
