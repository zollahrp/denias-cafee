package deniascafe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deniascafe.demo.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}