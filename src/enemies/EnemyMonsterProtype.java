package enemies;

import javax.swing.ImageIcon;
import prototypes.EnemyPrototype;
import suporte.GameGridModel;

/**
*
* @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
*/

public class EnemyMonsterProtype extends EnemyPrototype {

    protected EnemyMonsterProtype(EnemyPrototype enemy) {
        this.x = enemy.getX();
        this.y = enemy.getY();
        this.model = enemy.getModel();
        this.hitScore = 0;
    }
    
    public EnemyMonsterProtype() {
        this.x = 0;
        this.y = 0;
        this.model = null;
        this.hitScore = 0;
    }
    
    @Override
    public EnemyPrototype clonar() {
        return new EnemyMonsterProtype(this);
    }
    
    @Override
    public ImageIcon getImagem() {
        return new ImageIcon("enemies.EnemyMonster.png");
    }
    
    /*
    private GameGridModel model;
    private int x;
    private int y;
    private int hitScore;
    private List<ObservadorGame> observadores = new ArrayList<>();

    public Enemy(int y, int x, GameGridModel model) {
        this.x = x;
        this.y = y;
        this.model = model;
        this.hitScore = 0;
    }

    @Override
    public int getHitScore() {
        return this.hitScore;
    }
    
    public void atualizar() throws Exception {
        model.getObjetosNovoEstado()[this.y][this.x] = null;

        y++;
        if (y < 10) {
            if (model.getObjetosNovoEstado()[this.y][this.x] instanceof Hero) {
                ((Hero)model.getObjetosNovoEstado()[this.y][this.x]).die();
                if(model.getObjetosNovoEstado()[this.y][this.x].getVidas() >= 0) {
                    JOptionPane.showMessageDialog(null, "Você foi atingido! Cuidado, restam apenas " + model.getObjetosNovoEstado()[this.y][this.x].getVidas() + " vida(s)!");
                } else {
                    throw new Exception("Fim do Jogo");
                }
            }
            if(model.getObjetosNovoEstado()[this.y][this.x] instanceof Shot) {
                this.hitScore = 10 - this.y;
            }

            model.getObjetosNovoEstado()[this.y][this.x] = this;
        }
    }
    
    public void anexar(ObservadorGame obs) {
        observadores.add(obs);
    }
    
    public void desanexar(ObservadorGame obs) {
        observadores.remove(obs);
    }
    */
}
