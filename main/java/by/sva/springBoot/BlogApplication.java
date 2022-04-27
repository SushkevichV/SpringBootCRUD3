package by.sva.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// создание проекта https://start.spring.io/
//  примеры	https:/spring.io/guides/ в поиске задать MVC 
//		или	https://spring.io/guides/gs/serving-web-content/
// на русском https://spring-projects.ru/guides/handling-form-submission/

// в файле applicaton.properties прописаны настройки для доступа к БД
// также там можно прописать порт localhost и другие настройки

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
