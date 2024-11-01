class Product {
    private int productId;
    private String productName;
    private double price;
    private int stockQuantity;

    public Product(int productId, String productName, double price, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        setPrice(price);
        setStockQuantity(stockQuantity);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
    }

    public String getProductName() {
        return productName;
    }

    public int getProductId() {
        return productId;
    }
}

public class Main {
    public static void main(String[] args) {
        // Criando dois objetos da classe Product
        Product product1 = new Product(1, "Laptop", 2500.00, 10);
        Product product2 = new Product(2, "Smartphone", 1500.00, 5);

        // Exibindo informações dos produtos
        System.out.println("Product 1: " + product1.getProductName());
        System.out.println("Price: " + product1.getPrice());
        System.out.println("Stock Quantity: " + product1.getStockQuantity());

        System.out.println("\nProduct 2: " + product2.getProductName());
        System.out.println("Price: " + product2.getPrice());
        System.out.println("Stock Quantity: " + product2.getStockQuantity());

        // Atualizando o preço e a quantidade em estoque do produto 1
        product1.setPrice(2300.00);
        product1.setStockQuantity(8);

        // Exibindo informações atualizadas do produto 1
        System.out.println("\nUpdated Product 1:");
        System.out.println("Product: " + product1.getProductName());
        System.out.println("New Price: " + product1.getPrice());
        System.out.println("New Stock Quantity: " + product1.getStockQuantity());

        // Testando a validação de preço negativo
        try {
            product2.setPrice(-500.00);
        } catch (IllegalArgumentException e) {
            System.out.println("\nError setting price for Product 2: " + e.getMessage());
        }

        // Testando a validação de quantidade negativa
        try {
            product2.setStockQuantity(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("\nError setting stock quantity for Product 2: " + e.getMessage());
        }
    }
}
