// class for working with html
package bariga_v1;

import static bariga_v1.Bariga_v1.WORDFILTER_DELIMITERS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author user
 */
public class ParseHTML {
    
    // method that connect to url
    // and returns a Document
    public static Document getDocument (String url) {
        Document doc = null;
        if (url != null) {
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException ex) {
                System.out.println("Cant get page: " + url);
            }
        }
        else {
            return null;
        }
        return doc;
    }
    
    
    
    // method that transforms Document 
    // into array of advertisements
    public static ArrayList<Advertisement> getAds (Document doc) {
        
        ArrayList<Advertisement> ads = new ArrayList<>();
        if (doc != null) {
            Elements divTag = doc.getElementsByAttributeValue("class", "mheight tcenter");
        
            for (Element el : divTag) {
                Elements a = el.select("a").attr("class", "thumb vmiddle inlblk tdnone scale1 detailsLink linkWithHash ");
                Elements img = el.select("img").attr("class", "fleft rel zi2");
                Advertisement adv = new Advertisement(a.attr("title"), a.attr("href"), img.attr("src"));
                ads.add(adv);
            }
        }
        
//<div class="mheight tcenter">
//    <a title="Отдам даром детали от принтера" href="http://kiev.ko.olx.ua/obyavlenie/otdam-darom-detali-ot-printera-IDaOcGj.html#7ff3649696"
//			class="thumb vmiddle inlblk tdnone scale1 detailsLink linkWithHash ">
//    <img alt="Отдам даром детали от принтера" src="http://img28.olx.ua/images_slandocomua/158772533_1_261x203_otdam-darom-detali-ot-printera-kiev.jpg" class="fleft rel zi2">
//    </a>
//</div>
        
        return ads;
     }
        
    
    
    public static boolean stringContainsWordFromList(ArrayList blackList, String s) {
           
        if (s != null) {
            ArrayList<String> stringWords = new ArrayList<>();

            StringTokenizer token = new StringTokenizer(s, WORDFILTER_DELIMITERS);
            while (token.hasMoreTokens()) {
                stringWords.add(token.nextToken());
            }

            for (int i = 0; i < blackList.size(); i++) {
                if (stringWords.contains(blackList.get(i)))
                    return true;
            }
        }
        return false;
    }
    
    
    
    
    
    
    
    

}