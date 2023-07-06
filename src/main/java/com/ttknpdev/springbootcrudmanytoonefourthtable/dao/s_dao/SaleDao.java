package com.ttknpdev.springbootcrudmanytoonefourthtable.dao.s_dao;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.s_entity.Sale;
import com.ttknpdev.springbootcrudmanytoonefourthtable.log.Logging;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.s_repo.SaleRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleDao extends Logging implements SaleService<Sale> {

    private SaleRepository repository;
    @Autowired
    public SaleDao(SaleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sale create(Sale obj) {
        return repository.save(obj);
    }

    @Override
    public List<Sale> reads() {
        List<Sale> sales = new ArrayList<>();
        repository.findAll().forEach(sales::add);
        if (sales.size() == 0) {
            saleDao.warn("nothing there in your table header_sales");
            throw new RuntimeException();
        }
        return sales;
    }

    @Override
    public Sale read(Long id) {
        return repository.findById(id).orElseThrow(()->{
            saleDao.warn("there is no sale id "+id+" in read() method");
            throw new RuntimeException();
        });
    }

    @Override
    public Sale update(Sale obj, Long id) {
        return repository.findById(id)
                .map(sale -> {
                    sale.setFullname(obj.getFullname());
                    sale.setLevel(obj.getLevel());
                    sale.setSalary(obj.getSalary());
                    return repository.save(sale);
                })
                .orElseThrow(()->{
                    saleDao.warn("there is no sale id "+id+" in update() method");
                    throw new RuntimeException();
                });
    }

    @Override
    public Map<String, Sale> delete(Long id) {
        Map<String, Sale> response= new HashMap<>();
        return repository.findById(id).map(sale -> {
            repository.delete(sale);
            response.put("deleted",sale);
            return response;
        }).orElseThrow(()->{
            saleDao.warn("there is no sale id "+id+" in delete() method");
            marketingDao.warn("Or:::: field primary key s_id can be parent key of projects table");
            throw new RuntimeException();
        });
    }
}
