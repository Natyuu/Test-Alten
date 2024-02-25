package com.example.alten;

import com.example.alten.datas.ProductsDatasLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AltenTestApplication  implements CommandLineRunner {

	@Autowired
	private ProductsDatasLoader productsDatasLoader;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AltenTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Demarrage back...");
	}

	/**
	 * Charge les produits au démarrage de l'application, après la création de l'instance de la classe.
	 */
	@PostConstruct
	public void loadProductsOnStartup() {
		try {
			productsDatasLoader.loadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}