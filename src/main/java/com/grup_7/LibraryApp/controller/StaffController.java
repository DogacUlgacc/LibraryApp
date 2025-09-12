package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.StaffDto.request.CreateStaffDtoRequest;
import com.grup_7.LibraryApp.dto.StaffDto.request.StaffForUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.StaffDto.response.CreatedStaffResponse;
import com.grup_7.LibraryApp.dto.StaffDto.response.GetAllStaffResponse;
import com.grup_7.LibraryApp.dto.StaffDto.response.GetStaffByIdResponse;
import com.grup_7.LibraryApp.dto.StaffDto.response.StaffForUpdateResponse;
import com.grup_7.LibraryApp.service.StaffService;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {


    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    private final StaffService staffService;



    @GetMapping("/all")
    public List<GetAllStaffResponse> getAll() {

        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public GetStaffByIdResponse getById(@PathVariable int id) {
        return staffService.getStaffById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedStaffResponse addStaff(@RequestBody CreateStaffDtoRequest request) {
        return staffService.saveStaff(request);
    }

    @PutMapping("{id}")
    public StaffForUpdateResponse updateStaff(@PathVariable Long id, @RequestBody StaffForUpdateDtoRequest request) {
        return staffService.updateStaff(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {staffService.deleteStaff(id);
    }
}