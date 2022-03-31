package com.example.exc_11.dao;

import java.util.List;

public interface DAO<Entity, Key> {
    void create(Entity entity);
    Entity read(Key key);
    void update(Entity entity);
    void delete(Entity entity);

    List findAll();
    int getNextId();
}
