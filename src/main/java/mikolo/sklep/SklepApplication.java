package mikolo.sklep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mikolo.sklep.repositories.KlientRepository;

@SpringBootApplication
public class SklepApplication {

	public static void main(String[] args) {
		SpringApplication.run(SklepApplication.class, args);

		KlientRepository klientRepository = new KlientRepository();
//		AdresRespository adresRespository = new AdresRespository();
//		ProduktRepository produktRepository = new ProduktRepository();
//		KategoriaProduktuRepository kategoriaProduktuRepository = new KategoriaProduktuRepository();
//		ProducentRepository producentRepository = new ProducentRepository();
//
//		KategoriaProduktu kategoriaProduktu = kategoriaProduktuRepository.findById(10);
//		kategoriaProduktu.setNazwa("Artykuły różne");
//		System.out.println(kategoriaProduktuRepository.updateKategoriaProduktu(kategoriaProduktu));
//		kategoriaProduktuRepository.deleteKategoriaProduktuById(9);
		
//		
//
//		
//		Klient klient = Klient.builder().imie("Anna").nazwisko("Wanna").nrTelefonu("777-777-777").build();
//		Adres adres = Adres.adresBuilder().ulica("Uliczna").nrDomu("191").nrMieszkania("1").nrDomu("1")
//				.kodPocztowy("77-777").miejscowosc("Testowo").build();
//		klient.setAdres(adres);
//
//		System.out.println(adresRespository.addAres(adres));
//		klientRepository.addKlient(klient);

//		KategoriaProduktuRepository kategoriaProduktuRepository = new KategoriaProduktuRepository();
//		ProduktRepository produktRepository = new ProduktRepository();
//		
		System.out.println(klientRepository.findById(128));

//		Adres adres = adresRespository.findByKlientId(klient);
//		System.out.println(adres);

//        KlientRepository klientRepository = new KlientRepository();
//        ProduktRepository produktRepository = new ProduktRepository();
//        KategoriaProduktuRepository kategoriaProduktuRepository = new KategoriaProduktuRepository();
//        ProducentRepository producentRepository = new ProducentRepository();
//        
//        adresRespository.findByMiejscowosc("Gda").forEach(System.out::println);
//        System.out.println(adresRespository.findById(5));
//
//        produktRepository.findAll().forEach(System.out::println);
//        klientRepository.findAll().forEach(System.out::println);
//        adresRespository.findAll().forEach(System.out::println);
//        produktRepository.findProduktKupionyByIdKlienta(97).forEach(System.out::println);
//        produktRepository.findProduktKupionyByIdProduktu(168).forEach(System.out::println);
//        kategoriaProduktuRepository.findAll().forEach(System.out::println);
//        producentRepository.findAll().forEach(System.out::println);
	}

}
