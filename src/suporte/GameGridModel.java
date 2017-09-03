package suporte;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author wagnnersousa & adilsonv77
 * editado Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */

@SuppressWarnings("serial")
public class GameGridModel extends AbstractTableModel {

    private GameItem[][] objetos = new GameItem[10][10];
    private GameItem[][] objetosNovoEstado = new GameItem[10][10];

    private Hero hero;
    private int heroX;

    public GameItem[][] getObjetosNovoEstado() {
        return objetosNovoEstado;
    }

    public GameGridModel() {
        this.hero = Hero.getInstance();
        this.heroX = 5;
        this.objetos[9][heroX] = hero;
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public int getRowCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int lin, int col) {
        GameItem obj = objetos[lin][col];
        return (obj == null ? null : obj.getImagem());
    }

    public void addObjeto(GameItem obj, int lin, int col) {
        if (objetos[lin][col] == null)
            objetos[lin][col] = obj;
    }

    public void atualizar() throws Exception {
        for (int lin = 0; lin < 10; lin++) {
            for (int col = 0; col < 10; col++) {
                objetosNovoEstado[lin][col] = objetos[lin][col];
            }
        }

        for (int lin = 0; lin < 10; lin++) {
            for (int col = 0; col < 10; col++) {
                if (objetos[lin][col] != null) {
                    objetos[lin][col].atualizar();
                }
            }
        }

        for (int lin = 0; lin < 10; lin++) {
            for (int col = 0; col < 10; col++) {
                objetos[lin][col] = objetosNovoEstado[lin][col];
            }
        }
    }

    public void heroGoLeft() {
        if (heroX > 0) {
            moveHero(0);
        }
    }

    public void heroGoRight() {
        if (heroX < 9) {
            moveHero(1);
        }
    }

    private void moveHero(int direcao) {
        objetos[9][heroX] = null;

        switch (direcao) {
            case 0: heroX--; break;
            case 1: heroX++; break;
        }

        objetos[9][heroX] = hero;
    }

    /*
     * Parâmetros para tipo de tiro/arma:
     * 0: bumerangue, 1: míssel
     */
    public void heroShot(int opcaoArma) {
        /* Existe um erro que o tiro começa uma linha acima... isso é um problema que pode ser resolvido 
         * usando Padrões de Projeto ;)
         */
        
        Shot tiro = new Shot(heroX, 8, this, opcaoArma);
        objetos[8][heroX] = tiro;
    }
    
}