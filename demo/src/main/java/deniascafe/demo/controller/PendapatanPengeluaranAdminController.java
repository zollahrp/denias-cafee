package deniascafe.demo.controller;

import deniascafe.demo.model.PendapatanPengeluaran;
import deniascafe.demo.service.PendapatanPengeluaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PendapatanPengeluaranAdminController {

    @Autowired
    private PendapatanPengeluaranService service;

    @GetMapping("/pendapatanpengeluaran")
    public String showPendapatanPengeluaranPage(Model model) {
        model.addAttribute("pendapatanPengeluaranList", service.findAll());
        return "pendapatanpengeluaran";
    }

    @GetMapping("/pendapatanpengeluaran/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        PendapatanPengeluaran pendapatanPengeluaran = service.findById(id);
        model.addAttribute("pendapatanPengeluaran", pendapatanPengeluaran);
        return "editpendapatanpengeluaran";
    }

    @PostMapping("/pendapatanpengeluaran/update")
    public String updatePendapatanPengeluaran(PendapatanPengeluaran pendapatanPengeluaran) {
        service.save(pendapatanPengeluaran);
        return "redirect:/pendapatanpengeluaran";
    }

    @GetMapping("/pendapatanpengeluaran/delete/{id}")
    public String deletePendapatanPengeluaran(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/pendapatanpengeluaran";
    }

    @DeleteMapping("/deletependapatanpengeluaran/{id}")
public ResponseEntity<Void> deletePendapatanPengeluaran(@PathVariable Long id) {
    System.out.println("Menghapus pendapatan/pengeluaran dengan ID: " + id);
    service.deleteById(id); // Hapus data dari tabel lokal
    return ResponseEntity.ok().build();
}
}