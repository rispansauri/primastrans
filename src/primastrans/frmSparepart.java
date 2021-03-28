/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primastrans;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author chevi
 */
public class frmSparepart extends javax.swing.JFrame {

    /**
     * Creates new form frmSparepart
     */
    public frmSparepart() {
        initComponents();
        this.setLocationRelativeTo(null); 
        load_table();
        kosong();
        textboxOff();
        cmbMobil();
        cmbJenispembayaran();
    }

    private void kdBelanja() {
        try {
            String sql = "select * from tb_sparepart order by kd_belanja desc";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet rs=stm.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("kd_belanja").substring(3);
                String AN = "" + (Integer.parseInt(kd) + 1);
                txtKd.setText("SPT" + AN);
            } else {
                txtKd.setText("SPT1");
            }

           }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
           }
    }
    
    private void cmbMobil() {
        try { 
            String sql = "select * from tb_mobil";
            java.sql.Connection conn=Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                cmbKdmobil.addItem(res.getString("kd_mobil"));
                cmbMobil.addItem(res.getString("kd_mobil"));
            }
        }catch (Exception e) {
            
        }
        
    }
    
    private void cmbJenispembayaran(){
        cmbJenis.addItem("Cash");
        cmbJenis.addItem("Kredit");
    }
    
    private void total(){
        try {
            String sql = "SELECT * FROM tb_sparepart_detail WHERE kd_belanja = '"+txtKd.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            long total = 0;
            while(res.next()){
                long amount = Long.parseLong(res.getString("jumlah"));
                total += amount;
            }
            txtTotal.setText(""+total);
            lblHidden.setText(""+total);
        } catch (SQLException ex) {
            Logger.getLogger(frmSparepart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void jumlahtext(){
//        try {
//            String sql = "SELECT * FROM tb_sparepart_detail WHERE kd_belanja = '"+txtKd.getText()+"'";
//            java.sql.Connection conn=(Connection)Config.configDB();
//            java.sql.Statement stm=conn.createStatement();
//            java.sql.ResultSet res=stm.executeQuery(sql);
//            long total = 0;
//            if(res.next()){
//                while(res.next()){
//                    long amount = Long.parseLong(res.getString("jumlah"));
//                    total += amount;
//                }
//                lblHidden.setText(Long.toString(total));
                
                long a = Long.parseLong(txtHarga.getText());
                long b = Long.parseLong(txtQty.getText());
                long d = a * b; 
                txtJumlah.setText(Long.toString(d));     
                
                long c = Long.parseLong(lblHidden.getText());
                long e = d + c;
                txtTotal.setText(Long.toString(e));
                
//            }else{
//                long a = Long.parseLong(txtHarga.getText());
//                long b = Long.parseLong(txtQty.getText());
//                a *= b;
//                txtJumlah.setText(Long.toString(a));
//                txtTotal.setText(Long.toString(a));
//            }  
//        } catch (SQLException ex) {
//            Logger.getLogger(frmSparepart.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        BigDecimal a = new BigDecimal(txtHarga.getText());
//        BigDecimal b = new BigDecimal(txtQty.getText());
//        BigDecimal c = new BigDecimal(lblHidden.getText());
//        BigDecimal d = a.multiply(b);
//        BigDecimal e = c.add(d);
//        txtJumlah.setText(""+d);
//        txtTotal.setText(""+e);
    }
    
    
    private void textboxOn() {
        txtHarga.setEditable(true);
        txtPart.setEditable(true);
        txtToko.setEditable(true);
        txtQty.setEditable(true);
        txtNote.setEditable(true);
        cmbDate.setEnabled(true);
        cmbKdmobil.setEnabled(true);
        cmbJenis.setEnabled(true);
        jPanel4.setVisible(true);
        btnSimpan.setVisible(true);
        btnBatal.setVisible(true);
    }
    
    private void textboxOff() {
        txtKd.setEnabled(false);
        txtTotal.setEnabled(false);
        txtJumlah.setEnabled(false);
        txtHarga.setEditable(false);
        txtPart.setEditable(false);
        txtToko.setEditable(false);
        txtQty.setEditable(false);
        txtNote.setEditable(false);
        cmbDate.setEnabled(false);
        cmbKdmobil.setEnabled(false);
        cmbJenis.setEnabled(false);
        jPanel4.setVisible(false);
        btnSimpan.setVisible(false);
        btnBatal.setVisible(false);
    }
    
    private void kosong(){
        txtKd.setText("");
        txtHarga.setText("0");
        txtPart.setText("Nama Sparepart");
        txtJumlah.setText("0");
        txtTotal.setText("0");
        txtToko.setText("Nama Toko"); 
        txtQty.setText("0");       
        txtNote.setText("Note");  
        cmbDate.setDate(null);
        cmbKdmobil.setSelectedItem(null);
        cmbJenis.setSelectedItem(null);
        lblHidden.setVisible(false);
    }
    
    private void kosong2(){
        txtPart.setText("");
        txtHarga.setText("0");
        txtQty.setText("0");
        txtJumlah.setText("0");
        txtTotal.setText("0");
        txtToko.setEditable(false);        
        txtNote.setEditable(false);
        cmbDate.setEnabled(false);
        cmbKdmobil.setEnabled(false);
        cmbJenis.setEnabled(false);
    }
    
    private void load_table(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Belanja");
        model.addColumn("Kode Mobil");
        model.addColumn("Tanggal");
        model.addColumn("Toko");
        model.addColumn("Total Pembelian");
        model.addColumn("Jenis Pembayaran");
        model.addColumn("Note");
        
        //menampilkan data database kedalam tabel
        try {
            String sql = "select * from tb_sparepart";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7)});
            }
            jTable1.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
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
        jPanel7 = new javax.swing.JPanel();
        btnBaru = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnCetak = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnHapus = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmbMobil = new javax.swing.JComboBox<>();
        cmbDari = new com.toedter.calendar.JDateChooser();
        cmbSampai = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtKd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPart = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lblHidden = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        cmbJenis = new javax.swing.JComboBox<>();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        cmbDate = new com.toedter.calendar.JDateChooser();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        cmbKdmobil = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtToko = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JLabel();
        btnBatal = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        txtJumlah = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(61, 115, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("DATA BELANJA SPAREPART");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

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

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

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

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, -1, -1));

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

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, -1, -1));

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
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 111, 620, 236));
        jPanel1.add(cmbMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 60, -1));

        cmbDari.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(cmbDari, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 130, -1));

        cmbSampai.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(cmbSampai, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, 130, -1));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Sampai");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, -1, -1));

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Mobil");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, -1, -1));

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Dari");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, -1, -1));

        jPanel2.setBackground(new java.awt.Color(242, 233, 242));
        jPanel2.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Kode Belanja");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        txtKd.setBackground(new java.awt.Color(242, 233, 242));
        txtKd.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtKd.setForeground(new java.awt.Color(102, 102, 102));
        txtKd.setText("Kode");
        txtKd.setBorder(null);
        jPanel2.add(txtKd, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 210, 30));

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Nama Part");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtPart.setBackground(new java.awt.Color(242, 233, 242));
        txtPart.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPart.setForeground(new java.awt.Color(102, 102, 102));
        txtPart.setText("Nama Sparepart");
        txtPart.setBorder(null);
        txtPart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPartFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPartFocusLost(evt);
            }
        });
        jPanel2.add(txtPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 210, 30));

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator2.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 210, 10));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Harga");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        txtHarga.setBackground(new java.awt.Color(242, 233, 242));
        txtHarga.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtHarga.setForeground(new java.awt.Color(102, 102, 102));
        txtHarga.setText("Harga Satuan");
        txtHarga.setBorder(null);
        txtHarga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHargaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHargaFocusLost(evt);
            }
        });
        txtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHargaKeyReleased(evt);
            }
        });
        jPanel2.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 210, 30));

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 210, 10));

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Qty");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        txtQty.setBackground(new java.awt.Color(242, 233, 242));
        txtQty.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtQty.setForeground(new java.awt.Color(102, 102, 102));
        txtQty.setText("Jumlah Barang");
        txtQty.setBorder(null);
        txtQty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQtyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQtyFocusLost(evt);
            }
        });
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
        });
        jPanel2.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 210, 30));

        jSeparator4.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator4.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 210, 10));

        lblHidden.setBackground(new java.awt.Color(102, 102, 102));
        lblHidden.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHidden.setForeground(new java.awt.Color(102, 102, 102));
        lblHidden.setText("hidden");
        lblHidden.setEnabled(false);
        jPanel2.add(lblHidden, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

        txtTotal.setBackground(new java.awt.Color(242, 233, 242));
        txtTotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(102, 102, 102));
        txtTotal.setText("Total Harga Keseluruhan");
        txtTotal.setBorder(null);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel2.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 210, 30));

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator7.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 210, 10));

        jLabel14.setBackground(new java.awt.Color(102, 102, 102));
        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Jenis Pembayaran");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        cmbJenis.setBackground(new java.awt.Color(242, 233, 242));
        jPanel2.add(cmbJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 60, 30));

        jSeparator6.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator6.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 60, 10));

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Tanggal");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));

        cmbDate.setBackground(new java.awt.Color(242, 233, 242));
        cmbDate.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(cmbDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 210, -1));

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator3.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 210, 10));

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Kode Mobil");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, -1, -1));

        jPanel2.add(cmbKdmobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, -1, 30));

        jSeparator5.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator5.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 40, 10));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Toko");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        txtToko.setBackground(new java.awt.Color(242, 233, 242));
        txtToko.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtToko.setForeground(new java.awt.Color(102, 102, 102));
        txtToko.setText("Nama Toko");
        txtToko.setBorder(null);
        txtToko.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTokoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTokoFocusLost(evt);
            }
        });
        jPanel2.add(txtToko, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 210, 30));

        jSeparator9.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator9.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 210, 10));

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Note");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, -1, -1));

        jSeparator8.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator8.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, 210, 10));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("×");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, -1, 20));

        jPanel4.setBackground(new java.awt.Color(242, 233, 242));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setToolTipText("");

        btnSimpan.setBackground(new java.awt.Color(102, 102, 102));
        btnSimpan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(102, 102, 102));
        btnSimpan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSimpan.setText("Simpan");
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, -1, 40));

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
        jPanel2.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 102, 38));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtNote.setBackground(new java.awt.Color(242, 233, 242));
        txtNote.setColumns(20);
        txtNote.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNote.setForeground(new java.awt.Color(102, 102, 102));
        txtNote.setRows(5);
        txtNote.setText("Note");
        txtNote.setBorder(null);
        txtNote.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoteFocusLost(evt);
            }
        });
        jScrollPane3.setViewportView(txtNote);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, 210, 29));

        txtJumlah.setBackground(new java.awt.Color(242, 233, 242));
        txtJumlah.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtJumlah.setForeground(new java.awt.Color(102, 102, 102));
        txtJumlah.setText("Harga Barang");
        txtJumlah.setBorder(null);
        txtJumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtJumlahFocusGained(evt);
            }
        });
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        jPanel2.add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 210, 30));

        jLabel15.setBackground(new java.awt.Color(102, 102, 102));
        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Jumlah");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        jSeparator10.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator10.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 210, 10));

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Total Harga");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        jLabel17.setBackground(new java.awt.Color(204, 204, 204));
        jLabel17.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("?");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBaruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBaruMouseClicked
        textboxOn();
        kosong();
        kdBelanja();
        lblHidden.setText("0");
    }//GEN-LAST:event_btnBaruMouseClicked

    private void btnCetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseClicked
                    HashMap param = new HashMap();
                    param.put("mobil",cmbMobil.getSelectedItem());
                    param.put("dari",cmbDari.getDate());
                    param.put("sampai",cmbSampai.getDate());
                try {
                        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("rptSparepart.jasper"), param, Config.configDB());
                        JasperViewer.viewReport(jp, false);
                        
                    } catch(Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }
    }//GEN-LAST:event_btnCetakMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini?", "Penghapusan Data", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION){
            try {
                String sql ="DELETE FROM tb_sparepart where kd_belanja='"+txtKd.getText()+"'";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            load_table();
            kosong();
        }else{
            JOptionPane.showMessageDialog(null, "Data batal dihapus.");
        }
    }//GEN-LAST:event_btnHapusMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String kdbelanja =jTable1.getValueAt(baris, 0).toString();
        txtKd.setText(kdbelanja);
        String cmbmobil = jTable1.getValueAt(baris,1).toString();
        cmbKdmobil.setSelectedItem(cmbmobil);
        String tgl = jTable1.getValueAt(baris, 2).toString();
        try {
            java.util.Date date;
            date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
            cmbDate.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(frmOli.class.getName()).log(Level.SEVERE, null, ex);
        }
        String toko = jTable1.getValueAt(baris, 3).toString();
        txtToko.setText(toko);
        String jenis = jTable1.getValueAt(baris, 5).toString();
        cmbJenis.setSelectedItem(jenis);
        String note = jTable1.getValueAt(baris, 6).toString();
        txtNote.setText(note);
        textboxOn();
        total();
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtPartFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPartFocusGained
        // TODO add your handling code here:
         if (txtPart.getText().equals("Nama Sparepart")){
        txtPart.setText("");
         }
    }//GEN-LAST:event_txtPartFocusGained

    private void txtHargaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaFocusGained
        // TODO add your handling code here:
        if (txtHarga.getText().equals("0")){
        txtHarga.setText("");
         }
    }//GEN-LAST:event_txtHargaFocusGained

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        try{
            String query = "SELECT * FROM tb_sparepart WHERE kd_belanja = '"+txtKd.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(query);
            SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
            if(res.next() == true){
                if(txtPart.getText().equals("") && txtHarga.getText().equals("") && txtQty.getText().equals("")){
                    String sql ="UPDATE tb_sparepart SET kd_belanja = '"+txtKd.getText()+"',kd_mobil = '"+cmbKdmobil.getSelectedItem()+"', tgl = '"+Date_Format.format(cmbDate.getDate())+"', toko = '"+txtToko.getText()+"', total = '"+txtTotal.getText()+"', jenis_bayar = '"+cmbJenis.getSelectedItem()+"', note = '"+txtNote.getText()+"' WHERE kd_belanja = '"+txtKd.getText()+"'";
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "data berhasil di Edit");
                }else{
                    String sql2 = "INSERT INTO tb_sparepart_detail VALUES ('"+txtKd.getText()+"','"+txtPart.getText()+"','"+txtHarga.getText()+"','"+txtQty.getText()+"','"+txtJumlah.getText()+"')";
                    java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
                    pst2.execute();
                    String sql ="UPDATE tb_sparepart SET total = '"+txtTotal.getText()+"' WHERE kd_belanja = '"+txtKd.getText()+"'";
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "data berhasil di tambahkan");
                }
            }else{
                String sql = "INSERT INTO tb_sparepart VALUES ('"+txtKd.getText()+"','"+cmbKdmobil.getSelectedItem()+"','"+Date_Format.format(cmbDate.getDate())+"','"+txtToko.getText()+"','"+txtTotal.getText()+"','"+cmbJenis.getSelectedItem()+"','"+txtNote.getText()+"')";
                String sql2 = "INSERT INTO tb_sparepart_detail VALUES ('"+txtKd.getText()+"','"+txtPart.getText()+"','"+txtHarga.getText()+"','"+txtQty.getText()+"','"+txtJumlah.getText()+"')";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
                pst.execute();
                pst2.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan data berhasil");
            }
            load_table();
            kosong2();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new mainMenu().setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnBatalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseClicked
        // TODO add your handling code here:
        textboxOff();
        kosong();
    }//GEN-LAST:event_btnBatalMouseClicked

    private void txtQtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtyFocusGained
        // TODO add your handling code here:
        if (txtQty.getText().equals("0")){
        txtQty.setText("");
         }
    }//GEN-LAST:event_txtQtyFocusGained

    private void txtTokoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTokoFocusGained
        // TODO add your handling code here:
        if (txtToko.getText().equals("Nama Toko")){
        txtToko.setText("");
         }
    }//GEN-LAST:event_txtTokoFocusGained

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased
        jumlahtext();
    }//GEN-LAST:event_txtQtyKeyReleased

    private void txtHargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaKeyReleased
        jumlahtext();
    }//GEN-LAST:event_txtHargaKeyReleased

    private void txtJumlahFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtJumlahFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahFocusGained

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtNoteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoteFocusGained
        // TODO add your handling code here:
        if (txtNote.getText().equals("Note")){
        txtNote.setText("");
         }
    }//GEN-LAST:event_txtNoteFocusGained

    private void txtPartFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPartFocusLost
        // TODO add your handling code here:
        if (txtPart.getText().equals("")){
        txtPart.setText("Nama Sparepart");
         }
    }//GEN-LAST:event_txtPartFocusLost

    private void txtHargaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaFocusLost
        // TODO add your handling code here:
        if (txtHarga.getText().equals("")){
        txtHarga.setText("0");
         }
    }//GEN-LAST:event_txtHargaFocusLost

    private void txtQtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtyFocusLost
        // TODO add your handling code here:
        if (txtQty.getText().equals("")){
        txtQty.setText("0");
         }
    }//GEN-LAST:event_txtQtyFocusLost

    private void txtTokoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTokoFocusLost
        // TODO add your handling code here:
        if (txtToko.getText().equals("")){
        txtToko.setText("Nama Toko");
         }
    }//GEN-LAST:event_txtTokoFocusLost

    private void txtNoteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoteFocusLost
        // TODO add your handling code here:
        if (txtNote.getText().equals("")){
        txtNote.setText("Note");
         }
    }//GEN-LAST:event_txtNoteFocusLost

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Form Sparepart.\n - Mengisikan data: Klik 'Baru' lalu form akan tersedia untuk diisi.\n - Mengubah data: Piih data lalu klik pada tabel, data akan muncul di form untuk diubah.\n - Menghapus data: Piih data lalu klik pada tabel, lalu tekan tombol 'Hapus' data akan terhapus.\n - Mencetak laporan: Pilih kode mobil lalu masukan tanggal dari dan sampai lalu pilih 'Cetak'");
    }//GEN-LAST:event_jLabel17MouseClicked

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
            java.util.logging.Logger.getLogger(frmSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSparepart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSparepart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBaru;
    private javax.swing.JLabel btnBatal;
    private javax.swing.JLabel btnCetak;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnSimpan;
    private com.toedter.calendar.JDateChooser cmbDari;
    private com.toedter.calendar.JDateChooser cmbDate;
    private javax.swing.JComboBox<String> cmbJenis;
    private javax.swing.JComboBox<String> cmbKdmobil;
    private javax.swing.JComboBox<String> cmbMobil;
    private com.toedter.calendar.JDateChooser cmbSampai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblHidden;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKd;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtPart;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtToko;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
