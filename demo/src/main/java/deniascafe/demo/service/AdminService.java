package deniascafe.demo.service;

import deniascafe.demo.model.Admin;
import deniascafe.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}