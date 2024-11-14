/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

public class Warranty {

    public static int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    private String conditions;
    private static int warrantyPeriod = 5;
    private String warrantyType;

    public Warranty(String conditions, String warrantyType) {
        this.conditions = conditions;
        this.warrantyType = warrantyType;
    }

    public String getConditions() {
        return conditions;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" conditions: ").append(conditions);
        sb.append(", warranty period: ").append(warrantyPeriod).append(" months");
        sb.append(", warrantyType: ").append(warrantyType);
        return sb.toString();
    }

}
