package mikolo.sklep.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import mikolo.sklep.entity.Adres;
import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.entity.Klient;
import mikolo.sklep.entity.Producent;
import mikolo.sklep.entity.Produkt;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class KlientRepository {

	KategoriaProduktuRepository kategoriaProduktuRepository = new KategoriaProduktuRepository();
	ProduktRepository produktRepository = new ProduktRepository();
	ProducentRepository producentRepository = new ProducentRepository();
	AdresRespository adresRepository = new AdresRespository();

	@Transactional
	public Klient findById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Klient klient = session.createQuery("from Klient WHERE id=:id", Klient.class).setParameter("id", id)
				.getSingleResult();

		List<Produkt> produkty = produktRepository.findProduktKupionyByIdKlienta(id);

		produkty.forEach(p -> {
			p.setKlient(null);
			KategoriaProduktu katProduktu = kategoriaProduktuRepository.findById(p.getIdKategori().getId());
			p.setIdKategori(katProduktu);
			katProduktu.setProduktSet(null);
			Producent producent = producentRepository.findById(p.getIdProducenta().getId());
			producent.setProdukt(null);
			p.setIdProducenta(producent);
		});
		session.close();
		return klient;
	}

	public List<Klient> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Klient> klientList = session.createQuery("from Klient", Klient.class).getResultList();
		addProductsToKlientList(klientList);
		session.close();
		return klientList;
	}

	public List<Klient> findByImie(String imie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Klient> klientList = session.createQuery("from Klient WHERE imie LIKE CONCAT('%',:imie,'%')", Klient.class)
				.setParameter("imie", imie).getResultList();
		addProductsToKlientList(klientList);
		session.close();
		return klientList;
	}

	public List<Klient> findByNazwisko(String nazwisko) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Klient> klientList = session
				.createQuery("from Klient WHERE nazwisko LIKE CONCAT('%',:nazwisko,'%')", Klient.class)
				.setParameter("nazwisko", nazwisko).getResultList();
		addProductsToKlientList(klientList);
		session.close();
		return klientList;
	}

	public List<Klient> findByNrTelefonu(String nrTelefonu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Klient> klientList = session
				.createQuery("from Klient WHERE nrTelefonu LIKE CONCAT('%',:nrTelefonu,'%')", Klient.class)
				.setParameter("nrTelefonu", nrTelefonu).getResultList();
		addProductsToKlientList(klientList);
		session.close();
		return klientList;
	}

	public Klient findOneByNrTelefonu(String nrTelefonu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Klient klient = session.createQuery("from Klient WHERE nrTelefonu =:nrTelefonu", Klient.class)
				.setParameter("nrTelefonu", nrTelefonu).getSingleResult();
		List<Produkt> produkty = produktRepository.findProduktKupionyByIdKlienta(klient.getId());

		produkty.forEach(p -> {
			p.setKlient(null);
			KategoriaProduktu katProduktu = kategoriaProduktuRepository.findById(p.getIdKategori().getId());
			p.setIdKategori(katProduktu);
			katProduktu.setProduktSet(null);
			Producent producent = producentRepository.findById(p.getIdProducenta().getId());
			producent.setProdukt(null);
			p.setIdProducenta(producent);
		});
		klient.setProduktList(produkty);
		session.close();
		return klient;
	}

	@Transactional
	public Klient addKlient(Klient klient, Adres adres) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(adres);
		session.flush();
		session.close();
		return klient;
	}


	public Klient addKlient(Klient klient) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Adres adres = klient.getAdres();
		adres.setIdKlienta(klient);
		session.merge(adres);
		session.flush();
		session.close();
		return klient;
	}

	public Klient updateKlient(Klient klient) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		klient = (Klient) session.merge(klient);
		session.flush();
		session.close();
		return klient;
	}

	public void deleteKlientById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Klient usuwany = findById(id);
		if (usuwany != null) {
			Adres adres = usuwany.getAdres();
			session.remove(adres);
		}
		session.flush();
		session.close();
	}

	private void addProductsToKlientList(List<Klient> klientList) {
		klientList.forEach(k -> {
			List<Produkt> produkty = produktRepository.findProduktKupionyByIdKlienta(k.getId());

			produkty.forEach(p -> {
				p.setKlient(null);
				KategoriaProduktu katProduktu = kategoriaProduktuRepository.findById(p.getIdKategori().getId());
				p.setIdKategori(katProduktu);
				katProduktu.setProduktSet(null);
				Producent producent = producentRepository.findById(p.getIdProducenta().getId());
				producent.setProdukt(null);
				p.setIdProducenta(producent);
			});
			Adres adres = adresRepository.findByKlientId(k);
			adres.setIdKlienta(null);
			k.setAdres(adres);
			k.setProduktList(produkty);
		});
	}
}
