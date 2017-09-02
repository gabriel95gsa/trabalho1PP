package suporte;

import javax.swing.ImageIcon;

/**
 *
 * @author wagnnersousa & adilsonv77
 * 
 */

public class Hero extends GameItem {

    private boolean die;

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
