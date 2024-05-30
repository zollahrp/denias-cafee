package deniascafe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import deniascafe.demo.model.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
}
