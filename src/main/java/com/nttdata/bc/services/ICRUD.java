package com.nttdata.bc.services;

import java.util.List;

// import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface ICRUD<T, V> {
    Uni<T> insert(T obj);

    Uni<T> update(T obj);

    Uni<List<T>> listAll();

    Uni<T> findById(V id);

    Uni<Void> delete(V id);
}
