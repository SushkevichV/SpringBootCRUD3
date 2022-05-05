package by.sva.springBoot.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import by.sva.springBoot.models.Person;

@Controller
public class MainController {
	
	@GetMapping("/") // домашняя страница
	public String home(Model model) {
		model.addAttribute("title", "Главная страница");
		return "home";
	}

	@GetMapping("/about") // домашняя страница
	public String about(Model model) {
		model.addAttribute("title", "Cтраница про нас");
		return "about";
	}
	
	@GetMapping("/enter") // домашняя страница
	public String enter(Model model) {
		model.addAttribute("title", "Авторизация");
		model.addAttribute("person", new Person()); // передать в форму Person
		return "autorization";
	}
	/* Вместо этого метода можно использовать следующий
	 public String enter(@ModelAttribute("person") Person person) {
		// будет создан новый объект Person со значениями полей поумолчанию
		// и помещен в Model с именем "person"
		return "people/new";
	 }
	*/
	
	@PostMapping("/enter") // входящий параметр не указан
	public String autorization(@ModelAttribute("person") @Valid Person person, BindingResult br) {
		// аннотация @ModelAttribute перед параметром person создает объект Person
		// со всеми значениями полей полученного person и добавляет его в model
		
		if(br.hasErrors())
			return "autorization";
		return "redirect:/blog"; // переадресация
	}
}