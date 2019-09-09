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
import mikolo.sklep.entity.Produkt;
import mikolo.sklep.services.ProduktService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "sklep/service/produkt")
public class ProduktWebService {

	private ProduktService produktService;
	private Gson gson;

	@GetMapping(value = "/id={id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getById(@PathVariable("id") long id) {
		Produkt produkt = produktService.findById(id);
		return gson.toJson(produkt);
	}

	@GetMapping(value = "/getlast", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getLast() {
		Produkt produkt = produktService.findLast();
		return gson.toJson(produkt);
	}

	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getAll() {
		List<Produkt> produkts = produktService.findAll();
		return gson.toJson(produkts);
	}

	@GetMapping(value = "/nazwa={nazwa}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByNazwa(@PathVariable("nazwa") String nazwa) {
		List<Produkt> produkts = produktService.findByNazwa(nazwa);
		return gson.toJson(produkts);
	}

	@GetMapping(value = "/cena={cena}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByCena(@PathVariable("cena") double cena) {
		List<Produkt> produkts = produktService.findByCena(cena);
		return gson.toJson(produkts);
	}

	@GetMapping(value = "/cenabiggerthan={cena}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByCenaBiggerThan(@PathVariable("cena") double cena) {
		List<Produkt> produkts = produktService.findByCenaBiggerThan(cena);
		return gson.toJson(produkts);
	}

	@GetMapping(value = "/cenalowerthan={cena}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByCenaLowerThan(@PathVariable("cena") double cena) {
		List<Produkt> produkts = produktService.findByCenaLowerThan(cena);
		return gson.toJson(produkts);
	}

	@PostMapping(value = "/update", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private Produkt update(@RequestBody Produkt produkt) {
		return produktService.update(produkt);
	}

	@PostMapping(value = "/add", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String add(@RequestBody Produkt produkt) {
		return gson.toJson(produktService.add(produkt));
	}

	@GetMapping(value = "/dodajzakup/produkt={produktId}&klient={klientId}")
	private void addZakupy(@PathVariable long produktId, @PathVariable long klientId) {
		produktService.addZakupy(produktId, klientId);
	}

	@RequestMapping(value = "/delete&id={id}", method = RequestMethod.DELETE)
	private String delete(@PathVariable("id") long id) {
		if (produktService.deleteById(id)) {
			return "Produkt usunięty.";
		}
		return "Produkt ma powiązanie z klientem. Brak możliwości usunięcia.";
	}

}
