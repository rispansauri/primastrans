/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primastrans;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author chevi
 */

public class mainMenu extends javax.swing.JFrame {

    /**
     * Creates new form mainMenu
     */
    public mainMenu() {
        initComponents();
         this.setLocationRelativeTo(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnMaster = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnTransaksi = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnReport = new javax.swing.JLabel();
        pnlMobil = new javax.swing.JPanel();
        btnMobil = new javax.swing.JLabel();
        pnlSupir = new javax.swing.JPanel();
        btnSupir = new javax.swing.JLabel();
        pnlTransport = new javax.swing.JPanel();
        btnTransport = new javax.swing.JLabel();
        pnlSparepart = new javax.swing.JPanel();
        btnSparepart = new javax.swing.JLabel();
        pnlGantiOli = new javax.swing.JPanel();
        btnGantiOli = new javax.swing.JLabel();
        pnlUpahSupir = new javax.swing.JPanel();
        btnUpahSupir = new javax.swing.JLabel();
        pnlPenghasilan = new javax.swing.JPanel();
        btnPenghasilan = new javax.swing.JLabel();
        pnlSupp = new javax.swing.JPanel();
        btnSupp = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        masterPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        sidebarPanel.setBackground(new java.awt.Color(61, 115, 80));
        sidebarPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("PRIMASTRANS");
        sidebarPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jPanel3.setBackground(new java.awt.Color(61, 115, 80));

        btnMaster.setBackground(new java.awt.Color(153, 153, 153));
        btnMaster.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnMaster.setForeground(new java.awt.Color(153, 153, 153));
        btnMaster.setText("Master");
        btnMaster.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnMaster, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMaster)
        );

        sidebarPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 328, -1));

        jPanel4.setBackground(new java.awt.Color(61, 115, 80));

        btnTransaksi.setBackground(new java.awt.Color(153, 153, 153));
        btnTransaksi.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnTransaksi.setForeground(new java.awt.Color(153, 153, 153));
        btnTransaksi.setText("Transaksi");
        btnTransaksi.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTransaksi)
        );

        sidebarPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 383, 328, -1));

        jPanel5.setBackground(new java.awt.Color(61, 115, 80));

        btnReport.setBackground(new java.awt.Color(153, 153, 153));
        btnReport.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnReport.setForeground(new java.awt.Color(153, 153, 153));
        btnReport.setText("Rekap");
        btnReport.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReport)
        );

        sidebarPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 328, -1));

        pnlMobil.setBackground(new java.awt.Color(61, 115, 80));

        btnMobil.setBackground(new java.awt.Color(204, 204, 204));
        btnMobil.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnMobil.setForeground(new java.awt.Color(204, 204, 204));
        btnMobil.setText("Data Mobil");
        btnMobil.setToolTipText("");
        btnMobil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMobilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMobilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMobilMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlMobilLayout = new javax.swing.GroupLayout(pnlMobil);
        pnlMobil.setLayout(pnlMobilLayout);
        pnlMobilLayout.setHorizontalGroup(
            pnlMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMobilLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnMobil, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        pnlMobilLayout.setVerticalGroup(
            pnlMobilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMobil, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidebarPanel.add(pnlMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 271, 328, -1));

        pnlSupir.setBackground(new java.awt.Color(61, 115, 80));

        btnSupir.setBackground(new java.awt.Color(204, 204, 204));
        btnSupir.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnSupir.setForeground(new java.awt.Color(204, 204, 204));
        btnSupir.setText("Data Supir");
        btnSupir.setToolTipText("");
        btnSupir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSupirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSupirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSupirMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlSupirLayout = new javax.swing.GroupLayout(pnlSupir);
        pnlSupir.setLayout(pnlSupirLayout);
        pnlSupirLayout.setHorizontalGroup(
            pnlSupirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupirLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnSupir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        pnlSupirLayout.setVerticalGroup(
            pnlSupirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSupir, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidebarPanel.add(pnlSupir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 327, 328, -1));

        pnlTransport.setBackground(new java.awt.Color(61, 115, 80));

        btnTransport.setBackground(new java.awt.Color(204, 204, 204));
        btnTransport.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnTransport.setForeground(new java.awt.Color(204, 204, 204));
        btnTransport.setText("Transport");
        btnTransport.setToolTipText("");
        btnTransport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTransportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTransportMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlTransportLayout = new javax.swing.GroupLayout(pnlTransport);
        pnlTransport.setLayout(pnlTransportLayout);
        pnlTransportLayout.setHorizontalGroup(
            pnlTransportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTransportLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnTransport, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        pnlTransportLayout.setVerticalGroup(
            pnlTransportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTransport, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidebarPanel.add(pnlTransport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 412, 328, -1));

        pnlSparepart.setBackground(new java.awt.Color(61, 115, 80));

        btnSparepart.setBackground(new java.awt.Color(204, 204, 204));
        btnSparepart.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnSparepart.setForeground(new java.awt.Color(204, 204, 204));
        btnSparepart.setText("Sparepart");
        btnSparepart.setToolTipText("");
        btnSparepart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSparepartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSparepartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSparepartMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlSparepartLayout = new javax.swing.GroupLayout(pnlSparepart);
        pnlSparepart.setLayout(pnlSparepartLayout);
        pnlSparepartLayout.setHorizontalGroup(
            pnlSparepartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSparepartLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnSparepart, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        pnlSparepartLayout.setVerticalGroup(
            pnlSparepartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSparepart, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidebarPanel.add(pnlSparepart, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 468, 328, -1));

        pnlGantiOli.setBackground(new java.awt.Color(61, 115, 80));

        btnGantiOli.setBackground(new java.awt.Color(204, 204, 204));
        btnGantiOli.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnGantiOli.setForeground(new java.awt.Color(204, 204, 204));
        btnGantiOli.setText("Ganti Oli");
        btnGantiOli.setToolTipText("");
        btnGantiOli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGantiOliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGantiOliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGantiOliMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlGantiOliLayout = new javax.swing.GroupLayout(pnlGantiOli);
        pnlGantiOli.setLayout(pnlGantiOliLayout);
        pnlGantiOliLayout.setHorizontalGroup(
            pnlGantiOliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGantiOliLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnGantiOli, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        pnlGantiOliLayout.setVerticalGroup(
            pnlGantiOliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGantiOli, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidebarPanel.add(pnlGantiOli, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 524, 328, -1));

        pnlUpahSupir.setBackground(new java.awt.Color(61, 115, 80));

        btnUpahSupir.setBackground(new java.awt.Color(204, 204, 204));
        btnUpahSupir.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnUpahSupir.setForeground(new java.awt.Color(204, 204, 204));
        btnUpahSupir.setText("Upah Supir");
        btnUpahSupir.setToolTipText("");
        btnUpahSupir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpahSupirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpahSupirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpahSupirMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlUpahSupirLayout = new javax.swing.GroupLayout(pnlUpahSupir);
        pnlUpahSupir.setLayout(pnlUpahSupirLayout);
        pnlUpahSupirLayout.setHorizontalGroup(
            pnlUpahSupirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUpahSupirLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnUpahSupir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        pnlUpahSupirLayout.setVerticalGroup(
            pnlUpahSupirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpahSupir, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidebarPanel.add(pnlUpahSupir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 328, -1));

        pnlPenghasilan.setBackground(new java.awt.Color(61, 115, 80));

        btnPenghasilan.setBackground(new java.awt.Color(204, 204, 204));
        btnPenghasilan.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnPenghasilan.setForeground(new java.awt.Color(204, 204, 204));
        btnPenghasilan.setText("Penghasilan");
        btnPenghasilan.setToolTipText("");
        btnPenghasilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPenghasilanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPenghasilanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPenghasilanMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlPenghasilanLayout = new javax.swing.GroupLayout(pnlPenghasilan);
        pnlPenghasilan.setLayout(pnlPenghasilanLayout);
        pnlPenghasilanLayout.setHorizontalGroup(
            pnlPenghasilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPenghasilanLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnPenghasilan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        pnlPenghasilanLayout.setVerticalGroup(
            pnlPenghasilanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPenghasilan, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidebarPanel.add(pnlPenghasilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 328, -1));

        pnlSupp.setBackground(new java.awt.Color(61, 115, 80));

        btnSupp.setBackground(new java.awt.Color(204, 204, 204));
        btnSupp.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnSupp.setForeground(new java.awt.Color(204, 204, 204));
        btnSupp.setText("Data Supplier");
        btnSupp.setToolTipText("");
        btnSupp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuppMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSuppMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSuppMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlSuppLayout = new javax.swing.GroupLayout(pnlSupp);
        pnlSupp.setLayout(pnlSuppLayout);
        pnlSuppLayout.setHorizontalGroup(
            pnlSuppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSuppLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        pnlSuppLayout.setVerticalGroup(
            pnlSuppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSupp, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidebarPanel.add(pnlSupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 328, -1));

        jLayeredPane1.setBackground(new java.awt.Color(242, 233, 242));

        masterPanel.setBackground(new java.awt.Color(242, 233, 242));
        masterPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("WELCOME :)");
        masterPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 150, 60));

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("×");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        masterPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, -1, 20));

        jLayeredPane1.setLayer(masterPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(masterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(masterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void setColor(JPanel panel){
        panel.setBackground(new Color(242,233,242));
    }
    
    void resetColor(JPanel panel){
        panel.setBackground(new Color(61,115,80));
    }
    
    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void btnMobilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMobilMouseClicked
        this.setVisible(false);
        new frmMobil().setVisible(true);
    }//GEN-LAST:event_btnMobilMouseClicked

    private void btnSupirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupirMouseClicked
        this.setVisible(false);
        new frmSupir().setVisible(true);
    }//GEN-LAST:event_btnSupirMouseClicked

    private void btnTransportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransportMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new frmTransport().setVisible(true);
    }//GEN-LAST:event_btnTransportMouseClicked

    private void btnSparepartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSparepartMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new frmSparepart().setVisible(true);
    }//GEN-LAST:event_btnSparepartMouseClicked

    private void btnGantiOliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGantiOliMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new frmOli().setVisible(true);
    }//GEN-LAST:event_btnGantiOliMouseClicked

    private void btnUpahSupirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpahSupirMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new frmUpahSupir().setVisible(true);
    }//GEN-LAST:event_btnUpahSupirMouseClicked

    private void btnPenghasilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPenghasilanMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new frmPenghasilan().setVisible(true);
    }//GEN-LAST:event_btnPenghasilanMouseClicked

    private void btnMobilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMobilMouseEntered
        // TODO add your handling code here:
        setColor(pnlMobil);
    }//GEN-LAST:event_btnMobilMouseEntered

    private void btnSupirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupirMouseEntered
        // TODO add your handling code here:
        setColor(pnlSupir);
    }//GEN-LAST:event_btnSupirMouseEntered

    private void btnTransportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransportMouseEntered
        // TODO add your handling code here:
        setColor(pnlTransport);
    }//GEN-LAST:event_btnTransportMouseEntered

    private void btnSparepartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSparepartMouseEntered
        // TODO add your handling code here:
        setColor(pnlSparepart);
    }//GEN-LAST:event_btnSparepartMouseEntered

    private void btnGantiOliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGantiOliMouseEntered
        // TODO add your handling code here:
        setColor(pnlGantiOli);
    }//GEN-LAST:event_btnGantiOliMouseEntered

    private void btnUpahSupirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpahSupirMouseEntered
        // TODO add your handling code here:
        setColor(pnlUpahSupir);
    }//GEN-LAST:event_btnUpahSupirMouseEntered

    private void btnPenghasilanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPenghasilanMouseEntered
        // TODO add your handling code here:
        setColor(pnlPenghasilan);
    }//GEN-LAST:event_btnPenghasilanMouseEntered

    private void btnMobilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMobilMouseExited
        // TODO add your handling code here:
        resetColor(pnlMobil);
    }//GEN-LAST:event_btnMobilMouseExited

    private void btnSupirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupirMouseExited
        // TODO add your handling code here:
        resetColor(pnlSupir);
    }//GEN-LAST:event_btnSupirMouseExited

    private void btnTransportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransportMouseExited
        // TODO add your handling code here:
        resetColor(pnlTransport);
    }//GEN-LAST:event_btnTransportMouseExited

    private void btnSparepartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSparepartMouseExited
        // TODO add your handling code here:
        resetColor(pnlSparepart);
    }//GEN-LAST:event_btnSparepartMouseExited

    private void btnGantiOliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGantiOliMouseExited
        // TODO add your handling code here:
        resetColor(pnlGantiOli);
    }//GEN-LAST:event_btnGantiOliMouseExited

    private void btnUpahSupirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpahSupirMouseExited
        // TODO add your handling code here:
        resetColor(pnlUpahSupir);
    }//GEN-LAST:event_btnUpahSupirMouseExited

    private void btnPenghasilanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPenghasilanMouseExited
        // TODO add your handling code here:
        resetColor(pnlPenghasilan);
    }//GEN-LAST:event_btnPenghasilanMouseExited

    int xy, xx;
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x = xx, y = xy);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void btnSuppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuppMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new frmSupplier().setVisible(true);
    }//GEN-LAST:event_btnSuppMouseClicked

    private void btnSuppMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuppMouseEntered
        // TODO add your handling code here:
        setColor(pnlSupp);
    }//GEN-LAST:event_btnSuppMouseEntered

    private void btnSuppMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuppMouseExited
        // TODO add your handling code here:
        resetColor(pnlSupp);
    }//GEN-LAST:event_btnSuppMouseExited


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
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnGantiOli;
    private javax.swing.JLabel btnMaster;
    private javax.swing.JLabel btnMobil;
    private javax.swing.JLabel btnPenghasilan;
    private javax.swing.JLabel btnReport;
    private javax.swing.JLabel btnSparepart;
    private javax.swing.JLabel btnSupir;
    private javax.swing.JLabel btnSupp;
    private javax.swing.JLabel btnTransaksi;
    private javax.swing.JLabel btnTransport;
    private javax.swing.JLabel btnUpahSupir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel masterPanel;
    private javax.swing.JPanel pnlGantiOli;
    private javax.swing.JPanel pnlMobil;
    private javax.swing.JPanel pnlPenghasilan;
    private javax.swing.JPanel pnlSparepart;
    private javax.swing.JPanel pnlSupir;
    private javax.swing.JPanel pnlSupp;
    private javax.swing.JPanel pnlTransport;
    private javax.swing.JPanel pnlUpahSupir;
    private javax.swing.JPanel sidebarPanel;
    // End of variables declaration//GEN-END:variables
}
