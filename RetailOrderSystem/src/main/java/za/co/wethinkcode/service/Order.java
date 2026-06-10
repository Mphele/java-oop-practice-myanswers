package za.co.wethinkcode.service;

import za.co.wethinkcode.model.OrderStatus;

public class Order {

    private String orderId;
    private OrderStatus status;

    public Order(String orderId){
        if(orderId == null || orderId.isEmpty()){
            throw new IllegalArgumentException();
        }
        status = OrderStatus.NEW;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void processOrder(){
        if(status !=OrderStatus.NEW){
            throw new IllegalStateException();
        }

        status = OrderStatus.PROCESSING;
    }

    public void shipOrder(){
        if(status !=OrderStatus.PROCESSING){
            throw new IllegalStateException();
        }

        status = OrderStatus.SHIPPED;
    }

    public void deliverOrder(){
        if(status !=OrderStatus.SHIPPED){
            throw new IllegalStateException();
        }

        status = OrderStatus.DELIVERED;
    }
}
