package by.sva.springBoot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.sva.springBoot.models.Post;
import by.sva.springBoot.repo.PostRepositoryCRUD;
import by.sva.springBoot.repo.PostRepositoryJPA;

@Controller
public class BlogController {
	
	@Autowired
	//private PostRepositoryCRUD postRepositoryCRUD;
	private PostRepositoryJPA postRepositoryJPA;
	
	@GetMapping("/blog") 
	public String mainBlog(Model model) {
		model.addAttribute("title", "Блог");
		//Iterable<Post> posts = postRepositoryCRUD.findAll(); // получить все записи
		/* Вместо интерфейса PostRepositoryCRUD можно использовать интерфейс postRepositoryJPA
		 * В этом случае вместо Iterable можно использовать List
		 */
		List<Post> posts = postRepositoryJPA.findAll();
		model.addAttribute("posts", posts); 			// и положить их в модель
		return "mainBlog";
	}
	
	@GetMapping("/blog/add") 
	public String addBlog(Model model) {
		model.addAttribute("title", "Новая статья");
		model.addAttribute("post", new Post()); // передать в форму Post
		return "addBlog";
	}
	
	@PostMapping("/blog/add") // отслеживание post-запроса
	/*public String newPost(@RequestParam String title, @RequestParam String anons, @RequestParam String text, Model model) {
		Post post = new Post(title, anons, text);
		//postRepositoryCRUD.save(post);
		postRepositoryJPA.save(post);
		return "redirect:/blog"; // переадресация на страницу
	}
	*/
	public String newPost(@ModelAttribute("post") @Valid Post post, BindingResult br, @RequestParam String title, @RequestParam String anons, @RequestParam String text, Model model) {
		if(br.hasErrors())
			return "addBlog";
		post = new Post(title, anons, text);
		//postRepositoryCRUD.save(post);
		postRepositoryJPA.save(post);
		return "redirect:/blog"; // переадресация на страницу
	}
	
	@GetMapping("/blog/{id}")
	public String postDetails(@PathVariable(value = "id") long id, Model model) {
		//if(!postRepositoryCRUD.existsById(id)) { // если запись БД не найдена
		if(!postRepositoryJPA.existsById(id)) {
			return "redirect:/blog";		// переход на другую страницу
		}
		
		// Дальше идет странный блок
		//Optional<Post> post = postRepositoryCRUD.findById(id); // почему-то Optional нельзя передать в Model
		Optional<Post> post = postRepositoryJPA.findById(id);
		ArrayList<Post> posts = new ArrayList<>(); // нужно создать массив
		post.ifPresent(posts::add); // и туда положить этот Optional
		
		// хотя можно получить доступ к полям и передать их в Model
		model.addAttribute("title", post.get().getTitle());
		
		// можно вообще получить сам объект Post
		Post post2 = postRepositoryJPA.findById(id).orElseThrow(); // выбрасывает исключение, если объект не был найден
		post2.setViews(post2.getViews()+1); // при просмотре увеличить счетчик
		postRepositoryJPA.save(post2);
		
		model.addAttribute("posts", posts);
		return "/post";
	}
	
	@GetMapping("/blog/{id}/edit")
	public String editPost(@PathVariable(value = "id") long id, Model model) {
		//if(!postRepositoryCRUD.existsById(id)) { // если запись БД не найдена
		if(!postRepositoryJPA.existsById(id)) {
			return "redirect:/blog";		// переход на другую страницу
		}
		
		// Дальше идет странный блок. Оставлю как пример, может пригодиться
		//Optional<Post> postOptional = postRepositoryCRUD.findById(id); // почему-то Optional нельзя передать в Model
		Optional<Post> postOptional = postRepositoryJPA.findById(id);
		ArrayList<Post> posts = new ArrayList<>(); // нужно создать массив
		postOptional.ifPresent(posts::add); // и туда положить этот Optional
		
		Post post = postOptional.get(); //извлечь Post из Optional
		model.addAttribute("title", post.getTitle());
		model.addAttribute("post", post);
		
		return "/editPost";
	}
	
	@PostMapping("/blog/{id}/edit") // отслеживание post-запроса
	
	/*public String updatePost(@ModelAttribute("post") @Valid Post post, BindingResult br, @PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String text, Model model) {
		// Получить объект Post
		if(br.hasErrors())
			return "editPost";
		post = postRepositoryJPA.findById(id).orElseThrow(); // выбрасывает исключение, если объект не был найден
																 // т.к. findById возвращает Optional, т.е. объект может быть, а может и не быть
		post.setTitle(title);
		post.setAnons(anons);
		post.setText(text);
		
		postRepositoryJPA.save(post); // заменяет существующую запись
		return "redirect:/blog"; // переадресация на страницу
	}
	*/
	public String updatePost(@ModelAttribute("post") @Valid Post post, BindingResult br, @PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String text, Model model) {
		// Получить объект Post
		if(br.hasErrors())
			return "editPost";
		post = postRepositoryJPA.findById(id).orElseThrow(); // выбрасывает исключение, если объект не был найден
																 // т.к. findById возвращает Optional, т.е. объект может быть, а может и не быть
		post.setTitle(title);
		post.setAnons(anons);
		post.setText(text);
		
		postRepositoryJPA.save(post); // заменяет существующую запись
		return "redirect:/blog"; // переадресация на страницу
	}
	
	@PostMapping("/blog/{id}/delete") // отслеживание post-запроса
	public String deletePost(@PathVariable(value = "id") long id, Model model) {
		Post post = postRepositoryJPA.findById(id).orElseThrow(); // выбрасывает исключение, если объект не был найден
																 // т.к. findById возвращает Optional, т.е. объект может быть, а может и не быть
		
		postRepositoryJPA.delete(post); // удаляет существующую запись
		return "redirect:/blog"; // переадресация на страницу
	}

}
