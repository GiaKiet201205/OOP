package project;

import java.util.Scanner;

public class StatisticsManager {
    private OrderManager orderManager;

    public StatisticsManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    // Thống kê đơn hàng theo trạng thái (đã giao, chưa giao, đã hủy)
    public void reportOrderStatus() {
        int countDelivered = 0;
        int countPending = 0;
        int countCancelled = 0;

        for (Order order : orderManager.getOrders()) {
            String status = order.getStatus();

            if ("Da giao".equalsIgnoreCase(status)) {
                countDelivered++;
            } else if ("Chua giao".equalsIgnoreCase(status)) {
                countPending++;
            } else if ("Da huy".equalsIgnoreCase(status)) {
                countCancelled++;
            }
        }
        System.out.println("=== THONG KE DON HANG THEO TRANG THAI ===");
        System.out.println("Da giao: " + countDelivered + " don hang");
        System.out.println("Chua giao: " + countPending + " don hang");
        System.out.println("Da huy: " + countCancelled + " don hang");
    }

    // Thống kê tổng doanh thu của cửa hàng từ các đơn hàng đã giao
    public void reportTotalRevenue() {
        double totalRevenue = 0;

        for (Order order : orderManager.getOrders()) {
            if ("Đã giao".equalsIgnoreCase(order.getStatus())) {
                totalRevenue += order.getPrice();
            }
        }

        // Hiển thị tổng doanh thu
        System.out.println("=== TONG DOANH THU TU CAC DON HANG DA GIAO ===");
        System.out.println("Tong doanh thu: " + totalRevenue + " VND");
    }

    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        orderManager.loadFromFile(); 
        
        StatisticsManager statisticsManager = new StatisticsManager(orderManager);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== MENU THONG KE ===");
            System.out.println("1. Thong ke don hang theo trang thai");
            System.out.println("2. Thong ke theo tong doanh thu");
            System.out.println("0. Thoat");
            System.out.print("Chọn chuc nang: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    statisticsManager.reportOrderStatus();
                    break;
                case 2:
                    statisticsManager.reportTotalRevenue();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Error!");
            }
        }
    }
}
