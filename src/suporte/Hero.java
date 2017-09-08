package suporte;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author wagnnersousa & adilsonv77
 * 
 */

public class Hero extends GameItem {
    
    private static Hero instance;
    
    private boolean die;
    
    private int vidasDisponiveis;

    // Aplicado o padrão Singleton para instanciar o herói
    private Hero() {
        this.vidasDisponiveis = 3;
    }
    
    public synchronized static Hero getInstance(){
        if(instance == null) {
            instance = new Hero();
        }
        
        return instance;
    }
    
    public void setVidas(int valor) {
        this.vidasDisponiveis = valor;
    }
    
    @Override
    public int getVidas() {
        return this.vidasDisponiveis;
    }

    @Override
    public ImageIcon getImagem() {
        if (!die) {
            return super.getImagem();
        } else {
            /* Foi acertado, mas ainda existem vidas, retorna a imagem do herói
             * Foi acertado e não tem mais vidas, retorna a imagem de explosão
            */
            if(this.vidasDisponiveis >= 0) {
                this.die = false;
                return super.getImagem();
            } else {
                return new ImageIcon("suporte.Explosao.png");
            }
        }
    }

    public void die() {
        this.vidasDisponiveis--;
        this.die = true;
    }

}
