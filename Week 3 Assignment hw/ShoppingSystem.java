import java.util.*;

class Product {
    String productId;
    String productName;
    double price;
    String category;
    int stockQuantity;

    static int totalProducts = 0;
    static String[] categories = {"Electronics", "Clothing", "Books", "Food"};

    public Product(String productName, double price, String category, int stockQuantity) {
        this.productId = "P" + (1000 + ++totalProducts);
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.productId.equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> getProductsByCategory(Product[] products, String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p != null && p.category.equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }
}

class ShoppingCart {
    String cartId;
    String customerName;
    List<Product> products;
    List<Integer> quantities;
    double cartTotal;

    public ShoppingCart(String customerName) {
        this.cartId = "CART" + (int)(Math.random() * 1000);
        this.customerName = customerName;
        this.products = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.cartTotal = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        if (product.stockQuantity >= quantity) {
            products.add(product);
            quantities.add(quantity);
            product.stockQuantity -= quantity;
            calculateTotal();
            System.out.println(quantity + " x " + product.productName + " added to cart.");
        } else {
            System.out.println("Not enough stock for " + product.productName);
        }
    }

    public void removeProduct(String productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).productId.equals(productId)) {
                Product removed = products.get(i);
                removed.stockQuantity += quantities.get(i); // return stock
                products.remove(i);
                quantities.remove(i);
                calculateTotal();
                System.out.println("Removed product: " + removed.productName);
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    public void calculateTotal() {
        cartTotal = 0.0;
        for (int i = 0; i < products.size(); i++) {
            cartTotal += products.get(i).price * quantities.get(i);
        }
    }

    public void displayCart() {
        System.out.println("\n--- Shopping Cart for " + customerName + " ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).productName + " x " + quantities.get(i) +
                               " = " + (products.get(i).price * quantities.get(i)));
        }
        System.out.println("Cart Total: " + cartTotal);
    }

    public void checkout() {
        System.out.println("\nCheckout complete for " + customerName);
        displayCart();
        products.clear();
        quantities.clear();
        cartTotal = 0.0;
    }
}

public class ShoppingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create some products
        Product[] products = {
            new Product("Laptop", 50000, "Electronics", 5),
            new Product("Phone", 20000, "Electronics", 10),
            new Product("Shirt", 1500, "Clothing", 20),
            new Product("Book - Java", 500, "Books", 15),
            new Product("Headphones", 3000, "Electronics", 8),
            new Product("Jeans", 2000, "Clothing", 10),
            new Product("Fiction Novel", 400, "Books", 12),
            new Product("Chocolates", 300, "Food", 25),
            new Product("Shoes", 2500, "Clothing", 7),
            new Product("Pizza", 600, "Food", 30)
        };

        // Start shopping
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        ShoppingCart cart = new ShoppingCart(name);

        int choice;
        do {
            System.out.println("\n--- Shopping Menu ---");
            System.out.println("1. Browse Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Available Products ---");
                    for (Product p : products) {
                        System.out.println(p.productId + " | " + p.productName + " | " + p.price +
                                           " | " + p.category + " | Stock: " + p.stockQuantity);
                    }
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    String pid = sc.next();
                    Product p = Product.findProductById(products, pid);
                    if (p != null) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        cart.addProduct(p, qty);
                    } else {
                        System.out.println("Invalid Product ID.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Product ID to remove: ");
                    String rid = sc.next();
                    cart.removeProduct(rid);
                    break;
                case 4:
                    cart.displayCart();
                    break;
                case 5:
                    cart.checkout();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}
