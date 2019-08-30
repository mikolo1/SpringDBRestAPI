package mikolo.sklep.webservice;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import mikolo.sklep.entity.Adres;
import mikolo.sklep.services.AdresService;

@AllArgsConstructor
@RestController
@RequestMapping(value="/sklep/service/adres")
public class AdresWebService {
	
	private AdresService adresService;
	private Gson gson;
	
	@GetMapping(value="/id={id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getById(@PathVariable("id") long id) {
		Adres adres = adresService.findById(id);
		return gson.toJson(adres);
	}
	
	@GetMapping(value="/klientid={id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByKlientId(@PathVariable("id") long id) {
		Adres adres = adresService.findByKlientId(id);
		return gson.toJson(adres);
	}
	
	@GetMapping(value="/ulica={ulica}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByUlica(@PathVariable("ulica") String ulica) {
		List<Adres>adresList = adresService.findByUlica(ulica);
		return gson.toJson(adresList);
	}
	
	@GetMapping(value="/miejscowosc={miejscowosc}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByMiejscowosc(@PathVariable("miejscowosc") String miejscowosc) {
		List<Adres>adresList =  adresService.findByMiescowosc(miejscowosc);
		return gson.toJson(adresList);
	}
	
	@GetMapping(value="/kodpocztowy={kodpocztowy}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getByKodPocztowy(@PathVariable("kodpocztowy") String kodPocztowy) {
		List<Adres>adresList = adresService.findByKodPocztowy(kodPocztowy);
		return gson.toJson(adresList);
	}
	
	@GetMapping(value="/getall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getall() {
		List<Adres>adresList =  adresService.findAll();
		return gson.toJson(adresList);
	}
	
	@PostMapping(value="/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String updateAdres(@RequestBody Adres adres) {	
		return gson.toJson(adresService.updateAdres(adres));
	}
	

}
