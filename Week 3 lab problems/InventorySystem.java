import java.util.Scanner;

class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String supplierName;
    private String category;

    // Static members
    private static int productCounter = 0;
    private static int totalProducts = 0;
    private static double totalInventoryValue = 0;
    private static int lowStockCount = 0;
    private static String[] categories = new String[20]; // to track categories
    private static int categoryCount = 0;

    // Constructor
    public Product(String productName, double price, int quantity, String supplierName, String category) {
        this.productId = generateProductId();
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.supplierName = supplierName;
        this.category = category;

        totalProducts++;
        totalInventoryValue += calculateProductValue();

        // Track categories (avoid duplicates)
        boolean exists = false;
        for (int i = 0; i < categoryCount; i++) {
            if (categories[i].equalsIgnoreCase(category)) {
                exists = true;
                break;
            }
        }
        if (!exists && categoryCount < categories.length) {
            categories[categoryCount++] = category;
        }
    }

    // Stock management
    public void addStock(int qty) {
        if (qty > 0) {
            quantity += qty;
            totalInventoryValue += qty * price;
            System.out.println(qty + " units added to " + productName);
        } else {
            System.out.println("Invalid stock quantity.");
        }
    }

    public void reduceStock(int qty) {
        if (qty > 0 && qty <= quantity) {
            quantity -= qty;
            totalInventoryValue -= qty * price;
            System.out.println(qty + " units sold/removed from " + productName);
        } else {
            System.out.println("Invalid or insufficient stock.");
        }
    }

    public boolean isLowStock() {
        return quantity < 10;
    }

    public double calculateProductValue() {
        return price * quantity;
    }

    public void updatePrice(double newPrice) {
        totalInventoryValue -= calculateProductValue();
        price = newPrice;
        totalInventoryValue += calculateProductValue();
    }

    public void displayProductInfo() {
        System.out.println(productId + " | " + productName + " | " + category +
                " | Supplier: " + supplierName + " | Price: " + price +
                " | Qty: " + quantity + (isLowStock() ? " (LOW STOCK)" : ""));
    }

    // Static methods
    public static double calculateTotalInventoryValue(Product[] products, int count) {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].calculateProductValue();
        }
        return total;
    }

    public static void findLowStockProducts(Product[] products, int count) {
        System.out.println("\n--- Low Stock Products ---");
        for (int i = 0; i < count; i++) {
            if (products[i].isLowStock()) {
                products[i].displayProductInfo();
            }
        }
    }

    public static void generateInventoryReport(Product[] products, int count) {
        System.out.println("\n===== Inventory Report =====");
        System.out.println("Total Products: " + totalProducts);
        System.out.println("Total Inventory Value: " + calculateTotalInventoryValue(products, count));
        System.out.println("Categories Available: ");
        for (int i = 0; i < categoryCount; i++) {
            System.out.println("- " + categories[i]);
        }
        findLowStockProducts(products, count);
        System.out.println("=============================");
    }

    public static Product searchProduct(Product[] products, int count, String productName) {
        for (int i = 0; i < count; i++) {
            if (products[i].productName.equalsIgnoreCase(productName)) {
                return products[i];
            }
        }
        return null;
    }

    // Private static method
    private static String generateProductId() {
        productCounter++;
        return "P" + String.format("%03d", productCounter);
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = new Product[20];
        int pCount = 0;

        while (true) {
            System.out.println("\n===== Inventory Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Show All Products");
            System.out.println("3. Add Stock");
            System.out.println("4. Reduce Stock");
            System.out.println("5. Search Product");
            System.out.println("6. Update Price");
            System.out.println("7. Generate Inventory Report");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: 
                    if (pCount == products.length) {
                        System.out.println("Inventory full!");
                        break;
                    }
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Supplier Name: ");
                    String supplier = sc.nextLine();
                    System.out.print("Enter Category: ");
                    String cat = sc.nextLine();
                    products[pCount++] = new Product(name, price, qty, supplier, cat);
                    System.out.println("Product added successfully.");
                    break;

                case 2: 
                    System.out.println("\n--- Product List ---");
                    for (int i = 0; i < pCount; i++) {
                        products[i].displayProductInfo();
                    }
                    break;

                case 3: 
                    System.out.print("Enter Product Index (0-" + (pCount - 1) + "): ");
                    int ai = sc.nextInt();
                    System.out.print("Enter Quantity to Add: ");
                    int aq = sc.nextInt();
                    if (ai >= 0 && ai < pCount) {
                        products[ai].addStock(aq);
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 4: 
                    System.out.print("Enter Product Index (0-" + (pCount - 1) + "): ");
                    int ri = sc.nextInt();
                    System.out.print("Enter Quantity to Reduce: ");
                    int rq = sc.nextInt();
                    if (ri >= 0 && ri < pCount) {
                        products[ri].reduceStock(rq);
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 5: 
                    System.out.print("Enter Product Name: ");
                    String sName = sc.nextLine();
                    Product found = Product.searchProduct(products, pCount, sName);
                    if (found != null) {
                        found.displayProductInfo();
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 6: 
                    System.out.print("Enter Product Index (0-" + (pCount - 1) + "): ");
                    int ui = sc.nextInt();
                    System.out.print("Enter New Price: ");
                    double np = sc.nextDouble();
                    if (ui >= 0 && ui < pCount) {
                        products[ui].updatePrice(np);
                        System.out.println("Price updated.");
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 7: 
                    Product.generateInventoryReport(products, pCount);
                    break;

                case 8: 
                    System.out.println("Exiting Inventory System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
