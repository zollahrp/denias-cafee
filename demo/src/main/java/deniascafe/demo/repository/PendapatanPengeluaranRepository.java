package deniascafe.demo.repository;

import deniascafe.demo.model.PendapatanPengeluaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PendapatanPengeluaranRepository extends JpaRepository<PendapatanPengeluaran, Long> {
    List<PendapatanPengeluaran> findByTanggalBetween(LocalDate start, LocalDate end);
}