package adapters;

import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public class JogoSalvo {
    
    private int score;
    private int vidas;
    private String data;

    public JogoSalvo() {
        this.score = 0;
        this.vidas = 0;
        this.data = "";
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    /*
     * Recebe um string com os dados lidos de um arquivo e pega
     * os valores correspondentes a score, vidas e data do jogo
     */
    public void getConteudoArquivo(String conteudo) {
        String[] vetorDados = conteudo.split(";");
        
        int pos;
        
        pos = vetorDados[0].indexOf(":") + 2;
        
        this.score = Integer.parseInt(vetorDados[0].substring(pos));
        pos = vetorDados[1].indexOf(":") + 2;
        this.vidas = Integer.parseInt(vetorDados[1].substring(pos));
        pos = vetorDados[2].indexOf(":") + 2;
        this.data = vetorDados[2].substring(pos);
    }
    
    public boolean verificaJogoValido() {
        ValidarJogo validacao = new ValidarJogoSalvo();
        
        return validacao.validar(this.score, this.vidas, this.data);
    }
    
}