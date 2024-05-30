package deniascafe.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMenu;
    
    private String category;
    private String deskripsiMenu;
    private String gambarPath;
    private BigDecimal hargaMenu;
    private String namaMenu;
    private Boolean recommended;

    // Getters and setters
    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getDeskripsiMenu() {
        return deskripsiMenu;
    }

    public void setDeskripsiMenu(String deskripsiMenu) {
        this.deskripsiMenu = deskripsiMenu;
    }

    public BigDecimal getHargaMenu() {
        return hargaMenu;
    }

    public void setHargaMenu(BigDecimal hargaMenu) {
        this.hargaMenu = hargaMenu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGambarPath() {
        return gambarPath;
    }

    public void setGambarPath(String gambarPath) {
        this.gambarPath = gambarPath;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public void setImage(String gambarPath) {
        this.gambarPath = gambarPath;
    }
}