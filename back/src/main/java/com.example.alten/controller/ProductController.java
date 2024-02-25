package com.example.alten.controller;

import com.example.alten.model.Product;
import com.example.alten.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@CrossOrigin
public class ProductController {

    // Partie de tous les produits, affichage et création d'un produit.

    private ProductService productService;

    /**
     * Read - Obtenir tous les produits
     * @return - Liste des produits présents
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Create - Crée un nouveau produit
     *
     * @param product Le produit à créer
     * @return Le produit créé
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Partie d'un seul produit

    /**
     * Read - Récupère un produit par son ID
     *
     * @param productId L'id du produit à récupérer
     * @return ResponseEntity contenant le produit récupéré ou une réponse NotFound si le produit n'est pas trouvé
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update - Met à jour les informations d'un produit
     *
     * @param productId L'id du produit à mettre à jour
     * @param product   Les nouvelles données du produit
     * @return ResponseEntity contenant le produit mis à jour, ou une réponse NotFound si le produit n'est pas trouvé
     */
    @PatchMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete - Supprime un produit existant
     *
     * @param productId L'id du produit à supprimer
     * @return ResponseEntity sans contenu si le produit est supprimé avec succès, sinon une réponse NotFound si le produit n'est pas trouvé
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        if (productService.deleteProduct(productId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
