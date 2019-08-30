package mikolo.sklep.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "sklep", name = "kategoria_produktu")
public class KategoriaProduktu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kategori")
    private long id;

    @Column
    private String nazwa;

    @OneToMany(mappedBy = "idKategori")
    private Set<Produkt> produktSet;

    public static KategoriaProduktuBuilder katProdBuilder(){
        return new KategoriaProduktuBuilder();
    }

    @Override
    public String toString() {
        return "KategoriaProduktu{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

	public KategoriaProduktu(long id, String nazwa) {
		this.id = id;
		this.nazwa = nazwa;
	}

	public KategoriaProduktu(String nazwa) {
		this.nazwa = nazwa;
	}
   
	
	
}
