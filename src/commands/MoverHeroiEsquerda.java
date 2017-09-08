package commands;

import gameitenslogs.HeroLog;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public class MoverHeroiEsquerda implements Command {

    private HeroLog hero;

    public MoverHeroiEsquerda(HeroLog hero) {
        this.hero = hero;
    }
    
    @Override
    public void execute() {
        this.hero.setTextoLog("Moveu heroi para a esquerda");
        this.hero.escreverLog();
    }

    @Override
    public void undo() {
        this.hero.apagarLog();
    }
    
    @Override
    public String toString() {
        return "Moveu herói para a esquerda";
    }
    
}
