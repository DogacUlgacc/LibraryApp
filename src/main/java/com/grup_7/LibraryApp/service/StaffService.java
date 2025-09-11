package com.grup_7.LibraryApp.service;


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

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id) {

        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));

    }


    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id,Staff staffDetails) {
        Staff staff = getStaffById(id);
        staff.setName(staffDetails.getName());
        staff.setSurname(staffDetails.getSurname());
        staff.setRole(staffDetails.getRole());
        staff.setEmail(staffDetails.getEmail());
        staff.setPhone(staffDetails.getPhone());
        return staffRepository.save(staff);
    }


    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }
}









