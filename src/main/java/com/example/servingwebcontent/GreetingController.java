package com.example.servingwebcontent;
import com.example.servingwebcontent.domain.Person;
import com.example.servingwebcontent.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String get(Map<String, Object> model){
        Iterable<Person> people = personRepo.findAll();
        model.put("people", people);
        return "greeting";
    }

    @PostMapping
    public String add(@RequestParam String name, @RequestParam String surname, @RequestParam Integer age,  Map<String, Object> model){
        Person person = new Person(name, surname, age);
        personRepo.save(person);
        Iterable<Person> people = personRepo.findAll();
        model.put("people", people);
        return "greeting";
    }

    @PostMapping("/delete")
    public String  delete(@RequestParam String name, Map<String, Object> model) {
        Iterable<Person> people = personRepo.findAll();
        for (Person person : people){
            if (person.getName().equals(name))
                personRepo.delete(person);
        }
        model.put("people", people);
        return "greeting";
    }
}
