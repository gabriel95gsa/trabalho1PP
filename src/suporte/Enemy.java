package suporte;

/**
*
* @author adilsonv77
*/

public class Enemy extends GameItem {

    private GameGridModel model;
    private int x;
    private int y;

    public Enemy(int y, int x, GameGridModel model) {
        this.x = x;
        this.y = y;
        this.model = model;
    }

    public void atualizar() throws Exception {
        model.getObjetosNovoEstado()[this.y][this.x] = null;

        y++;
        if (y < 10) {
            if (model.getObjetosNovoEstado()[this.y][this.x] instanceof Hero) {
                ((Hero)model.getObjetosNovoEstado()[this.y][this.x]).die();
                throw new Exception("Fim do Jogo");
            }

            model.getObjetosNovoEstado()[this.y][this.x] = this;
        }
    }
    
}
