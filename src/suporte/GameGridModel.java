package suporte;

import observers.ObservadorGame;
import javax.swing.JOptionPane;
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
    
    private int score;
    private int vidas;

    public GameItem[][] getObjetosNovoEstado() {
        return objetosNovoEstado;
    }

    public GameGridModel() {
        this.hero = Hero.getInstance();
        this.heroX = 5;
        this.objetos[9][heroX] = hero;
        this.score = 0;
        this.vidas = 0;
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
    
    public int getScore() {
        return this.score;
    }
    
    public int getVidas() {
        return this.vidas;
    }
    
    public void addScore(int valor) {
        this.score += valor;
    }
    
    /*
     * Altera o valor da vida do objeto Hero caso o jogo tenha sido carregado,
     * a partir de outro salvo
     */
    public void setVidaHeroInstance(int valor) {
        this.hero.setVidas(valor);
    }
    
    public void setVida(int valor) {
        this.vidas = valor;
    }

    public void addObjeto(GameItem obj, int lin, int col) {
        if (objetos[lin][col] == null)
            objetos[lin][col] = obj;
    }

    public void atualizar() throws Exception {
        if(this.vidas >= 0) {
            objetos[9][heroX] = hero;
        }
        
        for (int lin = 0; lin < 10; lin++) {
            for (int col = 0; col < 10; col++) {
                objetosNovoEstado[lin][col] = objetos[lin][col];
            }
        }

        for (int lin = 0; lin < 10; lin++) {
            for (int col = 0; col < 10; col++) {
                if (objetos[lin][col] != null) {
                    objetos[lin][col].atualizar();
                    if(objetos[lin][col] instanceof Hero) {
                        this.setVida(objetos[lin][col].getVidas());
                    } else if(objetos[lin][col] instanceof Enemy) {
                        this.addScore(objetos[lin][col].getHitScore());
                    }
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