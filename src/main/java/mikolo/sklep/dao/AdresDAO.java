package mikolo.sklep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mikolo.sklep.entity.Adres;
import mikolo.sklep.entity.Klient;

@Repository
public interface AdresDAO extends JpaRepository<Adres, Long>{
	
	@Query(value="SELECT n FROM Adres n WHERE idKlienta =:idKlienta")
	public Adres findByKlient(@Param ("idKlienta") Klient idKlienta);

	@Query(value="SELECT * FROM adres WHERE id_klient =:idKlienta limit 1", nativeQuery = true)
	public Adres findByKlientId(@Param ("idKlienta") long idKlienta);
	
	@Query(value="SELECT * FROM adres WHERE ulica LIKE %:ulica%", nativeQuery = true)
	public List<Adres> findByUlica(@Param ("ulica") String ulica);
	
	@Query(value="SELECT * FROM adres WHERE miejscowosc LIKE %:miejscowosc%", nativeQuery = true)
	public List<Adres> findByMiejsowosc(@Param ("miejscowosc") String miejscowosc);
	
	@Query(value="SELECT n FROM Adres n WHERE kodPocztowy =:kodPocztowy")
	public List<Adres> findByKodPocztowy(@Param ("kodPocztowy") String kodPocztowy);
	
	@Modifying
	@Query(value="DELETE FROM adres WHERE id_klient =:klientId", nativeQuery = true)
	public void deleteByKlientId(@Param("klientId") long klientId);

}
