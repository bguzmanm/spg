package tech.bgm.spg.domain.service;

import org.springframework.stereotype.Service;
import tech.bgm.spg.domain.Product;
import tech.bgm.spg.domain.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll(){
        return repository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return repository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int quantity){
        return repository.getByCategory(quantity);
    }

    public Product save(Product product){
        return repository.save(product);
    }

    public boolean delete(int productId){
        return getProduct(productId).map(product -> {
            repository.delete(productId);
            return true;
        }).orElse(false);

/*        if (getProduct(productId).isPresent()){
            repository.delete(productId);
            return true;
        } else {
            return false;
        }*/
    }
}
