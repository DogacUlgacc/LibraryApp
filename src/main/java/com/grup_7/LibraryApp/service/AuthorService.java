package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.AuthorDto.request.CreateAuthorDtoRequest;
import com.grup_7.LibraryApp.dto.AuthorDto.request.UpdateAuthorDtoRequest;
import com.grup_7.LibraryApp.dto.AuthorDto.response.CreatedAuthorResponse;
import com.grup_7.LibraryApp.dto.AuthorDto.response.GetAllAuthorsResponse;
import com.grup_7.LibraryApp.dto.AuthorDto.response.GetAuthorByIdResponse;
import com.grup_7.LibraryApp.dto.AuthorDto.response.UpdatedAuthorResponse;
import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public CreatedAuthorResponse saveAuthor(CreateAuthorDtoRequest dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setSurname(dto.getSurname());
        author.setPhoneNumber(dto.getPhoneNumber());
        author.setEmail(dto.getEmail());

        Author saved = authorRepository.save(author);

        return new CreatedAuthorResponse(
                saved.getId(),
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

    public UpdatedAuthorResponse updateAuthor(int id, UpdateAuthorDtoRequest dto){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı."));

        if (dto.getPhoneNumber() != null) author.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getEmail() != null) author.setEmail(dto.getEmail());

        Author saved = authorRepository.save(author);

        return new UpdatedAuthorResponse(
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
