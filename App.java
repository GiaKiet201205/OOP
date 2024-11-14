package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

            boolean c = true;
        while (c) {
            CustomerManager manaCus = new CustomerManager();
            System.out.println("----------LOG IN----------");
            System.out.println("1. Dang nhap.");
            System.out.println("2. Dang ki.");
            System.out.println("0. Thoat chuong trinh.");
            System.err.println("--------------------------");
            int choice;
            try {
                choice = sc.nextInt();
                App.sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("User name: ");
                        String username = sc.nextLine();
                        System.out.print("Password: ");
                        String password = sc.nextLine();
                        if ("admin".equals(username) && "admin123".equals(password)) {
                            System.out.println("Hello Admin!");
                            while (true) {
                                System.out.println("\n=== MENU QUAN LY ===");
                                System.out.println("1. Quan ly nguoi dung");
                                System.out.println("2. Quan ly don hang");
                                System.out.println("3. Quan ly san pham");
                                System.out.println("4. Thong ke");
                                System.out.println("0. Thoat");
                                System.out.print("--Chon chuc nang: ");

                                sc.nextLine();

                                switch (choice) {
                                    case 1:
                                        System.out.println("UserManager...");
                                        CustomerManager.main(args);
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
                                        sc.close();
                                        return;

                                    default:
                                        System.out.println("Lua chon khong hop le.");
                                }
                            }
                        } else {
                            manaCus.importTo("Customer.txt");
                            List<Customer> listCus = manaCus.getListCus();
                            boolean found = false;

                            for (Customer cus : listCus) {
                                if (username.equals(cus.getUsername()) && password.equals(cus.getPassword())) {
                                    System.out.println("Dang Nhap Thanh Cong!");
                                    cus.menuForCustomer();
                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                System.out.println("Nhap sai ten hoac mat khau. Vui long thu lai!");
                            }
                        }
                        break;
                    case 2:
                        boolean z = true;
                        Customer newCus = new Customer();

                        while (z) {
                            System.err.print("Nhap ten dang nhap: ");
                            String name = App.sc.nextLine();
                            System.err.println(name);
                            System.err.print("Nhap mat khau: ");
                            String pass = App.sc.nextLine();
                            System.err.print("Nhap email cua ban: ");
                            String email = App.sc.nextLine();
                            System.out.print("Nhap sdt cua ban: ");
                            String phone = App.sc.nextLine();
                            newCus = newCus.register(name, pass, email, phone);
                            if (newCus == null) {
                                System.err.println("Dang ki that bai! Vui long thu lai!");
                                continue;
                            }
                            System.out.println("Successfully! Xin chao " + name + "!Dang nhap!");
                            manaCus.importTo("Customer.txt");
                            manaCus.add(newCus);
                            manaCus.exportFrom("Customer.txt");
                            break;
                        }
                        break;
                    default:
                        System.out.println("Thoat chuong trinh!");
                        c = false;
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    }
