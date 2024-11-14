package project;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ProductManager {
    private Product[] products = new Product[0];

    public Product[] getProducts() {
        return products;
    }

    // Thêm sản phẩm
    public void addProduct(Product product) {
        products = Arrays.copyOf(products, products.length + 1);
        products[products.length - 1] = product;
        saveToFile();
        System.out.println("Da them san pham " + product);
    }

    // Sửa thông tin sản phẩm
    public void editProduct(int id, String newName, double newPrice) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(newName);
                product.setPrice(newPrice);
                saveToFile();
                System.out.println("Da sua san pham voi ID: " + id);
                return;
            }
        }
        System.out.println("Khong tim thay san pham co ID: " + id);
    }

    // Xóa sản phẩm theo ID
    public void removeProduct(int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() == id) {
                for (int j = i; j < products.length - 1; j++) {
                    products[j] = products[j + 1];
                }
                products = Arrays.copyOf(products, products.length - 1);
                saveToFile();
                System.out.println("Da xoa san pham voi ID: " + id);
                return;
            }
        }
        System.out.println("Khong tim thay san pham co ID: " + id);
    }

    // Hiển thị tất cả sản phẩm
    public void displayProducts() {
        if (products.length == 0) {
            System.out.println("Danh sach san pham rong.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Tìm kiếm sản phẩm theo ID hoặc tên
    public void searchProduct(String keyword) {
        boolean found = false;
        for (Product product : products) {
            if (String.valueOf(product.getId()).equals(keyword) || product.getName().equalsIgnoreCase(keyword)) {
                System.out.println("Tim thay: " + product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay san pham : " + keyword);
        }
    }

    // Lưu danh sách sản phẩm vào file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.txt"))) {
            oos.writeObject(products);
            System.out.println("Du lieu da duoc luu vao hẹ thong.");
        } catch (IOException e) {
            System.out.println("Loi khong luu duoc!!! " + e.getMessage());
        }
    }

    // Đọc dữ liệu sản phẩm từ file
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("products.txt"))) {
            products = (Product[]) ois.readObject();
            System.out.println("Thong tin san pham da duoc tai len");
        } catch (FileNotFoundException e) {
            System.out.println("Khong co thong tin trong he thong");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR!!! " + e.getMessage());
        }
    }

    // Hàm main với menu điều khiển
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        manager.loadFromFile(); // Tải dữ liệu từ file khi bắt đầu chương trình
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU QUAN LY SAN PHAM ===");
            System.out.println("1. Them san pham");
            System.out.println("2. Sua san pham");
            System.out.println("3. Xoa sam pham");
            System.out.println("4. Hien thi danh sach san pham");
            System.out.println("5. Tim kiem san pham");
            System.out.println("0. Thoat");
            System.out.print("--Chon chuc nang: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhap ID san pham: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhap ten san pham: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhap gia san pham: ");
                    double price = scanner.nextDouble();
                    manager.addProduct(new Product(id, name, price));
                    break;

                case 2:
                    System.out.print("Nhap ID san pham can sua: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhap ten moi: ");
                    String newName = scanner.nextLine();
                    System.out.print("Nhap gia moi: ");
                    double newPrice = scanner.nextDouble();
                    manager.editProduct(editId, newName, newPrice);
                    break;

                case 3:
                    System.out.print("Nhap ID san pham can xoa: ");
                    int deleteId = scanner.nextInt();
                    manager.removeProduct(deleteId);
                    break;

                case 4:
                    manager.displayProducts();
                    break;

                case 5:
                    System.out.print("Nhap ID hoac ten san pham can tim: ");
                    String keyword = scanner.nextLine();
                    manager.searchProduct(keyword);
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh quan ly san pham");
                    return;

                default:
                    System.out.println("Lua chon khong hop lẹ!!!");
            }
        }
    }
}
