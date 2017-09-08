package observers;

import suporte.GameItem;

/**
 *
 * @author Gabriel Schenkel Albino, Cristiano Flores, Julio Vinicius
 */
public interface ObservadorGame {
 
    int atualizarScore(GameItem item);
    
    int atualizarVida(GameItem item);
    
}
