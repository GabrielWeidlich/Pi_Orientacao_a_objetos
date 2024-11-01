// BancoApp.java
import java.util.ArrayList;
import java.util.List;

// Classe Singleton que representa o Banco
class Banco {
    private static Banco instancia;
    private List<Conta> contas;

    private Banco() {
        contas = new ArrayList<>();
    }

    public static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
        System.out.println("Conta adicionada: " + conta);
    }

    public void listarContas() {
        System.out.println("Contas no Banco:");
        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }
}

// Classe abstrata para Conta
abstract class Conta {
    protected String titular;
    protected double saldo;

    public Conta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public abstract void mostrarTipoConta();

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito realizado. Saldo atual: " + saldo);
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado. Saldo atual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public String toString() {
        return "Titular: " + titular + ", Saldo: " + saldo;
    }
}

// Implementação de Conta Comum
class ContaComum extends Conta {
    public ContaComum(String titular, double saldo) {
        super(titular, saldo);
    }

    @Override
    public void mostrarTipoConta() {
        System.out.println("Conta Comum de " + titular);
    }
}

// Implementação de Conta Estudante com limite e empréstimo
class ContaEstudante extends Conta {
    private double limite;  // Limite para saldo negativo
    private double emprestimoDisponivel;  // Valor disponível para empréstimo

    public ContaEstudante(String titular, double saldo, double limite, double emprestimoDisponivel) {
        super(titular, saldo);
        this.limite = limite;
        this.emprestimoDisponivel = emprestimoDisponivel;
    }

    @Override
    public void mostrarTipoConta() {
        System.out.println("Conta Estudante de " + titular);
    }

    @Override
    public void sacar(double valor) {
        if (saldo - valor >= -limite) {
            saldo -= valor;
            System.out.println("Saque realizado. Saldo atual: " + saldo);
        } else {
            System.out.println("Limite de saque excedido.");
        }
    }

    // Método para solicitar um empréstimo
    public void solicitarEmprestimo(double valor) {
        if (valor <= emprestimoDisponivel) {
            saldo += valor;
            emprestimoDisponivel -= valor;
            System.out.println("Empréstimo de " + valor + " concedido. Saldo atual: " + saldo);
        } else {
            System.out.println("Empréstimo não disponível. Valor solicitado excede o limite.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Limite: " + limite + ", Empréstimo Disponível: " + emprestimoDisponivel;
    }
}

// Implementação de Conta Premium
class ContaPremium extends Conta {
    public ContaPremium(String titular, double saldo) {
        super(titular, saldo);
    }

    @Override
    public void mostrarTipoConta() {
        System.out.println("Conta Premium de " + titular);
    }
}

// Fábrica abstrata para contas
abstract class ContaFactory {
    public abstract Conta criarConta(String titular, double saldo);
}

// Fábrica de Conta Comum
class ContaComumFactory extends ContaFactory {
    @Override
    public Conta criarConta(String titular, double saldo) {
        return new ContaComum(titular, saldo);
    }
}

// Fábrica de Conta Estudante
class ContaEstudanteFactory extends ContaFactory {
    private double limite;
    private double emprestimoDisponivel;

    public ContaEstudanteFactory(double limite, double emprestimoDisponivel) {
        this.limite = limite;
        this.emprestimoDisponivel = emprestimoDisponivel;
    }

    @Override
    public Conta criarConta(String titular, double saldo) {
        return new ContaEstudante(titular, saldo, limite, emprestimoDisponivel);
    }
}

// Fábrica de Conta Premium
class ContaPremiumFactory extends ContaFactory {
    @Override
    public Conta criarConta(String titular, double saldo) {
        return new ContaPremium(titular, saldo);
    }
}

// Classe Main para demonstração
public class Main {
    public static void main(String[] args) {
        Banco banco = Banco.getInstancia();

        ContaFactory contaComumFactory = new ContaComumFactory();
        ContaFactory contaEstudanteFactory = new ContaEstudanteFactory(500.00, 1000.00); // Limite de 500 e empréstimo de 1000
        ContaFactory contaPremiumFactory = new ContaPremiumFactory();

        Conta conta1 = contaComumFactory.criarConta("João", 500.00);
        Conta conta2 = contaEstudanteFactory.criarConta("Maria", 300.00);
        Conta conta3 = contaPremiumFactory.criarConta("Carlos", 1000.00);

        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);
        banco.adicionarConta(conta3);

        banco.listarContas();

        // Operações em Conta Estudante
        conta2.mostrarTipoConta();
        conta2.depositar(100.00);
        conta2.sacar(700.00); // Teste do limite
        ((ContaEstudante) conta2).solicitarEmprestimo(500.00); // Solicitar empréstimo dentro do limite

        // Operações em Conta Comum e Premium
        conta1.mostrarTipoConta();
        conta1.depositar(200.00);
        conta1.sacar(100.00);

        conta3.mostrarTipoConta();
        conta3.depositar(500.00);
        conta3.sacar(1500.00); // Teste de saldo insuficiente
    }
}
