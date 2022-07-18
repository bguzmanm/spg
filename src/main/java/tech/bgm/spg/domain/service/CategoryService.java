package tech.bgm.spg.domain.service;

import org.springframework.stereotype.Service;
import tech.bgm.spg.domain.Category;
import tech.bgm.spg.domain.repository.CategoryRepository;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll(){
        return repository.getAll();
    }

    public Optional<Category> getCategory(int categoryId){
        return repository.getCategory(categoryId);
    }

    public Category save(Category category){
        return repository.save(category);
    }

    public boolean delete(int categoryId){
        return getCategory(categoryId).map(category -> {
            repository.delete(categoryId);
            return true;
        }).orElse(false);
    }
}
