package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.authorDto.request.CreateAuthorDtoRequest;
import com.grup_7.LibraryApp.dto.authorDto.request.UpdateAuthorDtoRequest;
import com.grup_7.LibraryApp.dto.authorDto.response.CreatedAuthorResponse;
import com.grup_7.LibraryApp.dto.authorDto.response.GetAllAuthorsResponse;
import com.grup_7.LibraryApp.dto.authorDto.response.GetAuthorByIdResponse;
import com.grup_7.LibraryApp.dto.authorDto.response.UpdatedAuthorResponse;
import com.grup_7.LibraryApp.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    private final AuthorService authorService;

    @GetMapping("/all")
    public List<GetAllAuthorsResponse> getAll(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public GetAuthorByIdResponse getById(@PathVariable int id){
        return  authorService.getAuthorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAuthorResponse addAuthor(@RequestBody CreateAuthorDtoRequest request){
        return authorService.saveAuthor(request);
    }

    @PutMapping("{id}")
    public UpdatedAuthorResponse updateAuthor(@PathVariable int id, @RequestBody UpdateAuthorDtoRequest request){
        return authorService.updateAuthor(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        authorService.deleteAuthor(id);
    }
}
