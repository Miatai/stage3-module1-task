package com.mjc.school.controller.interfaces;

import java.util.List;

public interface Controller<T1, T2> {
    List<T1> readAll();

    T1 readById(Long id);

    T1 create(T2 entity);

    T1 update(T2 entity);

    Boolean deleteById(Long id);
}
