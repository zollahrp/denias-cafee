package deniascafe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import deniascafe.demo.model.Pengguna;
import deniascafe.demo.service.PenggunaService;

import java.util.List;

@RestController
@RequestMapping("/api/pengguna")
public class PenggunaController {

    @Autowired
    private PenggunaService penggunaService;

    @PostMapping("/add")
    public Pengguna addPengguna(@RequestParam String nama, @RequestParam String komentar) {
        return penggunaService.savePengguna(nama, komentar);
    }

    @GetMapping("/all")
    public List<Pengguna> getAllPengguna() {
        return penggunaService.getAllPengguna();
    }

    @DeleteMapping("/{idPengguna}")
    public void deletePengguna(@PathVariable Long idPengguna) {
        penggunaService.deletePenggunaById(idPengguna);
    }
}