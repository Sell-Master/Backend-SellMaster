package pe.edu.upc.sellmaster.product_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pe.edu.upc.sellmaster.product_service.controller.ProductController;
import pe.edu.upc.sellmaster.product_service.model.dtos.ProductRequest;
import pe.edu.upc.sellmaster.product_service.model.dtos.ProductResponse;
import pe.edu.upc.sellmaster.product_service.service.ProductService;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // Test para agregar un producto
    @Test
    public void testAddProduct() {
        ProductRequest productRequest = new ProductRequest("Electronics", "Samsung", "Latest model", 999.99);
        doNothing().when(productService).addProduct(productRequest);

        productController.addProduct(productRequest);

        verify(productService).addProduct(productRequest);
    }

    // Test para obtener todos los productos
    @Test
    public void testGetAllProducts() {
        List<ProductResponse> expectedResponses = List.of(
                new ProductResponse(1L, "Electronics", "Samsung", "Latest model", 999.99)
        );
        when(productService.getAllProducts()).thenReturn(expectedResponses);

        List<ProductResponse> responses = productController.getAllProducts();

        assertEquals(1, responses.size());
        assertEquals("Electronics", responses.get(0).getProductType());
    }

    // Test para obtener un producto por ID
    @Test
    public void testGetProductById() {
        long id = 1L;
        ProductResponse expectedResponse = new ProductResponse(1L, "Electronics", "Samsung", "Latest model", 999.99);
        when(productService.getProductById(id)).thenReturn(expectedResponse);

        ProductResponse response = productController.getProductById(id);

        assertEquals(id, response.getProductID());
    }

    // Test para actualizar un producto
    @Test
    public void testUpdateProduct() {
        long id = 1L;
        ProductRequest productRequest = new ProductRequest("Electronics", "Sony", "Updated model", 1099.99);
        doNothing().when(productService).updateProduct(id, productRequest);

        productController.updateProduct(id, productRequest);

        verify(productService).updateProduct(id, productRequest);
    }

    // Test para eliminar un producto
    @Test
    public void testDeleteProduct() {
        long id = 1L;
        doNothing().when(productService).deleteProduct(id);

        productController.deleteProduct(id);

        verify(productService).deleteProduct(id);
    }
}

