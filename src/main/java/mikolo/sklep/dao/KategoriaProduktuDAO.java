package mikolo.sklep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mikolo.sklep.entity.KategoriaProduktu;

@Repository
public interface KategoriaProduktuDAO extends JpaRepository<KategoriaProduktu, Long>{
	
	@Query(value="SELECT * from kategoria_produktu WHERE nazwa LIKE %:nazwa%", nativeQuery = true)
	public List<KategoriaProduktu> findByNazwa(String nazwa); 
	
}
