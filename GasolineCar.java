/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

public class GasolineCar extends Car{
    private int fuelComsumption;
    private int tankComsumption;

    public GasolineCar(int fuelComsumption, int tankComsumption, int id, String name, String brand, int yearOfProduction, double price, String category, Warranty warranty) {
        super(id, name, brand, yearOfProduction, price, category, warranty);
        this.fuelComsumption = fuelComsumption;
        this.tankComsumption = tankComsumption;
    }

    public int getFuelComsumption() {
        return fuelComsumption;
    }

    public int getTankComsumption() {
        return tankComsumption;
    }

    public void setFuelComsumption(int fuelComsumption) {
        this.fuelComsumption = fuelComsumption;
    }

    public void setTankComsumption(int tankComsumption) {
        this.tankComsumption = tankComsumption;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", fuelComsumption: ").append(fuelComsumption);
        sb.append(", tankComsumption: ").append(tankComsumption);
        return sb.toString();
    }

}

