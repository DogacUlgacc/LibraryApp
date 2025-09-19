package com.grup_7.LibraryApp.service;


import com.grup_7.LibraryApp.dto.finesDto.request.CreateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.request.UpdateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.response.CreatedFinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesListResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.UpdateFinesResponse;
import com.grup_7.LibraryApp.entity.Fines;
import com.grup_7.LibraryApp.mapper.FinesMapper;
import com.grup_7.LibraryApp.repository.FinesRepository;
import com.grup_7.LibraryApp.rules.FinesBusinessRules;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class FinesService {

    private final FinesRepository finesRepository;
    private final FinesBusinessRules finesBusinessRules;
    private final FinesMapper finesMapper;


    public FinesService(FinesRepository finesRepository, FinesBusinessRules finesBusinessRules, FinesMapper finesMapper) {
        this.finesRepository = finesRepository;
        this.finesBusinessRules = finesBusinessRules;
        this.finesMapper = finesMapper;
    }

   //bir üyenin cezasını getirme;
    public List<FinesListResponse> getFinesByMember(int memberId){
        List<Fines> fines = finesRepository.findByReservationMemberId(memberId);
        return fines.stream()
                .map(finesMapper::toFinesListResponse)
                .toList();
    }
    //yeni ceza oluşturma
    public CreatedFinesResponse createFine(CreateFinesRequest request){
        finesBusinessRules.checkReservationExists(request.getReservationId());
        Fines fines = finesMapper.toFinesEntity(request);
        Fines savedFines = finesRepository.save(fines);
        return finesMapper.toCreatedFinesResponse(savedFines);
    }
    // cezayı güncelleme
    public UpdateFinesResponse updateFine(int id, UpdateFinesRequest request){
        Fines fines = finesRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Ceza bulunamadı." +id));
        finesMapper.updateFinesFromRequest(request, fines);
            Fines updated  = finesRepository.save(fines);
        return finesMapper.toUpdateFinesResponse(updated);
    }

    //ceza ödeme
    public FinesResponse payFine(int id){
        Fines fines = finesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ceza bulunamadı." + id));
        fines.setIsPaid(true);
        Fines updated =finesRepository.save(fines);
        return finesMapper.toFinesResponse(updated);
    }

    //cezayı silme
    public void deleteFine(int id){
        Fines fines = finesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ceza bulunamadı." +id));
        finesRepository.delete(fines);
    }
}
