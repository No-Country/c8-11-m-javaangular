package com.wallet.wallet.api.service.generic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface GenericServiceAPI<T, ID extends Serializable> {

    T save(T entity);

    void delete(ID id);

    JpaRepository<T, ID> getRepository();
}
