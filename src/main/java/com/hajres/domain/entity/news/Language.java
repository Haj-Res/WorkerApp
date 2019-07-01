package com.hajres.domain.entity.news;

import org.hibernate.annotations.SQLInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @Column
    @NotNull(message = "is required")
    @Size(max = 2, message = "Can't be longer than 2 character long")
    private String id;

    @Column
    @NotNull
    @Size(min = 1, max = 100, message = "Must be between 1 and 100 character long")
    private String name;

    public Language() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
