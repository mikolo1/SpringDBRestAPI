package mikolo.sklep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mikolo.sklep.dao.ProduktDAO;
import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.entity.Klient;
import mikolo.sklep.entity.Producent;
import mikolo.sklep.entity.Produkt;

@Service
public class ProduktService {

	@Autowired
	private ProduktDAO produktDAO;

	public Produkt findById(long id) {
		Produkt produkt = produktDAO.findById(id).get();
		getProductWithClientAndCategoryAndProducent(produkt);
		return produkt;
	}
	
	public Produkt findLast() {
		Produkt produkt = produktDAO.findLast();
		getProductWithClientAndCategoryAndProducent(produkt);
		return produkt;
	}

	public List<Produkt> findProduktKupionyByIdKlienta(long KlientId) {
		List<Produkt> produktList = produktDAO.findByIdKlienta(KlientId);
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	public List<Produkt> findAll() {
		List<Produkt> produktList = produktDAO.findAll();
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	public List<Produkt> findByNazwa(String nazwa) {
		List<Produkt> produktList = produktDAO.findByNazwa(nazwa);
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	public List<Produkt> findByKategoriaId(long kategoriaId) {
		List<Produkt> produktList = produktDAO.findByIdKategori(kategoriaId);
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	public List<Produkt> findByProducentId(long producentId) {
		List<Produkt> produktList = produktDAO.findByIdKategori(producentId);
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	public List<Produkt> findByCena(double cena) {
		List<Produkt> produktList = produktDAO.findByCena(cena);
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	public List<Produkt> findByCenaBiggerThan(double cena) {
		List<Produkt> produktList = produktDAO.findByBiggerCena(cena);
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	public List<Produkt> findByCenaLowerThan(double cena) {
		List<Produkt> produktList = produktDAO.findByLowerCena(cena);
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	public List<Produkt> findByKlientId(long klintId) {
		List<Produkt> produktList = produktDAO.findByIdKlienta(klintId);
		produktList.forEach(pl -> getProductWithClientAndCategoryAndProducent(pl));
		return produktList;
	}

	@Transactional
	public Produkt update(Produkt produkt) {
			produktDAO.update(produkt.getId(), produkt.getCena(), produkt.getNazwa(), produkt.getIdKategori().getId(), produkt.getIdProducenta().getId());
		return produkt;
	}
	
	@Transactional
	public Produkt add(Produkt produkt) {
			produktDAO.save(produkt);
			return produkt;
	}
	
	public boolean deleteById(long id) {
		Produkt produkt = produktDAO.findById(id).get();
		List<Klient> klientList = produkt.getKlient();
		if(klientList.stream().anyMatch(k->k.getProduktList()!=null)){
			return false;
		}
		produktDAO.delete(produkt);
		return true;
	}
	
	@Transactional
	public void addZakupy(long produktId, long klientId) {
		produktDAO.addZakupy(produktId, klientId);
	}

	private void getProductWithClientAndCategoryAndProducent(Produkt produkt) {
		produkt.setKlient(null);
		KategoriaProduktu kategoriaProduktu = produkt.getIdKategori();
		kategoriaProduktu.setProduktSet(null);
		produkt.setIdKategori(kategoriaProduktu);
		Producent producent = produkt.getIdProducenta();
		producent.setProdukt(null);
		produkt.setIdProducenta(producent);
	}
}