package mikolo.sklep.repositories;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import mikolo.sklep.entity.Adres;
import mikolo.sklep.entity.Klient;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class AdresRespository {
	
    public Adres findById(long id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Adres adres = session.createQuery("from Adres WHERE id=:id", Adres.class).setParameter("id", id).getSingleResult();
        session.close();
        return adres;
    }

    public List<Adres> findAll(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresList = session.createQuery("from Adres", Adres.class).getResultList();
        session.close();
        return adresList;
    }

    public Adres findByKlientId(Klient idKlienta) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Adres adres = session.createQuery("from Adres WHERE idKlienta=:idKlienta", Adres.class).setParameter("idKlienta", idKlienta).getSingleResult();
        session.close();
        return adres;
    }

    public List<Adres> findByUlica(String ulica) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = session.createQuery("from Adres WHERE ulica LIKE CONCAT('%',:gatunek,'%')", Adres.class).setParameter("ulica", ulica).getResultList();
        session.close();
        return adresLlist;
    }

    public List<Adres> findByNrDomu(String nrDomu) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = session.createQuery("from Adres WHERE nrDomu LIKE CONCAT('%',:nrdomu,'%')", Adres.class).setParameter("nrdomu", nrDomu).getResultList();
        session.close();
        return adresLlist;
    }

    public List<Adres> findByNrMieszkania(String nrMieszkania) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = session.createQuery("from Adres WHERE nrMieszkania LIKE CONCAT('%',:nrmieszkania,'%')", Adres.class).setParameter("nrmieszkania", nrMieszkania).getResultList();
        session.close();
        return adresLlist;
    }

    public List<Adres> findByMiejscowosc(String miejscowosc) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = session.createQuery("from Adres WHERE miejscowosc LIKE CONCAT('%',:miejscowosc,'%')", Adres.class).setParameter("miejscowosc", miejscowosc).getResultList();
        session.close();
        return adresLlist;
    }

    public List<Adres> findByKodPocztowy(String kodPocztowy) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<Adres> adresLlist = session.createQuery("from Adres WHERE kodPocztowy=:kodPocztowy)", Adres.class).setParameter("kodPocztowy", kodPocztowy).getResultList();
        session.close();
        return adresLlist;
    }

    public Adres addAres(Adres adres){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	session.persist(adres);
    	session.flush();
    	session.close();
        return adres;
    }

    public Adres updateAdres(Adres adres){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        adres = (Adres)session.merge(adres);
        session.flush();
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
        session.flush();
        session.close();
    }

}
