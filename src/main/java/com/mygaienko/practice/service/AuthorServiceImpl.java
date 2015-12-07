package com.mygaienko.practice.service;

import com.mygaienko.practice.dao.interfaces.AuthorDao;
import com.mygaienko.practice.model.Author;
import com.mygaienko.practice.service.interfaces.AuthorService;
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
