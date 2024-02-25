package com.example.alten;

import com.example.alten.model.Product;
import com.example.alten.repository.ProductRepository;
import com.example.alten.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static com.example.alten.utils.ProductUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringJUnitConfig(classes={ProductService.class, ProductRepository.class})
class ProductServiceTests {

	@Autowired
	private ProductService productService;
	@MockBean
	private ProductRepository productRepository;

	@Test
	void getProductsAll() {
		Product product_1 = createProduct(ID_1, NAME_1, CODE_1, DESCRIPTION, PRICE_1, QUANTITY_1, INVENTORYSTATUS, CATEGORY, IMAGE_1, RATING_1);
		Product product_2 = createProduct(ID_2, NAME_2, CODE_2, DESCRIPTION, PRICE_2, QUANTITY_2, INVENTORYSTATUS, CATEGORY, IMAGE_2, RATING_2);

		when(productRepository.findAll()).thenReturn(List.of(product_1, product_2));
		List<Product> products = productService.getAllProducts();

		assertEquals(2, products.size());
		assertTrue(products.stream().anyMatch(product -> product.getName().equals(product_1.getName()) && product.getCode().equals(product_1.getCode())));
		assertTrue(products.stream().anyMatch(product -> product.getName().equals(product_2.getName()) && product.getCode().equals(product_2.getCode())));
	}

	@Test
	void getProductById() {
		Product product_1 = createProduct(ID_1, NAME_1, CODE_1, DESCRIPTION, PRICE_1, QUANTITY_1, INVENTORYSTATUS, CATEGORY, IMAGE_1, RATING_1);

		when(productRepository.findById(ID_1)).thenReturn(Optional.of(product_1));

		assertEquals(product_1.getName(), productService.getProductById(ID_1).getName());
	}

	@Test
	void createOneProduct() {
		Product product_1 = createProduct(ID_1, NAME_1, CODE_1, DESCRIPTION, PRICE_1, QUANTITY_1, INVENTORYSTATUS, CATEGORY, IMAGE_1, RATING_1);

		when(productRepository.save(product_1)).thenReturn(product_1);

		assertEquals(product_1.getName(), productService.createProduct(product_1).getName());
	}

	@Test
	void updateProduct() {
		Product product_1 = createProduct(ID_1, NAME_1, CODE_1, DESCRIPTION, PRICE_1, QUANTITY_1, INVENTORYSTATUS, CATEGORY, IMAGE_1, RATING_1);
		Product product_input = createProduct(null, null, null, PRICE_2, QUANTITY_2, null, null, null, RATING_2);
		Product result = createProduct(NAME_1, CODE_1, DESCRIPTION, PRICE_2, QUANTITY_2, INVENTORYSTATUS, CATEGORY, IMAGE_1, RATING_2);

		when(productRepository.findById(ID_1)).thenReturn(Optional.of(product_1));
		when(productRepository.save(result)).thenReturn(result);

		Product product_output = productService.updateProduct(ID_1, product_input);

		assertEquals(result.getPrice(), product_output.getPrice());
	}

	@Test
	void deleteProduct() {
		when(productRepository.existsById(ID_1)).thenReturn(true);
		boolean result = productService.deleteProduct(ID_1);

		verify(productRepository).deleteById(ID_1);
		assertTrue(result);
	}

}
