package com.assessment.part1.problem1;

import java.util.ArrayList;
import java.util.List;

public class Category{
    Integer id;
    String name;
    Integer parentId;
    List<Category> children; 

    public Category(){
        this.children = new ArrayList<>();
    }

    public Category(Integer id, String name, Integer parentId){
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

   public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
    
}
