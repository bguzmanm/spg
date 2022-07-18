package tech.bgm.spg.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import tech.bgm.spg.persistence.entity.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
