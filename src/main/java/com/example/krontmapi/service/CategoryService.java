package com.example.krontmapi.service;

import com.example.krontmapi.entity.Category;
import com.example.krontmapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() throws Exception {
        var categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return categories;
    }

}
