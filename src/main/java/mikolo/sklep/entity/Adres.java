package mikolo.sklep.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "sklep", name = "adres")
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adres")
    private long id;

    @Column
    private String ulica;

    @Column(name="nrdomu")
    private String nrDomu;

    @Column(name="nrmieszkania")
    private String nrMieszkania;

    @Column(name="kod_pocztowy")
    private String kodPocztowy;

    @Column
    private String miejscowosc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_klient", unique = true)
    private Klient idKlienta;

    public static AdresBuilder adresBuilder(){
        return new AdresBuilder();
    }
  
    @Override
    public String toString() {
        return "Adres{" +
                "id=" + id +
                ", ulica='" + ulica + '\'' +
                ", nrDomu='" + nrDomu + '\'' +
                ", nrMieszkania='" + nrMieszkania + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                ", miejscowosc='" + miejscowosc + '\'' +
//                ", idKlienta=" + idKlienta.getId() +
                '}';
    }

	public Adres(String ulica, String nrDomu, String nrMieszkania, String kodPocztowy, String miejscowosc) {
		this.ulica = ulica;
		this.nrDomu = nrDomu;
		this.nrMieszkania = nrMieszkania;
		this.kodPocztowy = kodPocztowy;
		this.miejscowosc = miejscowosc;
	}

	public Adres(long id, String ulica, String nrDomu, String nrMieszkania, String kodPocztowy, String miejscowosc) {
		this.id = id;
		this.ulica = ulica;
		this.nrDomu = nrDomu;
		this.nrMieszkania = nrMieszkania;
		this.kodPocztowy = kodPocztowy;
		this.miejscowosc = miejscowosc;
	}
	
}
