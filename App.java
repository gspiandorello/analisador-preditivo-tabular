import java.util.ArrayList;
import java.util.Scanner;

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
                    //cadastrar toda gramática e verificar se ela é LL
                    case1();
                    break;
                case "2":
                    //mostrar os conjuntos FIRST e FOLLOW das produções
                    break;
                case "3":
                    //mostrar a tabela de análise preditiva tabular
                    break;
                case "4":
                    //realizar a simulação da análise: verifica se uma sentença digitada é reconhecida ou
                    //não, demonstrando o reconhecimento passo a passo (autômato de pilha controlado
                    //pela tabela de análise).
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
        System.out.println("[1] Cadastrar a gramática");
        System.out.println("[2] Mostrar os conjuntos FIRST e FOLLOW das Produções");
        System.out.println("[3] Mostrar a tabela de análise preditiva tabular");
        System.out.println("[4] Digitar uma sentença e mostrar se ela é reconhecida ou não (passo a passo)");
        System.out.println("[5] Finalizar o programa");
        System.out.print("Opção desejada: ");
    }

    public void case1(){
        String start;
        System.out.println("Digite o símbolo de start, com uma letra maiúscula (ex: 'S')");
        start = in.nextLine();
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

        String opcao;
        do{
            System.out.println("Escolha uma das opções abaixo:");
            System.out.println("1 - Quero cadastrar outro não terminal e suas derivações");
            System.out.println("2 - Já terminei de cadastrar a gramática");

            do{
                opcao = in.nextLine();
                if (!opcao.equals("1") && !opcao.equals("2")){
                    System.out.println("Por favor, escolha uma das opções citadas acima");
                }
            }while(!opcao.equals("1") && !opcao.equals("2"));

            if(opcao.equals("1")){
                if(naoTerminais.size() == 8 ){
                    System.out.println("O limite de não terminais (8) foi excedido, por favor escolha a opção para terminar a gramática");
                }
                else{
                    criaNaoTerminal();
                }   
            }
        }while(!opcao.equals("2"));

        System.out.println("\n" +  naoTerminais);
    }

    public void case2(){

    }

    public void case3(){

    }

    public void case4(){

    }

    public NaoTerminal criaNaoTerminal(){
        String simbolo;
        System.out.println("Digite o símbolo do não terminal, com uma letra maiúscula (ex: 'A')");
        simbolo = in.nextLine();
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

        return naoTerminal;
    }
}
