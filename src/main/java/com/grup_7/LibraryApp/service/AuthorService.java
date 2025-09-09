package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.AuthorDto.request.AuthorForAddDto;
import com.grup_7.LibraryApp.dto.AuthorDto.request.AuthorForUpdateDto;
import com.grup_7.LibraryApp.dto.AuthorDto.response.GetAllAuthorsResponse;
import com.grup_7.LibraryApp.dto.AuthorDto.response.GetAuthorByIdResponse;
import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.repository.AuthorRepository;

import java.util.List;

public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<GetAllAuthorsResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(a -> new GetAllAuthorsResponse(
                        a.getId(),
                        a.getName(),
                        a.getSurname(),
                        a.getPhoneNumber(),
                        a.getEmail()
                ))
                .toList();
    }

    public AuthorForAddDto saveAuthor(AuthorForAddDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setSurname(dto.getSurname());
        author.setPhoneNumber(dto.getPhoneNumber());
        author.setEmail(dto.getEmail());

        Author saved = authorRepository.save(author);

        return new AuthorForAddDto(
                saved.getName(),
                saved.getSurname(),
                saved.getPhoneNumber(),
                saved.getEmail()
        );
    }

    public GetAuthorByIdResponse getAuthorById(int id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı."));

        return new GetAuthorByIdResponse(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getPhoneNumber(),
                author.getEmail()
        );
    }

    public AuthorForUpdateDto updateAuthor(int id, AuthorForUpdateDto dto){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı."));

        if (dto.getName() != null) author.setName(dto.getName());
        if (dto.getSurname() != null) author.setSurname(dto.getSurname());
        if (dto.getPhoneNumber() != null) author.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getEmail() != null) author.setEmail(dto.getEmail());

        Author saved = authorRepository.save(author);

        return new AuthorForUpdateDto(
                saved.getId(),
                saved.getName(),
                saved.getSurname(),
                saved.getPhoneNumber(),
                saved.getEmail()
        );
    }

    public void deleteAuthor(int id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı."));

        authorRepository.delete(author);
    }
}
