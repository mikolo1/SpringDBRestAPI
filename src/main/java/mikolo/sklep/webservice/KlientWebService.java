package mikolo.sklep.webservice;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import mikolo.sklep.entity.Klient;
import mikolo.sklep.services.KlientService;

@AllArgsConstructor
@RestController
@RequestMapping(value="/sklep/service/klient")
public class KlientWebService {

	private KlientService klientService;
	private Gson gson;
	
	@GetMapping(value = "/id={id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getKlientById(@PathVariable("id") long id) {
		Klient klient = klientService.findById(id);
		return gson.toJson(klient);
	}
	
	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getAllKlientsByString() {
		List<Klient> klientList = klientService.findAll();
		return gson.toJson(klientList);
	}
	
	@GetMapping(value = "/name={name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getKlientByName(@PathVariable("name") String name) {
		List<Klient> klientList = klientService.findByImie(name);
		return gson.toJson(klientList);
	}
	
	@GetMapping(value = "/lastname={lastname}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getKlientByLastName(@PathVariable("lastname") String lastname) {
		List<Klient> klientList = klientService.findByNazwisko(lastname);
		return gson.toJson(klientList);
	}
	
	@GetMapping(value = "/phone={phone}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getKlientByPhone(@PathVariable("phone") String phone) {
		List<Klient> klientList = klientService.findByNtTel(phone);
		return gson.toJson(klientList);
	}
	
	@GetMapping(value = "/onephone={phone}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String getOneKlientByPhone(@PathVariable("phone") String phone) {
		Klient klient = klientService.findOneByNrTel(phone);
		return gson.toJson(klient);
	}
	
	@PostMapping(value = "/addklientwithaddress", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String addKlient(@RequestBody Klient klient) {
		klientService.addKlient(klient);
		return gson.toJson(klient);
	}
	
	@PostMapping(value = "/updateklient", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String updateKlient(@RequestBody Klient klient) {
		klientService.update(klient);
		return gson.toJson(klient);
	}
	
	@DeleteMapping(value = "/deleteid={id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private void deleteKlientById(@PathVariable("id") long id) {
		klientService.delete(id);
	}
	
}
