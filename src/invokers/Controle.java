package invokers;

import commands.Command;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public class Controle {
    
    private List<Command> commands = new ArrayList();
    
    public Controle() {}
    
    public void addCommand(Command command) {
        this.commands.add(command);
    }
    
    public void undo() {
        if(this.commands.size() == 0) {
            return;
        }
        this.commands.remove(0).undo();
    }
    
    public void executarCommands() {
        for(Command command : this.commands) {
            command.execute();
        }
    }
    
}