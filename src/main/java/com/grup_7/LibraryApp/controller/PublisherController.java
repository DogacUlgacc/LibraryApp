package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.publisherDto.request.CreatePublisherDtoRequest;
import com.grup_7.LibraryApp.dto.publisherDto.request.PublisherUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.publisherDto.response.CreatedPublisherResponse;
import com.grup_7.LibraryApp.dto.publisherDto.response.GetAllPublishersDtoResponse;
import com.grup_7.LibraryApp.dto.publisherDto.response.GetPublisherByIdDtoResponse;
import com.grup_7.LibraryApp.dto.publisherDto.response.PublisherUpdateDtoResponse;
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
    public List<GetAllPublishersDtoResponse> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public GetPublisherByIdDtoResponse getPublisherById(@PathVariable int id) {
        return publisherService.getPublisherByIdDtoResponse(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedPublisherResponse createPublisher(@RequestBody CreatePublisherDtoRequest request) {
        return publisherService.addPublisher(request);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable int id) {
        publisherService.delete(id);
    }

    @PutMapping("/{id}")
    public PublisherUpdateDtoResponse updatePublisher(@PathVariable int id, @RequestBody PublisherUpdateDtoRequest request) {
        return publisherService.updatePublisher(id, request);
    }


}
