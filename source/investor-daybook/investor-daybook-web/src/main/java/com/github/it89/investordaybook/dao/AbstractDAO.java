package com.github.it89.investordaybook.dao;

import java.util.List;

abstract class AbstractDAO<T> {
    protected T getOneRecord(List<T> queryList) {
        if (queryList.size() == 1) {
            return queryList.get(0);
        }
        else if (queryList.isEmpty()) {
            return null;
        } else {
            throw new AssertionError("Too many rows");
        }
    }
}
