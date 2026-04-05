package br.inatel.poo.pcmania.compras;

import br.inatel.poo.pcmania.perifericos.Computador;
import br.inatel.poo.pcmania.perifericos.HardwareBasico;

public class Cliente {

    private String nome;
    private String cpf;
    private Computador[] computadores = new Computador[10];
    private int qtdComputadores;

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        computadores = new Computador[10];
        qtdComputadores = 0;
    }

    public void adicionarComputador(Computador pc) {
            computadores[qtdComputadores] = pc;
            qtdComputadores++;
    }

    public float calculaTotalCompra() {
        float total = 0;

        for (int i = 0; i < qtdComputadores; i++) {
            total += computadores[i].getPreco();
        }

        return total;
    }

    public void mostraPromocoes(Computador[] promocoes){
        for (Computador pc : promocoes ){
            System.out.println("Marca: " + pc.getMarca() + " Preço R$" + pc.getPreco());

            for (HardwareBasico hardwareBasico : pc.getHardwares()){
                System.out.println(" - " + hardwareBasico.getNome() + " " + hardwareBasico.getCapacidade() + (hardwareBasico.getNome().contains("RAM") ? "Gb" : hardwareBasico.getNome().contains("Core") ? "Mhz" : "Gb"));
            }
            System.out.println(" - Sistema Operacional: " + pc.getSistemaOperacional().getNome() + " " + pc.getSistemaOperacional().getTipo() + " bits");
            System.out.println(" (OPCIONAL) - Memória USB: " + pc.getUsb().getNome() + " " + pc.getUsb().getCapacidade() + "Gb");
        }
    }

    public void mostraPCConfigs(){
        System.out.println("--- Resumo da compra feita ---");

        for (int i = 0; i < qtdComputadores; i++){
            Computador pc = computadores[i];
            System.out.println("Marca: " + pc.getMarca() + " Preço R$" + pc.getPreco());

            for (HardwareBasico hardwareBasico : pc.getHardwares()){
                System.out.println(" - " + hardwareBasico.getNome() + " " + hardwareBasico.getCapacidade() + (hardwareBasico.getNome().contains("RAM") ? "Gb" : hardwareBasico.getNome().contains("Core") ? "Mhz" : "Gb"));
            }
            System.out.println(" - Sistema Operacional: " + pc.getSistemaOperacional().getNome() + " " + pc.getSistemaOperacional().getTipo() + " bits");

            if (pc.getUsb() != null) {
                System.out.println(" - Memória USB: " + pc.getUsb().getNome() + " " + pc.getUsb().getCapacidade() + "Gb");
            }
        }
        System.out.println("\n--- Total: R$" + calculaTotalCompra() + " ---");
    }



    //Getters and setters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Computador[] getComputadores() {
        return computadores;
    }

    public int getQtdComputadores() {
        return qtdComputadores;
    }
}

