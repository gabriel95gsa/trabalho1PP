package suporte;

import gunfactory.GunFactory;
import gunfactory.GunBumerangue;
import gunfactory.GunMissel;
import javax.swing.ImageIcon;

/**
*
* @author wagnnersousa & adilsonv77
* editado Gabriel Schenkel, Cristiano Flores, Julio Vinicius
*/

public class Shot extends GameItem {

    private int turno = 1;
    private int x;
    private int y;
    private GameGridModel model;
    private int armaSelecionada; // 0: bumerangue, 1: missel
    private GunFactory gun;
    
    public Shot(int x, int y, GameGridModel model, int armaSelecionada) {
        this.x = x;
        this.y = y;
        this.model = model;
        this.armaSelecionada = armaSelecionada;
    }
    
    @Override
    public ImageIcon getImagem() {
        GunFactory gun;
        
        if(this.armaSelecionada == 0) {
            gun = new GunBumerangue();
            
            return gun.getImageAlteravel(turno);
        } else {
            gun = new GunMissel();
            
            return gun.getImageUnica();
        }
    }

    public void atualizar() {
        model.getObjetosNovoEstado()[this.y][this.x] = null;

        y--;
        if (y >= 0) {
            model.getObjetosNovoEstado()[this.y][this.x] = this;
            turno = (turno % 4) + 1;
        }
    }
    
}
