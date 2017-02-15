package com.itis.spring.service;

import java.util.List;

public interface BaseService<Model> {
    Model find(Long id);

    void delete(Long id);

    Long save(Model model);

    List<Model> findAll();

    void update(Model model);
}
