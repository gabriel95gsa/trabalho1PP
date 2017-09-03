package suporte;

import javax.swing.ImageIcon;

/**
 *
 * @author wagnnersousa & adilsonv77
 * 
 */

public class Hero extends GameItem {
    
    private static Hero instance;
    
    private boolean die;

    // Aplicado o padrão Singleton para instanciar o herói
    private Hero() {
        
    }
    
    public synchronized static Hero getInstance(){
        if(instance == null) {
            instance = new Hero();
        }
        
        return instance;
    }

    @Override
    public ImageIcon getImagem() {
        if (!die)
            return super.getImagem();
        else
            return new ImageIcon("suporte.Explosao.png");
    }

    public void die() {
        this.die = true;
    }

}
