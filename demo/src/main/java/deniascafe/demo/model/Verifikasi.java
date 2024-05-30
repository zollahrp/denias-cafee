package deniascafe.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "verifikasi")
public class Verifikasi {
    @Id
    @Column(name = "id_verifikasi")
    private Long id;
    
    @Column(name = "kode_verifikasi")
    private String code;

    // Constructors, getters, and setters
    public Verifikasi() {}

    public Verifikasi(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}