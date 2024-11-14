/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

public class Car {
    protected int id;
    protected String name, brand;
    protected int yearOfProduction;
    protected double price;
    protected String category;
    protected Warranty warranty;

    public Car(int id, String name, String brand, int yearOfProduction, double price, String category, Warranty warranty){
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.yearOfProduction = yearOfProduction;
        this.category = category;
        this.warranty = warranty;
        this.price = price;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Car:");
        sb.append("id: ").append(id);
        sb.append(", name: ").append(name);
        sb.append(", brand: ").append(brand);
        sb.append(", yearOfProduction: ").append(yearOfProduction);
        sb.append(", price: ").append(price);
        sb.append(", category:").append(category);
        sb.append(", warranty: ").append(warranty);
        return sb.toString();
    }

}
