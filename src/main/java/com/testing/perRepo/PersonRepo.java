package com.testing.perRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.entity.Person;

public interface PersonRepo extends JpaRepository<Person, Long> {

}
