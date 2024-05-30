package deniascafe.demo.controller;

import deniascafe.demo.service.VerifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VerifikasiController {
    @Autowired
    private VerifikasiService verifikasiService;

    @PostMapping("/verify")
    public String verifyCode(@RequestParam String code) {
        if (verifikasiService.verifyCode(code)) {
            return "Verifikasi berhasil!";
        } else {
            return "Kode verifikasi salah. Coba lagi.";
        }
    }
}
