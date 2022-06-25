package bg.softuni.shoppingList.models.dto;

import java.math.BigDecimal;

public class BuyDTO {
    private BigDecimal total = BigDecimal.valueOf(0);

    public BuyDTO() {
        this.total = BigDecimal.valueOf(0);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
