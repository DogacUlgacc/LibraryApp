package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.AuthorDto.request.AuthorForAddDto;
import com.grup_7.LibraryApp.dto.AuthorDto.request.AuthorForUpdateDto;
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

    private final AuthorService authorService;

    @GetMapping
    public List<GetAllAuthorsResponse> getAll(){
        return authorService.getAllAuthors();
    }

    @GetMapping("{id}")
    public GetAuthorByIdResponse getById(@PathVariable int id){
        return  authorService.getAuthorById(id);
    }

    @PostMapping
    public AuthorForAddDto addAuthor(@RequestBody AuthorForAddDto dto){
        return authorService.saveAuthor(dto);
    }

    @PutMapping("{id}")
    public AuthorForUpdateDto updateAuthor(@PathVariable int id, @RequestBody AuthorForUpdateDto dto){
        return authorService.updateAuthor(id, dto);
    }

    @DeleteMapping
    public void delete(@PathVariable int id){
        authorService.deleteAuthor(id);
    }
}
