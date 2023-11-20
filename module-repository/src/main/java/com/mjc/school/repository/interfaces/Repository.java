package com.mjc.school.repository.interfaces;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    T getById(Long id);

    T create(T entity);

    T update(T entity);

    boolean deleteById(Long id);

    boolean isExistById(Long id);
}
