// "OLX bariga v1"
//
//
// this program works with OLX.UA (ex-slando) engine
// searching some definite stuff for sale or for free

package bariga_v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Bariga_v1 {
    
    public static final String WORDFILTER_DELIMITERS = "-,.:;`'\"+ =!?()[]{}";
    private static final String WORDFILTER_FILENAME = "wordfilter.txt";
    public static final String ICON_FILENAME = "olx.jpg";
    
    
    // arraylist for bad words
    public static ArrayList<String> blackList = null;
   
    // method that fills arraylist of bad words
    public static ArrayList<String> fillBlackList (String fileName) {
        BufferedReader reader = null;
        String s = "";
        ArrayList<String> words = new ArrayList<>();
        
        if (fileName != null) {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Не найден файл \"wordfilter.txt\" в каталоге программы", "Ошибка", JOptionPane.ERROR_MESSAGE);

            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Bariga_v1.class.getName()).log(Level.SEVERE, null, ex);
            }

            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    s += line + " ";
                }
            } catch (IOException ex) {
                Logger.getLogger(Bariga_v1.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            if ( s!= null ) {
                StringTokenizer token = new StringTokenizer(s);
                while (token.hasMoreTokens()) {
                    words.add(token.nextToken());
                }
            }
        }
        return words;
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here

            blackList = fillBlackList(new File(".").getCanonicalPath()+ "\\" + WORDFILTER_FILENAME);
        } catch (IOException ex) {
            Logger.getLogger(Bariga_v1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Window w = new Window();
        w.setVisible(true);
     
    }
    
}
