package project;

import java.util.Scanner;

public class Admin {
    private static final String username = "admin";
    private static final String password = "admin123";
    public static boolean isAdmin(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ten dang nhap: ");
        String username = scanner.nextLine();

        System.out.print("Nhap password: ");
        String password = scanner.nextLine();

        if (Admin.isAdmin(username, password)) {
            System.out.println("Chao mung Admin!");
                    while (true) {
            System.out.println("\n=== MENU QUAN LY ===");
            System.out.println("1. Quan ly nguoi dung");
            System.out.println("2. Quan ly don hang");
            System.out.println("3. Quan ly san pham");
            System.out.println("4. Thong ke");
            System.out.println("0. Thoat");
            System.out.print("--Chon chuc nang: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("UserManager...");
                    UserManager.main(args);
                    break;

                case 2:
                    System.out.println("OrderManager...");
                    OrderManager.main(args);
                    break;

                case 3:
                    System.out.println("ProductManager...");
                    ProductManager.main(args);
                    break;
                    
                case 4:
                    System.out.println("StatisticsManager...");
                    StatisticsManager.main(args);
                    break;                    

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
        } else {
            System.out.println("!!!");
        }

        scanner.close();
    }
}


