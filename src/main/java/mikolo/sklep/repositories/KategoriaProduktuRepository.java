package mikolo.sklep.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class KategoriaProduktuRepository {
	


    public KategoriaProduktu findById(long id) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        KategoriaProduktu kategoriaProduktu = em.createQuery("from KategoriaProduktu WHERE id=:id", KategoriaProduktu.class).setParameter("id", id).getSingleResult();
        em.close();
        return kategoriaProduktu;
    }

    public List<KategoriaProduktu> findAll(){
    	Session  em = HibernateUtil.getSessionFactory().openSession();
        List<KategoriaProduktu> kategoriaProduktuList = em.createNativeQuery("select * from kategoria_produktu", KategoriaProduktu.class).getResultList();
        em.close();
        return kategoriaProduktuList;
    }

    public List<KategoriaProduktu> findByNazwa(String nazwa) {
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        List<KategoriaProduktu> kategoriaProduktuList = em.createQuery("from KategoriaProduktu WHERE nazwa LIKE CONCAT('%',:nazwa,'%')", KategoriaProduktu.class).setParameter("nazwa", nazwa).getResultList();
        em.close();
        return kategoriaProduktuList;
    }

    public KategoriaProduktu addKategoriaProduktu(KategoriaProduktu kategoriaProduktu){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        em.persist(kategoriaProduktu);
        em.close();
        return kategoriaProduktu;
    }

    public KategoriaProduktu updateKategoriaProduktu(KategoriaProduktu kategoriaProduktu){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        kategoriaProduktu = (KategoriaProduktu) em.merge(kategoriaProduktu);
        em.close();
        return kategoriaProduktu;
    }

    public void deleteKategoriaProduktuById(long id){
    	EntityManager em = HibernateUtil.getSessionFactory().openSession();
        KategoriaProduktu usuwany = findById(id);
        if(usuwany!=null) {
        	em.remove(usuwany);
        }
        em.close();
    }
}
