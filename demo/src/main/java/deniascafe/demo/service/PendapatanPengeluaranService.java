package deniascafe.demo.service;

import deniascafe.demo.model.PendapatanPengeluaran;
import deniascafe.demo.repository.PendapatanPengeluaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PendapatanPengeluaranService {

    @Autowired
    private PendapatanPengeluaranRepository repository;

    public PendapatanPengeluaran save(PendapatanPengeluaran entity) {
        return repository.save(entity);
    }

    public PendapatanPengeluaran findById(long id) {
        return repository.findById(id).orElse(null);
    }
    
    public void deleteById(Long id) {
        System.out.println("Menghapus dari repository id: " + id);
        repository.deleteById(id);
    }

    public List<PendapatanPengeluaran> findAll() {
        return repository.findAll();
    }

    public List<PendapatanPengeluaran> findPendapatanPengeluaranHariIni() {
        LocalDate today = LocalDate.now();
        return repository.findByTanggalBetween(today, today);
    }

    public List<PendapatanPengeluaran> findPendapatanPengeluaranBulanIni() {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        return repository.findByTanggalBetween(startOfMonth, endOfMonth);
    }

    public List<PendapatanPengeluaran> findPendapatanPengeluaranTahunIni() {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
        LocalDate endOfYear = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
        return repository.findByTanggalBetween(startOfYear, endOfYear);
    }

    public List<PendapatanPengeluaran> findPendapatanPengeluaranKemarin() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return repository.findByTanggalBetween(yesterday, yesterday);
    }
}