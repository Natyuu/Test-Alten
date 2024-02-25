package com.example.alten.service;

import com.example.alten.model.Product;
import com.example.alten.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    /**
     * Récupère tous les produits disponibles.
     *
     * @return Une liste de tous les produits disponibles.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Récupère un produit par son identifiant.
     *
     * @param productId L'identifiant du produit à récupérer.
     * @return Le produit correspondant à l'identifiant donné, ou null si le produit n'existe pas.
     */
    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);
    }

    /**
     * Crée un nouveau produit.
     *
     * @param product Le produit à créer.
     * @return Le produit créé.
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Mettre à jour les informations d'un produit existant.
     *
     * @param productId L'identifiant du produit à modifier.
     * @param newProduct Les nouvelles informations du produit.
     * @return Le produit mis à jour, ou null si aucun produit ne correspond à l'identifiant donné.
     */
    public Product updateProduct(Long productId, Product newProduct) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();
            // Met à jour les infos du produit entré
            existingProduct.setName(newProduct.getName()!= null ? newProduct.getName() : existingProduct.getName());
            existingProduct.setDescription(newProduct.getDescription() != null ? newProduct.getDescription() : existingProduct.getDescription());
            existingProduct.setPrice(newProduct.getPrice() != null ? newProduct.getPrice() : existingProduct.getPrice());
            existingProduct.setQuantity(newProduct.getQuantity() != null ? newProduct.getQuantity() : existingProduct.getQuantity());
            existingProduct.setInventoryStatus(newProduct.getInventoryStatus() != null ? newProduct.getInventoryStatus() : existingProduct.getInventoryStatus());
            existingProduct.setCategory(newProduct.getCategory() != null ? newProduct.getCategory() : existingProduct.getCategory());
            existingProduct.setRating(newProduct.getRating() != null ? newProduct.getRating() : existingProduct.getRating());
            existingProduct.setImage(newProduct.getImage() != null ? newProduct.getImage() : existingProduct.getImage());

            productRepository.save(existingProduct);
            return existingProduct;
        } else {
            return null;
        }
    }

    /**
     * Supprime un produit.
     *
     * @param productId L'identifiant du produit à supprimer.
     * @return Vrai si le produit existe et le supprime, Faux sinon.
     */
    public boolean deleteProduct(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return true;
        } else {
            return false;
        }
    }
}