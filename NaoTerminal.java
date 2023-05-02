import java.util.ArrayList;

public class NaoTerminal {
    
    private String naoTerminal;
    private ArrayList<String> derivacoes = new ArrayList<String>();
    private ArrayList<String> first = new ArrayList<String>();
    private ArrayList<String> follow = new ArrayList<String>();

    public NaoTerminal(String naoTerminal, ArrayList<String> derivacoes){
        this.naoTerminal = naoTerminal;
        this.derivacoes = derivacoes;
    }

    public String getNaoTerminal() {
        return naoTerminal;
    }

    public String toStringDerivacoes(){
        String toStringDerivacoes = "";

        for(int i = 0; i < derivacoes.size(); i++){
            if(i == derivacoes.size()-1){
                toStringDerivacoes += derivacoes.get(i);
            }
            else{
                toStringDerivacoes += derivacoes.get(i) + " | ";
            }
        }

        return toStringDerivacoes;
    }

    

}
