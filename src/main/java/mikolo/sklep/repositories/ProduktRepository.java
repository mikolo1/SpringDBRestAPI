package mikolo.sklep.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.entity.Producent;
import mikolo.sklep.entity.Produkt;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class ProduktRepository {

    public Produkt findById(long id) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        Produkt produkt = em.createQuery("from Produkt WHERE id=:id", Produkt.class).setParameter("id", id).getSingleResult();
        em.close();
        return produkt;
    }

    public Produkt findByProducentId(Producent idProducenta) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        Produkt produkt = em.createQuery("from Produkt WHERE idProducenta=:idProducenta", Produkt.class).setParameter("idProducenta", idProducenta).getSingleResult();
        em.close();
        return produkt;
    }

    public Produkt findByKategoriaId(KategoriaProduktu idKategori) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        Produkt produkt = em.createQuery("from Produkt WHERE idKategori=:idKategori", Produkt.class).setParameter("idKategori", idKategori).getSingleResult();
        em.close();
        return produkt;
    }

    public List<Produkt> findByNazwa(String nazwa) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Produkt> produktList = em.createQuery("from Produkt WHERE nazwa LIKE CONCAT('%',:nazwa,'%')", Produkt.class).setParameter("nazwa", nazwa).getResultList();
        em.close();
        return produktList;
    }

    public List<Produkt> findByCena(double cena) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Produkt> produktList = em.createQuery("from Produkt WHERE cena =:cena)", Produkt.class).setParameter("cena", cena).getResultList();
        em.close();
        return produktList;
    }

    public Produkt addProdukt(Produkt produkt){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        em.persist(produkt);
        em.close();
        return produkt;
    }

    public Produkt addProduktWithProducentAndKategoriaProduktu(Produkt produkt, Producent producent, KategoriaProduktu kategoriaProduktu){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        produkt.setIdProducenta(producent);
        produkt.setIdKategori(kategoriaProduktu);
        em.persist(produkt);
        em.close();
        return produkt;
    }

    public Produkt updateProdukt(Produkt produkt){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        produkt = (Produkt)em.merge(produkt);
        em.close();
        return produkt;
    }

    public void deleteProduktById(long id){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        Produkt usuwany = findById(id);
        if(usuwany!=null) {
        	em.remove(usuwany);
        }
        em.close();
    }

    public List<Produkt> findAll(){
    	Session em = HibernateUtil.getSessionFactory().openSession();
        List<Produkt> produktList = em.createNativeQuery("select * from produkt", Produkt.class).getResultList();
        em.close();
        return produktList;
    }


    public List<Produkt> findProduktKupionyByIdProduktu(long idProduktu){
    	Session em = HibernateUtil.getSessionFactory().openSession();
        List<Produkt> produktList = em.createNativeQuery("SELECT p.* from produkt p join zakupy z on z.id_produkt = p.id_produktu WHERE p.id_produktu=:idProduktu", Produkt.class).setParameter("idProduktu", idProduktu).getResultList();
        em.close();
        return produktList;
    }

    public List<Produkt> findProduktKupionyByIdKlienta(long idKlient){
    	Session em = HibernateUtil.getSessionFactory().openSession();
        List<Produkt> produktList = em.createNativeQuery("SELECT p.* from produkt p join zakupy z on z.id_produkt = p.id_produktu WHERE z.id_klient=:idKlient", Produkt.class).setParameter("idKlient", idKlient).getResultList();
        em.close();
        return produktList;
    }
}
