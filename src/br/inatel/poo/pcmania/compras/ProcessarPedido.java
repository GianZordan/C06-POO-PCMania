package br.inatel.poo.pcmania.compras;

import br.inatel.poo.pcmania.perifericos.Computador;

public class ProcessarPedido {

    public static void enviarPedido(Computador[] computadores, int qtdComputadores) {
        for (int i = 0; i < qtdComputadores; i++) {
            Computador pc = computadores[i];
        }
        System.out.println("Pedido enviado...");
    }
}