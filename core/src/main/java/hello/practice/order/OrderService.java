package hello.practice.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName,int price);
}
