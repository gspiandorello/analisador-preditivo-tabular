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

    public ArrayList<String> getDerivacoes() {
        return derivacoes;
    }

    public ArrayList<String> getFirst() {
        return first;
    }

    public ArrayList<String> getFollow() {
        return follow;
    }

    public void setFirst(ArrayList<String> first) {
        this.first = first;
    }

    public void addFirst(String firstSimbolo){
        first.add(firstSimbolo);
    }

    public void addFollow(String followSimbolo){
        follow.add(followSimbolo);
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

    public String toStringFirst(){
        String toStringFirst = "";
        toStringFirst += "First(" + naoTerminal + ") = { ";
        for(int i = 0; i < first.size(); i++){
            if(i == first.size()-1){
                toStringFirst += first.get(i) + " }";
            }
            else{
                toStringFirst += first.get(i) + ", ";
            }
        }
        return toStringFirst;
    }

    public String toStringFollow(){
        String toStringFollow = "";
        toStringFollow += "Follow(" + naoTerminal + ") = { ";
        for(int i = 0; i < follow.size(); i++){
            if(i == follow.size()-1){
                toStringFollow += follow.get(i) + " }";
            }
            else{
                toStringFollow += follow.get(i) + ", ";
            }
        }
        return toStringFollow;
    }
}
