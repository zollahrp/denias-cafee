package deniascafe.demo.service;

import deniascafe.demo.model.Verifikasi;
import deniascafe.demo.repository.VerifikasiRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VerifikasiService {
    private static final Logger logger = LoggerFactory.getLogger(VerifikasiService.class);

    @Autowired
    private VerifikasiRepository verifikasiRepository;

    public boolean verifyCode(String code) {
        return verifikasiRepository.findByCode(code).isPresent();
    }

    @Transactional
    public boolean updateVerifikasi(Long id, String code) {
        logger.info("Updating verification with ID: " + id + " and new code: " + code);
        Optional<Verifikasi> verifikasiOpt = verifikasiRepository.findById(id);
        if (verifikasiOpt.isPresent()) {
            Verifikasi verifikasi = verifikasiOpt.get();
            verifikasi.setCode(code);
            verifikasiRepository.save(verifikasi);
            logger.info("Update successful for ID: " + id);
            return true;
        } else {
            logger.warn("Verification ID: " + id + " not found");
            return false;
        }
    }
}