package com.mygaienko.practice.web;

import com.mygaienko.practice.model.Author;
import com.mygaienko.practice.service.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mygadmy on 03/12/15.
 */
@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/authors")
    Author authors() {
        return authorService.get(Long.valueOf(1));
    }
}
