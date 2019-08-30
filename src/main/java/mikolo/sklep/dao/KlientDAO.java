package mikolo.sklep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mikolo.sklep.entity.Klient;

@Repository
public interface KlientDAO extends JpaRepository<Klient, Long>{

	@Query(value="SELECT * from klient WHERE imie LIKE %:imie%", nativeQuery = true)
	public List<Klient> findByImie(@Param("imie") String imie);
	
	@Query(value="SELECT * from klient WHERE nazwisko LIKE %:nazwisko%", nativeQuery = true)
	public List<Klient> findByNazwisko(@Param("nazwisko") String nazwisko);
	
	@Query(value="SELECT * from klient WHERE telefon LIKE %:nrTelefonu%", nativeQuery = true)
	public List<Klient> findByPhone(@Param("nrTelefonu") String nrTelefonu);
	
	@Query(value="SELECT n from Klient n WHERE nrTelefonu =:nrTelefonu")
	public Klient findOneByPhone(@Param("nrTelefonu") String nrTelefonu);

}
