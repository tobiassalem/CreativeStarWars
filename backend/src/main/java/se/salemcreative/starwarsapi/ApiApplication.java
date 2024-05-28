package se.salemcreative.starwarsapi;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import se.salemcreative.starwarsapi.service.DataService;

@SpringBootApplication
@Slf4j
public class ApiApplication {

	@Autowired
	DataService dataService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@PostConstruct
	@Transactional
	public void initData() {
		dataService.initData();
	}

}
