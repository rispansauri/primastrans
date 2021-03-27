/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primastrans;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author chevi
 */
public class frmOli extends javax.swing.JFrame {

    /**
     * Creates new form frmOli
     */
    public frmOli() {
        initComponents();
        this.setLocationRelativeTo(null);
        load_table();
        cmbMobil();
        textboxOff();
        kosong();
    }
    
    private void load_table(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("Kode Mobil");
        model.addColumn("Tanggal");
        model.addColumn("Jarak");
        model.addColumn("Note");
        
        //menampilkan data database kedalam tabel
        try {
            String sql = "select * from tb_ganti_oli";
            java.sql.Connection conn=Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)});
            }
            jTable1.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cmbMobil() {
        try { 
            String sql = "select * from tb_mobil";
            java.sql.Connection conn=Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                cmbMobil.addItem(res.getString("kd_mobil"));
            }
        }catch (Exception e) {
            
        }
        
    }
    
    private void kdOli() {
        try {
            String sql = "select * from tb_ganti_oli order by kd_ganti_oli desc";
            java.sql.Connection conn=Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet rs=stm.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("kd_ganti_oli").substring(2);
                String AN = "" + (Integer.parseInt(kd) + 1);
                txtKode.setText("OL" + AN);
            } else {
               txtKode.setText("OL1");
            }

           }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           }
        
    }
    private void textboxOn() {
        cmbMobil.setEnabled(true);
        cmbDate.setEnabled(true);
        txtJarak.setEditable(true);
        txtNote.setEditable(true);
        jPanel4.setVisible(true);
        btnSimpan1.setVisible(true);
        btnBatal.setVisible(true);
    }
    
    private void textboxOff() {
        txtKode.setEnabled(false);
        cmbMobil.setEnabled(false);
        cmbDate.setEnabled(false);
        txtJarak.setEditable(false);
        txtNote.setEditable(false);
        jPanel4.setVisible(false);
        btnSimpan1.setVisible(false);
        btnBatal.setVisible(false);
    }
    
    private void kosong(){
        txtKode.setText(null);
        cmbMobil.setSelectedItem(null);
        cmbDate.setDate(null);
        txtJarak.setText("Jarak");
        txtNote.setText("Note");        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnHapus = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnCetak = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnBaru = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbMobil = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtJarak = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSimpan1 = new javax.swing.JLabel();
        btnBatal = new javax.swing.JLabel();
        cmbDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(61, 115, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("DATA GANTI OLI");

        jPanel5.setBackground(new java.awt.Color(61, 115, 80));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.setToolTipText("");

        btnHapus.setBackground(new java.awt.Color(204, 204, 204));
        btnHapus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(204, 204, 204));
        btnHapus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHapus.setText("Hapus");
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBackground(new java.awt.Color(61, 115, 80));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel6.setToolTipText("");

        btnCetak.setBackground(new java.awt.Color(204, 204, 204));
        btnCetak.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(204, 204, 204));
        btnCetak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCetak.setText("Cetak");
        btnCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCetakMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(61, 115, 80));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.setToolTipText("");

        btnBaru.setBackground(new java.awt.Color(204, 204, 204));
        btnBaru.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBaru.setForeground(new java.awt.Color(204, 204, 204));
        btnBaru.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBaru.setText("Baru");
        btnBaru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBaruMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(242, 233, 242));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator6.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator6.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 210, 10));

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator7.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 210, 10));

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator3.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 210, 10));

        jSeparator4.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator4.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 60, 10));

        jSeparator5.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator5.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 210, 10));

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("×");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, -1, 20));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Kode Ganti Oli");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        txtKode.setBackground(new java.awt.Color(242, 233, 242));
        txtKode.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtKode.setBorder(null);
        jPanel2.add(txtKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 210, 30));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Kode Mobil");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        cmbMobil.setBackground(new java.awt.Color(242, 233, 242));
        cmbMobil.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cmbMobil.setBorder(null);
        jPanel2.add(cmbMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 58, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Tanggal");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Jarak");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, -1, -1));

        txtJarak.setBackground(new java.awt.Color(242, 233, 242));
        txtJarak.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtJarak.setBorder(null);
        txtJarak.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtJarakFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtJarakFocusLost(evt);
            }
        });
        jPanel2.add(txtJarak, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 210, 30));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtNote.setBackground(new java.awt.Color(242, 233, 242));
        txtNote.setColumns(20);
        txtNote.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNote.setRows(5);
        txtNote.setBorder(null);
        txtNote.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoteFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txtNote);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 210, 29));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Note");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, -1, -1));

        jPanel4.setBackground(new java.awt.Color(242, 233, 242));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setToolTipText("");

        btnSimpan1.setBackground(new java.awt.Color(102, 102, 102));
        btnSimpan1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSimpan1.setForeground(new java.awt.Color(102, 102, 102));
        btnSimpan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSimpan1.setText("Simpan");
        btnSimpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpan1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, 40));

        btnBatal.setBackground(new java.awt.Color(102, 102, 102));
        btnBatal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(102, 102, 102));
        btnBatal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBatal.setText("Batal");
        btnBatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBatalMouseClicked(evt);
            }
        });
        jPanel2.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 102, 38));

        cmbDate.setBackground(new java.awt.Color(242, 233, 242));
        cmbDate.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(cmbDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 210, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String kdoli =jTable1.getValueAt(baris, 0).toString();
        txtKode.setText(kdoli);
        String cmbmobil = jTable1.getValueAt(baris,1).toString();
        cmbMobil.setSelectedItem(cmbmobil);
        String tgl = jTable1.getValueAt(baris, 2).toString();
        try {
            java.util.Date date;
            date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
            cmbDate.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(frmOli.class.getName()).log(Level.SEVERE, null, ex);
        }
        String jarak = jTable1.getValueAt(baris, 3).toString();
        txtJarak.setText(jarak);
        String note = jTable1.getValueAt(baris, 4).toString();
        txtNote.setText(note);
        textboxOn();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini?", "Penghapusan Data", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION){
            try {
            String sql ="delete from tb_ganti_oli where kd_ganti_oli='"+txtKode.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data berhasil di hapus.");
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        load_table();
        kosong();
        }else{
            JOptionPane.showMessageDialog(null, "Data batal dihapus.");
        }
    }//GEN-LAST:event_btnHapusMouseClicked

    private void btnCetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseClicked
        try {
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("rptOli.jasper"), null, Config.configDB());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnCetakMouseClicked

    private void btnBaruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBaruMouseClicked
        // TODO add your handling code here:
        textboxOn();
        kosong();
        kdOli();
    }//GEN-LAST:event_btnBaruMouseClicked

    private void btnSimpan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpan1MouseClicked
        try{
            String query = "select * from tb_ganti_oli where kd_ganti_oli = '"+txtKode.getText()+"'";
            java.sql.Connection conn=Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(query);
            SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
            if(res.next()){
                String sql ="UPDATE tb_ganti_oli SET kd_ganti_oli = '"+txtKode.getText()+"', kd_mobil = '"+cmbMobil.getSelectedItem()+"', tgl = '"+Date_Format.format(cmbDate.getDate())+"', jarak = '"+txtJarak.getText()+"', note = '"+txtNote.getText()+"' WHERE kd_ganti_oli = '"+txtKode.getText()+"'";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "data berhasil di edit");
            }else{
                String sql = "INSERT INTO tb_ganti_oli VALUES ('"+txtKode.getText()+"','"+cmbMobil.getSelectedItem()+"','"+Date_Format.format(cmbDate.getDate())+"','"+txtJarak.getText()+"','"+txtNote.getText()+"')";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan data berhasil");
            }
            load_table();
            kosong();
            textboxOff();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSimpan1MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.dispose();
        new mainMenu().setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btnBatalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseClicked
        textboxOff();
        kosong();
    }//GEN-LAST:event_btnBatalMouseClicked

    private void txtJarakFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtJarakFocusGained
        if (txtJarak.getText().equals("Jarak")){
            txtJarak.setText("");
        }
    }//GEN-LAST:event_txtJarakFocusGained

    private void txtJarakFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtJarakFocusLost
        if (txtJarak.getText().equals("")){
            txtJarak.setText("Jarak");
        }
    }//GEN-LAST:event_txtJarakFocusLost

    private void txtNoteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoteFocusGained
        if (txtNote.getText().equals("Note")){
            txtNote.setText("");
        }
    }//GEN-LAST:event_txtNoteFocusGained

    private void txtNoteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoteFocusLost
        if (txtNote.getText().equals("")){
            txtNote.setText("Note");
        }
    }//GEN-LAST:event_txtNoteFocusLost

    
    
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
            java.util.logging.Logger.getLogger(frmOli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmOli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmOli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmOli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmOli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBaru;
    private javax.swing.JLabel btnBatal;
    private javax.swing.JLabel btnCetak;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnSimpan1;
    private com.toedter.calendar.JDateChooser cmbDate;
    private javax.swing.JComboBox<String> cmbMobil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtJarak;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextArea txtNote;
    // End of variables declaration//GEN-END:variables
}
