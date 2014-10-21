// class that describes each ads
// 
// each ads has name, image for preview etc

package bariga_v1;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class Advertisement {
    
    private String adsName = null;
    
    private String adsURL = null;
    
    private String adsImgURL = null;
    
    private boolean wasSelected = false;
    
    
    public Advertisement(String adsName, String adsURL, String adsImgURL){
        this.adsName = adsName;
        this.adsURL = adsURL;
        this.adsImgURL = adsImgURL;
    }
    
    
    
    public String getAdsName(){
        return adsName;
    }
    
    public String getAdsURL(){
        return adsURL;
    }
   
    public String getAdsImgURL(){
        return adsImgURL;
    }
    
    public BufferedImage getImage() throws MalformedURLException {
        URL url = new URL(adsImgURL);
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(Advertisement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
    
    public void setSelection() {
        wasSelected = true;
    }
    
    
    public boolean wasSelected() {
        if (wasSelected) return true;
        else return false;
    }
    
   
    
    
}
