package mikolo.sklep.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "sklep", name = "produkt")
public class Produkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produktu")
    private long id;

    @Column
    private String nazwa;

    @Column
    private double cena;

    @ManyToOne(cascade = {CascadeType.MERGE})  
    @JoinColumn(name = "id_producenta")
    private Producent idProducenta;

    @ManyToOne(cascade = {CascadeType.MERGE})  
    @JoinColumn(name="id_kategori")
    private KategoriaProduktu idKategori;

    @ManyToMany
    @JoinTable(name="zakupy",
            joinColumns=@JoinColumn(name="id_produkt"),
            inverseJoinColumns=@JoinColumn(name="id_klient"))
    private List<Klient> klient;

    @Override
    public String toString() {
        return "Produkt{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", idProducenta=" + idProducenta +
                ", idKategori=" + idKategori +
                '}';
    }
  
	public Produkt(String nazwa, double cena) {
		this.nazwa = nazwa;
		this.cena = cena;
	}
	
	public Produkt(long id, String nazwa, double cena) {
		this.id = id;
		this.nazwa = nazwa;
		this.cena = cena;
	}

	public Produkt(long id, String nazwa, double cena, Producent idProducenta, KategoriaProduktu idKategori) {
		this.id = id;
		this.nazwa = nazwa;
		this.cena = cena;
		this.idProducenta = idProducenta;
		this.idKategori = idKategori;
	}
	
	public Produkt(String nazwa, double cena, Producent idProducenta, KategoriaProduktu idKategori) {
		this.nazwa = nazwa;
		this.cena = cena;
		this.idProducenta = idProducenta;
		this.idKategori = idKategori;
	}
	
    public static ProduktBuilder produktBuilder(){
        return new ProduktBuilder();
    }

}
