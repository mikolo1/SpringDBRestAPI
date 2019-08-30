package mikolo.sklep.repositories;

import java.util.List;

import javax.persistence.EntityManager;
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
		EntityManager em = HibernateUtil.getSessionFactory().openSession();
		Klient klient = em.createQuery("from Klient WHERE id=:id", Klient.class).setParameter("id", id)
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

		klient.setProduktList(produkty);
		em.close();
		return klient;
	}

	public List<Klient> findAll() {
		EntityManager em = HibernateUtil.getSessionFactory().openSession();
		List<Klient> klientList = em.createQuery("from Klient", Klient.class).getResultList();
		addProductsToKlientList(klientList);
		em.close();
		return klientList;
	}

	public List<Klient> findByImie(String imie) {
		EntityManager em = HibernateUtil.getSessionFactory().openSession();
		List<Klient> klientList = em.createQuery("from Klient WHERE imie LIKE CONCAT('%',:imie,'%')", Klient.class)
				.setParameter("imie", imie).getResultList();
		addProductsToKlientList(klientList);
		em.close();
		return klientList;
	}

	public List<Klient> findByNazwisko(String nazwisko) {
		EntityManager em = HibernateUtil.getSessionFactory().openSession();
		List<Klient> klientList = em
				.createQuery("from Klient WHERE nazwisko LIKE CONCAT('%',:nazwisko,'%')", Klient.class)
				.setParameter("nazwisko", nazwisko).getResultList();
		addProductsToKlientList(klientList);
		em.close();
		return klientList;
	}

	public List<Klient> findByNrTelefonu(String nrTelefonu) {
		EntityManager em = HibernateUtil.getSessionFactory().openSession();
		List<Klient> klientList = em
				.createQuery("from Klient WHERE nrTelefonu LIKE CONCAT('%',:nrTelefonu,'%')", Klient.class)
				.setParameter("nrTelefonu", nrTelefonu).getResultList();
		addProductsToKlientList(klientList);
		em.close();
		return klientList;
	}

	public Klient findOneByNrTelefonu(String nrTelefonu) {
		EntityManager em = HibernateUtil.getSessionFactory().openSession();
		Klient klient = em.createQuery("from Klient WHERE nrTelefonu =:nrTelefonu", Klient.class)
				.setParameter("nrTelefonu", nrTelefonu).getSingleResult();
		em.close();
		return klient;
	}

	@Transactional
	public Klient addKlient(Klient klient, Adres adres) {
		Session em = HibernateUtil.getSessionFactory().openSession();
		em.persist(adres);
		em.close();
		return klient;
	}


	public Klient addKlient(Klient klient) {
		Session em = HibernateUtil.getSessionFactory().openSession();
		Adres adres = klient.getAdres();
		adres.setIdKlienta(klient);
		em.merge(adres);
		em.close();
		return klient;
	}

	public Klient updateKlient(Klient klient) {
		EntityManager em = HibernateUtil.getSessionFactory().openSession();
		klient = (Klient) em.merge(klient);
		em.close();
		return klient;
	}

	public void deleteKlientById(long id) {
		EntityManager em = HibernateUtil.getSessionFactory().openSession();
		Klient usuwany = findById(id);
		if (usuwany != null) {
			Adres adres = usuwany.getAdres();
			em.remove(adres);
		}
		em.close();
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
