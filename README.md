# C06-POO-PCMania
Exercício Prático de POO (C06)

Nome: Gian Luca Saldanha Zordan
Curso: GECN
Matrícula: 1821

Algumas ajudas solicitadas para IA:
Modelo utilizado: GPT-5.3 

1️⃣ Meu primeiro empate foi conseguir realizar a saída dos componentes da promoção de forma prática, tentei algumas tratativas mas sempre resultava no uso de muitos SOUT's, então fui buscar uma forma prática de fazer isso.

🖥️ "Tenho um combo de sout, que a saída depende do que meu usuário digita, mas não quero usar infinitos sout's, como posso simplificar?"

Inicialmente me orientou a usar uma única string, o que não me agradou muito, pois a intenção era usar os valores fixos puxando do getter, e não digitando apenas no SOUT. Depois me foi indicado StringBuilder, o que, na realidade, não vimos em aula e não tenho conhecimento do mesmo. Pós isso, me foi indicado arrays ou map, o map se encaixa no mesmo do anterior, não tenho conhecimento, o array vimos em sala, então com o exemplo que ele me forneceu, consegui encaixar na minha necessidade.

2️⃣ Outra dificuldade foi de manipular a soma total dos computadores, usando array, por cada uma ser de um preço, então usei o prompt:
"Como consigo realizar a soma de um array onde cada entrada (computador selecionado) pode ter um valor diferente?"

Me foi retornado dizendo que era mais prático o uso do list:


public class Cliente {
    private String nome;
    private String cpf;
    private List<Computador> computadores;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.computadores = new ArrayList<>();
    }

    public void adicionarComputador(Computador pc) {
        computadores.add(pc);
    }

Mas como não utilizei muito do mesmo (List), preferi o array, então solicitei para me ajudar utilizando array, sem list:

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.computadores = new Computador[10];
        this.qtdComputadores = 0;
    }

    public void adicionarComputador(Computador pc) {
        if (qtdComputadores < computadores.length) {
            computadores[qtdComputadores] = pc;
            qtdComputadores++;
        } else {
            System.out.println("Limite de computadores atingido!");
        }

   public float calculaTotalCompra() {
        float total = 0;

        for (int i = 0; i < qtdComputadores; i++) {
            total += computadores[i].getPreco();
        }

        return total;
    }

Dessa forma, ele indicou um limite de computadores, apenas 10, eu mantive esse valor para ter um teto e ter uma reserva de memória específica, já que n foi definido nenhum limite. O if também fica para esse limite, apesar de manter o tamanho do array como 10, eu não usei as restrições caso chegue em 10, mas caso queira passar disso, é um problema que não ajustei por não ter necessidade, mas em uso real, deverá ser adaptado.

Ele puxa da classe Computador quantas vezes o construtor de um novo computador é executado, pois cada vez que o usuário escolhe uma promoção, ele é chamado, assim, temos a quantidade.

Para a soma, ele usa a lógica anterior (para ser o limite do for e somar todos os computadores) somada com o getPreco de cada computador, e, para isso, ele varre o array de computadores, pega o getPreco de cada um e realiza a soma na variável total. 

3️⃣ Outra dificuldade foi sair as especificações somente dos computadores que comprei e inserindo ou não a memória USB, usando o prompt:
🖥️ "Como posso varrer meu array e mostrar as especificações do computador que comprei?" 
Me foi retornado isso:


    // Mostra apenas os computadores comprados
    public void mostrarCompras() {
        System.out.println("\n--- Computadores comprados ---");
        for (int i = 0; i < qtdCompras; i++) {
            Computador pc = compras[i];
            System.out.println("\nMarca: " + pc.getMarca() + " | Preço: R$" + pc.getPreco());

            System.out.println("Hardware:");
            for (HardwareBasico hw : pc.getHardwares()) {
                System.out.println(" - " + hw.getNome() + " " + hw.getCapacidade() + (hw.getNome().contains("RAM") ? "Gb" : "Mhz/Gb"));
            }

            System.out.println("Sistema Operacional: " + pc.getSO().getNome() + " " + pc.getSO().getTipo() + " bits");
            System.out.println("Memória USB: " + pc.getUSB().getNome() + " " + pc.getUSB().getCapacidade() + "Gb");
        }
        System.out.println("\n=== Total: R$" + calculaTotalCompra() + " ===");
    }
}


Tinha alguns problemas no sout do hardware pelo motivo que ele pega somente o RAM como Gb e deixa os outros dois (HD e Processador) como "Mhz/Gb", o que não se pode acontecer, pois o HD sair 500Mhz/Gb é totalmente errado.
Para resolver isso, adicionei mais uma condição:

(hardwareBasico.getNome().contains("RAM") ? "Gb" : hardwareBasico.getNome().contains("Core") ? "Mhz" : "Gb"));
            
Adicionei mais um contains, pois se o nome conter "RAM", ele vai retornar Gb após o getNome, se conter "Core", retorna "Mhz", e se não atingir nenhuma das duas condições, retorna "Gb", que é o caso do HD.

Eu utilizaria um para solucionar esse problema, mas com o retorno do prompt da IA usando "?" e ":", aprendi uma forma mais simplificada ao invés do if e mantive.

4️⃣ Mas ainda sim existia um problema, eu podia manter ou não meu HD, mas ele alterava para todos os computadores da mesma promoção, e eu não queria isso, gostaria de pegar um computador da promoção 1 com HD e outro sem, mas como?
Eu estava alterando o HD da promoção, e não da array dos computadores que comprei, tentei algumas implementações mas sempre dava algum problema.
Até esse ponto eu estava usando o case para as interações do usuário, até consegui algumas soluções mas estava grande parte na main, estava fugindo da parte de POO e virando programação estruturada, precisava de algo mais detalhado para esse problema.

🖥️ "Consigo alterar a necessidade do HD (que é opcional), mas precisei aplicar em cada item do case e mais uma função na classe cliente, como posso otimizar isso?"

Com isso ele me deu um retorno muito bom, não precisava do case, era somente alterar a necessidade do HD ANTES de adicionar o computador:

if (pcEscolhido != null) {
    System.out.println("Deseja adicionar memória USB? (S/N)");
    String resp = scanner.next();

    if (resp.equalsIgnoreCase("S")) {
        MemoriaUSB usbBase = usbs[opcao - 1];

        pcEscolhido.setUsb(new MemoriaUSB(
            usbBase.getNome(),
            usbBase.getCapacidade()
        ));
    } else {
        pcEscolhido.setUsb(null);
    }

    cliente.adicionarComputador(pcEscolhido);
}

Dessa forma antes de eu salvar meu computador no array eu já indicava se tinha ou não HD, que era o inverso que eu fazia, eu adicionava o computador, perguntava ao usuário e depois eu tentava apagar o HD daquele computador específico do array.
Depois disso eu fiz algumas alterações pra atender o projeto, como a necessidade mínima da compra de computadores e utilizando o 0 para finalizar a compra.

Grande parte das dúvidas de uso dos itens da programação em geral foi utilizado os próprios slides dados em aula para sanar, mas esses pontos foram os que mais me causaram dúvidas buscando sozinho, mas, com a IA consegui sanar, sempre lendo o que ela manda pra eu entender o que estou fazendo e pra saber se realmente é o que preciso.
