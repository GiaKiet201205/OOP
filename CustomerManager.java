package project;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerManager implements IModify<Customer> {

    private List<Customer> listCus;

    public CustomerManager() {
        listCus = new ArrayList<>();
    }

    @Override
    public void add(Customer cus) {
        this.listCus.add(cus);
        saveToFile("Customer.txt"); // Lưu vào file sau khi thêm
    }

    @Override
    public void edit(Customer updatedCustomer) {
        for (Customer cus : listCus) {
            if (cus.getId() == updatedCustomer.getId()) {
                cus.setUsername(updatedCustomer.getUsername());
                cus.setPassword(updatedCustomer.getPassword());
                cus.setEmail(updatedCustomer.getEmail());
                cus.setPhone(updatedCustomer.getPhone());
                System.out.println("Da chinh sua nguoi dung thanh cong.");
                saveToFile("Customer.txt"); // Lưu vào file sau khi sửa
                return;
            }
        }
        System.out.println("Khong tim thay nguoi dung!!!.");
    }

    @Override
    public void delete(int id) {
        boolean removed = listCus.removeIf(cus -> cus.getId() == id);
        if (removed) {
            System.out.println("Da xoa nguoi dung thanh cong.");
            saveToFile("Customer.txt"); // Lưu vào file sau khi xóa
        } else {
            System.out.println("Khong tim thay nguoi dung!!!.");
        }
    }

    @Override
    public void importTo(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Customer> result = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    int id = Integer.parseInt(fields[0]);
                    String username = fields[1];
                    String password = fields[2];
                    String email = fields[3];
                    String phone = fields[4];
                    Customer newCus = new Customer();
                    newCus.setId(id);
                    newCus.setUsername(username);
                    newCus.setPassword(password);
                    newCus.setEmail(email);
                    newCus.setPhone(phone);
                    result.add(newCus);
                }
            }
            this.listCus = result;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void exportFrom(String filePath) {
        try (FileWriter myWriter = new FileWriter(filePath)) {
            for (Customer cus : listCus) {
                myWriter.write(cus.getId() + "," + cus.getUsername() + "," + cus.getPassword() + "," + cus.getEmail() + "," + cus.getPhone() + "\n");
            }
            System.out.println("Dữ liệu đã được lưu thành công!");
        } catch (IOException ex) {
            System.out.println("Cannot export!");
        }
    }

    @Override
    public List<Customer> search(List<Customer> t, String attribute, String value) {
        List<Customer> result = new ArrayList<>();
        switch (attribute.toLowerCase()) {
            case "username":
                result = t.stream().filter(c -> c.getUsername().contains(value)).collect(Collectors.toList());
                break;
            case "email":
                result = t.stream().filter(c -> c.getEmail().contains(value)).collect(Collectors.toList());
                break;
            case "phone":
                result = t.stream().filter(c -> c.getPhone().contains(value)).collect(Collectors.toList());
                break;
            default:
                System.out.println("Thuoc tinh khong hop le!");
        }
        return result;
    }

    public void displayCustomers(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("Khong co du lieu.");
        } else {
            for (Customer cus : customers) {
                System.out.println("ID: " + cus.getId() + " - Username: " + cus.getUsername() + " - Email: " + cus.getEmail() + " - Phone: " + cus.getPhone());
            }
        }
    }

    public void saveToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Customer cus : listCus) {
                writer.write(cus.getId() + "," + cus.getUsername() + "," + cus.getPassword() + "," + cus.getEmail() + "," + cus.getPhone() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Loi khong luu duoc!!! " + e.getMessage());
        }
    }

    public List<Customer> getListCus() {
        return listCus;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerManager manager = new CustomerManager();
        manager.importTo("Customer.txt"); 

        while (true) {
            System.out.println("\n=== MENU QUAN LY NGUOI DUNG ===");
            System.out.println("1. Them nguoi dung");
            System.out.println("2. Sua nguoi dung");
            System.out.println("3. Xoa nguoi dung");
            System.out.println("4. Hien thi danh sach nguoi dung");
            System.out.println("5. Tim kiem nguoi dung");
            System.out.println("0. Thoat");
            System.out.print("--Chon chuc nang: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Nhap ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhap username: ");
                    String username = scanner.nextLine();
                    System.out.print("Nhap password: ");
                    String password = scanner.nextLine();
                    System.out.print("Nhap email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nhap phone: ");
                    String phone = scanner.nextLine();
                    manager.add(new Customer(id, username, password, email, phone));
                    break;
                case 2:
                    System.out.print("Nhap ID cua nguoi dung can chinh sua: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Nhap username moi: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Nhap password moi: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Nhap email moi: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nhap phone moi: ");
                    String newPhone = scanner.nextLine();
                    manager.edit(new Customer(editId, newUsername, newPassword, newEmail, newPhone));
                    break;
                case 3:
                    System.out.print("Nhap ID cua nguoi dung can xoa: ");
                    int deleteId = scanner.nextInt();
                    manager.delete(deleteId);
                    break;
                case 4:
                    System.out.println("\nDanh sach nguoi dung:");
                    manager.displayCustomers(manager.getListCus());
                    break;
                case 5:
                    System.out.print("Nhap thuoc tinh (username/email/phone) de tim kiem: ");
                    String attribute = scanner.nextLine();
                    System.out.print("Nhap gia tri de tim kiem: ");
                    String value = scanner.nextLine();
                    System.out.println("Ket qua tim kiem:");
                    manager.displayCustomers(manager.search(manager.getListCus(), attribute, value));
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
