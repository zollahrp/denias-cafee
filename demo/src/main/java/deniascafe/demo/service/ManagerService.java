package deniascafe.demo.service;

import deniascafe.demo.model.Manager;
import deniascafe.demo.repository.ManagerRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    public Optional<Manager> findByUsername(String username) {
        return managerRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        Optional<Manager> managerOpt = managerRepository.findByUsername(username);
        if (managerOpt.isPresent()) {
            Manager manager = managerOpt.get();
            return manager.getPassword().equals(password);
        } else {
            return false;
        }
    }

    public void updateManager(Manager manager) {
        managerRepository.save(manager);
    }
}