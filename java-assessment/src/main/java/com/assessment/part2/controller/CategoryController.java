package com.assessment.part2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.part2.model.Category;
import com.assessment.part2.service.CategoryTreeService;

@RestController
@RequestMapping("/api/transform")
public class CategoryController {

    private final CategoryTreeService categoryTreeService;

    @Autowired
    public CategoryController(CategoryTreeService categoryTreeService) {
        this.categoryTreeService = categoryTreeService;
    }

    @PostMapping("/tree")
    public List<Category> buildTree(@RequestBody List<Category> categories){
        return categoryTreeService.buildCategoryTree(categories);
    }

}
