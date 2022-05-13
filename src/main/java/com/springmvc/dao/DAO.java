package com.springmvc.dao;

import java.util.List;

public interface DAO<Entity> {
    List<Entity> findAll();

    Entity findOne(int id);

    Entity save(Entity entity);

    void delete(int id);
}
