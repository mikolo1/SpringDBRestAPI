package mikolo.sklep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mikolo.sklep.dao.AdresDAO;
import mikolo.sklep.entity.Adres;
import mikolo.sklep.entity.Klient;

@Service
public class AdresService {

	@Autowired
	private AdresDAO adresDAO;

	@Autowired
	private KlientService klientService;

	public Adres findById(long id) {
		Adres adres = adresDAO.findById(id).get();
		getAdresWithKlient(adres);
		return adres;
	}

	public Adres findByKlient(Klient idKlienta) {
		Adres adres = adresDAO.findByKlient(idKlienta);
		getAdresWithKlient(adres);
		return adres;
	}

	public Adres findByKlientId(long klientId) {
		Adres adres = adresDAO.findByKlientId(klientId);
		getAdresWithKlient(adres);
		return adres;
	}

	public List<Adres> findByUlica(String ulica) {
		List<Adres> adresList = adresDAO.findByUlica(ulica);
		adresList.forEach(al -> getAdresWithKlient(al));
		return adresList;
	}

	public List<Adres> findByKodPocztowy(String kodPocztowy) {
		List<Adres> adresList = adresDAO.findByKodPocztowy(kodPocztowy);
		adresList.forEach(al -> getAdresWithKlient(al));
		return adresList;
	}

	public List<Adres> findByMiescowosc(String miejscowosc) {
		List<Adres> adresList = adresDAO.findByMiejsowosc(miejscowosc);
		adresList.forEach(al -> getAdresWithKlient(al));
		return adresList;
	}

	public List<Adres> findAll() {
		List<Adres> adresList = adresDAO.findAll();
		adresList.forEach(al -> getAdresWithKlient(al));
		return adresList;
	}

	public Adres updateAdres(Adres adres) {
		getAdresWithKlient(adres);
		adresDAO.save(adres);
		return adres;
	}

	public Adres addAdres(Adres adres) {
		adresDAO.save(adres);
		return adres;
	}

	private void getAdresWithKlient(Adres adres) {
		Klient klient = klientService.findById(adres.getIdKlienta().getId());
		klient.setAdres(null);
		adres.setIdKlienta(klient);
	}

}
