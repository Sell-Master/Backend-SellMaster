package pe.edu.upc.sellmaster.product_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.upc.sellmaster.product_service.model.dtos.ProductRequest;
import pe.edu.upc.sellmaster.product_service.model.dtos.ProductResponse;
import pe.edu.upc.sellmaster.product_service.model.entities.Product;
import pe.edu.upc.sellmaster.product_service.repository.ProductRepository;
import pe.edu.upc.sellmaster.product_service.service.ProductService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Test para agregar un producto
    @Test
    public void testAddProduct() {
        ProductRequest request = new ProductRequest("Electronics", "Samsung", "Latest model", 999.99);
        Product product = Product.builder()
                .productType(request.getProductType())
                .brand(request.getBrand())
                .additionalInfo(request.getAdditionalInfo())
                .price(request.getPrice())
                .build();
        productService.addProduct(request);
        verify(productRepository).save(product);
    }

    // Test para obtener todos los productos
    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(new Product(1L, "Electronics", "Samsung", "Latest model", 999.99)));
        List<ProductResponse> responses = productService.getAllProducts();
        assertEquals(1, responses.size(), "Expected one product in the list");
        assertEquals("Electronics", responses.get(0).getProductType(), "Expected product type to match");
    }

    // Test para obtener un producto por ID
    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Electronics", "Samsung", "Latest model", 999.99);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductResponse response = productService.getProductById(1L);
        assertEquals(1L, response.getProductID(), "Expected product ID to match");
    }

    // Test para actualizar un producto
    @Test
    public void testUpdateProduct() {
        Product existingProduct = new Product(1L, "Electronics", "Samsung", "Old model", 899.99);
        ProductRequest request = new ProductRequest("Electronics", "Samsung", "Latest model", 999.99);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        productService.updateProduct(1L, request);
        assertEquals("Latest model", existingProduct.getAdditionalInfo(), "Expected additional info to be updated");
        verify(productRepository).save(existingProduct);
    }

    // Test para eliminar un producto
    @Test
    public void testDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository).deleteById(1L);
    }
}

