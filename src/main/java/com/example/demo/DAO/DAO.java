package com.example.demo.DAO;

import java.util.Optional;

public interface DAO<T> {

    void add(T object);

    Optional<T> get(int id);

    void update(T updatedObject);

    void delete(int id);

}
