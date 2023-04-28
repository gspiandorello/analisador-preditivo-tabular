import java.util.ArrayList;

public class NaoTerminal {
    
    private String naoTerminal;
    private ArrayList<String> derivacoes = new ArrayList<String>();

    public NaoTerminal(String naoTerminal, ArrayList<String> derivacoes){
        this.naoTerminal = naoTerminal;
        this.derivacoes = derivacoes;
    }

    public String getNaoTerminal() {
        return naoTerminal;
    }

    @Override
    public String toString() {
        return "" + naoTerminal +  " -> " + toStringDerivacoes();
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
