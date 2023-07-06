package com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.p_repo;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import org.springframework.data.repository.CrudRepository;

public interface ProgrammerRepository extends CrudRepository<Programmer,Long> { }
