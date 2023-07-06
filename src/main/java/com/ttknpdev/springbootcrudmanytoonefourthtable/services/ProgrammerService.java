package com.ttknpdev.springbootcrudmanytoonefourthtable.services;

import java.util.List;
import java.util.Map;

public interface ProgrammerService <T>{
    T create(T obj);
    List<T> reads();
    T read(Long id);
    T update(T obj,Long id);
    Map<String,T> delete(Long id);
}
