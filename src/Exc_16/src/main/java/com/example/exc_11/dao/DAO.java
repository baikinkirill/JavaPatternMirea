package com.example.exc_11.dao;

import com.sun.istack.NotNull;

import java.util.List;

public interface DAO<Entity, Key> {
    void create(Entity entity);
    Entity read(Key key);
    void update(Entity entity);
    void delete(Long id);

    List findAll();
    int getNextId();
    List<Entity> filter(String column);
    List<Entity> filter(String column,String pattern);
}
