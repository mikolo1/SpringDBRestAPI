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
@Table(schema = "sklep", name = "producent")
public class Producent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producenta")
    private long id;

    @Column
    String nazwa;

    @OneToMany(mappedBy = "idProducenta")
    private Set<Produkt> produkt;

    public static ProducentBuilder producentBuilder(){
        return new ProducentBuilder();
    }

    @Override
    public String toString() {
        return "Producent{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }

	public Producent(long id, String nazwa) {
		this.id = id;
		this.nazwa = nazwa;
	}

	public Producent(String nazwa) {
		this.nazwa = nazwa;
	}
    
    
}
