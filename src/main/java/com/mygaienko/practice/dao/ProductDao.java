package com.mygaienko.practice.dao;

import com.mygaienko.practice.model.Product;
import com.mygaienko.practice.model.Product_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by dmygaenko on 15/01/2016.
 */
@Repository
public class ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        CriteriaQuery<Product> allQuery = query.select(productRoot);

        return entityManager.createQuery(allQuery).getResultList();
    }

    public Product get(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        query.where(builder.equal(productRoot.get(Product_.id), id));

        return entityManager.createQuery(query).getSingleResult();
    }

    public Product get(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        query.where(builder.equal(productRoot.get(Product_.name), name));

        return entityManager.createQuery(query).getSingleResult();
    }

    @Transactional
    public Product merge(Product product) {
        return entityManager.merge(product);
    }

    @Transactional
    public Product pessimisticMerge(Product product) {
        Product productToMerge = entityManager.find(Product.class, product.getId(), LockModeType.PESSIMISTIC_WRITE);
        productToMerge.setCode(product.getCode());
        productToMerge.setName(product.getName());
        productToMerge.setVersion(product.getVersion());
        return entityManager.merge(productToMerge);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void refresh(Product product) {
        entityManager.refresh(product);
    }

}
