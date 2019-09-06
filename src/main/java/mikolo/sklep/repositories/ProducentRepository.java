package mikolo.sklep.repositories;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import mikolo.sklep.entity.Producent;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class ProducentRepository {

    public Producent findById(long id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Producent producent = session.createQuery("from Producent WHERE id=:id", Producent.class).setParameter("id", id).getSingleResult();
        session.close();
        return producent;
    }

    public List<Producent> findAll(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        List<Producent> producentList = session.createQuery("from Producent", Producent.class).getResultList();
        session.close();
        return producentList;
    }

    public List<Producent> findByNazwa(String nazwa) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        List<Producent> producentList = session.createQuery("from Producent WHERE nazwa LIKE CONCAT('%',:nazwa,'%')", Producent.class).setParameter("nazwa", nazwa).getResultList();
        session.close();
        return producentList;
    }

    public Producent addProducent(Producent producent){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	session.persist(producent);
    	session.flush();
    	session.close();
        return producent;
    }

    public Producent updateProducent(Producent producent){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        producent = (Producent) session.merge(producent);
        session.flush();
        session.close();
        return producent;
    }

    public void deleteProducentById(long id){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        Producent usuwany = findById(id);
        if(usuwany!=null) {
        	session.remove(usuwany);
        }
        session.flush();
        session.close();
    }

}
