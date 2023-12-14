package hello.itemservice.domain.item;

import lombok.Data;

@Data
//위험해 근데 공부할떄니깐~
// @Setter @Getter
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
