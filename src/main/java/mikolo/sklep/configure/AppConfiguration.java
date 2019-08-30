package mikolo.sklep.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class AppConfiguration {

	@Bean
	public Gson gson() {
		return new GsonBuilder().setPrettyPrinting().create();
	}
}
