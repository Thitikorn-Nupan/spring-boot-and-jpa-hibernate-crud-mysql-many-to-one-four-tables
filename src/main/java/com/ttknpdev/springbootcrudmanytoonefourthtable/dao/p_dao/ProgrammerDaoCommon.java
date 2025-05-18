package com.ttknpdev.springbootcrudmanytoonefourthtable.dao.p_dao;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import com.ttknpdev.springbootcrudmanytoonefourthtable.log.Logging;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.p_repo.ProgrammerRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.ProgrammerService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgrammerDaoCommon extends Logging implements ServiceCommon<Programmer> {

    private final ProgrammerRepository repository;
    @Autowired
    public ProgrammerDaoCommon(ProgrammerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Programmer create(Programmer obj) {
        return repository.save(obj);
    }

    @Override
    public <U1, U2, U3> Programmer create(U1 key1, U2 key2, U3 key3, Programmer obj) {
        return null;
    }

    @Override
    public List<Programmer> reads() {
        List<Programmer> programmers = new ArrayList<>();
        repository.findAll().forEach(programmers::add);
        if (programmers.isEmpty()) {
            programmerDao.warn("nothing there in your table header_programmers");
            throw new RuntimeException();
        }
        return programmers;
    }

    @Override
    public <U> Programmer read(U key) {
        Long id = Long.parseLong(key.toString());
        return repository.findById(id).orElseThrow(()->{
            programmerDao.warn("there is no programmer id "+id);
            throw new RuntimeException();
        });
    }

    @Override
    public <U> Programmer update(Programmer obj, U key) {
        Long id = Long.parseLong(key.toString());
        return repository.findById(id)
                .map(programmer -> {
                    programmer.setFullname(obj.getFullname());
                    programmer.setLevel(obj.getLevel());
                    programmer.setSalary(obj.getSalary());
                    return repository.save(programmer);
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public <U> Map<String, U> delete(U key) {
        Long id = Long.parseLong(key.toString());
        Map<String, Programmer> response= new HashMap<>();
        return (Map<String, U>) repository.findById(id).map(programmer -> {
            repository.delete(programmer);
            response.put("deleted",programmer);
            return response;
        }).orElseThrow(RuntimeException::new);
    }


}
