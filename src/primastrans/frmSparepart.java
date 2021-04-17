/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primastrans;

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
        load_table2();
        kosong();
        textboxOff();
        cmbMobil();
        cmbJenispembayaran();
        barangvisibleOff();
        btnTambahBarang.setVisible(false);
        jPanel9.setVisible(false);
        lblHidden.setVisible(false);
        lblHidden1.setVisible(false);
    }
    
    private void headvisibleOff(){
        cmbJenis.setVisible(false);
        cmbDate.setVisible(false);
        cmbKdmobil.setVisible(false);
        txtToko.setVisible(false);
        txtNote.setVisible(false);
        jLabel14.setVisible(false);
        jLabel8.setVisible(false);
        jLabel7.setVisible(false);
        jLabel13.setVisible(false);
        jLabel11.setVisible(false);
        jSeparator6.setVisible(false);
        jSeparator3.setVisible(false);
        jSeparator5.setVisible(false);
        jSeparator9.setVisible(false);
        jSeparator8.setVisible(false);
        btnBatal.setVisible(false);
        btnSimpan.setVisible(false);
        jPanel4.setVisible(false);   
    }
    
    private void headvisibleOn(){
        cmbJenis.setVisible(true);
        cmbDate.setVisible(true);
        cmbKdmobil.setVisible(true);
        txtToko.setVisible(true);
        txtNote.setVisible(true);
        jLabel14.setVisible(true);
        jLabel8.setVisible(true);
        jLabel7.setVisible(true);
        jLabel13.setVisible(true);
        jLabel11.setVisible(true);
        jSeparator6.setVisible(true);
        jSeparator3.setVisible(true);
        jSeparator5.setVisible(true);
        jSeparator9.setVisible(true);
        jSeparator8.setVisible(true);
    }
    
     private void barangvisibleOff(){
        txtPart.setVisible(false);
        txtHarga.setVisible(false);
        txtQty.setVisible(false);
        txtJumlah.setVisible(false);
        txtTotal.setVisible(false);
        jLabel9.setVisible(false);
        jLabel2.setVisible(false);
        jLabel10.setVisible(false);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        jSeparator1.setVisible(false);
        jSeparator2.setVisible(false);
        jSeparator4.setVisible(false);
        jSeparator7.setVisible(false);
        jSeparator10.setVisible(false);
        btnBatalbarang.setVisible(false);
        btnSimpanbarang.setVisible(false);
        jPanel8.setVisible(false);   
        btnTambahBarang.setVisible(true);
        jPanel9.setVisible(true);
    }
    
    private void barangvisibleOn(){
        txtPart.setVisible(true);
        txtHarga.setVisible(true);
        txtQty.setVisible(true);
        txtJumlah.setVisible(true);
        txtTotal.setVisible(true);
        jLabel9.setVisible(true);
        jLabel2.setVisible(true);
        jLabel10.setVisible(true);
        jLabel15.setVisible(true);
        jLabel16.setVisible(true);
        jSeparator1.setVisible(true);
        jSeparator2.setVisible(true);
        jSeparator4.setVisible(true);
        jSeparator7.setVisible(true);
        jSeparator10.setVisible(true);
        btnBatalbarang.setVisible(true);
        btnSimpanbarang.setVisible(true);
        jPanel8.setVisible(true);
        btnTambahBarang.setVisible(false);
        jPanel9.setVisible(false);
    }
    
    private void kdBelanja() {
        try {
            String sql = "select * from tb_sparepart ORDER BY LENGTH(kd_belanja) desc, kd_belanja desc";
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
    
    private void noDetail() {
        try {
            String sql = "select * from tb_sparepart_detail order by no_detail desc";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet rs=stm.executeQuery(sql);
            if (rs.next()) {
                int kd = Integer.parseInt(rs.getString("no_detail"));
                int AN = kd + 1;
                lblHidden1.setText(""+AN);
            } else {
                lblHidden1.setText("1");
            }

           }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
           }
    }
    
    private void cmbMobil() {
        try { 
            String sql = "select * from tb_mobil ORDER BY LENGTH(kd_mobil) asc, kd_mobil asc";
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
        cmbJenis.addItem("Kredit");
        cmbJenis.addItem("Cash");
    }
    
    private void total(){
        try {
            String sql = "SELECT * FROM tb_sparepart_detail WHERE kd_belanja = '"+txtKd.getText()+"' AND NOT no_detail = '"+lblHidden1.getText()+"' ";
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
                long a = Long.parseLong(txtHarga.getText());
                long b = Long.parseLong(txtQty.getText());
                long d = a * b; 
                txtJumlah.setText(Long.toString(d));     
                
                long c = Long.parseLong(lblHidden.getText());
                long e = d + c;
                txtTotal.setText(Long.toString(e));
                
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
            String sql = "select * from tb_sparepart ORDER BY LENGTH(kd_belanja) ASC, kd_belanja ASC";
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
    
    private void load_table2(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Belanja");
        model.addColumn("Nama Part");
        model.addColumn("Harga");
        model.addColumn("QTY");
        model.addColumn("Jumlah");
        model.addColumn("No Barang");
        
        //menampilkan data database kedalam tabel
        try {
            String sql = "select * from tb_sparepart_detail where kd_belanja ='"+txtKd.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)});
            }
            jTable2.setModel(model);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnHapusBarang = new javax.swing.JLabel();
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
        jPanel4 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JLabel();
        btnBatal = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtNote = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnSimpanbarang = new javax.swing.JLabel();
        btnBatalbarang = new javax.swing.JLabel();
        lblHidden1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnTambahBarang = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(229, 152, 60));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(248, 246, 233));
        jLabel1.setText("DATA BELANJA SPAREPART");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        jPanel7.setBackground(new java.awt.Color(229, 152, 60));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(248, 246, 233)));
        jPanel7.setToolTipText("");

        btnBaru.setBackground(new java.awt.Color(204, 204, 204));
        btnBaru.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBaru.setForeground(new java.awt.Color(248, 246, 233));
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

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        jPanel6.setBackground(new java.awt.Color(229, 152, 60));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(248, 246, 233)));
        jPanel6.setToolTipText("");

        btnCetak.setBackground(new java.awt.Color(204, 204, 204));
        btnCetak.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(248, 246, 233));
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

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 410, -1, -1));

        jPanel5.setBackground(new java.awt.Color(229, 152, 60));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(248, 246, 233)));
        jPanel5.setToolTipText("");

        btnHapus.setBackground(new java.awt.Color(204, 204, 204));
        btnHapus.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(248, 246, 233));
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

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, -1, -1));

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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

        cmbMobil.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel1.add(cmbMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 366, 60, 30));

        cmbDari.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(cmbDari, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 409, 130, 30));

        cmbSampai.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(cmbSampai, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, 130, 30));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(248, 246, 233));
        jLabel3.setText("Sampai");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 470, -1, -1));

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(248, 246, 233));
        jLabel6.setText("Mobil");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, -1, -1));

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(248, 246, 233));
        jLabel12.setText("Dari");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 420, -1, -1));

        jTable2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 370, 240));

        jLabel17.setBackground(new java.awt.Color(248, 246, 233));
        jLabel17.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(248, 246, 233));
        jLabel17.setText("?");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, 20));

        jLabel4.setBackground(new java.awt.Color(248, 246, 233));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(248, 246, 233));
        jLabel4.setText("×");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, 20));

        jPanel10.setBackground(new java.awt.Color(229, 152, 60));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(248, 246, 233)));
        jPanel10.setToolTipText("");

        btnHapusBarang.setBackground(new java.awt.Color(204, 204, 204));
        btnHapusBarang.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnHapusBarang.setForeground(new java.awt.Color(248, 246, 233));
        btnHapusBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHapusBarang.setText("Hapus Barang");
        btnHapusBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusBarangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, -1, -1));

        jPanel2.setBackground(new java.awt.Color(248, 246, 233));
        jPanel2.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Kode Belanja");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        txtKd.setBackground(new java.awt.Color(248, 246, 233));
        txtKd.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtKd.setForeground(new java.awt.Color(102, 102, 102));
        txtKd.setText("Kode");
        txtKd.setBorder(null);
        jPanel2.add(txtKd, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 210, 30));

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Nama Part");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, -1, -1));

        txtPart.setBackground(new java.awt.Color(248, 246, 233));
        txtPart.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
        jPanel2.add(txtPart, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, 210, 30));

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator2.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 210, 10));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Harga");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, -1, -1));

        txtHarga.setBackground(new java.awt.Color(248, 246, 233));
        txtHarga.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
        jPanel2.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 210, 30));

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 210, 10));

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Qty");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, -1, -1));

        txtQty.setBackground(new java.awt.Color(248, 246, 233));
        txtQty.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
        jPanel2.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 220, 210, 30));

        jSeparator4.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator4.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 250, 210, 10));

        lblHidden.setBackground(new java.awt.Color(102, 102, 102));
        lblHidden.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHidden.setForeground(new java.awt.Color(102, 102, 102));
        lblHidden.setText("hidden");
        lblHidden.setEnabled(false);
        jPanel2.add(lblHidden, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        txtTotal.setBackground(new java.awt.Color(248, 246, 233));
        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(102, 102, 102));
        txtTotal.setText("Total Harga Keseluruhan");
        txtTotal.setBorder(null);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel2.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, 210, 30));

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator7.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 350, 210, 10));

        jLabel14.setBackground(new java.awt.Color(102, 102, 102));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Jenis Pembayaran");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        cmbJenis.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel2.add(cmbJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 100, 30));

        jSeparator6.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator6.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 100, 10));

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Tanggal");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        cmbDate.setBackground(new java.awt.Color(242, 233, 242));
        cmbDate.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(cmbDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 210, -1));

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator3.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 210, 10));

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Kode Mobil");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        cmbKdmobil.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel2.add(cmbKdmobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, 30));

        jSeparator5.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator5.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 40, 10));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Toko");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        txtToko.setBackground(new java.awt.Color(248, 246, 233));
        txtToko.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
        jPanel2.add(txtToko, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 210, 30));

        jSeparator9.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator9.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 210, 10));

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Note");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        jSeparator8.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator8.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 210, 10));

        jPanel4.setBackground(new java.awt.Color(248, 246, 233));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setToolTipText("");

        btnSimpan.setBackground(new java.awt.Color(102, 102, 102));
        btnSimpan.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
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

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, -1, 40));

        btnBatal.setBackground(new java.awt.Color(102, 102, 102));
        btnBatal.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(102, 102, 102));
        btnBatal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBatal.setText("Batal");
        btnBatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBatalMouseClicked(evt);
            }
        });
        jPanel2.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 102, 38));

        txtJumlah.setBackground(new java.awt.Color(248, 246, 233));
        txtJumlah.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
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
        jPanel2.add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 270, 210, 30));

        jLabel15.setBackground(new java.awt.Color(102, 102, 102));
        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Jumlah");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, -1, -1));

        jSeparator10.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator10.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, 210, 10));

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Total Harga");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, -1, -1));

        txtNote.setBackground(new java.awt.Color(248, 246, 233));
        txtNote.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNote.setForeground(new java.awt.Color(102, 102, 102));
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
        jPanel2.add(txtNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 210, 30));

        jPanel8.setBackground(new java.awt.Color(248, 246, 233));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel8.setToolTipText("");

        btnSimpanbarang.setBackground(new java.awt.Color(102, 102, 102));
        btnSimpanbarang.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnSimpanbarang.setForeground(new java.awt.Color(102, 102, 102));
        btnSimpanbarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSimpanbarang.setText("Simpan");
        btnSimpanbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanbarangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSimpanbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSimpanbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 390, -1, 40));

        btnBatalbarang.setBackground(new java.awt.Color(102, 102, 102));
        btnBatalbarang.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnBatalbarang.setForeground(new java.awt.Color(102, 102, 102));
        btnBatalbarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBatalbarang.setText("Batal");
        btnBatalbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBatalbarangMouseClicked(evt);
            }
        });
        jPanel2.add(btnBatalbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, 102, 38));

        lblHidden1.setBackground(new java.awt.Color(102, 102, 102));
        lblHidden1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblHidden1.setForeground(new java.awt.Color(102, 102, 102));
        lblHidden1.setText("hidden");
        lblHidden1.setEnabled(false);
        jPanel2.add(lblHidden1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, -1, -1));

        jPanel9.setBackground(new java.awt.Color(248, 246, 233));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel9.setToolTipText("");

        btnTambahBarang.setBackground(new java.awt.Color(102, 102, 102));
        btnTambahBarang.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnTambahBarang.setForeground(new java.awt.Color(102, 102, 102));
        btnTambahBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTambahBarang.setText("+ Barang");
        btnTambahBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahBarangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTambahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTambahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBaruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBaruMouseClicked
        textboxOn();
        kosong();
        kdBelanja();
        noDetail();
        headvisibleOn();
        barangvisibleOn();
        btnTambahBarang.setVisible(false);
        jPanel9.setVisible(false);
        lblHidden.setText("0");
        jPanel8.setVisible(false);
        btnSimpanbarang.setVisible(false);
        btnBatalbarang.setVisible(false);
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
            load_table2();
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
        txtPart.setText("Nama Sparepart");
        txtHarga.setText("0");
        txtQty.setText("0");
        txtJumlah.setText("0");
        noDetail();
        headvisibleOn();
        barangvisibleOff();
        textboxOn();
        total();
        load_table2();
        btnTambahBarang.setVisible(true);
        jPanel9.setVisible(true);
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
                String sql ="UPDATE tb_sparepart SET kd_belanja = '"+txtKd.getText()+"',kd_mobil = '"+cmbKdmobil.getSelectedItem()+"', tgl = '"+Date_Format.format(cmbDate.getDate())+"', toko = '"+txtToko.getText()+"', total = '"+txtTotal.getText()+"', jenis_bayar = '"+cmbJenis.getSelectedItem()+"', note = '"+txtNote.getText()+"' WHERE kd_belanja = '"+txtKd.getText()+"'";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "data berhasil di Edit");
            }else{
                String sql = "INSERT INTO tb_sparepart VALUES ('"+txtKd.getText()+"','"+cmbKdmobil.getSelectedItem()+"','"+Date_Format.format(cmbDate.getDate())+"','"+txtToko.getText()+"','"+txtTotal.getText()+"','"+cmbJenis.getSelectedItem()+"','"+txtNote.getText()+"')";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
                String sql2 = "INSERT INTO tb_sparepart_detail VALUES ('"+txtKd.getText()+"','"+txtPart.getText()+"','"+txtHarga.getText()+"','"+txtQty.getText()+"','"+txtJumlah.getText()+"','"+Integer.parseInt(lblHidden1.getText())+"')";
                java.sql.PreparedStatement pst1=conn.prepareStatement(sql2);
                pst1.execute();
                
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
        textboxOff();
        kosong();
        barangvisibleOff();
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

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Form Sparepart.\n - Mengisikan data: Klik 'Baru' lalu form akan tersedia untuk diisi.\n - Mengubah data: Piih data lalu klik pada tabel, data akan muncul di form untuk diubah.\n - Menghapus data: Piih data lalu klik pada tabel, lalu tekan tombol 'Hapus' data akan terhapus.\n - Mencetak laporan: Pilih kode mobil lalu masukan tanggal dari dan sampai lalu pilih 'Cetak'");
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int baris = jTable2.rowAtPoint(evt.getPoint());
        String kd =jTable2.getValueAt(baris, 0).toString();
        txtKd.setText(kd);
        String part =jTable2.getValueAt(baris, 1).toString();
        txtPart.setText(part);
        String harga =jTable2.getValueAt(baris, 2).toString();
        txtHarga.setText(harga);
        String qty =jTable2.getValueAt(baris, 3).toString();
        txtQty.setText(qty);
        String jumlah =jTable2.getValueAt(baris, 4).toString();
        txtJumlah.setText(jumlah);
        String nodet =jTable2.getValueAt(baris, 5).toString();
        lblHidden1.setText(nodet);
        headvisibleOff();
        barangvisibleOn();
        total();
    }//GEN-LAST:event_jTable2MouseClicked

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

    private void btnSimpanbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanbarangMouseClicked
        try {
            String query = "SELECT * FROM tb_sparepart_detail WHERE kd_belanja = '"+txtKd.getText()+"' AND no_detail = '"+lblHidden1.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(query);
            if(res.next() == true){
                String sql ="UPDATE tb_sparepart SET total = '"+txtTotal.getText()+"' WHERE kd_belanja = '"+txtKd.getText()+"'";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();

                String sql1 ="UPDATE tb_sparepart_detail SET nm_part = '"+txtPart.getText()+"',harga = '"+txtHarga.getText()+"',qty = '"+txtQty.getText()+"',jumlah = '"+txtJumlah.getText()+"' WHERE kd_belanja = '"+txtKd.getText()+"' AND no_detail = '"+lblHidden1.getText()+"'";
                java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Update Berhasil");
            }else{
                String sql ="UPDATE tb_sparepart SET total = '"+txtTotal.getText()+"' WHERE kd_belanja = '"+txtKd.getText()+"'";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
                String sql2 = "INSERT INTO tb_sparepart_detail VALUES ('"+txtKd.getText()+"','"+txtPart.getText()+"','"+txtHarga.getText()+"','"+txtQty.getText()+"','"+txtJumlah.getText()+"','"+Integer.parseInt(lblHidden1.getText())+"')";
                java.sql.PreparedStatement pst1=conn.prepareStatement(sql2);
                pst1.execute();
            }
            load_table();
            load_table2();
            noDetail();
        } catch (SQLException ex) {
            Logger.getLogger(frmSparepart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSimpanbarangMouseClicked

    private void btnBatalbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalbarangMouseClicked
        headvisibleOn();
        barangvisibleOff();
        btnTambahBarang.setVisible(true);
        jPanel9.setVisible(true);
    }//GEN-LAST:event_btnBatalbarangMouseClicked

    private void btnTambahBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahBarangMouseClicked
        // TODO add your handling code here:
        barangvisibleOn();
    }//GEN-LAST:event_btnTambahBarangMouseClicked
    int xy, xx;
    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void btnHapusBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusBarangMouseClicked
        int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini?", "Penghapusan Data", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION){
            try {
                String sql ="DELETE FROM tb_sparepart_detail where no_detail='"+lblHidden1.getText()+"'";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            load_table();
            load_table2();
            kosong();
        }else{
            JOptionPane.showMessageDialog(null, "Data batal dihapus.");
        }
    }//GEN-LAST:event_btnHapusBarangMouseClicked

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
    private javax.swing.JLabel btnBatalbarang;
    private javax.swing.JLabel btnCetak;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnHapusBarang;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JLabel btnSimpanbarang;
    private javax.swing.JLabel btnTambahBarang;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblHidden;
    private javax.swing.JLabel lblHidden1;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKd;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtPart;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtToko;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
