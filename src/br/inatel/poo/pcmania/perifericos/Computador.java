package br.inatel.poo.pcmania.perifericos;

public class Computador {

    private String marca;
    private float preco;
    private HardwareBasico[] hardwares;
    private SistemaOperacional sistemaOperacional;
    private MemoriaUSB usb;

    public Computador(String marca, float preco, HardwareBasico[] hardwares, SistemaOperacional so, MemoriaUSB usb) {
        this.marca = marca;
        this.preco = preco;
        this.hardwares = hardwares;
        this.sistemaOperacional = so;
        this.usb = usb;
    }

    public Computador(Computador outro) {
        this.marca = outro.marca;
        this.preco = outro.preco;
        this.hardwares = outro.hardwares;
        this.sistemaOperacional= outro.sistemaOperacional;
        this.usb = null; // começa sem USB
    }

    //Getters

    public String getMarca() {
        return marca;
    }

    public float getPreco() {
        return preco;
    }

    public HardwareBasico[] getHardwares() {
        return hardwares;
    }

    public SistemaOperacional getSistemaOperacional() {
        return sistemaOperacional;
    }

    public MemoriaUSB getUsb() {
        return usb;
    }

    public void setUsb(MemoriaUSB usb) {
        this.usb = usb;
    }
}
