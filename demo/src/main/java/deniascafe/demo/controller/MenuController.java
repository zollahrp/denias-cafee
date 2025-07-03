package deniascafe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import deniascafe.demo.model.Menu;
import deniascafe.demo.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private LocaleResolver localeResolver;

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @GetMapping("/menus")
    public String viewMenuPage(Model model) {
        try {
            List<Menu> menus = menuService.getAllMenus();
            model.addAttribute("menus", menus);
            for (Menu menu : menus) {
                if (menu.getGambarPath() == null) {
                    menu.setGambarPath("default/path/to/image.jpg");
                }
            }
            return "menu";
        } catch (Exception e) {
            LOGGER.error("Error in viewMenuPage. Cause: ", e);
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/menu/{id}")
    public String viewMenuDetail(@PathVariable Long id, Model model) {
        Menu menu = menuService.getMenuById(id).orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
        model.addAttribute("menu", menu);
        return "menu_detail";
    }

    @GetMapping("/menu")
    public String viewMenuPageAll(Model model, @RequestParam(value = "lang", defaultValue = "id") String lang, HttpServletRequest request, HttpServletResponse response) {
        // Set the locale based on the 'lang' parameter
        Locale locale = Locale.forLanguageTag(lang);
        localeResolver.setLocale(request, response, locale);
        LocaleContextHolder.setLocale(locale);

        List<Menu> menus = menuService.getAllMenus();
        List<Menu> coffees = menus.stream().filter(m -> "coffee".equalsIgnoreCase(m.getCategory())).collect(Collectors.toList());
        List<Menu> foods = menus.stream().filter(m -> "food".equalsIgnoreCase(m.getCategory())).collect(Collectors.toList());
        List<Menu> chocos = menus.stream().filter(m -> "choco".equalsIgnoreCase(m.getCategory())).collect(Collectors.toList());
        List<Menu> juices = menus.stream().filter(m -> "juice".equalsIgnoreCase(m.getCategory())).collect(Collectors.toList());
        List<Menu> teas = menus.stream().filter(m -> "tea".equalsIgnoreCase(m.getCategory())).collect(Collectors.toList());

        model.addAttribute("coffees", coffees);
        model.addAttribute("foods", foods);
        model.addAttribute("chocos", chocos);
        model.addAttribute("juices", juices);
        model.addAttribute("teas", teas);
        model.addAttribute("menus", menus);
        model.addAttribute("lang", lang); // Tambahkan atribut lang ke model

        // Logging
        LOGGER.info("Coffees: " + coffees);
        LOGGER.info("Foods: " + foods);
        LOGGER.info("Chocos: " + chocos);
        LOGGER.info("Juices: " + juices);
        LOGGER.info("Teas: " + teas);

        return "menu";
    }

    @GetMapping("/menuadmin")
    public String viewMenuAdminPage(Model model) {
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "menuadmin";
    }

    @GetMapping("/tambahmenu")
    public String showTambahMenuForm(Model model) {
        model.addAttribute("menu", new Menu());
        return "tambahmenu";
    }

    @PostMapping("/savemenu")
public String saveMenu(@ModelAttribute("menu") Menu menu, @RequestParam("file") MultipartFile file, BindingResult result, Model model) {
    if (result.hasErrors()) {
        LOGGER.error("Form has errors: " + result.getAllErrors());
        return "tambahmenu";
    }
    try {
        // Log informasi file yang diunggah
        LOGGER.info("Uploaded file name: " + file.getOriginalFilename());

        // Simpan file ke direktori public/img
        String fileName = file.getOriginalFilename();
        Path path = Paths.get("C:\\Users\\Zolla\\Documents\\Visual Studi Code\\PROJEK DANIS COFE\\denias-cafe\\demo\\public\\img");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        Path filePath = path.resolve(fileName);
        Files.write(filePath, file.getBytes());
        menu.setGambarPath("/img/" + fileName); // Update path untuk disimpan di database

        // Log informasi menu yang akan disimpan
        LOGGER.info("Saving menu: " + menu);
        menuService.save(menu);
    } catch (IOException e) {
        LOGGER.error("Error uploading file", e);
        model.addAttribute("errorMessage", "Error uploading file");
        return "tambahmenu";
    } catch (Exception e) {
        LOGGER.error("Error saving menu", e);
        model.addAttribute("errorMessage", "Error saving menu");
        return "tambahmenu";
    }
    return "redirect:/menuadmin";
}

@GetMapping("/formeditmenu/{id}")
public String showEditMenuForm(@PathVariable("id") Long id, Model model) {
    Menu menu = menuService.getMenuById(id).orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
    model.addAttribute("menu", menu);
    return "formeditmenu";
}

@PostMapping("/editmenu/{id}")
public String editMenu(@PathVariable("id") Long id, @ModelAttribute("menu") Menu menu, @RequestParam("file") MultipartFile file) throws IOException {
    try {
        // Simpan menu dengan gambar menggunakan metode di MenuService
        menuService.saveMenu(menu, file);
    } catch (IOException e) {
        LOGGER.error("Error uploading file", e);
        return "formeditmenu";
    }
    return "redirect:/menuadmin";
}

    @GetMapping("/hapusmenu/{id}")
    public String deleteMenu(@PathVariable("id") Long id) {
        menuService.deleteMenu(id);
        return "redirect:/menuadmin";
    }
}