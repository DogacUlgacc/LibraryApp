package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.AuthorDto.response.GetAllAuthorsResponse;
import com.grup_7.LibraryApp.dto.AuthorDto.response.GetAuthorByIdResponse;
import com.grup_7.LibraryApp.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    public List<GetAllAuthorsResponse> getAll(){
        return authorService.getAllAuthors();
    }

    public GetAuthorByIdResponse getById(@PathVariable int id){
        return  authorService.getAuthorById(id);
    }

    @PostMapping
}

@PutMapping("{id}")
    }

public void delete(@PathVariable int id){
    authorService.deleteAuthor(id);
}
}