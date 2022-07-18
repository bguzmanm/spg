package tech.bgm.spg.persistence;

import org.springframework.stereotype.Repository;
import tech.bgm.spg.domain.Purchase;
import tech.bgm.spg.persistence.crud.CompraCrudRepository;
import tech.bgm.spg.persistence.entity.Compra;
import tech.bgm.spg.persistence.mapper.PurchaseMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements tech.bgm.spg.domain.repository.PurchaseRepository {

    private CompraCrudRepository crudRepository;
    private PurchaseMapper mapper;

    public CompraRepository(CompraCrudRepository crudRepository, PurchaseMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) crudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return crudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(crudRepository.save(compra));
    }
}
