package mikolo.sklep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mikolo.sklep.entity.Produkt;

@Repository
public interface ProduktDAO extends JpaRepository<Produkt, Long>{
	
	@Query(value="SELECT * from produkt WHERE nazwa LIKE %:nazwa%", nativeQuery = true)
	public List<Produkt> findByNazwa(@Param("nazwa") String nazwa); 
	
	@Query(value="SELECT * from produkt WHERE cena =:cena", nativeQuery = true)
	public List<Produkt> findByCena(@Param("cena")double cena); 
	
	@Query(value="SELECT * from produkt WHERE cena >:cena", nativeQuery = true)
	public List<Produkt> findByBiggerCena(@Param("cena")double cena); 
	
	@Query(value="SELECT * from produkt WHERE cena <:cena", nativeQuery = true)
	public List<Produkt> findByLowerCena(@Param("cena")double cena); 
	
	@Query(value="SELECT p.* from produkt p join zakupy z on z.id_produkt = p.id_produktu WHERE z.id_klient =:klientId", nativeQuery = true)
	List<Produkt> findByIdKlienta (@Param("klientId") long klientId);
	
	@Query(value="SELECT * from produkt WHERE id_kategori=:idKategori", nativeQuery = true)
	List<Produkt> findByIdKategori (@Param("idKategori") long idKategorii);

	@Query(value="SELECT * from produkt WHERE id_producenta=:id", nativeQuery = true)
	List<Produkt> findByProducentId (@Param("id") long id);

	@Modifying
	@Query(value = "UPDATE produkt SET cena=:cena, nazwa=:nazwa, id_kategori=:idKategori, id_producenta=:idProducenta WHERE id_produktu=:id", nativeQuery = true)
	public void update(@Param("id") long id, @Param("cena") double cena, @Param("nazwa") String nazwa, @Param("idKategori")long idKategori, @Param("idProducenta") long idProducenta);

	@Modifying
	@Query(value="INSERT INTO zakupy (id_produkt, id_klient) VALUES (:produktId, :klientId)", nativeQuery = true)
	public void addZakupy(@Param("produktId") long produktId, @Param("klientId") long klientId);

	@Query(value="SELECT * from produkt ORDER BY id_produktu DESC limit 1", nativeQuery = true)
	public Produkt findLast();

}
