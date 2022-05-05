package by.sva.springBoot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.sva.springBoot.models.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{ // <Model, Тип данных уникального идентификатора>

}
