package br.inatel.poo.pcmania.compras;

import br.inatel.poo.pcmania.perifericos.Computador;
import br.inatel.poo.pcmania.perifericos.HardwareBasico;
import br.inatel.poo.pcmania.perifericos.MemoriaUSB;
import br.inatel.poo.pcmania.perifericos.SistemaOperacional;

import java.util.Scanner;

public class Main {

    static void main() {

        Scanner scanner = new Scanner(System.in);

        HardwareBasico hardwareBasico1 = new HardwareBasico("Pentium Core i3", 2200);
        HardwareBasico hardwareBasico2 = new HardwareBasico("Memória RAM", 8);
        HardwareBasico hardwareBasico3 = new HardwareBasico("HD", 500);
        SistemaOperacional sistemaOperacional1 = new SistemaOperacional("macOS Sequoia", 64);
        MemoriaUSB memoriaUSB1 = new MemoriaUSB("Pendrive", 16);

        HardwareBasico hardwareBasico4 = new HardwareBasico("Pentium Core i5", 3370);
        HardwareBasico hardwareBasico5 = new HardwareBasico("Memória RAM", 16);
        HardwareBasico hardwareBasico6 = new HardwareBasico("HD", 1000);
        SistemaOperacional sistemaOperacional2 = new SistemaOperacional("Windows 8", 64);
        MemoriaUSB memoriaUSB2 = new MemoriaUSB("Pendrive", 32);

        HardwareBasico hardwareBasico7 = new HardwareBasico("Pentium Core i7", 4500);
        HardwareBasico hardwareBasico8 = new HardwareBasico("Memória RAM", 32);
        HardwareBasico hardwareBasico9 = new HardwareBasico("HD", 2000);
        SistemaOperacional sistemaOperacional3 = new SistemaOperacional("Windows 10", 64);
        MemoriaUSB memoriaUSB3 = new MemoriaUSB("HD Externo", 1000);

        MemoriaUSB[] usbs = {
                new MemoriaUSB("Pendrive", 16),
                new MemoriaUSB("Pendrive", 32),
                new MemoriaUSB("HD Externo", 1000)
        };

        Computador computador1 = new Computador(
                "Apple",
                1821,
                new HardwareBasico[]{hardwareBasico1, hardwareBasico2, hardwareBasico3},
                sistemaOperacional1,
                memoriaUSB1
        );

        Computador computador2 = new Computador(
                "Samsung",
                3055,
                new HardwareBasico[]{hardwareBasico4, hardwareBasico5, hardwareBasico6},
                sistemaOperacional2,
                memoriaUSB2
        );

        Computador computador3 = new Computador(
                "Dell",
                7499,
                new HardwareBasico[]{hardwareBasico7, hardwareBasico8, hardwareBasico9},
                sistemaOperacional3,
                memoriaUSB3
        );

        Computador[] promocoes = { computador1, computador2, computador3 };

        //Apresentação da loja e as opções disponíveis
        System.out.println("Bem-vindo a loja PC Mania!!");

        Scanner nomeCliente = new Scanner(System.in);

        System.out.println("Por favor, digite seu nome:");
        String nome = nomeCliente.next();

        System.out.println("Por favor, digite seu CPF:");
        String cpf = nomeCliente.next();
        Cliente cliente = new Cliente(nome,cpf);

        System.out.println("Digite o número de cada promoção para escolher o seu:");
        cliente.mostraPromocoes(promocoes);

        int opcao;
        do{
            System.out.println("Digite a promoção desejada (1-3) ou 0 para sair");
            opcao = scanner.nextInt();

                if (opcao >= 1 && opcao <= 3) {
                    Computador pcEscolhido = new Computador(promocoes[opcao - 1]);

                    System.out.println("Gostaria da memória USB que acompanha? 1 para sim e 0 para não");
                    int memoriaUsb = scanner.nextInt();

                    if (memoriaUsb == 1) {
                        MemoriaUSB usbBase = usbs[opcao - 1];

                        pcEscolhido.setUsb(new MemoriaUSB(
                                usbBase.getNome(), usbBase.getCapacidade()
                        ));
                    }
                    cliente.adicionarComputador(pcEscolhido);
                    System.out.println("Promoção 1 adicionada!!");

                }else if (opcao > 3){
                    System.out.println("Opção inválida");

                }else  if(opcao == 0 && cliente.getQtdComputadores() < 2){
                    System.out.println("A compra mínima é de dois computadores, adicione outro antes");
                }
        }while(opcao != 0 || cliente.getQtdComputadores() < 2);


        float total = cliente.calculaTotalCompra();

        System.out.println("Resumo do pedido - br.inatel.poo.pcmania.compras.Cliente: " + cliente.getNome() + " e CPF: " + cliente.getCpf());
        System.out.println("Valor total da compra: R$" + total);

        cliente.mostraPCConfigs();
        ProcessarPedido.enviarPedido(cliente.getComputadores(), cliente.getQtdComputadores());

        scanner.close();

    }
}
