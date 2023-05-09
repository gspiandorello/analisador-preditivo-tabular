import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {

    Scanner in = new Scanner(System.in);

    //E = sentenca vazia e nao pode ser um nao terminal
    //somente letras maiusculas e podem ter no maximo 8 producoes
    private ArrayList<NaoTerminal> naoTerminais = new ArrayList<NaoTerminal>();

    //somente letras minusculas ou digitos
    private ArrayList<String> terminais = new ArrayList<String>();

    public void start() {
        String option;
        do {
            showMenu();
            option = in.nextLine();
            switch(option) {
                case "1":
                    //cadastrar a gramática e verificar se ela é LL
                    case1();
                    break;
                case "2":
                    //mostrar os conjuntos FIRST e FOLLOW das produções
                    case2();
                    break;
                case "3":
                    //mostrar a tabela de análise preditiva tabular
                    case3();
                    break;
                case "4":
                    //realizar a simulação da análise: verifica se uma sentença digitada é reconhecida ou
                    //não, demonstrando o reconhecimento passo a passo (autômato de pilha controlado
                    //pela tabela de análise).
                    case4();
                    break;
                case "5":
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção inválida! Redigite.");
                    System.out.println();
            }
        } while(!option.equals("5"));
    }

    public void showMenu() {
        System.out.println("\n===============MENU===============");
        System.out.println("[1] Cadastrar a gramática a ser usada e verificar se ela é LL");
        System.out.println("[2] Mostrar os conjuntos FIRST e FOLLOW das Produções");
        System.out.println("[3] Mostrar a tabela de análise preditiva tabular");
        System.out.println("[4] Digitar uma sentença e mostrar se ela é reconhecida ou não (passo a passo)");
        System.out.println("[5] Finalizar o programa");
        System.out.print("Opção desejada: ");
    }

    public void case1(){

        criaSimboloStart();

        String opcao;
        do{
            System.out.println("Escolha uma das opções abaixo:");
            System.out.println("1 - Quero cadastrar outro não terminal e suas derivações");
            System.out.println("2 - Já terminei de cadastrar a gramática");

            do{
                opcao = in.nextLine();
                if (!opcao.equals("1") && !opcao.equals("2")){
                    System.err.println("Por favor, escolha uma das opções citadas acima");
                }
            }while(!opcao.equals("1") && !opcao.equals("2"));

            if(opcao.equals("1")){
                if(naoTerminais.size() == 8 ){
                    System.err.println("O limite de não terminais (8) foi excedido, por favor escolha a opção para terminar a gramática");
                }
                else{
                    criaNaoTerminal();
                }   
            }
        }while(!opcao.equals("2"));

        System.out.println(printGramatica());
        addTodosFirst();
        arrumandoFirstNaoTerminal();
        verificaGramaticaLL();
        addFollowManual();
        //addTodosFollow();
        //arrumandoFollowNaoTerminal();
    }

    public void case2(){
        System.out.println("\n");
        printFirsts();
        System.out.println("\n");
        printFollows();
    }

    public void case3(){
        System.out.println("\nTABELA PREDITIVA TABULAR:\n");
        gerarTabela();
    }

    public void case4(){

    }

    /*Este método foi feito para criar o simbolo start, pedindo o input ao usuário */
    public void criaSimboloStart(){
        String start;
        System.out.println("Digite o símbolo de start, com uma letra maiúscula (ex: 'S')");
        start = in.nextLine();
        boolean verificaVazio = true;
        do{
            if(start.equals("E")){
                System.err.println("Digite outro símbolo não terminal, com uma letra maiúscula (ex: 'A'), pois a letra E é considerada como vazio");
                start = in.nextLine();
            }
            else{
                verificaVazio = false;
            }
        }while(verificaVazio);
        String derivacoesStart;
        System.out.println("Digite as derivações, separadas por ';' e sem espaços (ex: 'aB;Bb;c')");
        derivacoesStart = in.nextLine();
        String[] temporario = derivacoesStart.split(";");
        ArrayList<String> conjuntoDerivacoesStart = new ArrayList<String>();
        for(int i = 0; i < temporario.length; i ++){
            conjuntoDerivacoesStart.add(temporario[i]);
        }
        NaoTerminal naoTerminal = new NaoTerminal(start, conjuntoDerivacoesStart);
        naoTerminais.add(naoTerminal);
    }

    /*Este método cria os outros símbolos não terminais além do símbolo start, pedindo o input ao usuário */
    public void criaNaoTerminal(){
        String simbolo;
        System.out.println("Digite o símbolo do não terminal, com uma letra maiúscula (ex: 'A')");
        simbolo = in.nextLine();
        boolean simboloJaUsado = true;
        do{
            if(simbolo.equals("E")){
                System.err.println("Digite outro símbolo não terminal, com uma letra maiúscula (ex: 'A'), pois a letra E é considerada como vazio");
                simbolo = in.nextLine();
            }
            if(simboloJaUsado){
                for(int i = 0; i < naoTerminais.size(); i++){
                    if(simbolo.equals(naoTerminais.get(i).getNaoTerminal())){
                        System.err.println("Digite outro símbolo não terminal, com uma letra maiúscula (ex: 'A'), pois este já está sendo usado");
                        simbolo = in.nextLine();
                    }
                    else {
                        simboloJaUsado = false;
                    }
                }
            }
        } while(simboloJaUsado);
        String derivacoesSimbolo;
        System.out.println("Digite as derivações, separadas por ';' e sem espaços (ex: 'aA;Bb;c;E')");
        derivacoesSimbolo = in.nextLine();
        String[] temporario = derivacoesSimbolo.split(";");
        ArrayList<String> conjuntoDerivacoesSimbolo = new ArrayList<String>();
        for(int i = 0; i < temporario.length; i ++){
            conjuntoDerivacoesSimbolo.add(temporario[i]);
        }
        NaoTerminal naoTerminal = new NaoTerminal(simbolo, conjuntoDerivacoesSimbolo);
        naoTerminais.add(naoTerminal);
    }

    /*Este foi o método que realizar para adicionar o First ao símbolo passado por parâmetro */
    public void addFirst(String simbolo){
        for(int i = 0; i < naoTerminais.size(); i++){
            if(simbolo.equals(naoTerminais.get(i).getNaoTerminal())){
                for(int j = 0; j < naoTerminais.get(i).getDerivacoes().size(); j++){
                    String derivacao = naoTerminais.get(i).getDerivacoes().get(j);
                    if(derivacao.equals("id")){
                        naoTerminais.get(i).addFirst(derivacao);
                    }
                    else{
                        naoTerminais.get(i).addFirst(String.valueOf(derivacao.charAt(0)));
                    }
                }
            }
        }
    }

    /*Este método chama o método addFirst para todos os símbolos não terminais */
    public void addTodosFirst(){
        for(int i = 0; i < naoTerminais.size(); i++){
            addFirst(naoTerminais.get(i).getNaoTerminal());
        }
    }

    /*Este método foi desenvolvido para arrumar todos os First que estão com não terminais*/
    public void arrumandoFirstNaoTerminal(){
        boolean verifica = true;
        while(verifica){
            verifica = false;
            for(int i = 0; i < naoTerminais.size(); i++){
                for(int j = 0; j < naoTerminais.get(i).getFirst().size(); j++){
                    for(int k = 0; k < naoTerminais.size(); k++){
                        if(naoTerminais.get(i).getFirst().get(j).equals(naoTerminais.get(k).getNaoTerminal())){
                            naoTerminais.get(i).setFirst(naoTerminais.get(k).getFirst());
                            verifica = true;
                        }
                    }
                }
            } 
        }
    }

    /*Este foi o método que verifica se a gramática é LL, verificando se há recursão a esquerda ou disjunção par a par */
    public void verificaGramaticaLL(){
        boolean temRecursaoAEsquerda = false;
        boolean temDisjuncaoParAPar = false;

        for(int i = 0; i < naoTerminais.size(); i++){
            for(int j = 0; j < naoTerminais.get(i).getFirst().size(); j++){
                String first = naoTerminais.get(i).getFirst().get(j);
                if(naoTerminais.get(i).getNaoTerminal().equals(first)){
                    temRecursaoAEsquerda = true;
                }
            }
        }

        for(int i = 0; i < naoTerminais.size(); i++){
            for(int j = 0; j < naoTerminais.get(i).getFirst().size(); j++){
                String first = naoTerminais.get(i).getFirst().get(j);
                for(int k = 0; k < naoTerminais.get(i).getFirst().size(); k++){
                    if(j!=k && first.equals(naoTerminais.get(i).getFirst().get(k))){
                        temDisjuncaoParAPar = true;
                    }
                }
            }
        }

        if(temRecursaoAEsquerda && temDisjuncaoParAPar){
            System.out.println("\nEssa gramática apresenta recursão à esquerda e disjunção par a par");
            System.out.println("Por favor, rode o programa novamente e insira outra gramática");
        }
        else if(temRecursaoAEsquerda){
            System.out.println("\nEssa gramática apresenta recursão à esquerda");
            System.out.println("Por favor, rode o programa novamente e insira outra gramática");
        }
        else if(temDisjuncaoParAPar){
            System.out.println("\nEssa gramática apresenta disjunção par a par");
            System.out.println("Por favor, rode o programa novamente e insira outra gramática");
        }
        else{
            System.out.println("\nEssa é uma gramática LL, pode selecionar as outras opções do menu");
        }
    }

    /*Neste método inserimos os Follow manualmente para tentar gerar a tabela preditiva tabular */
    public void addFollowManual(){
        naoTerminais.get(0).addFollow("$");
        naoTerminais.get(0).addFollow(")");
        naoTerminais.get(1).addFollow("$");
        naoTerminais.get(1).addFollow(")");
        naoTerminais.get(2).addFollow("$");
        naoTerminais.get(2).addFollow("+");
        naoTerminais.get(2).addFollow(")");
        naoTerminais.get(3).addFollow("$");
        naoTerminais.get(3).addFollow("+");
        naoTerminais.get(3).addFollow(")");
        naoTerminais.get(4).addFollow("*");
        naoTerminais.get(4).addFollow("+");
        naoTerminais.get(4).addFollow(")");
        naoTerminais.get(4).addFollow("$");
    }

    /*Este método foi uma tentativa de adicionar os Follow ao símbolo passado por parâmetro */
    public void addFollow(String simbolo){
        int s = -1;
        if(simbolo.equals(naoTerminais.get(0).getNaoTerminal())){
            naoTerminais.get(0).addFollow("$");
        }
        for(int a = 0; a < naoTerminais.size(); a++){
            if(simbolo.equals(naoTerminais.get(a).getNaoTerminal())){
                s = a;
            }
        }
        for(int i = 0; i < naoTerminais.size(); i++){
            for(int j = 0; j < naoTerminais.get(i).getDerivacoes().size(); j++){
                String derivacao = naoTerminais.get(i).getDerivacoes().get(j);
                if(derivacao.contains(naoTerminais.get(s).getNaoTerminal())){
                    for(int k = 0; k < derivacao.length(); k++){
                        if(String.valueOf(derivacao.charAt(k)).equals(naoTerminais.get(s).getNaoTerminal()) && k < derivacao.length()-1){
                            naoTerminais.get(s).addFollow(String.valueOf(derivacao.charAt(k+1)));
                        }
                        else{
                            if(!naoTerminais.get(s).getFollow().contains("follow"+naoTerminais.get(i).getNaoTerminal())){
                                naoTerminais.get(s).addFollow("follow"+naoTerminais.get(i).getNaoTerminal());
                            }
                        }
                    }
                }
            }
        }
    }

    /*Este método chama todos os símbolos não terminais para serem adicionados os Follow em cada um deles */
    public void addTodosFollow(){
        for(int i = 0; i < naoTerminais.size(); i++){
            addFollow(naoTerminais.get(i).getNaoTerminal());
        }
    }

    /*Este método foi uma tentativa de arrumar todos os símbolos não terminais que estavam nos Follows */
    public void arrumandoFollowNaoTerminal(){
        for(int i = 0; i < naoTerminais.size(); i++){
            for(int j = 0; j < naoTerminais.get(i).getFollow().size(); j++){
                if(naoTerminais.get(i).getFollow().get(j).contains("follow")){
                    String follow = naoTerminais.get(i).getFollow().get(j);
                    for(int k = 0; k < naoTerminais.size(); k++){
                        if(String.valueOf(follow.charAt(6)).equals(naoTerminais.get(k).getNaoTerminal())){
                            for(int l = 0; l < naoTerminais.get(k).getFollow().size(); l++){
                                naoTerminais.get(i).addFollow(naoTerminais.get(k).getFollow().get(l));
                            }
                        }
                    }
                }
            }
        }

        boolean verifica = true;
        while(verifica){
            verifica = false;
            for(int i = 0; i < naoTerminais.size(); i++){
                for(int j = 0; j < naoTerminais.get(i).getFollow().size(); j++){
                    for(int k = 0; k < naoTerminais.size(); k++){
                        if(naoTerminais.get(i).getFollow().get(j).equals(naoTerminais.get(k).getNaoTerminal())){
                            for(int m = 0; m < naoTerminais.get(k).getFirst().size(); m++){
                                naoTerminais.get(i).addFollow(naoTerminais.get(k).getFirst().get(m));
                            }
                            if(naoTerminais.get(k).getDerivacoes().contains(naoTerminais.get(k).getNaoTerminal()) && naoTerminais.get(k).getDerivacoes().contains("E")){
                                for(int l = 0; l < naoTerminais.get(k).getFollow().size(); l++){
                                    naoTerminais.get(i).addFollow(naoTerminais.get(k).getFollow().get(l));
                                }
                            }
                            verifica = true;
                        }
                    }
                }
            } 
        }
    }

    /*Neste método tentamos gerar a tabela com os Follows inseridos manualmente */
    public void gerarTabela(){
        for(int i = 0; i < naoTerminais.size(); i++){
            for(int j = 0; j < naoTerminais.get(i).getDerivacoes().size(); j++){
                String derivacao = naoTerminais.get(i).getDerivacoes().get(j);
                if(derivacao.equals("E")){

                }
                String print = "";
                String printDerivacoes = "";
                ArrayList<String> derivacoes = new ArrayList<String>();
                for(int k = 0; k < naoTerminais.get(i).getFirst().size(); k++){
                    if(!naoTerminais.get(i).getFirst().get(k).equals("E")){
                        if(k == naoTerminais.get(i).getFirst().size()-1){
                            printDerivacoes += naoTerminais.get(i).getFirst().get(k) + " }";
                            derivacoes.add(naoTerminais.get(i).getFirst().get(k));
                        }
                        else{
                            printDerivacoes += naoTerminais.get(i).getFirst().get(k) + ", ";
                            derivacoes.add(naoTerminais.get(i).getFirst().get(k));
                        }
                    }
                }
                print += naoTerminais.get(i).getNaoTerminal() + " -> " + derivacao + " ... FIRST(" + derivacao + ") = { " + printDerivacoes;

                for(int l = 0; l < derivacoes.size(); l++){
                    print += "\nAdicionar " + naoTerminais.get(i).getNaoTerminal() + " -> " + derivacao + " em M[E," + derivacoes.get(l) + "]";
                }
                System.out.println(print);
            }
        }
    }
 
    /*Este método serve para printar na tela todos os First */
    public void printFirsts(){
        for(int i = 0; i < naoTerminais.size(); i++){
            System.out.println(naoTerminais.get(i).toStringFirst());
        }
    }

    /*Este método serve para printar na tela todos os Follow */
    public void printFollows(){
        for(int i = 0; i < naoTerminais.size(); i++){
            System.out.println(naoTerminais.get(i).toStringFollow());
        }
    }

    /*Este método serve para printar na tela toda a Gramatica */
    public String printGramatica(){
        String gramatica = "";
        for(int i = 0; i < naoTerminais.size(); i++){
            gramatica += "\n" + naoTerminais.get(i).getNaoTerminal() +  " -> " + naoTerminais.get(i).toStringDerivacoes();
        }
        return gramatica;
    }
}
