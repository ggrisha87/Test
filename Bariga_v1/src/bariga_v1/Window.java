/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bariga_v1;

import static bariga_v1.Bariga_v1.ICON_FILENAME;
import static bariga_v1.ParseHTML.stringContainsWordFromList;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;

/**
 *
 * @author user
 */
public class Window extends javax.swing.JFrame {

    private String cityName = null;
    
    private String categoryName = null; 
    
    private int adsNumber = 0;
    
    private String mainURL = null;
    
    private ArrayList<Advertisement> currArrayList= null;
    
    private Advertisement[] lastFiveArray = null;
    
    private int lastFiveArrayIndex = 0;
    
    private ImageIcon iconJpg = new ImageIcon(ICON_FILENAME);

    private Timer timer = null;
    
    
    
    // method that puts String into a clipboard
    private static void setClipboard(String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }

    
    // method for checking if string contains a number
    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) { }
        return false;
    }

    // method that puts image on JLabel
    private void setImageToLabel(BufferedImage image, JLabel jLabel) {
        if (image != null) {
            ImageIcon icon = null;
            icon = new ImageIcon(image);
            jLabel.setIcon(icon);
        }
    }
    
    private void fillLastFiveAds() {
        
        if (lastFiveArrayIndex > 4) {
            lastFiveArrayIndex = 0;
        }
        
        lastFiveArray[lastFiveArrayIndex] = currArrayList.get(adsNumber);
        lastFiveArrayIndex++;
        
        for (int i = 0; i <= 4; i++) {
            if (jPanel3.getComponent(i) instanceof JLabel) {
                JLabel label = (JLabel) jPanel3.getComponent(i);
                if (lastFiveArray[i] != null) {
                    try {
                        setImageToLabel(lastFiveArray[i].getImage(), label);
                        label.setToolTipText(lastFiveArray[i].getAdsName());
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    
    
    
    
    
    // method that builds main URL to make a request
    // returns URL
    private String buildURL(String cityName, String categoryName) {
        
        // start string with special symbols
        String initialURL = "http://CITYNAMEolx.ua/CATEGORYNAME/?search%5Bfilter_float_price%3Afrom%5D=free&view=galleryWide";
        
        // for changing the category & city
        String tmpURL = null;
        
        switch (categoryName) {
            case "Все рубрики": tmpURL = initialURL.replaceAll("CATEGORYNAME", "list");
            break;

            case "Детский мир": tmpURL = initialURL.replaceAll("CATEGORYNAME", "detskiy-mir");
            break;

            case "Недвижимость": tmpURL = initialURL.replaceAll("CATEGORYNAME", "nedvizhimost");
            break;

            case "Транспорт": tmpURL = initialURL.replaceAll("CATEGORYNAME", "transport");
            break;

            case "Животные": tmpURL = initialURL.replaceAll("CATEGORYNAME", "zhivotnye");
            break;

            case "Дом и сад": tmpURL = initialURL.replaceAll("CATEGORYNAME", "dom-i-sad");
            break;

            case "Электроника": tmpURL = initialURL.replaceAll("CATEGORYNAME", "elektronika");
            break;
                    
            case "Бизнес и услуги": tmpURL = initialURL.replaceAll("CATEGORYNAME", "uslugi");
            break;
                        
            case "Мода и стиль": tmpURL = initialURL.replaceAll("CATEGORYNAME", "moda-i-stil");
            break;
                            
            case "Хобби отдых и спорт": tmpURL = initialURL.replaceAll("CATEGORYNAME", "hobbi-otdyh-i-sport");
            break;
        }
        
        
        switch (cityName) {
            case "Вся Украина": tmpURL = tmpURL.replaceAll("CITYNAME", "");
            break;

            case "Винница": tmpURL = tmpURL.replaceAll("CITYNAME", "vinnitsa.vin.");
            break;

            case "Днепропетровск": tmpURL = tmpURL.replaceAll("CITYNAME", "dnepropetrovsk.dnp.");
            break;

            case "Донецк": tmpURL = tmpURL.replaceAll("CITYNAME", "donetsk.don.");
            break;

            case "Житомир": tmpURL = tmpURL.replaceAll("CITYNAME", "zhitomir.zht.");
            break;

            case "Запорожье": tmpURL = tmpURL.replaceAll("CITYNAME", "zaporozhe.zap.");
            break;

            case "Ивано-Франковск": tmpURL = tmpURL.replaceAll("CITYNAME", "ivano-frankovsk.if.");
            break;
                    
            case "Киев": tmpURL = tmpURL.replaceAll("CITYNAME", "kiev.ko.");
            break;
                        
            case "Кировоград": tmpURL = tmpURL.replaceAll("CITYNAME", "kirovograd.kir.");
            break;
                            
            case "Луганск": tmpURL = tmpURL.replaceAll("CITYNAME", "lugansk.lug.");
            break;
                
            case "Луцк": tmpURL = tmpURL.replaceAll("CITYNAME", "lutsk.vol.");
            break;
                    
            case "Львов": tmpURL = tmpURL.replaceAll("CITYNAME", "lvov.lv.");
            break;
                        
            case "Николаев": tmpURL = tmpURL.replaceAll("CITYNAME", "nikolaev.nik.");
            break;
                
            case "Одесса": tmpURL = tmpURL.replaceAll("CITYNAME", "odessa.od.");
            break;
                
            case "Полтава": tmpURL = tmpURL.replaceAll("CITYNAME", "poltava.pol.");
            break;
            
            case "Ровно": tmpURL = tmpURL.replaceAll("CITYNAME", "rovno.rov.");
            break;
            
            case "Сумы": tmpURL = tmpURL.replaceAll("CITYNAME", "sumy.sum.");
            break;
            
            case "Тернополь": tmpURL = tmpURL.replaceAll("CITYNAME", "ternopol.ter.");
            break;
                    
            case "Ужгород": tmpURL = tmpURL.replaceAll("CITYNAME", "uzhgorod.zak.");
            break;
                        
            case "Харьков": tmpURL = tmpURL.replaceAll("CITYNAME", "kharkov.kha.");
            break;
                
            case "Херсон": tmpURL = tmpURL.replaceAll("CITYNAME", "kherson.khe.");
            break;
            
            case "Хмельницкий": tmpURL = tmpURL.replaceAll("CITYNAME", "khmelnitskiy.khm.");
            break;
                
            case "Черкассы": tmpURL = tmpURL.replaceAll("CITYNAME", "cherkassy.chk.");
            break;
                
            case "Чернигов": tmpURL = tmpURL.replaceAll("CITYNAME", "chernigov.chn.");
            break;
                    
            case "Черновцы": tmpURL = tmpURL.replaceAll("CITYNAME", "chernovtsy.chv.");
            break;
        }
        return tmpURL;
    }
    
    
    
    
    // method that searches advertisements automatically
    public void doSearch(){
        
        jTextArea1.setText(null);
        mainURL = buildURL(cityName, categoryName);
        
        currArrayList = ParseHTML.getAds(ParseHTML.getDocument(mainURL));
        
        for (int cnt = 0; cnt < currArrayList.size(); cnt++) {
            if (jCheckBox1.isSelected()) {
                String tmpString = currArrayList.get(cnt).getAdsName();
                if (stringContainsWordFromList(Bariga_v1.blackList, tmpString)) {
                    currArrayList.remove(cnt);
                    cnt--;
                    continue;
                }
                jTextArea1.append(tmpString + "\n");
            } else {
              jTextArea1.append(currArrayList.get(cnt).getAdsName() + "\n");  
            }
        }
        jTextArea1.setCaretPosition(0);
    }
    
    
    
    
    
    
    
    
    /**
     * Creates new form Window
     */
    public Window() {
        initComponents();
        
        cityName = "Вся Украина";
        categoryName = "Все рубрики";
        lastFiveArray = new Advertisement[5];
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OLX барыга v1");
        setIconImage(iconJpg.getImage());
        setResizable(false);

        jLabel1.setText("Город, в котором ищем:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Вся Украина", "Винница", "Днепропетровск", "Донецк", "Житомир", "Запорожье", "Ивано-Франковск", "Киев", "Кировоград", "Луганск", "Луцк", "Львов", "Николаев", "Одесса", "Полтава", "Ровно", "Сумы", "Тернополь", "Ужгород", "Харьков", "Херсон", "Хмельницкий", "Черкассы", "Чернигов", "Черновцы" }));
        jComboBox1.setToolTipText("Выберите город");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Что хотим искать:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Все рубрики", "Детский мир", "Недвижимость", "Транспорт", "Животные", "Дом и сад", "Электроника", "Бизнес и услуги", "Мода и стиль", "Хобби отдых и спорт" }));
        jComboBox2.setToolTipText("Выберите рубрику");
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("С каким интервалом смотреть, с:");

        jTextField1.setText("5");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Запуск");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setToolTipText("Нажмите на объявление и ссылка на него скопируется в буфер обмена");
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jCheckBox1.setText("Фильтровать котиков");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton2.setText("Остановка");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setToolTipText("Последние 5 выбранных объявлений");

        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, 143, Short.MAX_VALUE)
                                    .addComponent(jTextField1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        timer.stop();

        jButton1.setEnabled(true);
        jCheckBox1.setEnabled(true);
        jTextField1.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked

        // TODO add your handling code here:

        if (currArrayList != null) {
            try {
                adsNumber = jTextArea1.getLineOfOffset(jTextArea1.getCaret().getDot());
                //System.out.println(adsNumber + "  " + currArrayList.get(adsNumber).getAdsName());

            } catch (BadLocationException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }

            setClipboard(currArrayList.get(adsNumber).getAdsURL());
            currArrayList.get(adsNumber).setSelection();

            fillLastFiveAds();

            jLabel4.removeAll();
            try {
                setImageToLabel(currArrayList.get(adsNumber).getImage(), jLabel4);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        jButton2.setEnabled(true);

        String delayMs = jTextField1.getText();
        if (isNumber(delayMs)) {
            if (Integer.valueOf(delayMs) <= 0) {
                delayMs = "1";
                jTextField1.setText("1");
            }
            timer = new Timer(Integer.valueOf(delayMs) * 1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    doSearch();
                }
            });

            timer.start();

            jButton1.setEnabled(false);
            jCheckBox1.setEnabled(false);
            jTextField1.setEnabled(false);
            jComboBox1.setEnabled(false);
            jComboBox2.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Введите число секунд - период обновления", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        categoryName = jComboBox2.getSelectedItem().toString();

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        cityName = jComboBox1.getSelectedItem().toString();

    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
