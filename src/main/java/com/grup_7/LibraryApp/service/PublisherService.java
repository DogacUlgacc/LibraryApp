package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.publisherDto.request.CreatePublisherDtoRequest;
import com.grup_7.LibraryApp.dto.publisherDto.request.PublisherUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.publisherDto.response.CreatedPublisherResponse;
import com.grup_7.LibraryApp.dto.publisherDto.response.GetAllPublishersDtoResponse;

import com.grup_7.LibraryApp.dto.publisherDto.response.GetPublisherByIdDtoResponse;
import com.grup_7.LibraryApp.dto.publisherDto.response.PublisherUpdateDtoResponse;
import com.grup_7.LibraryApp.entity.Publisher;
import com.grup_7.LibraryApp.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    public List<GetAllPublishersDtoResponse> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(publisher -> new GetAllPublishersDtoResponse(
                        publisher.getName(),
                        publisher.getAddress()
                ))
                .toList();
    }

    public GetPublisherByIdDtoResponse getPublisherByIdDtoResponse(int id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
        return new GetPublisherByIdDtoResponse(
                publisher.getName(),
                publisher.getAddress()
        );
    }

    public CreatedPublisherResponse addPublisher(CreatePublisherDtoRequest request) {
        Publisher publisher = new Publisher();
        publisher.setName(request.getName());
        publisher.setAddress(request.getAddress());

        Publisher savedPublisher = publisherRepository.save(publisher);


        return new CreatedPublisherResponse(
                savedPublisher.getName(),
                savedPublisher.getAddress()
        );
    }

    public void delete(int id) {
        publisherRepository.deleteById(id);
    }


    public PublisherUpdateDtoResponse updatePublisher(int id, PublisherUpdateDtoRequest request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher bulunamadı"));

        publisher.setName(request.getName());
        publisher.setAddress(request.getAddress());

        Publisher updatedPublisher = publisherRepository.save(publisher);

        return new PublisherUpdateDtoResponse(
                updatedPublisher.getName(),
                updatedPublisher.getAddress()
        );
    }

}
