    package main.java.Vitebsk.ATP4.controllers;

    import java.util.ArrayList;
    import java.util.Optional;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import main.java.Vitebsk.ATP4.models.Contact;
    import main.java.Vitebsk.ATP4.repo.ContactRepository;

    @Controller
    public class ContactController 
    {
        @Autowired
        private ContactRepository contactRepository;
    
        @GetMapping("/contacts")
        public String news(Model model)
        {
            Iterable<Contact> contacts = contactRepository.findAll();
            model.addAttribute("contacts", contacts);
                return "contacts";   
        }
    
        @GetMapping("/new-contact")
        public String newsAdd(Model model)
        {
            
                return "/new-contact";   
        }
    
        @PostMapping("/new-contact")
        public String newsPostAdd(@RequestParam String full_txt, @RequestParam String name, @RequestParam String mobail, Model model)
        {
            Contact contact = new Contact(name, full_txt, mobail);
            contactRepository.save(contact);
            return "redirect:/contacts";
        }
    
        @GetMapping("/contacts/{id}")
        public String newsDetails(@PathVariable(value = "id") long id, Model model)
        {
            if(!contactRepository.existsById(id))
            {
            return "redirect:/contacts";
            }
            
            Optional<Contact> contact = contactRepository.findById(id);
            ArrayList <Contact> res = new ArrayList<>();
            contact.ifPresent(res::add);
            model.addAttribute("contact", res);
                return "contacts";    
        }
    
        @GetMapping("/contacts/{id}/edit")
        public String newsEdit(@PathVariable(value = "id") long id, Model model)
        {
            if(!contactRepository.existsById(id))
            {
            return "redirect:/contacts";
            }
            Optional<Contact> contact = contactRepository.findById(id);
            ArrayList <Contact> res = new ArrayList<>();
            contact.ifPresent(res::add);
            model.addAttribute("post", res);
                return "contact-edit";    
        }
        
        @PostMapping("/contacts/{id}/edit")
        public String newsPostUpdate(@PathVariable(value = "id") long id, @RequestParam String full_txt, @RequestParam String name, @RequestParam String mobail, Model model)
        {
            Contact contact = contactRepository.findById(id).orElseThrow();
            contact.setName(name);
            contact.setMobail(mobail);
            contact.setFull_txt(full_txt);
            contactRepository.save(contact);
            return "redirect:/contacts";
        }
    
        @PostMapping("/contacts/{id}/remove")
        public String newsPostDell(@PathVariable(value = "id") long id, Model model)
        {
            Contact contact = contactRepository.findById(id).orElseThrow();
            contactRepository.delete(contact);
            return "redirect:/contacts";
        }
    }