package com.ttknpdev.springbootcrudmanytoonefourthtable.dao.m_dao;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.log.Logging;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.m_repo.MarketingRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.MarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketingDao extends Logging implements MarketingService<Marketing> {

    private MarketingRepository repository;
    @Autowired
    public MarketingDao(MarketingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Marketing create(Marketing obj) {
        return repository.save(obj);
    }

    @Override
    public List<Marketing> reads() {
        List<Marketing> marketings = new ArrayList<>();
        repository.findAll().forEach(marketings::add);
        if (marketings.size() == 0) {
            marketingDao.warn("nothing there in your table header_marketings");
            throw new RuntimeException();
        }
        return marketings;
    }

    @Override
    public Marketing read(Long id) {
        return repository.findById(id).orElseThrow(()->{
            marketingDao.warn("there is no marketing id "+id);
            throw new RuntimeException();
        });
    }

    @Override
    public Marketing update(Marketing obj, Long id) {
        return repository.findById(id)
                .map(marketing -> {
                    marketing.setFullname(obj.getFullname());
                    marketing.setLevel(obj.getLevel());
                    marketing.setSalary(obj.getSalary());
                    return repository.save(marketing);
                })
                .orElseThrow(()->{
                    marketingDao.warn("there is no marketing id "+id+" in update() method");
                    throw new RuntimeException();
                });
    }

    @Override
    public Map<String, Marketing> delete(Long id) {
        Map<String,Marketing> response= new HashMap<>();
        return repository.findById(id).map(marketing -> {
            repository.delete(marketing);
            response.put("deleted",marketing);
            return response;
        }).orElseThrow(()->{
            marketingDao.warn("there is no marketing id "+id+" in delete() method");
            marketingDao.warn("Or:::: field primary key m_id can be parent key of projects table");
            throw new RuntimeException();
        });
    }
}
