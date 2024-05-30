package deniascafe.demo.controller;

import deniascafe.demo.model.PendapatanPengeluaran;
import deniascafe.demo.service.PendapatanPengeluaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PendapatanPengeluaranRestController {

    @Autowired
    private PendapatanPengeluaranService service;

    @GetMapping("/pendapatan_pengeluaran")
    public List<PendapatanPengeluaran> getAllPendapatanPengeluaran() {
        return service.findAll();
    }

    @GetMapping("/pendapatan_pengeluaran/hari_ini")
    public List<PendapatanPengeluaran> getPendapatanPengeluaranHariIni() {
        return service.findPendapatanPengeluaranHariIni();
    }

    @GetMapping("/pendapatan_pengeluaran/bulan_ini")
    public List<PendapatanPengeluaran> getPendapatanPengeluaranBulanIni() {
        return service.findPendapatanPengeluaranBulanIni();
    }

    @GetMapping("/pendapatan_pengeluaran/tahun_ini")
    public List<PendapatanPengeluaran> getPendapatanPengeluaranTahunIni() {
        return service.findPendapatanPengeluaranTahunIni();
    }
}