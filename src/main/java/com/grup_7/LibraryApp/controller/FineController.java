package com.grup_7.LibraryApp.controller;


import com.grup_7.LibraryApp.dto.finesDto.request.CreateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.request.UpdateFinesRequest;
import com.grup_7.LibraryApp.dto.finesDto.response.CreatedFinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesListResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.FinesResponse;
import com.grup_7.LibraryApp.dto.finesDto.response.UpdateFinesResponse;
import com.grup_7.LibraryApp.service.FineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fines")
public class FineController {

    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    //cezalı üyeleri getirme
    @GetMapping("/members/{memberId}")
    public List<FinesListResponse> getFinesByMember(@PathVariable int memberId, @RequestParam(required = false) Boolean isPaid) {
        return fineService.getFinesByMember(memberId,isPaid);
    }
//cezayı oluşturma
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFinesResponse createFines(@RequestBody CreateFinesRequest request) {
        return fineService.createFine(request);
    }
//ceza güncelle
    @PutMapping("/{id}")
    public UpdateFinesResponse updateFine(@PathVariable int id, @RequestBody UpdateFinesRequest request) {
        return fineService.updateFine(id, request);
    }
//ceza ödendiyse true
    @PostMapping("/{id}/pay")
    public FinesResponse payFine(@PathVariable int id) {
        return fineService.payFine(id);
    }

    //cezayı silme
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFine(@PathVariable int id) {
        fineService.deleteFine(id);
    }

}
