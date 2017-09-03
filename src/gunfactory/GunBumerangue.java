package gunfactory;

import javax.swing.ImageIcon;
import suporte.GameGridModel;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public class GunBumerangue extends GunFactory {
    
    @Override
    public ImageIcon getImageAlteravel(int posicao) {
        return new ImageIcon("suporte.Tiro" + posicao + ".png");
    }

    @Override
    public ImageIcon getImageUnica() {
        return new ImageIcon("suporte.Tiro1" + ".png");
    }
    
}