import java.util.Scanner;

class Product {
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;

    private static int totalProducts = 0;
    private static String[] categories = {"Electronics", "Clothing", "Books", "Home", "Toys"};

    public Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }
    public void reduceStock(int qty) { stockQuantity -= qty; }
    public void increaseStock(int qty) { stockQuantity += qty; }

    public static int getTotalProducts() { return totalProducts; }
    public static String[] getCategories() { return categories; }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) return p;
        }
        return null;
    }

    public static void getProductsByCategory(Product[] products, String category) {
        System.out.println("Products in category: " + category);
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                System.out.println(p.getProductId() + " - " + p.getProductName() + " - Rs." + p.getPrice() + " - Stock: " + p.getStockQuantity());
            }
        }
    }

    public void displayProduct() {
        System.out.println(productId + " | " + productName + " | Rs." + price + " | " + category + " | Stock: " + stockQuantity);
    }
}

class ShoppingCart {
    private String cartId;
    private String customerName;
    private Product[] products;
    private int[] quantities;
    private int productCount;
    private double cartTotal;

    public ShoppingCart(String cartId, String customerName) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.products = new Product[20]; // max 20 items in cart
        this.quantities = new int[20];
        this.productCount = 0;
        this.cartTotal = 0;
    }

    public void addProduct(Product product, int quantity) {
        if (product.getStockQuantity() < quantity) {
            System.out.println("Not enough stock for " + product.getProductName());
            return;
        }
        for (int i = 0; i < productCount; i++) {
            if (products[i].getProductId().equals(product.getProductId())) {
                quantities[i] += quantity;
                product.reduceStock(quantity);
                System.out.println(quantity + " more " + product.getProductName() + " added to cart.");
                calculateTotal();
                return;
            }
        }
        products[productCount] = product;
        quantities[productCount] = quantity;
        product.reduceStock(quantity);
        productCount++;
        System.out.println(product.getProductName() + " added to cart.");
        calculateTotal();
    }

    public void removeProduct(String productId) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getProductId().equals(productId)) {
                products[i].increaseStock(quantities[i]);
                System.out.println(products[i].getProductName() + " removed from cart.");
                // Shift remaining products
                for (int j = i; j < productCount - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                products[productCount - 1] = null;
                quantities[productCount - 1] = 0;
                productCount--;
                calculateTotal();
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    public void calculateTotal() {
        cartTotal = 0;
        for (int i = 0; i < productCount; i++) {
            cartTotal += products[i].getPrice() * quantities[i];
        }
    }

    public void displayCart() {
        System.out.println("Cart for " + customerName + " (" + cartId + ")");
        if (productCount == 0) {
            System.out.println("Cart is empty.");
        } else {
            for (int i = 0; i < productCount; i++) {
                System.out.println(products[i].getProductName() + " x " + quantities[i] + " = Rs." + (products[i].getPrice() * quantities[i]));
            }
            System.out.println("Cart Total: Rs." + cartTotal);
        }
        System.out.println("---------------------------");
    }

    public void checkout() {
        if (productCount == 0) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }
        System.out.println("Checkout successful for " + customerName + ". Total: Rs." + cartTotal);
        productCount = 0;
        cartTotal = 0;
        products = new Product[20];
        quantities = new int[20];
    }
}

public class ShoppingCartSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create 10 products
        Product[] products = new Product[10];
        products[0] = new Product("P001", "Smartphone", 15000, "Electronics", 10);
        products[1] = new Product("P002", "Jeans", 1200, "Clothing", 20);
        products[2] = new Product("P003", "Cookbook", 500, "Books", 15);
        products[3] = new Product("P004", "Mixer", 2500, "Home", 8);
        products[4] = new Product("P005", "Toy Car", 350, "Toys", 25);
        products[5] = new Product("P006", "Laptop", 40000, "Electronics", 5);
        products[6] = new Product("P007", "T-shirt", 400, "Clothing", 30);
        products[7] = new Product("P008", "Novel", 300, "Books", 12);
        products[8] = new Product("P009", "Vacuum Cleaner", 3200, "Home", 6);
        products[9] = new Product("P010", "Puzzle", 250, "Toys", 18);

        ShoppingCart cart = new ShoppingCart("CART001", "Alice");

        while (true) {
            System.out.println("\n--- Online Shopping Cart Menu ---");
            System.out.println("1. Browse all products");
            System.out.println("2. Browse by category");
            System.out.println("3. Add product to cart");
            System.out.println("4. Remove product from cart");
            System.out.println("5. View cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("All Products:");
                    for (Product p : products) {
                        p.displayProduct();
                    }
                    break;
                case 2:
                    System.out.println("Available categories:");
                    String[] cats = Product.getCategories();
                    for (String cat : cats) System.out.println("- " + cat);
                    System.out.print("Enter category: ");
                    String catChoice = sc.nextLine();
                    Product.getProductsByCategory(products, catChoice);
                    break;
                case 3:
                    System.out.print("Enter Product ID to add: ");
                    String addId = sc.nextLine();
                    Product prodToAdd = Product.findProductById(products, addId);
                    if (prodToAdd == null) {
                        System.out.println("Product not found.");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    cart.addProduct(prodToAdd, qty);
                    break;
                case 4:
                    System.out.print("Enter Product ID to remove: ");
                    String remId = sc.nextLine();
                    cart.removeProduct(remId);
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    cart.checkout();
                    break;
                case 7:
                    System.out.println("Thank you for shopping!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}