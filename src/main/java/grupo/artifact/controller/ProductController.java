package grupo.artifact.controller;

import org.springframework.web.bind.annotation.*;
import grupo.artifact.model.Product;
import grupo.artifact.model.dto.ProductDTO;
import grupo.artifact.service.ProductService;
import java.util.List;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) { //Inyeccion de dependencias
        this.productService = productService;
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody ProductDTO productDTO) {
        return this.productService.saveProduct(productDTO);
    }
    
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getAllProducts() {
        return this.productService.getListProductsInBD();
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        return this.productService.updateProductWithId(id, productDTO);
    }
}
