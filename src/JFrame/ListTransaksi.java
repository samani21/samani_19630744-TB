/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrame;

import db.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Jadwal;
import model.Session;
import model.Trans;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author sidik ansari
 */
public class ListTransaksi extends javax.swing.JFrame {
    Trans trans;
    /**
     * Creates new form ListTransaksi
     */
    public ListTransaksi() {
        initComponents();
        setLocationRelativeTo(null);
        resetTable("");
        eCari.setText(Session.getId());
        if(eCari.getText().equals("291227")){
            eCari.setEnabled(true);
            eCari.setText("");
        }else{
            eCari.setEnabled(false);
            resetTable(" WHERE id_pelanggan like '%" + eCari.getText() + "%'");
        }
        tanggal();
    }
    
    public void tanggal(){
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        eTanggal.setText(s.format(ys));
    }
    public ArrayList<Trans> getTransaksiList(String keyword){
     ArrayList<Trans> transaksiList = new ArrayList<Trans>();
     Koneksi koneksi =  new Koneksi();
     Connection connection = koneksi.getConnection();
     
     String query ="SELECT * FROM tb_transaksi"+keyword;
     Statement st;
     ResultSet rs;
     
     try{
         st = connection.createStatement();
         rs = st.executeQuery(query);
         while (rs.next()){
             trans = new Trans(
                     rs.getInt("no_tiket"),
                     rs.getString("id_pelanggan"),
                     rs.getString("nama_pelanggan"),
                     rs.getString("maskapai"),
                     rs.getString("penerbangan"),
                     rs.getString("tujuan1"),
                     rs.getString("tanggal"),
                     rs.getString("jam"),
                     rs.getString("kelas"),
                     rs.getString("biaya")
             );
             transaksiList.add(trans);
        
         }
     } catch (SQLException | NullPointerException ex){
         System.err.println("Koneksi Null Gagal");
     }
        return transaksiList;
    }
    
    public void selectJadwal(String keyword){
        ArrayList<Trans> list = getTransaksiList(keyword);
        DefaultTableModel model = (DefaultTableModel)tTransaksi.getModel();
        Object[] row = new Object[10];
        
        for (int i = 0; i <list.size(); i++){
            row[0] = list.get(i).getNo_tiket();
            row[1] = list.get(i).getId_pelanggan();
            row[2] = list.get(i).getNm_pelanggan();
            row[3] = list.get(i).getMaskapai();
            row[4] = list.get(i).getPenerbangan();
            row[5] = list.get(i).getTujuan();
            row[6] = list.get(i).getTanggal();
            row[7] = list.get(i).getJam();
            row[8] = list.get(i).getKelas();
            row[9] = list.get(i).getBiaya();
            
            model.addRow(row);
        }
    }
    
    public final void resetTable(String keyword){
        DefaultTableModel model = (DefaultTableModel)tTransaksi.getModel();
        model.setRowCount(0);
        selectJadwal(keyword);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        eCari = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTransaksi = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btCetak = new javax.swing.JButton();
        eTanggal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel1.setText("LIST TRANSAKSI TIKET");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        eCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eCariActionPerformed(evt);
            }
        });
        eCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                eCariKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eCariKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARI TIKET");

        tTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Tiket", "Id Pelanggan", "Nama Pelanggan", "Maskapai", "Penerbangan", "Tujuan", "Tanggal", "Jam", "Kelas", "Biaya"
            }
        ));
        tTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tTransaksiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tTransaksiMousePressed(evt);
            }
        });
        tTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tTransaksiKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tTransaksi);
        if (tTransaksi.getColumnModel().getColumnCount() > 0) {
            tTransaksi.getColumnModel().getColumn(3).setMinWidth(160);
            tTransaksi.getColumnModel().getColumn(3).setMaxWidth(160);
            tTransaksi.getColumnModel().getColumn(4).setMinWidth(160);
            tTransaksi.getColumnModel().getColumn(4).setMaxWidth(160);
        }

        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btCetak.setText("Cetak");
        btCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCetakActionPerformed(evt);
            }
        });

        eTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eTanggalActionPerformed(evt);
            }
        });
        eTanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eTanggalKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eCari, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(eTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(176, 771, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btCetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1005, 540));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(eCari.getText().equals("12001")){
            new Penjual().setVisible(true);
            dispose();
        }else{
            Pelanggan a = new Pelanggan();
            a.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void eCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_eCariKeyPressed

    private void eCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eCariActionPerformed

    private void eCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCariKeyReleased
        // TODO add your handling code here:
        resetTable(" WHERE no_tiket like '%"+eCari.getText()+"%' OR"
                   +" id_pelanggan like '%"+eCari.getText()+"%' OR" +" nama_pelanggan like '%"+eCari.getText()+"%' OR"
                   +" maskapai like '%"+eCari.getText()+"%'OR" +" penerbangan like '%"+eCari.getText()+"%'OR"
                   +" tujuan1 like '%"+eCari.getText()+"%'OR" +" tanggal like '%"+eCari.getText()+"%'OR"
                   +" jam like '%"+eCari.getText()+"%'OR" +" biaya like '%"+eCari.getText()+"%'");
    }//GEN-LAST:event_eCariKeyReleased

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // TODO add your handling code here:
        try{
            Koneksi koneksi =  new Koneksi();
            Connection cn = koneksi.getConnection();
            HashMap hash = new HashMap();
            hash.put("id_pelanggan", eCari.getText());
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("../Laporan/CetakTiket.jasper"), hash, cn);
            JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btCetakActionPerformed

    private void tTransaksiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTransaksiKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tTransaksiKeyReleased

    private void tTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tTransaksiMouseClicked

    private void tTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTransaksiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tTransaksiMousePressed

    private void eTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eTanggalActionPerformed

    private void eTanggalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eTanggalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_eTanggalKeyReleased

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
            java.util.logging.Logger.getLogger(ListTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCetak;
    private javax.swing.JTextField eCari;
    private javax.swing.JTextField eTanggal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tTransaksi;
    // End of variables declaration//GEN-END:variables
}
