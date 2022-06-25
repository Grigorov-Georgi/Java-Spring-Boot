package bg.softuni.shoppingList.models.dto;

import bg.softuni.shoppingList.models.Product;

import java.math.BigDecimal;

public class ProductViewDTO {
    private long id;

    private String name;
    private BigDecimal price;

    public ProductViewDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
