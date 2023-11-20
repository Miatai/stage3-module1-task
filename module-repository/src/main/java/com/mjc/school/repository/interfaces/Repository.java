package com.mjc.school.repository.interfaces;

import java.util.List;

public interface Repository<T> {
    List<T> readAll();

    T readById(Long id);

    T create(T entity);

    T update(T entity);

    boolean deleteById(Long id);

    Boolean isExistById(Long id);
}
