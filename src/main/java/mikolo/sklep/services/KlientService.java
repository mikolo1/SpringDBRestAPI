package mikolo.sklep.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mikolo.sklep.dao.AdresDAO;
import mikolo.sklep.dao.KlientDAO;
import mikolo.sklep.entity.Adres;
import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.entity.Klient;
import mikolo.sklep.entity.Producent;
import mikolo.sklep.entity.Produkt;

@Service
public class KlientService {

	@Autowired
	private AdresDAO adresDAO;

	@Autowired
	private KlientDAO klientDAO;

	public Klient findById(long id) {
		Klient klient = klientDAO.findById(id).get();
		List<Produkt> produkts = getKlientWithProducts(klient);
		klient.setProduktList(produkts);
		return klient;
	}

	public List<Klient> findAll() {
		List<Klient> klientList = klientDAO.findAll();
		klientList.forEach(k -> {
			List<Produkt> produkts = getKlientWithProducts(k);
			k.setProduktList(produkts);
		});
		return klientList;
	}

	public List<Klient> findByImie(String imie) {
		List<Klient> klientList = klientDAO.findByImie(imie);
		klientList.forEach(k -> {
			List<Produkt> produkts = getKlientWithProducts(k);
			k.setProduktList(produkts);
		});
		return klientList;
	}

	public List<Klient> findByNazwisko(String nazwisko) {
		List<Klient> klientList = klientDAO.findByNazwisko(nazwisko);
		klientList.forEach(k -> {
			List<Produkt> produkts = getKlientWithProducts(k);
			k.setProduktList(produkts);
		});
		return klientList;
	}

	public List<Klient> findByNtTel(String nrTel) {
		List<Klient> klientList = klientDAO.findByPhone(nrTel);
		klientList.forEach(k -> {
			List<Produkt> produkts = getKlientWithProducts(k);
			k.setProduktList(produkts);
		});
		return klientList;
	}

	public Klient findOneByNrTel(String nrTel) {
		Klient klient = klientDAO.findOneByPhone(nrTel);
		List<Produkt> produkts = getKlientWithProducts(klient);
		klient.setProduktList(produkts);
		return klient;
	}

	public Klient addKlient(Klient klient) {
		Adres adres = klient.getAdres();
		adres.setIdKlienta(klient);
		adresDAO.save(adres);
		return klient;
	}

	public Klient update(Klient klient) {
		Adres adres = klient.getAdres();
		if (adres != null) {
			adres.setIdKlienta(klient);
			adresDAO.save(adres);
			adres.setIdKlienta(null);
		} else {
			klientDAO.save(klient);
		}

		return klient;
	}

	@Transactional
	public void delete(long id) {
		Adres adres = adresDAO.findByKlient(findById(id));
		if (adres != null) {
			adresDAO.deleteByKlientId(id);
		}
		klientDAO.deleteById(id);
	}

	private List<Produkt> getKlientWithProducts(Klient klient) {
		Adres adres = klient.getAdres();
		if (adres != null) {
			adres.setIdKlienta(null);
			klient.setAdres(adres);
		}
		List<Produkt> produkts = klient.getProduktList();
		produkts.forEach(p -> {
			p.setKlient(null);
			KategoriaProduktu kategoriaProduktu = p.getIdKategori();
			kategoriaProduktu.setProduktSet(null);
			Producent producent = p.getIdProducenta();
			producent.setProdukt(null);
		});
		return produkts;
	}

}
