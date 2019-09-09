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
import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.services.KategoriaProduktuService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "sklep/service/kategoria")
public class KategoriaProduktuWebService {

	private KategoriaProduktuService kategoriaProduktuService;
	private Gson gson;

	@GetMapping(value = "/id={id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getById(@PathVariable("id") long id) {
		KategoriaProduktu kategoriaProduktu = kategoriaProduktuService.findById(id);
		return gson.toJson(kategoriaProduktu);
	}

	@GetMapping(value = "/nazwa={nazwa}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByNazwa(@PathVariable("nazwa") String nazwa) {
		List<KategoriaProduktu> kategoriaList = kategoriaProduktuService.findByNazwa(nazwa);
		return gson.toJson(kategoriaList);
	}

	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getAll() {
		List<KategoriaProduktu> kategoriaList = kategoriaProduktuService.findAll();
		return gson.toJson(kategoriaList);
	}

	@PostMapping(value = "/update", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String update(@RequestBody KategoriaProduktu kategoriaProduktu) {
		return gson.toJson(kategoriaProduktuService.update(kategoriaProduktu));
	}

	@PostMapping(value = "/add", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String add(@RequestBody KategoriaProduktu kategoriaProduktu) {
		return gson.toJson(kategoriaProduktuService.update(kategoriaProduktu));
	}

	@RequestMapping(value = "/delete&id={id}", method = RequestMethod.DELETE)
	private String delete(@PathVariable("id") long id) {
		if (kategoriaProduktuService.delete(id)) {
			return "Kategoria usunięta.";
		}
		return "Kategoria ma powiązanie z produktem. Brak możliwości usunięcia.";
	}

}
