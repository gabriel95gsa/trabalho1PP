package suporte;

import javax.swing.ImageIcon;

/**
 *
 * @author wagnnersousa & adilsonv77
 */

public abstract class GameItem {
    
    public ImageIcon getImagem() {
        return new ImageIcon(getClass().getCanonicalName() + ".png");
    }

    public void atualizar()  throws Exception  { }
    
    public int getHitScore() { return 0; }
    
    public int getVidas() { return 0; }
    
}