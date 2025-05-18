package com.ttknpdev.springbootcrudmanytoonefourthtable.dao.s_dao;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.s_entity.Sale;
import com.ttknpdev.springbootcrudmanytoonefourthtable.log.Logging;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.s_repo.SaleRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.SaleService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleDaoCommon extends Logging implements ServiceCommon<Sale> {

    private final SaleRepository repository;
    @Autowired
    public SaleDaoCommon(SaleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sale create(Sale obj) {
        return repository.save(obj);
    }

    @Override
    public <U1, U2, U3> Sale create(U1 key1, U2 key2, U3 key3, Sale obj) {
        return null;
    }

    @Override
    public List<Sale> reads() {
        List<Sale> sales = new ArrayList<>();
        repository.findAll().forEach(sales::add);
        if (sales.isEmpty()) {
            throw new RuntimeException("nothing there in your table header_sales");
        }
        return sales;
    }

    @Override
    public <U> Sale read(U key) {
        Long id = Long.parseLong(key.toString());
        return repository.findById(id).orElseThrow(()-> new RuntimeException("there is no sale id "+id+" in read() method"));
    }

    @Override
    public <U> Sale update(Sale obj, U key) {
        Long id = Long.parseLong(key.toString());
        return repository.findById(id)
                .map(sale -> {
                    sale.setFullname(obj.getFullname());
                    sale.setLevel(obj.getLevel());
                    sale.setSalary(obj.getSalary());
                    return repository.save(sale);
                })
                .orElseThrow(()-> new RuntimeException("there is no sale id "+id+" in update() method"));

    }

    @Override
    public <U> Map<String, U> delete(U key) {
        Long id = Long.parseLong(key.toString());
        Map<String, Sale> response= new HashMap<>();
        return (Map<String, U>) repository.findById(id).map(sale -> {
            repository.delete(sale);
            response.put("deleted",sale);
            return response;
        }).orElseThrow(()-> new RuntimeException("there is no sale id "+id+" in delete() method Or:::: field primary key s_id can be parent key of projects table"));

    }
}
