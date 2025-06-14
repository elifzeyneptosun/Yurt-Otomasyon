/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package swingdeneme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class Ogrenciler extends javax.swing.JFrame {

    /**
     * Creates new form Ogrenciler
     */
    public Ogrenciler() {
        initComponents();
        listele();
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
        ogrenciListe = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ogrenciListe.setColumns(20);
        ogrenciListe.setRows(5);
        jScrollPane1.setViewportView(ogrenciListe);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 430, 360));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Ogrenciler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ogrenciler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ogrenciler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ogrenciler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ogrenciler().setVisible(true);
            }
        });
    }
    
    public void listele(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=yurtOtomasyon;username=localhost;password=123456789;encrypt=true;trustServerCertificate=true;";
            Connection con = DriverManager.getConnection(url);
            String sql = "DECLARE \n" +
"  @ogrenciİD INT,\n" +
"  @adi NVARCHAR(50),\n" +
"  @soyad NVARCHAR(50),\n" +
"  @tc NVARCHAR(20),\n" +
"  @kanGrubu NVARCHAR(10),\n" +
"  @okul NVARCHAR(50)\n" +
"\n" +
"DECLARE @cursor CURSOR\n" +
"SET @cursor = CURSOR FOR\n" +
"  SELECT\n" +
"    Ogrenci.ogrenciİD,\n" +
"    Ogrenci.Adı,\n" +
"    Ogrenci.Soyad,\n" +
"    Ogrenci.Tc,\n" +
"    Ogrenci.kanGrubu,\n" +
"    Ogrenci.Okul\n" +
"  FROM\n" +
"    Ogrenci\n" +
"\n" +
"OPEN @cursor\n" +
"FETCH NEXT FROM @cursor INTO @ogrenciİD, @adi, @soyad, @tc, @kanGrubu, @okul\n" +
"WHILE @@FETCH_STATUS = 0\n" +
"BEGIN\n" +
"  PRINT CONCAT('ID: ', @ogrenciİD, ', Adı: ', @adi, ', Soyad: ', @soyad, ', TC: ', @tc, ', Kan Grubu: ', @kanGrubu, ', Okul: ', @okul)\n" +
"  FETCH NEXT FROM @cursor INTO @ogrenciİD, @adi, @soyad, @tc, @kanGrubu, @okul\n" +
"END\n" +
"CLOSE @cursor\n" +
"DEALLOCATE @cursor";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            JOptionPane.showMessageDialog(null, "Öğrenciler Listelendi.");
            if(rs.next()){
                while(rs.next()){
                    ogrenciListe.append(rs.getString(1));
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Öğrenci yok.");
            }
           
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ogrenciListe;
    // End of variables declaration//GEN-END:variables
}
