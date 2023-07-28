package hello.practice.order;

public class Order {
    private Long memberId;
    private String itemId;
    private int price;
    private int discountPrice;

    public Order(Long memberId, String itemId, int price, int discountPrice) {
        this.memberId = memberId;
        this.itemId = itemId;
        this.price = price;
        this.discountPrice = discountPrice;
    }
    public int calculatePrice(){
        return price-discountPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemId='" + itemId + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
