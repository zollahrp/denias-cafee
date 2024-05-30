package deniascafe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import deniascafe.demo.model.Pengguna;

public interface PenggunaRepository extends JpaRepository<Pengguna, Long> {
}