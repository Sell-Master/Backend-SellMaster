package pe.edu.upc.sellmaster.product_service.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import pe.edu.upc.sellmaster.product_service.model.dtos.ProductRequest;
import pe.edu.upc.sellmaster.product_service.model.dtos.ProductResponse;
import pe.edu.upc.sellmaster.product_service.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new product", description = "Adds a new product to the system", security = @SecurityRequirement(name = "bearerAuth"))
    public void addProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a product by ID", description = "Retrieves a specific product by their ID", security = @SecurityRequirement(name = "bearerAuth"))
    public ProductResponse getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a product", description = "Updates a specific product by their ID", security = @SecurityRequirement(name = "bearerAuth"))
    public void updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a product", description = "Deletes a specific product by their ID", security = @SecurityRequirement(name = "bearerAuth"))
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }
}
