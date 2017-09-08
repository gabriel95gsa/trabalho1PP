package commands;

import gameitenslogs.HeroLog;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public class MoverHeroDireita implements Command {

    private HeroLog hero;

    public MoverHeroDireita(HeroLog hero) {
        this.hero = hero;
    }
    
    @Override
    public void execute() {
        this.hero.setTextoLog("Moveu heroi para a direita");
        this.hero.escreverLog();
    }

    @Override
    public void undo() {
        this.hero.apagarLog();
    }
    
    @Override
    public String toString() {
        return "Moveu herói para a direita";
    }
    
}