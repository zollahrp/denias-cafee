package deniascafe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import deniascafe.demo.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}