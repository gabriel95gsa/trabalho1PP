package gunfactory;

import javax.swing.ImageIcon;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public class GunMissel extends GunFactory {
    
    @Override
    public ImageIcon getImageAlteravel(int posicao) {
        return new ImageIcon("suporte.Missel" + posicao + ".png");
    }

    @Override
    public ImageIcon getImageUnica() {
        return new ImageIcon("suporte.Missel" + ".png");
    }
    
}