package project;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String customerName;
    private String product;
    private int quantity;
    private double price;
    private String status;

    public Order(int id, String customerName, String product, int quantity, double price, String status) {
        this.id = id;
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", customerName='" + customerName + "', product='" + product + "', quantity=" + quantity + ", price=" + price + ", status='" + status + "'}";
    }
}

