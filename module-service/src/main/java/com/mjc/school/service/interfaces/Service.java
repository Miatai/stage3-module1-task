package com.mjc.school.service.interfaces;

import java.util.List;

public interface Service<T1, T2> {
    List<T1> getAll();

    T1 getById(Long id);

    T1 create(T2 entity);

    T1 update(T2 entity);

    boolean deleteById(Long id);

}
