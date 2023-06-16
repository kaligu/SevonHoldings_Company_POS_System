package lk.sevonholdings.dao;

import lk.sevonholdings.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface CrudDAO<T extends SuperEntity , ID extends Serializable> extends SuperDAO , Serializable {
    boolean save(T entity);
    T update(T entity);
    List<T> findAll();
    boolean deleteByPk(ID pk);
    Optional<T> findByPk(ID pk) ;

    boolean existByPk(ID pk);
}
