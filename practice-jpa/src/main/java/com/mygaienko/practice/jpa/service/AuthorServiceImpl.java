package com.mygaienko.practice.jpa.service;

import com.mygaienko.practice.jpa.dao.interfaces.AuthorDao;
import com.mygaienko.practice.jpa.service.interfaces.AuthorService;
import com.mygaienko.practice.jpa.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by enda1n on 05.12.2015.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDao authorDao;

    @Override
    public Author get(Long id) {
        return authorDao.get(id);
    }

}
