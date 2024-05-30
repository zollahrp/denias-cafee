package deniascafe.demo.service;

import deniascafe.demo.repository.VerifikasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifikasiService {
    @Autowired
    private VerifikasiRepository verifikasiRepository;

    public boolean verifyCode(String code) {
        return verifikasiRepository.findByCode(code).isPresent();
    }
}
