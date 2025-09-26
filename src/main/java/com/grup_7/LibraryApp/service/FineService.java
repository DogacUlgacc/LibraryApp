package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.finesDto.request.CreateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.request.UpdateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.response.CreatedFinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesListResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.UpdateFinesResponse;
import com.grup_7.LibraryApp.entity.Fine;
import com.grup_7.LibraryApp.mapper.FineMapper;
import com.grup_7.LibraryApp.repository.FineRepository;
import com.grup_7.LibraryApp.rules.FineBusinessRules;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class FineService {

    private final FineRepository fineRepository;
    private final FineBusinessRules fineBusinessRules;
    private final FineMapper fineMapper;


    public FineService(FineRepository fineRepository, FineBusinessRules fineBusinessRules, FineMapper fineMapper) {
        this.fineRepository = fineRepository;
        this.fineBusinessRules = fineBusinessRules;
        this.fineMapper = fineMapper;
    }

    public List<FinesListResponse> getFinesByMember(int memberId, Boolean paid) {
        List<Fine> fines;

        if (paid != null) {
            // paid parametresi varsa filtreleme yap
            fines = fineRepository.findByReservationMemberMemberIdAndIsPaid(memberId, paid);
        } else {
            // paid parametresi yoksa tüm cezalar
            fines = fineRepository.findByReservationMemberMemberId(memberId);
        }
        return fines.stream().map(fineMapper::toFinesListResponse).toList();
    }


    //yeni ceza oluşturma
    public CreatedFinesResponse createFine(CreateFinesRequest request) {
        fineBusinessRules.checkReservationExists(request.getReservationId());

        Fine fine = fineMapper.toFinesEntity(request);
        Fine savedFine = fineRepository.save(fine);
        return fineMapper.toCreatedFinesResponse(savedFine);
    }

    // cezayı güncelleme
    public UpdateFinesResponse updateFine(int id, UpdateFinesRequest request) {
        Fine fine = fineRepository.findById(id).orElseThrow(() -> new RuntimeException("Ceza bulunamadı." + id));
        fineMapper.updateFinesFromRequest(request, fine);
        Fine updated = fineRepository.save(fine);
        return fineMapper.toUpdateFinesResponse(updated);
    }

    //ceza ödeme
    public FinesResponse payFine(int id) {
        Fine fine = fineRepository.findById(id).orElseThrow(() -> new RuntimeException("Ceza bulunamadı." + id));
        fine.setPaid(true);
        Fine updated = fineRepository.save(fine);
        return fineMapper.toFinesResponse(updated);
    }

    //cezayı silme
    public void deleteFine(int id) {
        Fine fine = fineRepository.findById(id).orElseThrow(() -> new RuntimeException("Ceza bulunamadı." + id));
        fineRepository.delete(fine);
    }
}
