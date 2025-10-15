package store.product;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;
import java.math.BigDecimal;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> findAll() {
        return StreamSupport.stream(
            productRepository.findAll().spliterator(), false)
            .map(ProductModel::to)
            .toList();
        }    
        
        public Product findById(String id) {
            return productRepository.findById(id)
            .map(ProductModel::to)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto não encontrado"
                ));
            }
            
                public Product create(Product product) {
                    if (null == product.name()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Campo 'nome' é obrigatório!"
                        );
                    }
                    if (null == product.price()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Campo 'preço' é obrigatório!"
                        );
                    }
            
                    if (productRepository.findByName(product.name()) != null)
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Nome já cadastrado!"
                        );
                    return productRepository.save(
                        new ProductModel(product)
                    ).to();
                }
            
    public void delete(String id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto não encontrado"
            );
        }
        productRepository.deleteById(id);
    }
}