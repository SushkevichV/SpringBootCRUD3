package by.sva.springBoot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import by.sva.springBoot.models.Post;

public interface PostRepositoryJPA extends JpaRepository<Post, Long> { // <Model, Тип данных уникального идентификатора>

}
