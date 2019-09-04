package mikolo.sklep.repositories;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import mikolo.sklep.entity.KategoriaProduktu;
import mikolo.sklep.utils.HibernateUtil;

@Service
public class KategoriaProduktuRepository {
	

    public KategoriaProduktu findById(long id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        KategoriaProduktu kategoriaProduktu = session.createQuery("from KategoriaProduktu WHERE id=:id", KategoriaProduktu.class).setParameter("id", id).getSingleResult();
        session.close();
        return kategoriaProduktu;
    }

    public List<KategoriaProduktu> findAll(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<KategoriaProduktu> kategoriaProduktuList = session.createNativeQuery("select * from kategoria_produktu", KategoriaProduktu.class).getResultList();
        session.close();
        return kategoriaProduktuList;
    }

    public List<KategoriaProduktu> findByNazwa(String nazwa) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<KategoriaProduktu> kategoriaProduktuList = session.createQuery("from KategoriaProduktu WHERE nazwa LIKE CONCAT('%',:nazwa,'%')", KategoriaProduktu.class).setParameter("nazwa", nazwa).getResultList();
        session.close();
        return kategoriaProduktuList;
    }

    public KategoriaProduktu addKategoriaProduktu(KategoriaProduktu kategoriaProduktu){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	session.persist(kategoriaProduktu);
    	session.flush();
    	session.close();
        return kategoriaProduktu;
    }

    public KategoriaProduktu updateKategoriaProduktu(KategoriaProduktu kategoriaProduktu){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        kategoriaProduktu = (KategoriaProduktu) session.merge(kategoriaProduktu);
        session.flush();
    	session.close();
        return kategoriaProduktu;
    }

    public void deleteKategoriaProduktuById(long id){
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        KategoriaProduktu usuwany = findById(id);
        if(usuwany!=null) {
        	session.remove(usuwany);
        }
        session.flush();
        session.close();
    }
}
