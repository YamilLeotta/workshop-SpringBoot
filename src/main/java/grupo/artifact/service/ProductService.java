package grupo.artifact.service;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import grupo.artifact.exception.custom.EmptyElementException;
import grupo.artifact.exception.custom.InvalidDataException;
import grupo.artifact.exception.custom.NotCreatedException;
import grupo.artifact.exception.custom.NotFoundException;
import grupo.artifact.exception.custom.NotModifiedException;
import grupo.artifact.model.Product;
import grupo.artifact.model.dto.ProductDTO;
import grupo.artifact.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product saveProduct(ProductDTO productDTO){
        if (checkProductDTO(productDTO))
            return this.productRepository.save(new Product(productDTO));

        throw new NotCreatedException("Product hasn't been created");
    }

    public ProductDTO updateProductWithId(Integer id, ProductDTO productDTO){
        if (this.checkProductDTO(productDTO)) {
            Product modifying = this.productRepository.findById(id)
                .orElseThrow(
                    () -> new NotFoundException("Product not found")
                );
            
            modifying.setName(productDTO.getName());
            modifying.setType(productDTO.getType());
            modifying.setCost(productDTO.getCost());
            modifying.setSupport("SERVICE".equals(productDTO.getType()) ? productDTO.getSupport() : null);

            return this.productRepository.save(modifying).toDTO();
        }

        throw new NotModifiedException("Product hasn't been modified");
    }

    public List<Product> getListProductsInBD() {
        return this.productRepository.findAll().stream().collect(Collectors.toList());
/*
            .stream()
            .map(Product::toDTO)
            .collect(Collectors.toList());
*/
    }

    private Boolean checkProductDTO(ProductDTO productDTO){
        if (StringUtils.isEmpty(productDTO.getName()))
            throw new EmptyElementException("Name is empty");

        if (StringUtils.isEmpty(productDTO.getType()))
            throw new EmptyElementException("Type is empty");
        else
            if ((!"PRODUCT".equals(productDTO.getType())) && (!"SERVICE".equals(productDTO.getType())))
                throw new InvalidDataException("Type is invalid");

        if (productDTO.getCost() < 0) 
            throw new InvalidDataException("Invalid cost");

        if (("SERVICE".equals(productDTO.getType())) && (productDTO.getSupport() < 0))
            throw new InvalidDataException("Invalid support");

        return Boolean.TRUE;
    }
}