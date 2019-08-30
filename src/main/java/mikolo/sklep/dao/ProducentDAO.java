package mikolo.sklep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mikolo.sklep.entity.Producent;

@Repository
public interface ProducentDAO extends JpaRepository<Producent, Long>{
	
	@Query(value="SELECT * from producent WHERE nazwa LIKE %:nazwa%", nativeQuery = true)
	public List<Producent> findByNazwa(@Param("nazwa") String nazwa); 
	
	@Modifying
	@Query(value="UPDATE producent SET nazwa=:nazwa WHERE id_producenta =:id", nativeQuery = true)
	public void uptade(@Param("id") long id, @Param("nazwa") String nazwa);

}
