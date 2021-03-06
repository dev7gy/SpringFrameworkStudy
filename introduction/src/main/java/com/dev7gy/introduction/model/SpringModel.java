package com.dev7gy.introduction.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SpringModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long visitCount;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Long getVisitCount() {
        return visitCount;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }
    public void setName(String name) {
        this.name = name;
    }
}
