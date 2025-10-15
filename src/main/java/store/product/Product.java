package store.product;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;
import java.math.BigDecimal;

@Builder @Data @Accessors(fluent = true, chain = true)
public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private String unit;
}