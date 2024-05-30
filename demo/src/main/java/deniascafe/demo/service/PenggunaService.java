package deniascafe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deniascafe.demo.model.Pengguna;
import deniascafe.demo.repository.PenggunaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PenggunaService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    public Pengguna savePengguna(String namaPengguna, String komentar) {
        Pengguna pengguna = new Pengguna();
        pengguna.setNamaPengguna(namaPengguna);
        pengguna.setKomentar(komentar);
        pengguna.setTanggalReview(LocalDate.now());
        return penggunaRepository.save(pengguna);
    }

    public List<Pengguna> getAllPengguna() {
        return penggunaRepository.findAll();
    }

    public void deletePenggunaById(Long idPengguna) {
        penggunaRepository.deleteById(idPengguna);
    }
}