/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Customer {
    private int id;
    private String username, password, email, phone;
    private Cart cart;
    static Scanner sc = new Scanner(System.in);

    public Customer(int id,String username, String password, String email, String phone) {
        this.email = email;
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.username = username;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Cart getCart() {
        return cart;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id);
        sb.append(", username: ").append(username);
        sb.append(", email: ").append(email);
        sb.append(", phone: ").append(phone);
        return sb.toString();
    }
    public static boolean validatePassword(String password) {
        // Check if password length is between 1 and 10
        if (password.length() < 1 || password.length() > 10) {
            System.out.println("Password must be between 1 and 10 characters.");
            return false;
        }

        // Regex to check if the password contains at least one uppercase letter
        String uppercasePattern = ".*[A-Z].*";

        // Regex to check if the password contains at least one special character
        String specialCharPattern = ".*[!@#$%^&*()_+=|<>?{}\\\\[\\\\]~-].*";

        // Check if password contains at least one uppercase letter
        if (!password.matches(uppercasePattern)) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }

        // Check if password contains at least one special character
        if (!password.matches(specialCharPattern)) {
            System.out.println("Password must contain at least one special character.");
            return false;
        }

        // If all conditions are met
        return true;
    }

    public static boolean validateEmail(String email) {
        // Regular expression for a valid email address
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        // Check if the email matches the pattern
        if (!email.matches(emailPattern)) {
            System.out.println("Invalid email format.");
            return false;
        }

        return true;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        // Regular expression for phone number that starts with '0' and contains only digits
        String phonePattern = "^0\\d{9}$"; // Assumes a total length of 10 digits starting with 0

        // Check if the phone number matches the pattern
        if (!phoneNumber.matches(phonePattern)) {
            System.out.println("Invalid phone number. It must start with '0' and be 10 digits long.");
            return false;
        }

        return true;
    }

    public List<Car> viewProduct(){
        CarManager carMana = new CarManager();
        List<Car> listCar = new ArrayList<>();
        carMana.importTo("Car.txt");
        listCar = carMana.getCars();
        return listCar;
    }

    public void viewCart(){
        boolean c = true;
        List<CartItem> listItem = new ArrayList<>();
        Cart cart1 = new Cart(listItem);
        while(c){
            if(cart==null){
                System.out.println("Your cart have no product!");
            }else{
                System.err.println("----------");
                System.err.println(this.cart.toString());
                System.err.println("----------");
            }
            try {
                List<Car> listCar = new ArrayList<>();
                listCar = viewProduct();
                for(int i = 0; i< listCar.size(); i++){
                    System.err.println((i+1)+". "+listCar.get(i));
                }
                System.out.println("0. Exit!");
                System.err.print("Enter car's index:"); int choice = sc.nextInt();
                if(choice== 0){
                    System.err.println("Exit the cart!");
                    //c = false;
                    break;
                }
                System.out.print("Enter the quantity: "); int quantity = sc.nextInt();
                CartItem cartItem = new CartItem(listCar.get(choice -1), quantity);
                listItem.add(cartItem);
                
            } catch (Exception e) {
                System.err.println("Invalid input! Please try again!");
            }
            cart1.setItem(listItem);
            this.cart=cart1;
        }
    }

    public void deleteItem(){
        boolean c = true;
        while(c){
            if(this.cart==null){
                System.err.println("Your cart is empty!There are nothing to delete!");break;
            }
            Cart cart = new Cart();
            cart = this.cart;
            List<CartItem> listItem = new ArrayList<>();
            listItem = cart.getItem();
            for(int i = 0; i< listItem.size(); i++){
                System.out.println("1. "+listItem.get(i));
            }
            System.err.println("0. Exit!");
            System.err.print("Enter the number which you want to delete: "); 
            try {
                int choice = sc.nextInt();
                if(choice == 0) break;
                if(choice -1 > listItem.size() || choice - 1 <0){ System.err.println("Wrong input!"); break;}
                listItem.remove(choice-1);
            } catch (Exception e) {
                System.out.println("Please enter the number which appear on the screen!"); sc.next();
            }
            cart.setItem(listItem);
            this.cart = cart;
        }
    }

    public Customer register(String username, String password,String email, String phone){
        CustomerManager manaCus = new CustomerManager();
        manaCus.importTo("Customer.txt");
        List<Customer> listCus = new ArrayList<>();
        listCus = manaCus.getListCus();
        for(Customer cus : listCus){
            if(username.equals(cus.getUsername())){
                System.err.println("The username "+username+" has been used!");
                return null;
            }else if(!validatePassword(password)){
                return null;
            }else if (!validateEmail(email)) {
                return null;
            }else if(!validatePhoneNumber(phone)){
                return null;
            }
        }
        int id = ThreadLocalRandom.current().nextInt(1000,9999);
        Customer newCus = new Customer(id,username,password,email,phone);
        return newCus;
    }

    public void menuForCustomer(){
        boolean c =true;
        while (c) { 
            System.out.println("----------WELCOM----------");
            System.out.println("1. View product.");
            System.out.println("2. View cart.");
            System.out.println("3. Delete product from cart.");
            System.out.println("4. View profile.");
            System.out.println("5. Update your profile.");
            System.out.println("0. Exit!");
            System.out.println("---------------------------"); 
            int choice;
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        List<Car> listCar = new ArrayList<>();
                        listCar = viewProduct();
                        for(Car car: listCar){
                            System.out.println(car);
                            System.err.println("----------");
                        }
                        break;  
                    case 2:
                        viewCart();
                        break;
                    case 3:
                        deleteItem();
                        break;
                    case 4:
                        String s = toString();
                        System.err.println(s);
                        break;
                    case 5:
                        boolean a = true;
                        while(a){
                            
                            System.out.println("1. Change password.");
                            System.err.println("2. Change your email.");
                            System.err.println("3. Change your phone number.");
                            System.err.println("0. Exit!");
                            try {
                                int choose = sc.nextInt();sc.nextLine();
                                if(choose >3|| choose <1){System.err.println("Exit!"); break;}
                                switch (choose) {
                                    case 1:
                                        System.err.print("Enter old password: "); String password = sc.nextLine();
                                        
                                        if(!password.equals(this.password)){
                                            System.err.println("Incorrect old password!"); break;
                                        }
                                        if(this.password.equals(password)){
                                            System.err.print("Enter new password:");
                                            String newpass = sc.nextLine();
                                            if(validatePassword(password)){
                                                this.password = newpass;
                                                System.out.println("Successfully!");
                                            }
                                        } 
                                    break;
                                case 2:
                                    System.err.print("Enter new email: ");String newemail= sc.nextLine();
                                        if(validateEmail(newemail)){
                                            this.email = newemail;
                                            System.out.println("Successfully!");
                                        }
                                    break;
                                case 3:
                                    System.err.print("Enter your new phone number"); String num = sc.nextLine();
                                    if(validatePhoneNumber(num)){
                                        setPassword(num);
                                        System.err.println("Successfully!");
                                    }
                                    break;
                                }
                            } catch (Exception e) {
                                System.err.println("Undefined input! You have just type not a number!");
                            }
                        }
                        break;
                      
                    default:
                        System.out.println("Exit!");
                        c = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter number from 0 to 6 to use the application!");
                sc.next();
            }
        }
    }
}
