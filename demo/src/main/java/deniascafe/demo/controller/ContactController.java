package deniascafe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import deniascafe.demo.model.Contact;
import deniascafe.demo.service.ContactService;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "contact";
    }

    @GetMapping("/admin/contact")
    public String getAdminContactPage(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        model.addAttribute("contact", new Contact()); // Initialize a new contact object
        return "contactadmin";
    }

    @PostMapping("/admin/contact")
    public String saveOrUpdateContact(@ModelAttribute Contact contact) {
        contactService.saveOrUpdateContact(contact);
        return "redirect:/admin/contact";
    }

    @GetMapping("/admin/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/admin/contact";
    }

    @GetMapping("/admin/contact/edit/{id}")
    public String editContact(@PathVariable Long id, Model model) {
    Contact contact = contactService.getContactById(id);
    if (contact == null) {
        // Handle the case where the contact is not found, e.g., redirect to an error page or list page
        return "redirect:/admin/contact";
    }
    model.addAttribute("contact", contact);
    return "editcontactadmin";
}

}