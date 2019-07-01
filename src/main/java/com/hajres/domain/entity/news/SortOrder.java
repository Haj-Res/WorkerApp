package com.hajres.domain.entity.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sort_order")
public class SortOrder {

    @Id
    @Column
    @NotNull(message = "is required")
    @Size(min = 1, max = 50, message = "Must be between 1 and 50 character long")
    private String id;

    @Column
    @NotNull(message = "is required")
    @Size(min = 1, max = 100, message = "Must be between 1 and 100 character long")
    private String name;

    public SortOrder() {
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
        return "SortOrder{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
