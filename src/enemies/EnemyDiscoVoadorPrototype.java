package enemies;

import javax.swing.ImageIcon;
import prototypes.EnemyPrototype;
import suporte.GameGridModel;

/**
*
* @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
*/
public class EnemyDiscoVoadorPrototype extends EnemyPrototype {

    protected EnemyDiscoVoadorPrototype(EnemyPrototype enemy) {
        this.x = enemy.getX();
        this.y = enemy.getY();
        this.model = enemy.getModel();
        this.hitScore = 0;
    }
    
    public EnemyDiscoVoadorPrototype() {
        this.x = 0;
        this.y = 0;
        this.model = null;
        this.hitScore = 0;
    }
    
    @Override
    public EnemyPrototype clonar() {
        return new EnemyDiscoVoadorPrototype(this);
    }
    
    @Override
    public ImageIcon getImagem() {
        return new ImageIcon("enemies.EnemyDiscoVoador.png");
    }
    
}
