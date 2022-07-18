package tech.bgm.spg.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import tech.bgm.spg.persistence.entity.Categoria;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {
}
