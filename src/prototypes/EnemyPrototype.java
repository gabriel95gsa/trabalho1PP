package prototypes;

import suporte.GameItem;
import observers.ObservadorGame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import suporte.GameGridModel;
import suporte.Hero;
import suporte.Shot;

/*
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public abstract class EnemyPrototype extends GameItem {
    
    protected GameGridModel model;
    protected int x;
    protected int y;
    protected int hitScore;
    protected List<ObservadorGame> observadores = new ArrayList<>();
    
    public GameGridModel getModel() {
        return model;
    }

    public void setModel(GameGridModel model) {
        this.model = model;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<ObservadorGame> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<ObservadorGame> observadores) {
        this.observadores = observadores;
    }

    public int getHitScore() {
        return hitScore;
    }

    public void setHitScore(int hitScore) {
        this.hitScore = hitScore;
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
    
    public abstract EnemyPrototype clonar();
    
    public void anexar(ObservadorGame obs) {
        observadores.add(obs);
    }
    
    public void desanexar(ObservadorGame obs) {
        observadores.remove(obs);
    }
    
}