package by.sva.springBoot.repo;

import org.springframework.data.repository.CrudRepository;

import by.sva.springBoot.models.Post;

public interface PostRepositoryCRUD extends CrudRepository<Post, Long> { // <Model, Тип данных уникального идентификатора>

}
