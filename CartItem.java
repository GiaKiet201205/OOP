/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

public class CartItem {
    private Car car;
    private int quantity;
    private double totalItem;

    public CartItem(Car car, int quantity) {
        this.car = car;
        this.quantity = quantity;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItem(){
        return this.quantity * this.car.price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CartItem{");
        sb.append(" ").append(car);
        sb.append(", quantity: ").append(quantity);
        sb.append(", totalItem: ").append(getTotalItem());
        sb.append('}');
        return sb.toString();
    }

}
