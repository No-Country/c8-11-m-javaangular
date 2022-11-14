package com.wallet.wallet.api.service.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public abstract class GenericServiceImpl <T, ID extends Serializable> implements GenericServiceAPI<T, ID>{

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(ID id){
        getRepository().deleteById(id);
    }

    @Override
    public abstract JpaRepository<T, ID> getRepository();
}
