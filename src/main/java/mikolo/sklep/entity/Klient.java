package mikolo.sklep.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "sklep", name = "klient")
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta")
    private long id;

    @Column
    private String imie;

    @Column
    private String nazwisko;

    @Column(name ="telefon")
    private String nrTelefonu;

    @OneToOne(mappedBy = "idKlienta")
    private Adres adres;

    @ManyToMany(mappedBy = "klient")
    private List<Produkt> produktList;

    public static KlientBuilder klientBuilder(){
        return new KlientBuilder();
    }

	@Override
	public String toString() {
		return "Klient [id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", nrTelefonu=" + nrTelefonu
				+ ", adres=" + adres + ", produktList=" + produktList + "]";
	}

	public Klient(String imie, String nazwisko, String nrTelefonu, Adres adres) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nrTelefonu = nrTelefonu;
		this.adres = adres;
	}

	public Klient(long id, String imie, String nazwisko, String nrTelefonu) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nrTelefonu = nrTelefonu;
	}

	public Klient(long id, Adres adres) {
		this.id = id;
		this.adres = adres;
	}
	
	
  
}
