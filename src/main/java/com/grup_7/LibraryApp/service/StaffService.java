package com.grup_7.LibraryApp.service;


import com.grup_7.LibraryApp.dto.staffDto.request.CreateStaffDtoRequest;
import com.grup_7.LibraryApp.dto.staffDto.request.StaffForUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.staffDto.response.CreatedStaffResponse;
import com.grup_7.LibraryApp.dto.staffDto.response.GetAllStaffResponse;
import com.grup_7.LibraryApp.dto.staffDto.response.GetStaffByIdResponse;
import com.grup_7.LibraryApp.dto.staffDto.response.StaffForUpdateResponse;
import com.grup_7.LibraryApp.entity.Staff;
import com.grup_7.LibraryApp.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<GetAllStaffResponse> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(staff  -> new GetAllStaffResponse(
                       staff.getId(),
                        staff.getName(),
                        staff.getSurname(),
                        staff.getRole(),
                        staff.getEmail(),
                        staff.getPhone()
                ))
                .toList();

    }


    public CreatedStaffResponse saveStaff(CreateStaffDtoRequest dto) {

        Staff staff = new Staff();
        staff.setName(dto.getName());
        staff.setSurname(dto.getSurname());
        staff.setRole(dto.getRole());
        staff.setEmail(dto.getEmail());
        staff.setPhone(dto.getPhone());

        Staff saved = staffRepository.save(staff);

        return new CreatedStaffResponse(
                saved.getId(),
                saved.getName(),
                saved.getSurname(),
                saved.getRole(),
                saved.getEmail(),
                saved.getPhone());
    }



    public GetStaffByIdResponse getStaffById(int id) {

        Staff staff = staffRepository.findById(id).orElseThrow( () -> new RuntimeException("Staff bulunamadı."));

        return  new GetStaffByIdResponse(
                staff.getId(),
                staff.getName(),
                staff.getSurname(),
                staff.getRole(),
                staff.getEmail(),
                staff.getPhone()
        );
    }

    public StaffForUpdateResponse updateStaff(Long id, StaffForUpdateDtoRequest dto) {
        Staff staff = staffRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Staff bulunamadı."));

        staff.setName(dto.getName());
        staff.setSurname(dto.getSurname());
        staff.setRole(dto.getRole());
        staff.setEmail(dto.getEmail());
        staff.setPhone(dto.getPhone());

        Staff updateStaff = staffRepository.save(staff);
        return new StaffForUpdateResponse(

                updateStaff.getId(),
                updateStaff.getEmail(),
                updateStaff.getPhone(),
                updateStaff.getRole(),
                updateStaff.getSurname(),
                updateStaff.getName()

        );
    }


public void deleteStaff(int id){
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff bulunamadı."));
        staffRepository.delete(staff);
     }
}









