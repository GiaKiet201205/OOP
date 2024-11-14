package project;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserManager {
    private User[] users = new User[0];

    // Thêm người dùng
    public void addUser(User user) {
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
        saveToFile();
        System.out.println("Da them nguoi dung " + user);
    }

    // Sửa thông tin người dùng
    public void editUser(int id, String newName, String newEmail, String newPassword) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(newName);
                user.setEmail(newEmail);
                user.setPassword(newPassword);
                saveToFile();
                System.out.println("Da sua nguoi dung ID: " + id);
                return;
            }
        }
        System.out.println("KKhong tim thay nguoi dung co ID " + id);
    }

    // Xóa người dùng theo ID
    public void removeUser(int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId() == id) {
                for (int j = i; j < users.length - 1; j++) {
                    users[j] = users[j + 1];
                }
                users = Arrays.copyOf(users, users.length - 1);
                saveToFile();
                System.out.println("Da xoa nguoi dung ID: " + id);
                return;
            }
        }
        System.out.println("Khong tim thay nguoi dung co ID: " + id);
    }

    // Hiển thị tất cả người dùng
    public void displayUsers() {
        if (users.length == 0) {
            System.out.println("Danh sach nguoi dung rong");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    // Tìm kiếm người dùng theo ID hoặc tên
    public void searchUser(String keyword) {
        boolean found = false;
        for (User user : users) {
            if (String.valueOf(user.getId()).equals(keyword) || user.getName().equalsIgnoreCase(keyword)) {
                System.out.println("Tim thay " + user);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay nguoi dung " + keyword);
        }
    }

    // Lưu danh sách người dùng vào file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Customer.txt"))) {
            oos.writeObject(users);
            System.out.println("Thong tin ban da duoc luu vao he thong:");
        } catch (IOException e) {
            System.out.println("Loi khong luu duoc!!! " + e.getMessage());
        }
    }

    // Đọc dữ liệu người dùng từ file
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Customer.txt"))) {
            users = (User[]) ois.readObject();
            System.out.println("Thong tin nguoi dung da duoc tai len.");
        } catch (FileNotFoundException e) {
            System.out.println("Khong co nguoi dung nao trong he thong !!!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR!!! " + e.getMessage());
        }
    }

    // Hàm main với menu điều khiển
    public static void main(String[] args) {
        UserManager manager = new UserManager();
        manager.loadFromFile();
        Scanner scanner = new Scanner(System.in);

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
                    System.out.print("Nhap ID nguoi dung: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhap ten nguoi dung: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhap email nguoi dung: ");
                    String email = scanner.nextLine();
                    System.out.print("Nhap password nguoi dung: ");
                    String password = scanner.nextLine();
                    manager.addUser(new User(id, name, email, password));
                    break;

                case 2:
                    System.out.print("Nhap ID nguoi dung can sua: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhap ten moi: ");
                    String newName = scanner.nextLine();
                    System.out.print("Nhap email moi: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nhap password moi: ");
                    String newPassword = scanner.nextLine();
                    manager.editUser(editId, newName, newEmail, newPassword);
                    break;

                case 3:
                    System.out.print("Nhap ID nguoi dung can xoa: ");
                    int deleteId = scanner.nextInt();
                    manager.removeUser(deleteId);
                    break;

                case 4:
                    manager.displayUsers();
                    break;

                case 5:
                    System.out.print("Nhap ID hoac ten nguoi dung can tim: ");
                    String keyword = scanner.nextLine();
                    manager.searchUser(keyword);
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh quan ly nguoi dung.");
                    return;

                default:
                    System.out.println("Lua chon khong hop le!!!");
            }
        }
    }
}

