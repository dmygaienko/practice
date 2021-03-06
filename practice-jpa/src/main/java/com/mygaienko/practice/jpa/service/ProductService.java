package com.mygaienko.practice.jpa.service;

import com.mygaienko.practice.jpa.dao.ProductDao;
import com.mygaienko.practice.jpa.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dmygaenko on 15/01/2016.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Transactional
    public Product merge(Product product) {
        Product productToMerge = productDao.get(product.getId());
        return productDao.merge(productToMerge);
    }

}
