package adapters;

/**
 *
 * @Gabriel Schenkel, Cristiano Flores, Julio Vinicius
 */
public class JogoSalvoValidador {
    
    public boolean validar(int score, int vidas, String data) {
        boolean retorno = true;
        
        if(score < 0) {
            retorno = false;
        }
        
        if(vidas <= 0) {
            retorno = false;
        }
        
        if(data.equals("")) {
            retorno = false;
        }
        
        return retorno;
    }
    
}
