package com.example.commons_exams.exams.models.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    private String name;


    /*
        Many subjects can have only one parent
        One parent can have many children
        
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"children"}, allowSetters = true)
    private Subject parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy ="parent", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"parent"}, allowSetters = true)
    private Set<Subject> children;

    public Subject() {
        this.children = new HashSet<>();
    }

    public Long getSubjectId() {
        return subjectId;
    }
   
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getParent() {
        return parent;
    }

    public void setParent(Subject parent) {
        this.parent = parent;
    }

    public Set<Subject> getChildren() {
        return children;
    }

    public void setChildren(Set<Subject> children) {
        this.children = children;
    }

    public void addChild(Subject child) {
        this.children.add(child);
        child.setParent(this);
    }

    public void removeChild(Subject child) {
        this.children.remove(child);
        child.setParent(null);
    }
}