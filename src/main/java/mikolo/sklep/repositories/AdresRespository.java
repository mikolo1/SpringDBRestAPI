package mikolo.sklep.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import mikolo.sklep.entity.Adres;
import mikolo.sklep.entity.Klient;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class AdresRespository {
	
    public mikolo.sklep.entity.Adres findById(long id) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        Adres adres = em.createQuery("from Adres WHERE id=:id", Adres.class).setParameter("id", id).getSingleResult();
        em.close();
        return adres;
    }

    public List<Adres> findAll(){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresList = em.createQuery("from Adres", Adres.class).getResultList();
        em.close();
        return adresList;
    }

    public Adres findByKlientId(Klient idKlienta) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        Adres adres = em.createQuery("from Adres WHERE idKlienta=:idKlienta", Adres.class).setParameter("idKlienta", idKlienta).getSingleResult();
        em.close();
        return adres;
    }

    public List<Adres> findByUlica(String ulica) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = em.createQuery("from Adres WHERE ulica LIKE CONCAT('%',:gatunek,'%')", Adres.class).setParameter("ulica", ulica).getResultList();
        em.close();
        return adresLlist;
    }

    public List<Adres> findByNrDomu(String nrDomu) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = em.createQuery("from Adres WHERE nrDomu LIKE CONCAT('%',:nrdomu,'%')", Adres.class).setParameter("nrdomu", nrDomu).getResultList();
        em.close();
        return adresLlist;
    }

    public List<Adres> findByNrMieszkania(String nrMieszkania) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = em.createQuery("from Adres WHERE nrMieszkania LIKE CONCAT('%',:nrmieszkania,'%')", Adres.class).setParameter("nrmieszkania", nrMieszkania).getResultList();
        em.close();
        return adresLlist;
    }

    public List<Adres> findByMiejscowosc(String miejscowosc) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = em.createQuery("from Adres WHERE miejscowosc LIKE CONCAT('%',:miejscowosc,'%')", Adres.class).setParameter("miejscowosc", miejscowosc).getResultList();
        em.close();
        return adresLlist;
    }

    public List<Adres> findByKodPocztowy(String kodPocztowy) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = em.createQuery("from Adres WHERE kodPocztowy=:kodPocztowy)", Adres.class).setParameter("kodPocztowy", kodPocztowy).getResultList();
        em.close();
        return adresLlist;
    }

    public Adres addAres(Adres adres){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        em.persist(adres);
        em.close();
        return adres;
    }

    public Adres updateAdres(Adres adres){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        adres = (Adres)session.merge(adres);
        session.close();
        return adres;
    }

    public void deleteAdresById(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Adres usuwany = findById(id);
        if(usuwany!=null) {
            session.delete(usuwany);
        }
        session.close();
    }

}
