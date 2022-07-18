package tech.bgm.spg.domain.service;

import org.springframework.stereotype.Service;
import tech.bgm.spg.domain.Purchase;
import tech.bgm.spg.domain.repository.PurchaseRepository;


import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private PurchaseRepository repository;

    public PurchaseService(PurchaseRepository repository) {
        this.repository = repository;
    }

    public List<Purchase> getAll(){
        return repository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId){
        return repository.getByClient(clientId);
    }
    public Purchase save(Purchase purchase){
        return repository.save(purchase);
    }
}
