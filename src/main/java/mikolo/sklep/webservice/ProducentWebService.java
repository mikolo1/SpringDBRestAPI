package mikolo.sklep.webservice;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import mikolo.sklep.entity.Producent;
import mikolo.sklep.services.ProducentService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "sklep/service/producent")
public class ProducentWebService {

	private ProducentService producentService;
	private Gson gson;

	@GetMapping(value = "/id={id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getById(@PathVariable("id") long id) {
		Producent producent = producentService.findById(id);
		return gson.toJson(producent);
	}

	@GetMapping(value = "/nazwa={nazwa}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByNazwa(@PathVariable("nazwa") String nazwa) {
		List<Producent> producentList = producentService.findByNazwa(nazwa);
		return gson.toJson(producentList);
	}

	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getAll() {
		List<Producent> producentList = producentService.findAll();
		return gson.toJson(producentList);
	}

	@PostMapping(value = "/update",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String update(@RequestBody Producent producent) {
		return gson.toJson(producentService.updateOrAdd(producent));
	}

	@PostMapping(value = "/add",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String add(@RequestBody Producent producent) {
		return gson.toJson(producentService.updateOrAdd(producent));
	}

	@RequestMapping(value = "/delete&id={id}", method = RequestMethod.DELETE)
	private String delete(@PathVariable("id") long id) {
		if (producentService.delete(id)) {
			return "Producent usunięty.";
		}
		return "Producent ma powiązanie z produktem. Brak możliwości usunięcia.";
	}

}
