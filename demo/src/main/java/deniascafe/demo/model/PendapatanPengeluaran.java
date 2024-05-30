package deniascafe.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PendapatanPengeluaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long pendapatan;
    private long pengeluaran;
    private long total;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tanggal;

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(long pendapatan) {
        this.pendapatan = pendapatan;
    }

    public long getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(long pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public static void deleteById(Long id2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}