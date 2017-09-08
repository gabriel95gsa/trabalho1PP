package observers;

import suporte.GameItem;

/**
 *
 * @author Gabriel Schenkel Albino, Cristino Flores, Julio Vinicius
 */
public class ObservadorEnemy implements ObservadorGame {

    @Override
    public int atualizarScore(GameItem item) {
        return item.getHitScore();
    }

    @Override
    public int atualizarVida(GameItem item) {
        return 0;
    }
    
}
