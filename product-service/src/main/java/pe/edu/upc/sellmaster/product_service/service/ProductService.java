package pe.edu.upc.sellmaster.product_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.sellmaster.product_service.model.dtos.ProductRequest;
import pe.edu.upc.sellmaster.product_service.model.dtos.ProductResponse;
import pe.edu.upc.sellmaster.product_service.model.entities.Product;
import pe.edu.upc.sellmaster.product_service.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void addProduct(ProductRequest request) {
        Product product = Product.builder()
                .productType(request.getProductType())
                .brand(request.getBrand())
                .additionalInfo(request.getAdditionalInfo())
                .price(request.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product added: {}", product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public ProductResponse getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToProductResponse(product);
    }

    @Transactional
    public void updateProduct(long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setProductType(request.getProductType());
        product.setBrand(request.getBrand());
        product.setAdditionalInfo(request.getAdditionalInfo());
        product.setPrice(request.getPrice());
        productRepository.save(product);
        log.info("Updated Product: {}", product);
    }

    public void deleteProduct(long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        log.info("Deleted Product with id {}", id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productID(product.getProductID())
                .productType(product.getProductType())
                .brand(product.getBrand())
                .additionalInfo(product.getAdditionalInfo())
                .price(product.getPrice())
                .build();
    }
}

