package observers;

import suporte.GameItem;

/**
 *
 * @author Gabriel Schenkel Albino, Cristino Flores, Julio Vinicius
 */
public class ObservadorHero implements ObservadorGame {

    @Override
    public int atualizarScore(GameItem item) {
        return 0;
    }

    @Override
    public int atualizarVida(GameItem item) {
        return item.getVidas();
    }
    
}