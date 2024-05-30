package deniascafe.demo.repository;

import deniascafe.demo.model.Verifikasi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VerifikasiRepository extends JpaRepository<Verifikasi, Long> {
    Optional<Verifikasi> findByCode(String code);
}