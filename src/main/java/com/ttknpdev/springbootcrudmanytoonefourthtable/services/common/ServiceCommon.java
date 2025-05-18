package com.ttknpdev.springbootcrudmanytoonefourthtable.services.common;

import java.util.List;
import java.util.Map;

public interface ServiceCommon <T>{
    T create(T obj);
    <U1,U2,U3> T create(U1 key1 ,U2 key2 ,U3 key3 ,T obj);
    List<T> reads();
    <U> T read(U key);
    <U> T update(T obj,U key);
    <U> Map<String,U> delete(U key);
}
