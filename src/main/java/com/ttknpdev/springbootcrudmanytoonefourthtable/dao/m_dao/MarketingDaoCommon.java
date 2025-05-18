package com.ttknpdev.springbootcrudmanytoonefourthtable.dao.m_dao;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.log.Logging;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.m_repo.MarketingRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.MarketingService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketingDaoCommon extends Logging implements ServiceCommon<Marketing> {

    private final MarketingRepository repository;

    @Autowired
    public MarketingDaoCommon(MarketingRepository repository) {
        this.repository = repository;
    }


    @Override
    public Marketing create(Marketing obj) {
        return repository.save(obj);
    }

    @Override
    public <U1, U2, U3> Marketing create(U1 key1, U2 key2, U3 key3, Marketing obj) {
        return null;
    }

    @Override
    public List<Marketing> reads() {
        List<Marketing> marketings = (List<Marketing>) repository.findAll();
        if (marketings.isEmpty()) {
            throw new RuntimeException("nothing there in your table header_marketings");
        }
        return marketings;
    }

    @Override
    public <U> Marketing read(U key) {
        Long id = Long.parseLong(key.toString());
        return repository.findById(id).orElseThrow(()->  new RuntimeException("there is no marketing id "+id));
    }

    @Override
    public <U> Marketing update(Marketing obj, U key) {
        Long id = Long.parseLong(key.toString());
        return repository.findById(id)
                .map(marketing -> {
                    marketing.setFullname(obj.getFullname());
                    marketing.setLevel(obj.getLevel());
                    marketing.setSalary(obj.getSalary());
                    return repository.save(marketing);
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public <U> Map<String, U> delete(U key) {
        Long id = Long.parseLong(key.toString());
        Map<String,Marketing> response= new HashMap<>();
        return (Map<String, U>) repository.findById(id).map(marketing -> {
            repository.delete(marketing);
            response.put("deleted",marketing);
            return response;
        }).orElseThrow(RuntimeException::new);
    }
}
