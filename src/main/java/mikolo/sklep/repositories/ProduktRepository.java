package mikolo.sklep.repositories;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.entity.Producent;
import mikolo.sklep.entity.Produkt;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class ProduktRepository {

	public Produkt findById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Produkt produkt = session.createQuery("from Produkt WHERE id=:id", Produkt.class).setParameter("id", id)
				.getSingleResult();
		session.close();
		return produkt;
	}

	public Produkt findByProducentId(Producent idProducenta) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Produkt produkt = session.createQuery("from Produkt WHERE idProducenta=:idProducenta", Produkt.class)
				.setParameter("idProducenta", idProducenta).getSingleResult();
		session.close();
		return produkt;
	}

	public Produkt findByKategoriaId(KategoriaProduktu idKategori) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Produkt produkt = session.createQuery("from Produkt WHERE idKategori=:idKategori", Produkt.class)
				.setParameter("idKategori", idKategori).getSingleResult();
		session.close();
		return produkt;
	}

	public List<Produkt> findByNazwa(String nazwa) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Produkt> produktList = session
				.createQuery("from Produkt WHERE nazwa LIKE CONCAT('%',:nazwa,'%')", Produkt.class)
				.setParameter("nazwa", nazwa).getResultList();
		session.close();
		return produktList;
	}

	public List<Produkt> findByCena(double cena) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Produkt> produktList = session.createQuery("from Produkt WHERE cena =:cena)", Produkt.class)
				.setParameter("cena", cena).getResultList();
		session.close();
		return produktList;
	}

	public Produkt addProdukt(Produkt produkt) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(produkt);
		session.flush();
		session.close();
		return produkt;
	}

	public Produkt addProduktWithProducentAndKategoriaProduktu(Produkt produkt, Producent producent,
			KategoriaProduktu kategoriaProduktu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		produkt.setIdProducenta(producent);
		produkt.setIdKategori(kategoriaProduktu);
		session.persist(produkt);
		session.flush();
		session.close();
		return produkt;
	}

	public Produkt updateProdukt(Produkt produkt) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		produkt = (Produkt) session.merge(produkt);
		session.flush();
		session.close();
		return produkt;
	}

	public void deleteProduktById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Produkt usuwany = findById(id);
		if (usuwany != null) {
			session.remove(usuwany);
		}
		session.flush();
		session.close();
	}

	public List<Produkt> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Produkt> produktList = session.createNativeQuery("select * from produkt", Produkt.class).getResultList();
		session.close();
		return produktList;
	}

	public List<Produkt> findProduktKupionyByIdProduktu(long idProduktu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Produkt> produktList = session.createNativeQuery(
				"SELECT p.* from produkt p join zakupy z on z.id_produkt = p.id_produktu WHERE p.id_produktu=:idProduktu",
				Produkt.class).setParameter("idProduktu", idProduktu).getResultList();
		session.close();
		return produktList;
	}

	public List<Produkt> findProduktKupionyByIdKlienta(long idKlient) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Produkt> produktList = session.createNativeQuery(
				"SELECT p.* from produkt p join zakupy z on z.id_produkt = p.id_produktu WHERE z.id_klient=:idKlient",
				Produkt.class).setParameter("idKlient", idKlient).getResultList();
		session.close();
		return produktList;
	}
}
