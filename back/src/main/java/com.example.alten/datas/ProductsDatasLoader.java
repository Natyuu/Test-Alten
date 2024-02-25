package com.example.alten.datas;

import com.example.alten.model.Product;
import com.example.alten.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductsDatasLoader {

    private ProductRepository productRepository;

    /**
     * Charge les données des produits à partir d'un fichier JSON dans la base de données.
     * Le chargement des données se fait dans un "Transactional", ce qui garantie l'intégrité des données.
     *
     * @throws IOException Si une erreur se produit lors de la lecture du JSON.
     */
    @Transactional
    public void loadData() throws IOException {
        File file = ResourceUtils.getFile("classpath:products.json");
        byte[] jsonData = Files.readAllBytes(file.toPath());
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = objectMapper.readValue(jsonData, new TypeReference<>() {});
        productRepository.saveAll(products);
    }
}
