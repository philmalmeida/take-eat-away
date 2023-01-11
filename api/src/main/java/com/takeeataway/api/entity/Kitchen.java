package com.takeeataway.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Kitchen {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String name;

    protected Kitchen() {}

    public Kitchen(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Kitchen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
