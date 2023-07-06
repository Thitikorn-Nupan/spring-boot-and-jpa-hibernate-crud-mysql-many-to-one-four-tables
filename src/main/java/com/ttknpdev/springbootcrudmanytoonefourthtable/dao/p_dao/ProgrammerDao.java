package com.ttknpdev.springbootcrudmanytoonefourthtable.dao.p_dao;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import com.ttknpdev.springbootcrudmanytoonefourthtable.log.Logging;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.p_repo.ProgrammerRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgrammerDao extends Logging implements ProgrammerService<Programmer> {

    private ProgrammerRepository repository;
    @Autowired
    public ProgrammerDao(ProgrammerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Programmer create(Programmer obj) {
        return repository.save(obj);
    }

    @Override
    public List<Programmer> reads() {
        List<Programmer> programmers = new ArrayList<>();
        repository.findAll().forEach(programmers::add);
        if (programmers.size() == 0) {
            programmerDao.warn("nothing there in your table header_programmers");
            throw new RuntimeException();
        }
        return programmers;
    }

    @Override
    public Programmer read(Long id) {
        return repository.findById(id).orElseThrow(()->{
            programmerDao.warn("there is no programmer id "+id);
            throw new RuntimeException();
        });
    }

    @Override
    public Programmer update(Programmer obj, Long id) {
        return repository.findById(id)
                .map(programmer -> {
                    programmer.setFullname(obj.getFullname());
                    programmer.setLevel(obj.getLevel());
                    programmer.setSalary(obj.getSalary());
                    return repository.save(programmer);
                })
                .orElseThrow(()->{
                    saleDao.warn("there is no programmer id "+id+" in update() method");
                    throw new RuntimeException();
                });
    }

    @Override
    public Map<String, Programmer> delete(Long id) {
        Map<String, Programmer> response= new HashMap<>();
        return repository.findById(id).map(programmer -> {
            repository.delete(programmer);
            response.put("deleted",programmer);
            return response;
        }).orElseThrow(()->{
            programmerDao.warn("there is no programmer id "+id+" in delete() method");
            marketingDao.warn("Or:::: field primary key p_id can be parent key of projects table");
            throw new RuntimeException();
        });
    }
}
