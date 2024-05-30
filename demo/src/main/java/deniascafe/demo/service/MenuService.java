package deniascafe.demo.service;

import deniascafe.demo.model.Menu;
import deniascafe.demo.repository.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    private MenuRepository menuRepository;

    private final String uploadDir = "C:\\Users\\Zolla\\Documents\\Visual Studi Code\\PROJEK DANIS COFE\\denias-cafe\\demo\\public\\img";

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public void saveMenu(Menu menu, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            menu.setGambarPath("/img/" + file.getOriginalFilename()); // Path gambar di database
        } else {
            if (menu.getGambarPath() == null || menu.getGambarPath().isEmpty()) {
                menu.setGambarPath("default/path/to/image.jpg");
            }
        }
        LOGGER.info("Saving menu: " + menu);
        menuRepository.save(menu);
    }

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    public List<Menu> getRecommendedMenus() {
        return menuRepository.findAll().stream()
                .filter(Menu::getRecommended)
                .collect(Collectors.toList());
    }
}