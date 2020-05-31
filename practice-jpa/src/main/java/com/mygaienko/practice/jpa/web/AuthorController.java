package com.mygaienko.practice.jpa.web;

import com.mygaienko.practice.jpa.model.Author;
import com.mygaienko.practice.jpa.service.interfaces.AuthorService;
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
