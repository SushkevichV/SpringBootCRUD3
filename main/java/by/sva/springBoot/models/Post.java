package by.sva.springBoot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// должен создть таблицу в БД, если ее нет. НО НЕ СОЗДАЕТ. MySQL создает

@Entity
// @Table(name = "post') // вроде нужно указывать, но без нее тоже работает
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // уникальный сгенерированный идентификатор
	private long id;
	private String title;
	private String anons;
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
