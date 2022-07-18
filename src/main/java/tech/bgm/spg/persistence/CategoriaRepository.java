package tech.bgm.spg.persistence;

import org.springframework.stereotype.Repository;
import tech.bgm.spg.domain.Category;
import tech.bgm.spg.persistence.crud.CategoriaCrudRepository;
import tech.bgm.spg.persistence.entity.Categoria;
import tech.bgm.spg.persistence.mapper.CategoryMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements tech.bgm.spg.domain.repository.CategoryRepository {

    private CategoriaCrudRepository crudRepository;
    private CategoryMapper mapper;

    public CategoriaRepository(CategoriaCrudRepository crudRepository, CategoryMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Category> getAll() {
        List<Categoria> categorias = (List<Categoria>) crudRepository.findAll();
        return mapper.toCategorys(categorias);
    }

    @Override
    public Optional<Category> getCategory(int categoryId) {
        return crudRepository.findById(categoryId).map(categoria -> mapper.toCategory(categoria));
    }

    @Override
    public Category save(Category category) {
        Categoria categoria = mapper.toCategoria(category);
        return mapper.toCategory(crudRepository.save(categoria));
    }

    @Override
    public void delete(int categorytId) {
        crudRepository.deleteById(categorytId);
    }
}
