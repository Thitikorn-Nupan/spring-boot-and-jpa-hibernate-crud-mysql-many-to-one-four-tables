package com.ttknpdev.springbootcrudmanytoonefourthtable.services;

import java.util.List;
import java.util.Map;

public interface ProjectService <T>{
    T create(Long p ,Long s ,Long m ,T obj);
    List<T> reads();
    T read(String project_name);
    T update(T obj,String project_name);
    Map<String,T> delete(String project_name);
}
