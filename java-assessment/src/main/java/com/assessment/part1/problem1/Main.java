package com.assessment.part1.problem1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Prepare input (INTENTIONALLY random order)
        List<Category> categories = new ArrayList<>();

        categories.add(new Category(4, "MacBook", 2));
        categories.add(new Category(1, "Electronics", null));
        categories.add(new Category(3, "PC", 1));
        categories.add(new Category(2, "Laptops", 1));
        categories.add(new Category(5, "Fashion", null));


        // Build tree
        CategoryTree treeBuilder = new CategoryTree();
        List<Category> roots = treeBuilder.buildCategorysTree(categories);

        // Print result
        for (Category root : roots) {
            printCategory(root, 0);
        }
    }

    private static void printCategory(Category category, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + category.getName());

        for (Category child : category.getChildren()) {
            printCategory(child, level + 1);
        }
    }
}