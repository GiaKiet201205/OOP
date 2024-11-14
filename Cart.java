/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;



import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> item;

    public Cart(List<CartItem> item) {
        this.item = item;
    }

    public Cart() {
    }

    public List<CartItem> getItem() {
        return item;
    }

    public void setItem(List<CartItem> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(CartItem it : item){
            s.append(it.toString());
            s.append("\n");
        }
        return s.toString();
    }
    
    public List<CartItem> addToCart(CartItem newItem){
        List<CartItem> listCart = new ArrayList<>();
        listCart.add(newItem);
        return listCart;
    }

    public List<CartItem> deleteFromCart(CartItem curr,int id){
        List<CartItem> listCart = new ArrayList<>();
        for(CartItem item:listCart){
            Car car = new Car();
            car = item.getCar();
            if(car.getId() == id){
                listCart.remove(car);
            }
        }
        return listCart;
    }

    public double totalCart(Cart cart){
        List<CartItem> listCart = new ArrayList<>();
        listCart = cart.getItem();
        double total=0;
        for(CartItem car:listCart){
            total+= car.getTotalItem();
        }
        return total;
    }
}
