package mikolo.sklep.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mikolo.sklep.dao.ProducentDAO;
import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.entity.Producent;
import mikolo.sklep.entity.Produkt;

@Service
public class ProducentService {

	@Autowired
	private ProducentDAO producentDAO;
	
	public Producent findById(long id) {
		Producent producent = producentDAO.findById(id).get();
		getProducentWithProductList(producent);
		return producent;
	}

	public List<Producent> findByNazwa(String nazwa) {
		List<Producent> producents = producentDAO.findByNazwa(nazwa);
		producents.forEach(p -> getProducentWithProductList(p));
		return producents;
	}

	public List<Producent> findAll() {
		List<Producent> producents = producentDAO.findAll();
		producents.forEach(p -> getProducentWithProductList(p));
		return producents;
	}

	@Transactional
	public Producent updateOrAdd(Producent producent) {
		producentDAO.save(producent);
		return producent;
	}

	public boolean delete(long id) {
		Producent producent = producentDAO.findById(id).get();
		Set<Produkt> produktSet = producent.getProdukt();

		if (produktSet.stream().anyMatch(p -> p.getIdProducenta() != null)) {
			return false;
		} 
			producentDAO.delete(producent);
			return true;
	}

	private void getProducentWithProductList(Producent producent) {
		Set<Produkt> produktset = producent.getProdukt();
		produktset.forEach(ps -> {
			ps.setIdProducenta(null);
			KategoriaProduktu kategoriaProduktu = ps.getIdKategori();
			kategoriaProduktu.setProduktSet(null);
			ps.setIdKategori(kategoriaProduktu);
			ps.setKlient(null);
		});
		producent.setProdukt(produktset);
	}
}
