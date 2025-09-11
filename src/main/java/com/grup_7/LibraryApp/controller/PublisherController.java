package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.publisherDto.request.CreatePublisherRequestDto;
import com.grup_7.LibraryApp.dto.publisherDto.request.UpdatePublisherRequestDto;
import com.grup_7.LibraryApp.dto.publisherDto.response.CreatedPublisherResponse;
import com.grup_7.LibraryApp.dto.publisherDto.response.PublisherListResponseDto;
import com.grup_7.LibraryApp.dto.publisherDto.response.PublisherResponseDto;
import com.grup_7.LibraryApp.dto.publisherDto.response.UpdatedPublisherResponseDto;
import com.grup_7.LibraryApp.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<PublisherListResponseDto> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public PublisherResponseDto getPublisherById(@PathVariable int id) {
        return publisherService.getPublisherByIdDtoResponse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPublisherResponse createPublisher(@RequestBody CreatePublisherRequestDto request) {
        return publisherService.save(request);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable int id) {
        publisherService.delete(id);
    }

    @PutMapping("/{id}")
    public UpdatedPublisherResponseDto updatePublisher(@PathVariable int id, @RequestBody UpdatePublisherRequestDto request) {
        return publisherService.updatePublisher(id, request);
    }



}
