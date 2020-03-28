package com.example.servingwebcontent.repositories;

import com.example.servingwebcontent.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person, Integer> {

}
