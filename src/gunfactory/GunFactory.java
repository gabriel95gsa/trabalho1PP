package gunfactory;

import javax.swing.ImageIcon;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public abstract class GunFactory {
    
    public abstract ImageIcon getImageAlteravel(int posicao);
    
    public abstract ImageIcon getImageUnica();
    
}