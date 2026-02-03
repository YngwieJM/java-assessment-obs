package com.assessment.part1.problem1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryTree {
    public List<Category> buildCategorysTree(List<Category> categories) {
        if(categories == null){
            throw new IllegalArgumentException("Input category list cannot be null");
        }


        Map<Integer, Category> categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getId(), category);
        }

        List <Category> roots = new ArrayList<>();

        for (Category category : categories){
            Integer parentId = category.getParentId();

            if (parentId == null) {
                roots.add(category);
            }else{
                Category parent = categoryMap.get(parentId);
                if (parent == null){
                    throw new IllegalArgumentException(
                        "Parent category not found for category id: " + category.getId()
                    );
                    }
                    parent.getChildren().add(category);
                }
            }

            Map<Integer, Integer> visitState = new HashMap<>();
            for (Category category : categories){
                visitState.put(category.getId(), 0);
            }

            for (Category root: roots){
                if(visitState.get(root.getId()) == 0){
                    detectCycle(root, visitState);
                }
            }

        return roots;
            
        }

        private void detectCycle(Category category, Map<Integer, Integer> visitState) {
        visitState.put(category.getId(), 1); // visiting

        for(Category child : category.getChildren()){
            Integer state = visitState.get(child.getId());

            if(state == 1){
                throw new IllegalStateException(
                    "Circular dependency detected involving category id: " + child.getId()
                );
            }if(state == 0){
                detectCycle(child, visitState);
            }
        }

        visitState.put(category.getId(), 2); // visited
    }
}