package project;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OrderManager implements IModify<Order> {

    @Override
    public void add(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void importTo(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void exportFrom(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Order> search(List<Order> t, String attribute, String value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Order[] orders = new Order[0];
    
    public Order[] getOrders() {
        return orders;
    }

    // Thêm đơn hàng
    public void addOrder(Order order) {
        orders = Arrays.copyOf(orders, orders.length + 1);
        orders[orders.length - 1] = order;
        saveToFile();
        System.out.println("Da them don hang: " + order);
    }

    // Sửa thông tin đơn hàng
    public void editOrder(int id, String newCustomerName, String newProduct, int newQuantity, double newPrice) {
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setCustomerName(newCustomerName);
                order.setProduct(newProduct);
                order.setQuantity(newQuantity);
                order.setPrice(newPrice);
                saveToFile();
                System.out.println("Da sua don hang voi ID: " + id);
                return;
            }
        }
        System.out.println("Khong tim thay don hang co ID: " + id);
    }

    // Xóa đơn hàng theo ID
    public void removeOrder(int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                for (int j = i; j < orders.length - 1; j++) {
                    orders[j] = orders[j + 1];
                }
                orders = Arrays.copyOf(orders, orders.length - 1);
                saveToFile();
                System.out.println("Da xoa don hang voi ID: " + id);
                return;
            }
        }
        System.out.println("Khong tim thay don hang co ID: " + id);
    }

    // Hiển thị tất cả đơn hàng
    public void displayOrders() {
        if (orders.length == 0) {
            System.out.println("Danh sach don hang rong.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    //Hàm xem chi tiết sản phẩm
    public void viewOrderDetails(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                System.out.println("Chi tiet don hang:");
                System.out.println("Ten san pham: " + order.getProduct());
                System.out.println("Gia san pham: " + order.getPrice());
                System.out.println("So luong: " + order.getQuantity());
                return;
            }
        }
        System.out.println("Khong tim thay don hang co ID: " + id);
    }

    // Tìm kiếm đơn hàng theo ID hoặc tên khách hàng
    public void searchOrder(String keyword) {
        boolean found = false;
        for (Order order : orders) {
            if (String.valueOf(order.getId()).equals(keyword) || order.getCustomerName().equalsIgnoreCase(keyword)) {
                System.out.println("Tim thay: " + order);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay don hang: " + keyword);
        }
    }

    // Chỉnh sửa trạng thái đơn hàng
    public void updateStatus(int id, String newStatus) {
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setStatus(newStatus);
                saveToFile();
                System.out.println("Da cap nhat trang thai cho don hang co ID: " + id);
                return;
            }
        }
        System.out.println("Khong tim thay don hang co ID: " + id);
    }

    // Lưu danh sách đơn hàng vào file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("orders.txt"))) {
            oos.writeObject(orders);
            System.out.println("Du lieu da duoc luu vao file.");
        } catch (IOException e) {
            System.out.println("loi khong luu duoc!!! " + e.getMessage());
        }
    }

    // Đọc dữ liệu đơn hàng từ file
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("orders.txt"))) {
            orders = (Order[]) ois.readObject();
            System.out.println("Thong tin don hang da duoc tai len.");
        } catch (FileNotFoundException e) {
            System.out.println("Khong có don hang nao trong he thong.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR!!! " + e.getMessage());
        }
    }

    // Hàm main với menu điều khiển
    public static void main(String[] args) {
        OrderManager manager = new OrderManager();
        manager.loadFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU QUAN LY DON HANG ===");
            System.out.println("1. Them don hang");
            System.out.println("2. Sua don hang");
            System.out.println("3. Xoa don hang");
            System.out.println("4. Hien thi danh sach don hang");
            System.out.println("5. Tim kiem don hang");
            System.out.println("6. Cap nhat trang thai don hang");
            System.out.println("7. Xem chi tiet don hang");
            System.out.println("0. Thoat");
            System.out.print("--Chon chuc nang: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhap ID cho don hang: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhap ten khach hang: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Nhap san pham: ");
                    String product = scanner.nextLine();
                    System.out.print("Nhap so luong: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Nhap gia: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.print("Nhap trang thai: ");
                    String status = scanner.nextLine();
                    manager.addOrder(new Order(id, customerName, product, quantity, price, status));
                    break;

                case 2:
                    System.out.print("Nhap ID don hang can sua: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhap ten khach hang moi: ");
                    String newCustomerName = scanner.nextLine();
                    System.out.print("Nhap san pham moi: ");
                    String newProduct = scanner.nextLine();
                    System.out.print("Nhap so luong moi: ");
                    int newQuantity = scanner.nextInt();
                    System.out.print("Nhap gia moi: ");
                    double newPrice = scanner.nextDouble();
                    manager.editOrder(editId, newCustomerName, newProduct, newQuantity, newPrice);
                    break;

                case 3:
                    System.out.print("Nhap ID don hang can xoa: ");
                    int deleteId = scanner.nextInt();
                    manager.removeOrder(deleteId);
                    break;

                case 4:
                    manager.displayOrders();
                    break;

                case 5:
                    System.out.print("Nhap ID hoac ten khach hang can tim kiem trong don hang: ");
                    String keyword = scanner.nextLine();
                    manager.searchOrder(keyword);
                    break;

                case 6:
                    System.out.print("Nhap ID cho don hang can cap nhat trang thai: ");
                    int statusId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhap trang thai moi: ");
                    String newStatus = scanner.nextLine();
                    manager.updateStatus(statusId, newStatus);
                    break;
                case 7:
                    System.out.print("Nhap ID don hang de xem chi tiet: ");
                    int detailsId = scanner.nextInt();
                    manager.viewOrderDetails(detailsId);
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh quan ly don hang.");
                    return;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}

