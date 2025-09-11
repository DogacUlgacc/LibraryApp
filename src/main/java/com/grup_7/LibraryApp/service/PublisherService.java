package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.publisherDto.request.CreatePublisherRequestDto;
import com.grup_7.LibraryApp.dto.publisherDto.request.UpdatePublisherRequestDto;
import com.grup_7.LibraryApp.dto.publisherDto.response.CreatedPublisherResponse;
import com.grup_7.LibraryApp.dto.publisherDto.response.PublisherListResponseDto;

import com.grup_7.LibraryApp.dto.publisherDto.response.PublisherResponseDto;
import com.grup_7.LibraryApp.dto.publisherDto.response.UpdatedPublisherResponseDto;
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


    public List<PublisherListResponseDto> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(publisher -> new PublisherListResponseDto(
                        publisher.getName(),
                        publisher.getAddress()
                ))
                .toList();
    }

    public PublisherResponseDto getPublisherByIdDtoResponse(int id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
        return new PublisherResponseDto(
                publisher.getName(),
                publisher.getAddress()
        );
    }

    public CreatedPublisherResponse save(CreatePublisherRequestDto request) {
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


    public UpdatedPublisherResponseDto updatePublisher(int id, UpdatePublisherRequestDto request) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher bulunamadı"));

        publisher.setName(request.getName());
        publisher.setAddress(request.getAddress());

        Publisher updatedPublisher = publisherRepository.save(publisher);

        return new UpdatedPublisherResponseDto(
                updatedPublisher.getName(),
                updatedPublisher.getAddress()
        );
    }

}
