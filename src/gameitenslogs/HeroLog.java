package gameitenslogs;

import org.w3c.dom.*;
import javax.xml.parsers.*; 
import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

/**
 *
 * @author Gabriel Schenkel, Cristiano Flores, Julio Vinicius gabri
 */
public class HeroLog {

    private String textoLog;
    private final String arquivoLog;
    private Element tagPai;
    private Document document;
    private File file;
    private DocumentBuilderFactory factory;
    
    public HeroLog() {
        this.arquivoLog = "game data/log.xml";
        this.file = new File(this.arquivoLog);
        this.factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            try {
                if(!file.exists()) {
                    FileWriter arquivo;
            
                    try {
                        arquivo = new FileWriter(new File(this.arquivoLog));
                        arquivo.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                                      "<logs></logs>");
                        arquivo.close();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                
                this.document = builder.parse(this.file);
            } catch (SAXException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public void setTextoLog(String textoLog) {
        this.textoLog = textoLog;
    }
    
    public void escreverLog() {
        try {
            Element root = document.getDocumentElement();

            // Pega o elemento raiz
            if(this.tagPai == null) {
                // Cria a tag partida
                this.tagPai = document.createElement("partida");
                Date data = new Date();
                SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = dataFormato.format(data);

                SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
                Date hora = Calendar.getInstance().getTime();
                String horaFormatada = horaFormato.format(hora);
                this.tagPai.setAttribute("data", dataFormatada + " " + horaFormatada);

                // Cria a tag log, filha da tag partida
                JOptionPane.showMessageDialog(null, this.textoLog);
                Element tagLog = document.createElement("log");
                tagLog.setAttribute("acao", this.textoLog);
                this.tagPai.appendChild(tagLog);

                root.appendChild(this.tagPai);
            } else {
                Date data = new Date();
                SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = dataFormato.format(data);

                SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
                Date hora = Calendar.getInstance().getTime();
                String horaFormatada = horaFormato.format(hora);
                this.tagPai.setAttribute("data", dataFormatada + " " + horaFormatada);

                // Cria a tag log, filha da tag partida
                //JOptionPane.showMessageDialog(null, this.textoLog);
                Element tagLog = document.createElement("log");
                tagLog.setAttribute("acao", this.textoLog);
                this.tagPai.appendChild(tagLog);
            }

            DOMSource source = new DOMSource(document);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");

            StreamResult arquivo = new StreamResult(file);
            transformer.transform(source, arquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void apagarLog() {
        this.textoLog = "";
    }
    
}
