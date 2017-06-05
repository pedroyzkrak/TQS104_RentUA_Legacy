/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs104_rentua_legacy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.json.*;

/**
 *
 * @author Pedro
 */
public class MyProperties extends javax.swing.JFrame {

    /**
     * Creates new form MyProperties
     */
    public static String username;
    public static List<Property> props = new ArrayList<>();
    public static String auth;
    public MyProperties(String username,String auth) {
        initComponents();
        props.removeAll(props);
        this.auth = auth;
        this.username = username;
        String url = "http://192.168.160.225:8080/RentUA_RestAPI/rest/property/user/" + username;
        HttpURLConnection connection = null;
        URL obj;
        StringBuilder result = new StringBuilder();
        JSONTokener tk;
        JSONArray jsonarray;
        String line;
        String title;
        String type;
        String description = "";
        String price;
        try {
            obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            tk = new JSONTokener(result.toString());
            jsonarray = new JSONArray(tk);
            for(int i = 0;i<jsonarray.length();i++)
            {
                JSONObject json_data = jsonarray.getJSONObject(i);
                title = "Título: "+json_data.getString("title");
                if (!json_data.isNull("descriptin"))
                    description = "Descrição: "+json_data.getString("description");
                type = String.valueOf(json_data.getInt("type"));
                if(type.equals("0"))
                {
                    type = "Tipo: Casa";
                }
                else {
                    type = "Tipo: Quarto";
                }
                price = "Preço por Semestre: "+json_data.getInt("price")+"€";
                Property prop = new Property(title,price,type,description);
                props.add(prop);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(MyProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            DefaultListModel model = new DefaultListModel();
            for(Property p : props)
            {
                model.addElement(p);
            }
            MyPropsList.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        MyPropsList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        SubmitPropButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MyPropsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(MyPropsList);

        jLabel1.setText("Minhas Propriedades Submetidas");

        SubmitPropButton.setText("Submeter Propriedade");
        SubmitPropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitPropButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(60, 60, 60)
                        .addComponent(SubmitPropButton)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(SubmitPropButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitPropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitPropButtonActionPerformed
        new SubmitProperty(auth).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SubmitPropButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MyProperties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyProperties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyProperties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyProperties.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyProperties(username,auth).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> MyPropsList;
    private javax.swing.JButton SubmitPropButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
