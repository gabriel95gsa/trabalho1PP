package commands;

import gameitenslogs.HeroLog;

/**
 *
 * @author gabri
 */
public class ExecutarShot implements Command {

    private HeroLog hero;

    public ExecutarShot(HeroLog hero) {
        this.hero = hero;
    }
    
    @Override
    public void execute() {
        this.hero.setTextoLog("Heroi disparou um tiro");
        this.hero.escreverLog();
    }

    @Override
    public void undo() {
        this.hero.apagarLog();
    }
    
    @Override
    public String toString() {
        return "Herói disparou um tiro";
    }
    
}