package by.sva.springBoot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// должен создть таблицу в БД, если ее нет. НО НЕ СОЗДАЕТ. MySQL создает
// таблицу person создал!!!

@Entity
// @Table(name = "post") // нужно указывать, если не совпадает с названием таблицы
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // уникальный сгенерированный идентификатор
	private long id;
	
	@NotEmpty(message = "Заголовок не должен быть пустым")
	//@Size(min=1, max=50, message = "Заголовок должен быть не более 50 символов")
	private String title;
	
	@NotEmpty(message = "Поле не должно быть пустым")
	private String anons;
	
	@NotEmpty(message = "Поле не должно быть пустым")
	private String text;
	private int views;
	
	public Post() {
		
	}
	
	public Post(String title, String anons, String text) {
		this.title = title;
		this.anons = anons;
		this.text = text;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnons() {
		return anons;
	}
	public void setAnons(String anons) {
		this.anons = anons;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	

}
