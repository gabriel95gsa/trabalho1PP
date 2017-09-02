package suporte;
import javax.swing.ImageIcon;

/**
*
* @author wagnnersousa & adilsonv77
*/

public class Shot extends GameItem {

    private int turno = 1;
    private int x;
    private int y;
    private GameGridModel model;

    public Shot(int x, int y, GameGridModel model) {
        this.x = x;
        this.y = y;
        this.model = model;
    }

    @Override
    public ImageIcon getImagem() {
        return new ImageIcon("suporte.Tiro" + turno + ".png");
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
