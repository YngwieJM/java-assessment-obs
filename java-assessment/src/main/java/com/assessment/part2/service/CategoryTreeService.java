package com.assessment.part2.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.assessment.part2.model.Category;


@Service
public class CategoryTreeService {

    public List<Category> buildCategoryTree(List<Category> categories) {
        if (categories == null) {
            throw new IllegalArgumentException("Category list cannot be null");
        }

        for (Category category : categories) {
            category.getChildren().clear();
        }

        Map<Integer, Category> categoryMap = new HashMap<>();
        for (Category category : categories) {
            if(category.getId() == null){
                throw new IllegalArgumentException("Category ID cannot be null");
            }
            categoryMap.put(category.getId(), category);
        }

        List<Category> roots = new ArrayList<>();

        for (Category category : categories){
            Integer parentId = category.getParentId();

            if(parentId == null){
                roots.add(category);
            }else{
                Category parent = categoryMap.get(parentId);
                if (parent == null){
                    throw new IllegalArgumentException("Parent category with ID " + parentId + " not found for category ID " + category.getId());
                }
                parent.getChildren().add(category);
            }
        }

        Map <Integer, Integer> visitState = new HashMap<>();
        for(Category category : categories){
            visitState.put(category.getId(), 0);
        }

        for(Category category : categories){
            if(visitState.get(category.getId()) == 0){
                detectCycle(category, visitState);
            }
        }

        return roots;
    }

    private void detectCycle(Category category, Map<Integer, Integer> visitState){
        visitState.put(category.getId(), 1);

        for(Category child : category.getChildren()){
            Integer state = visitState.get(child.getId());

            if(state == 1){
                throw new IllegalArgumentException("Cycle detected involving category ID " + child.getId());
            }

            if(state == 0){
                detectCycle(child, visitState);
            }
        }
        visitState.put(category.getId(), 2);
    }
    

}
