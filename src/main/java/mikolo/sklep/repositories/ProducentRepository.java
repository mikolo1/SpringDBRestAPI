package mikolo.sklep.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import mikolo.sklep.entity.Producent;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class ProducentRepository {

    public Producent findById(long id) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        Producent producent = em.createQuery("from Producent WHERE id=:id", Producent.class).setParameter("id", id).getSingleResult();
        em.close();
        return producent;
    }

    public List<Producent> findAll(){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Producent> producentList = em.createQuery("from Producent", Producent.class).getResultList();
        em.close();
        return producentList;
    }

    public List<Producent> findByNazwa(String nazwa) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<Producent> producentList = em.createQuery("from Producent WHERE nazwa LIKE CONCAT('%',:nazwa,'%')", Producent.class).setParameter("nazwa", nazwa).getResultList();
        em.close();
        return producentList;
    }

    public Producent addProducent(Producent producent){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        em.persist(producent);
        em.close();
        return producent;
    }

    public Producent updateProducent(Producent producent){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        producent = (Producent) em.merge(producent);
        em.close();
        return producent;
    }

    public void deleteProducentById(long id){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        Producent usuwany = findById(id);
        if(usuwany!=null) {
            em.remove(usuwany);
        }
        em.close();
    }

}
