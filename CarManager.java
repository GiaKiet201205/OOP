package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CarManager implements IModify<Car> {

    private List<Car> cars;

    // Constructor
    public CarManager() {
        cars = new ArrayList<>();
    }

    @Override
    public void add(Car car) {
        cars.add(car);
        saveToFile("Car.txt"); // Luu vao file sau khi them
    }

    @Override
    public void edit(Car updatedCar) {
        try {
            List<Car> result = new ArrayList<>();
            for (Car car : cars) {
                if (car.getId() == updatedCar.getId()) {
                    car = updatedCar;  // Cap nhat thong tin xe
                }
                result.add(car);
            }
            this.cars = result;
            saveToFile("Car.txt"); // Luu vao file sau khi sua
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int id) {
        cars.removeIf(car -> car.getId() == id);
        saveToFile("Car.txt"); // Luu vao file sau khi xoa
    }

    @Override
    public void importTo(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Car> result = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 10) {
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    String brand = fields[2];
                    int year = Integer.parseInt(fields[3]);
                    double price = Double.parseDouble(fields[4]);
                    String cate = fields[5];
                    String[] word = fields[6].split("(?<= 5 )|(?= 5 )");
                    Warranty w = new Warranty(word[0], word[2]);
                    if ("e".equalsIgnoreCase(fields[7])) {
                        int battery = Integer.parseInt(fields[8]);
                        int range = Integer.parseInt(fields[9]);
                        ElectricCar elec = new ElectricCar(battery, range, id, name, brand, year, price, cate, w);
                        result.add(elec);
                    } else if ("g".equalsIgnoreCase(fields[7])) {
                        int fuel = Integer.parseInt(fields[8]);
                        int tank = Integer.parseInt(fields[9]);
                        GasolineCar gas = new GasolineCar(fuel, tank, id, name, brand, year, price, cate, w);
                        result.add(gas);
                    }
                }
            }
            this.cars = result;
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void exportFrom(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Car car : cars) {
                writer.write(car.getId() + "," + car.getName() + "," + car.getBrand() + "," + car.getYearOfProduction()
                        + "," + car.getPrice() + "," + car.getCategory() + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Cannot export!");
        }
    }

    @Override
    public List<Car> search(List<Car> cars, String attribute, String value) {
        List<Car> result = new ArrayList<>();
        attribute = attribute.toLowerCase();
        if ("id".equals(attribute)) {
            int id = Integer.parseInt(value);
            for (Car car : cars) if (car.getId() == id) result.add(car);
        } else if ("name".equals(attribute)) {
            for (Car car : cars) if (value.equals(car.getName())) result.add(car);
        } else if ("brand".equals(attribute)) {
            for (Car car : cars) if (value.equals(car.getBrand())) result.add(car);
        } else if ("year of production".equals(attribute)) {
            for (Car car : cars) {
                int year = Integer.parseInt(value);
                if (car.getYearOfProduction() == year) result.add(car);
            }
        } else if ("category".equals(attribute)) {
            for (Car car : cars) if (value.equals(car.getCategory())) result.add(car);
        }
        return result;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void saveToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Car car : cars) {
                writer.write(car.getId() + "," + car.getName() + "," + car.getBrand() + "," + car.getYearOfProduction()
                        + "," + car.getPrice() + "," + car.getCategory() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    public void displayCars(List<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("No data found.");
        } else {
            for (Car car : cars) {
                System.out.println("ID: " + car.getId() + " - Name: " + car.getName() + " - Brand: " + car.getBrand() +
                        " - Year: " + car.getYearOfProduction() + " - Price: " + car.getPrice() + " - Category: " + car.getCategory());
            }
        }
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarManager manager = new CarManager();
        manager.importTo("Car.txt"); // Import data from file

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Them Xe");
            System.out.println("2. Sua Xe");
            System.out.println("3. Xoa Xe");
            System.out.println("4. Hien Thi Danh Sach Xe");
            System.out.println("5. Tim Kiem Xe");
            System.out.println("6. Thoat");
            System.out.print("Chon thao tac: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Nhap ID Xe: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Nhap Ten Xe: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhap Hang Xe: ");
                    String brand = scanner.nextLine();
                    System.out.print("Nhap Nam San Xuat: ");
                    int year = scanner.nextInt();
                    System.out.print("Nhap Gia: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Nhap Loai Xe: ");
                    String category = scanner.nextLine();
                    manager.add(new Car(id, name, brand, year, price, category));
                    break;
                case 2:
                    System.out.print("Nhap ID cua xe can sua: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Nhap Ten Moi: ");
                    String newName = scanner.nextLine();
                    System.out.print("Nhap Hang Moi: ");
                    String newBrand = scanner.nextLine();
                    System.out.print("Nhap Nam San Xuat Moi: ");
                    int newYear = scanner.nextInt();
                    System.out.print("Nhap Gia Moi: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Nhap Loai Xe Moi: ");
                    String newCategory = scanner.nextLine();
                    manager.edit(new Car(editId, newName, newBrand, newYear, newPrice, newCategory));
                    break;
                case 3:
                    System.out.print("Nhap ID cua xe can xoa: ");
                    int deleteId = scanner.nextInt();
                    manager.delete(deleteId);
                    break;
                case 4:
                    System.out.println("\nDanh Sach Xe:");
                    manager.displayCars(manager.getCars());
                    break;
                case 5:
                    System.out.print("Nhap thuoc tinh tim kiem (id/name/brand/year/category): ");
                    String attribute = scanner.nextLine();
                    System.out.print("Nhap gia tri tim kiem: ");
                    String value = scanner.nextLine();
                    System.out.println("Ket Qua Tim Kiem:");
                    manager.displayCars(manager.search(manager.getCars(), attribute, value));
                    break;
                case 6:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        }
    }
}
