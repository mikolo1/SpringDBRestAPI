package mikolo.sklep.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mikolo.sklep.dao.KategoriaProduktuDAO;
import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.entity.Producent;
import mikolo.sklep.entity.Produkt;

@Service
public class KategoriaProduktuService {

	@Autowired
	private KategoriaProduktuDAO kategoriaProduktuDAO;

	public KategoriaProduktu findById(long id) {
		KategoriaProduktu kategoriaProduktu = kategoriaProduktuDAO.findById(id).get();
		getProducentWithProductList(kategoriaProduktu);
		return kategoriaProduktu;
	}

	public List<KategoriaProduktu> findByNazwa(String nazwa) {
		List<KategoriaProduktu> kategoriaProduktus = kategoriaProduktuDAO.findByNazwa(nazwa);
		kategoriaProduktus.forEach(p -> getProducentWithProductList(p));
		return kategoriaProduktus;
	}

	public List<KategoriaProduktu> findAll() {
		List<KategoriaProduktu> kategoriaProduktus = kategoriaProduktuDAO.findAll();
		kategoriaProduktus.forEach(p -> getProducentWithProductList(p));
		return kategoriaProduktus;
	}

	public KategoriaProduktu update(KategoriaProduktu kategoriaProduktu) {
		kategoriaProduktuDAO.save(kategoriaProduktu);
		return kategoriaProduktu;
	}

	public boolean delete(long id) {
		KategoriaProduktu kategoriaProduktu = kategoriaProduktuDAO.findById(id).get();
		Set<Produkt> produktSet = kategoriaProduktu.getProduktSet();

		if (produktSet.stream().anyMatch(p -> p.getIdKategori() != null)) {
			return false;
		}
		kategoriaProduktuDAO.delete(kategoriaProduktu);
		return true;
	}

	private void getProducentWithProductList(KategoriaProduktu kategoriaProduktu) {
		Set<Produkt> produktset = kategoriaProduktu.getProduktSet();
		produktset.forEach(ps -> {
			ps.setIdKategori(null);
			Producent producent = ps.getIdProducenta();
			producent.setProdukt(null);
			ps.setIdProducenta(producent);
			ps.setKlient(null);
		});
		kategoriaProduktu.setProduktSet(produktset);
	}
}
