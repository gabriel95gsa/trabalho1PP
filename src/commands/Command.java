package commands;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public interface Command {
   
    void execute();
    
    void undo();
    
}
