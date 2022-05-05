package by.sva.springBoot.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// автоматически создает таблицу person, если ее нет!
@Entity
public class Person {
	@Id
	private int id;
	
	@NotEmpty(message = "Имя не должно быть пустым")
	@Size(min=2, max=20, message = "Имя должно быть от 2 до 20 символов")
	private String name;
	
	@NotEmpty(message = "E-mail не должен быть пустым")
	@Email(message = "Неверный e-mail")
	private String email;
	
	public Person() {
		
	}
	
	public Person(int id, String name, String email) {
		this.id = 1; // временная реализация
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
