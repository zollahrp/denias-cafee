package deniascafe.demo.controller;

import deniascafe.demo.model.PendapatanPengeluaran;
import deniascafe.demo.service.PendapatanPengeluaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PendapatanPengeluaranController {

    @Autowired
    private PendapatanPengeluaranService service;

    @GetMapping("/formpendapatan")
    public String showForm(Model model) {
        model.addAttribute("pendapatanPengeluaran", new PendapatanPengeluaran());
        return "formpendapatan";
    }

    @PostMapping("/simpanpendapatan")
    public String savePendapatanPengeluaran(@ModelAttribute("pendapatanPengeluaran") PendapatanPengeluaran pendapatanPengeluaran) {
        pendapatanPengeluaran.setTotal(pendapatanPengeluaran.getPendapatan() - pendapatanPengeluaran.getPengeluaran());
        service.save(pendapatanPengeluaran);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
public String showDashboard(Model model) {
    List<PendapatanPengeluaran> pendapatanPengeluaranList = service.findAll();
    model.addAttribute("pendapatanPengeluaranList", pendapatanPengeluaranList);

    long totalPendapatan = pendapatanPengeluaranList.stream().mapToLong(PendapatanPengeluaran::getPendapatan).sum();
    long totalPengeluaran = pendapatanPengeluaranList.stream().mapToLong(PendapatanPengeluaran::getPengeluaran).sum();

    // Ambil data hari ini dan kemarin
    List<PendapatanPengeluaran> hariIniList = service.findPendapatanPengeluaranHariIni();
    List<PendapatanPengeluaran> kemarinList = service.findPendapatanPengeluaranKemarin();

    long pendapatanHariIni = hariIniList.stream().mapToLong(PendapatanPengeluaran::getPendapatan).sum();
    long pengeluaranHariIni = hariIniList.stream().mapToLong(PendapatanPengeluaran::getPengeluaran).sum();

    long pendapatanKemarin = kemarinList.stream().mapToLong(PendapatanPengeluaran::getPendapatan).sum();
    long pengeluaranKemarin = kemarinList.stream().mapToLong(PendapatanPengeluaran::getPengeluaran).sum();

    double persentasePendapatan = 0;
    if (pendapatanKemarin > 0) {
        persentasePendapatan = ((double) (pendapatanHariIni - pendapatanKemarin) / pendapatanKemarin) * 100;
    } else if (pendapatanHariIni > 0) {
        persentasePendapatan = 100;
    }

    double persentasePengeluaran = 0;
    if (pengeluaranKemarin > 0) {
        persentasePengeluaran = ((double) (pengeluaranHariIni - pengeluaranKemarin) / pengeluaranKemarin) * 100;
    } else if (pengeluaranHariIni > 0) {
        persentasePengeluaran = 100;
    }

    model.addAttribute("totalPendapatan", totalPendapatan);
    model.addAttribute("totalPengeluaran", totalPengeluaran);
    model.addAttribute("persentasePendapatan", persentasePendapatan);
    model.addAttribute("persentasePengeluaran", persentasePengeluaran);
    return "dashboard";
}

/*     
@GetMapping("/editpendapatan/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        PendapatanPengeluaran pendapatanPengeluaran = service.findById(id);
        model.addAttribute("pendapatanPengeluaran", pendapatanPengeluaran);
        return "formpendapatan"; // Reusing the same form for editing
    }

    @GetMapping("/deletependapatan/{id}")
    public String deletePendapatanPengeluaran(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/dashboard";
    } */

    @GetMapping("/manajemen-keuangan")
    public String showManajemenKeuangan(Model model) {
        List<PendapatanPengeluaran> pendapatanPengeluaranList = service.findAll();
        model.addAttribute("pendapatanPengeluaranList", pendapatanPengeluaranList);

        long totalPendapatan = pendapatanPengeluaranList.stream().mapToLong(PendapatanPengeluaran::getPendapatan).sum();
        long totalPengeluaran = pendapatanPengeluaranList.stream().mapToLong(PendapatanPengeluaran::getPengeluaran).sum();

        // Ambil data hari ini dan kemarin
        List<PendapatanPengeluaran> hariIniList = service.findPendapatanPengeluaranHariIni();
        List<PendapatanPengeluaran> kemarinList = service.findPendapatanPengeluaranKemarin();

        long pendapatanHariIni = hariIniList.stream().mapToLong(PendapatanPengeluaran::getPendapatan).sum();
        long pengeluaranHariIni = hariIniList.stream().mapToLong(PendapatanPengeluaran::getPengeluaran).sum();

        long pendapatanKemarin = kemarinList.stream().mapToLong(PendapatanPengeluaran::getPendapatan).sum();
        long pengeluaranKemarin = kemarinList.stream().mapToLong(PendapatanPengeluaran::getPengeluaran).sum();

        double persentasePendapatan = 0;
        if (pendapatanKemarin > 0) {
            persentasePendapatan = ((double) (pendapatanHariIni - pendapatanKemarin) / pendapatanKemarin) * 100;
        } else if (pendapatanHariIni > 0) {
            persentasePendapatan = 100;
        }

        double persentasePengeluaran = 0;
        if (pengeluaranKemarin > 0) {
            persentasePengeluaran = ((double) (pengeluaranHariIni - pengeluaranKemarin) / pengeluaranKemarin) * 100;
        } else if (pengeluaranHariIni > 0) {
            persentasePengeluaran = 100;
        }

        model.addAttribute("totalPendapatan", totalPendapatan);
        model.addAttribute("totalPengeluaran", totalPengeluaran);
        model.addAttribute("persentasePendapatan", persentasePendapatan);
        model.addAttribute("persentasePengeluaran", persentasePengeluaran);
        
        return "manajemen-keuangan";
    }

    @GetMapping("/editpendapatan/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
    PendapatanPengeluaran pendapatanPengeluaran = service.findById(id);
    model.addAttribute("pendapatanPengeluaran", pendapatanPengeluaran);
    return "editpendapatanpengeluaran"; // Mengarahkan ke halaman editpendapatanpengeluaran.html
}



    @GetMapping("/deletependapatan/{id}")
    public String deletePendapatanPengeluaran(@PathVariable("id") long id) {
        service.deleteById(id);
        return "redirect:/manajemen-keuangan";
    }

    @PostMapping("/pendapatanpengeluaran/update")
    public String updatePendapatanPengeluaran(@ModelAttribute("pendapatanPengeluaran") PendapatanPengeluaran pendapatanPengeluaran) {
        pendapatanPengeluaran.setTotal(pendapatanPengeluaran.getPendapatan() - pendapatanPengeluaran.getPengeluaran());
        service.save(pendapatanPengeluaran); // `save` will update if ID exists
        return "redirect:/manajemen-keuangan";
    }
}