abstract class Person {
    private int id;
    private String name;
    private String cpf;

    public Person(int id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public abstract void displayInfo();  // Método abstrato

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
}

class Customer extends Person {
    private String phone;

    public Customer(int id, String name, String cpf, String phone) {
        super(id, name, cpf);
        this.phone = phone;
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer: " + getName() + ", Phone: " + phone);
    }

    public String getPhone() {
        return phone;
    }
}

class Employee extends Person {
    private String position;

    public Employee(int id, String name, String cpf, String position) {
        super(id, name, cpf);
        this.position = position;
    }

    @Override
    public void displayInfo() {
        System.out.println("Employee: " + getName() + ", Position: " + position);
    }

    public String getPosition() {
        return position;
    }
}

public class Main {
    public static void main(String[] args) {
        Person customer = new Customer(1, "John Doe", "123.456.789-00", "555-1234");
        Person employee = new Employee(2, "Jane Smith", "987.654.321-00", "Manager");

        customer.displayInfo();  // Exibe informações de um cliente
        employee.displayInfo();  // Exibe informações de um funcionário
    }
}
