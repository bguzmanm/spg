package tech.bgm.spg.persistence;

import org.springframework.stereotype.Repository;
import tech.bgm.spg.domain.Product;
import tech.bgm.spg.domain.Purchase;
import tech.bgm.spg.domain.repository.ProductRepository;
import tech.bgm.spg.domain.repository.PurchaseRepository;
import tech.bgm.spg.persistence.crud.CompraCrudRepository;
import tech.bgm.spg.persistence.crud.ProductoCrudRepository;
import tech.bgm.spg.persistence.entity.Compra;
import tech.bgm.spg.persistence.entity.Producto;
import tech.bgm.spg.persistence.mapper.ProductMapper;
import tech.bgm.spg.persistence.mapper.PurchaseMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepository crudRepository;
    private ProductMapper mapper;

    public ProductoRepository(ProductoCrudRepository crudRepository, ProductMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) crudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos =
                crudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos =
                crudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return crudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(crudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        crudRepository.deleteById(productId);
    }

}
