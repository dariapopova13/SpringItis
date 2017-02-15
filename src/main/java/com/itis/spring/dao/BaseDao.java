package com.itis.spring.dao;

import java.util.List;

public interface BaseDao<Model> {

    Model find(Long id);

    void delete(Long id);

    Long save(Model model);

    List<Model> findAll();

    void update(Model model);
}
