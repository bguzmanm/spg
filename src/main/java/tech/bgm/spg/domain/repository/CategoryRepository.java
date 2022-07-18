package tech.bgm.spg.domain.repository;

import tech.bgm.spg.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> getAll();
    Optional<Category> getCategory(int categoryId);
    Category save(Category category);
    void delete(int categorytId);
}
