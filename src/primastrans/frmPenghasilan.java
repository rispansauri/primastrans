/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primastrans;

/**
 *
 * @author R
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
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

public class frmPenghasilan extends javax.swing.JFrame {

    /**
     * Creates new form frmPenghasilan
     */
    public frmPenghasilan() {
        initComponents();
        this.setLocationRelativeTo(null);
        load_table();
        kosong();
        textboxOff();
        cmbMobil();
        
        cmbKdMobil.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    String month;
                    String year;
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                    java.util.Date date1 = cmbDate.getDate();
                    java.util.Date date2 = cmbDate.getDate();
                    month = sdf1.format(date1);
                    year = sdf2.format(date2);
                    String q1 = "select * from tb_penghasilan where kd_mobil = '"+cmbKdMobil.getSelectedItem()+"' AND MONTH(tgl) = '"+month+"' AND YEAR(tgl) = '"+year+"'";
                    java.sql.Connection con=(Connection)Config.configDB();
                    java.sql.Statement st=con.createStatement();
                    java.sql.ResultSet rs=st.executeQuery(q1);
                    
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Penghasilan mobil "+cmbKdMobil.getSelectedItem()+" pada bulan "+month+" tahun "+year+" sudah terdaftar.\nMemasuki mode edit");
                        txtKdPenghasilan.setText(rs.getString(1));
                        cmbDate.setDate(rs.getDate(2));
                        txtBatubara.setText(rs.getString(4));
                        txtRitBatubara.setText(rs.getString(5));
                        txtSplit.setText(rs.getString(6));
                        txtBeskos.setText(rs.getString(7));
                        txtRitBeskos.setText(rs.getString(8));
                        txtLoading.setText(rs.getString(9));
                        txtJumlah.setText(rs.getString(10));
                        txtSparepart.setText(rs.getString(11));
                        txtTotal.setText(rs.getString(12));
                        txtLabaKotor.setText(rs.getString(13));
                        txtLabaBersih.setText(rs.getString(14));
                    }else{
                        getTransport();
                        getSparepart();
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
    }
    
    
    private void getLaba(){
        long a = Long.parseLong(txtJumlah.getText());
        long b = Long.parseLong(txtRitBatubara.getText());
        long c = Long.parseLong(txtRitBeskos.getText());
        long d = a /(b+c); 
        txtLabaKotor.setText(Long.toString(d));
        
        long e = Long.parseLong(txtTotal.getText());
        long f = e / (b+c); 
        txtLabaBersih.setText(Long.toString(f));
    }
    
    private void getTotal(){
        long a = Long.parseLong(txtJumlah.getText());
        long b = Long.parseLong(txtSparepart.getText());
        long c = a - b; 
        txtTotal.setText(Long.toString(c));
    }
    
    private void matjumlah(){
        long a = Long.parseLong(txtBatubara.getText());
        long b = Long.parseLong(txtSplit.getText());
        long c = Long.parseLong(txtBeskos.getText());
        long d = Long.parseLong(txtLoading.getText());
        long e = a + b + c + d; 
        txtJumlah.setText(Long.toString(e));
    }
    
    private void getSparepart(){
        try {
            String month;
            String year;
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            java.util.Date date1 = cmbDate.getDate();
            java.util.Date date2 = cmbDate.getDate();
            month = sdf1.format(date1);
            year = sdf2.format(date2);
            
            String sql = "SELECT * FROM tb_sparepart WHERE kd_mobil = '"+cmbKdMobil.getSelectedItem()+"' AND MONTH(tgl) = '"+month+"' AND YEAR(tgl) = '"+year+"' ";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            long total = 0;
            while(res.next()){
                long amount = Long.parseLong(res.getString("total"));
                total += amount;
            }
            txtSparepart.setText(""+total);
        } catch (SQLException ex) {
            Logger.getLogger(frmSparepart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getTransport(){
        try {
            String month;
            String year;
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            java.util.Date date1 = cmbDate.getDate();
            java.util.Date date2 = cmbDate.getDate();
            month = sdf1.format(date1);
            year = sdf2.format(date2);
            
            String sql = "SELECT * FROM tb_transport WHERE kd_mobil = '"+cmbKdMobil.getSelectedItem()+"' AND MONTH(tgl_muat) = '"+month+"' AND YEAR(tgl_muat) = '"+year+"' ";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            long total = 0;
            while(res.next()){
                long amount = Long.parseLong(res.getString("cashin_primas"));
                total += amount;
            }
            txtBatubara.setText(""+total);
        } catch (SQLException ex) {
            Logger.getLogger(frmSparepart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void kdPenghasilan() {
        try {
            String sql = "select * from tb_penghasilan order by kd_penghasilan desc";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet rs=stm.executeQuery(sql);
            if (rs.next()) {
                String kd = rs.getString("kd_penghasilan").substring(3);
                String AN = "" + (Integer.parseInt(kd) + 1);
                txtKdPenghasilan.setText("PNG" + AN);
            } else {
                txtKdPenghasilan.setText("PNG1");
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
                cmbKdMobil.addItem(res.getString("kd_mobil"));
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private void textboxOn() {
        cmbDate.setEnabled(true);
        cmbKdMobil.setEnabled(true);
        txtRitBatubara.setEditable(true);
        txtSplit.setEditable(true);
        txtBeskos.setEditable(true);
        txtRitBeskos.setEditable(true);
        txtLoading.setEditable(true);
        jPanel4.setVisible(true);
        btnSimpan.setVisible(true);
        btnBatal.setVisible(true);
        cbxPenghasilanTambahan.setEnabled(true);
    }
    
    private void textboxOff() {
        txtKdPenghasilan.setEnabled(false);
        cmbDate.setEnabled(false);
        cmbKdMobil.setEnabled(false);
        txtBatubara.setEnabled(false);
        txtRitBatubara.setEditable(false);
        txtSplit.setEditable(false);
        txtBeskos.setEditable(false);
        txtRitBeskos.setEditable(false);
        txtLoading.setEditable(false);
        txtJumlah.setEnabled(false);
        txtSparepart.setEnabled(false);
        txtTotal.setEnabled(false);
        txtLabaKotor.setEnabled(false);
        txtLabaBersih.setEnabled(false);
        jPanel4.setVisible(false);
        btnSimpan.setVisible(false);
        btnBatal.setVisible(false);
        txtSplit.setVisible(false);
        txtBeskos.setVisible(false);
        txtRitBeskos.setVisible(false);
        txtLoading.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel18.setVisible(false);
        jSeparator10.setVisible(false);
        jSeparator11.setVisible(false);
        jSeparator12.setVisible(false);
        jSeparator13.setVisible(false);
        cbxPenghasilanTambahan.setSelected(false);
        cbxPenghasilanTambahan.setEnabled(false);
    }
    
    private void kosong(){
        txtKdPenghasilan.setText("Kode Penghasilan");
        cmbDate.setDate(null);
        txtBatubara.setText("0");
        txtRitBatubara.setText("0");
        txtSplit.setText("0");
        txtBeskos.setText("0");
        txtRitBeskos.setText("0");
        txtLoading.setText("0");
        txtJumlah.setText("0");
        txtSparepart.setText("0");
        txtTotal.setText("0");
        txtLabaKotor.setText("0");
        txtLabaBersih.setText("0");
    }
    
    private void load_table(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Penghasilan");
        model.addColumn("Tanggal");
        model.addColumn("Kode Mobil");
        model.addColumn("Batubara");
        model.addColumn("Rit Batubara");
        model.addColumn("Split");
        model.addColumn("Beskos");
        model.addColumn("Rit Beskos");
        model.addColumn("Loading");
        model.addColumn("Jumlah");
        model.addColumn("Sparepart");
        model.addColumn("Total");
        model.addColumn("Pendapatan Kotor");
        model.addColumn("Pendapatan Bersih");
        
        //menampilkan data database kedalam tabel
        try {
            String sql = "select * from tb_penghasilan";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10),res.getString(11),res.getString(12),res.getString(13),res.getString(14)});
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

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtKdPenghasilan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        btnBatal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBatubara = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        txtSplit = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtBeskos = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txtRitBeskos = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtLoading = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtSparepart = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        txtLabaKotor = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtLabaBersih = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        cmbKdMobil = new javax.swing.JComboBox<>();
        txtRitBatubara = new javax.swing.JTextField();
        cmbDate = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbxPenghasilanTambahan = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnBaru1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnCetak = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnHapus1 = new javax.swing.JLabel();
        cmbTGL = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(242, 233, 242));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Kode Penghasilan");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        txtKdPenghasilan.setBackground(new java.awt.Color(242, 233, 242));
        txtKdPenghasilan.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtKdPenghasilan.setForeground(new java.awt.Color(102, 102, 102));
        txtKdPenghasilan.setText("Kode Penghasilan");
        txtKdPenghasilan.setBorder(null);
        jPanel2.add(txtKdPenghasilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 170, 30));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Tanggal");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jLabel4.setBackground(new java.awt.Color(102, 102, 102));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Kode Mobil");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Batubara");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        jSeparator5.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator5.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 170, 10));

        jSeparator6.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator6.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 170, 10));

        jSeparator7.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator7.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 120, 10));

        jSeparator8.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator8.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 60, 10));

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
        jPanel2.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, 102, 38));

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("×");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, 20));

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

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 360, -1, 40));

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Ritase Batubara");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        txtBatubara.setBackground(new java.awt.Color(242, 233, 242));
        txtBatubara.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtBatubara.setForeground(new java.awt.Color(102, 102, 102));
        txtBatubara.setText("0");
        txtBatubara.setBorder(null);
        txtBatubara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBatubaraKeyReleased(evt);
            }
        });
        jPanel2.add(txtBatubara, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 170, 30));

        jSeparator9.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator9.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 170, 10));

        txtSplit.setBackground(new java.awt.Color(242, 233, 242));
        txtSplit.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtSplit.setForeground(new java.awt.Color(102, 102, 102));
        txtSplit.setText("0");
        txtSplit.setBorder(null);
        txtSplit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSplitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSplitFocusLost(evt);
            }
        });
        txtSplit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSplitKeyReleased(evt);
            }
        });
        jPanel2.add(txtSplit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 170, 30));

        jSeparator10.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator10.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 170, 10));

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Beskos");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, -1, -1));

        txtBeskos.setBackground(new java.awt.Color(242, 233, 242));
        txtBeskos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtBeskos.setForeground(new java.awt.Color(102, 102, 102));
        txtBeskos.setText("0");
        txtBeskos.setBorder(null);
        txtBeskos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBeskosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBeskosFocusLost(evt);
            }
        });
        txtBeskos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBeskosKeyReleased(evt);
            }
        });
        jPanel2.add(txtBeskos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, 170, 30));

        jSeparator11.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator11.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 170, 10));

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Ritase Beskos");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, -1, -1));

        txtRitBeskos.setBackground(new java.awt.Color(242, 233, 242));
        txtRitBeskos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtRitBeskos.setForeground(new java.awt.Color(102, 102, 102));
        txtRitBeskos.setText("0");
        txtRitBeskos.setBorder(null);
        txtRitBeskos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRitBeskosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRitBeskosFocusLost(evt);
            }
        });
        txtRitBeskos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRitBeskosKeyReleased(evt);
            }
        });
        jPanel2.add(txtRitBeskos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 170, 30));

        jSeparator12.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator12.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, 170, 10));

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Loading");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, -1, -1));

        txtLoading.setBackground(new java.awt.Color(242, 233, 242));
        txtLoading.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtLoading.setForeground(new java.awt.Color(102, 102, 102));
        txtLoading.setText("0");
        txtLoading.setBorder(null);
        txtLoading.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLoadingFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLoadingFocusLost(evt);
            }
        });
        txtLoading.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoadingKeyReleased(evt);
            }
        });
        jPanel2.add(txtLoading, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 560, 170, 30));

        jSeparator13.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator13.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 590, 170, 10));

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Jumlah");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, -1, -1));

        txtJumlah.setBackground(new java.awt.Color(242, 233, 242));
        txtJumlah.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtJumlah.setForeground(new java.awt.Color(102, 102, 102));
        txtJumlah.setText("0");
        txtJumlah.setBorder(null);
        jPanel2.add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 170, 30));

        jSeparator14.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator14.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 170, 10));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Sparepart");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, -1, -1));

        txtSparepart.setBackground(new java.awt.Color(242, 233, 242));
        txtSparepart.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtSparepart.setForeground(new java.awt.Color(102, 102, 102));
        txtSparepart.setText("0");
        txtSparepart.setBorder(null);
        txtSparepart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSparepartKeyReleased(evt);
            }
        });
        jPanel2.add(txtSparepart, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 170, 30));

        jSeparator15.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator15.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 170, 10));

        jLabel14.setBackground(new java.awt.Color(102, 102, 102));
        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Total");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, -1));

        txtTotal.setBackground(new java.awt.Color(242, 233, 242));
        txtTotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(102, 102, 102));
        txtTotal.setText("0");
        txtTotal.setBorder(null);
        jPanel2.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 170, 30));

        jSeparator16.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator16.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 170, 10));

        jLabel15.setBackground(new java.awt.Color(102, 102, 102));
        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Laba Kotor / Rit");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, -1, -1));

        txtLabaKotor.setBackground(new java.awt.Color(242, 233, 242));
        txtLabaKotor.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtLabaKotor.setForeground(new java.awt.Color(102, 102, 102));
        txtLabaKotor.setText("0");
        txtLabaKotor.setBorder(null);
        jPanel2.add(txtLabaKotor, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 170, 30));

        jSeparator17.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator17.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 170, 10));

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Laba Bersih / Rit");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, -1, -1));

        txtLabaBersih.setBackground(new java.awt.Color(242, 233, 242));
        txtLabaBersih.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtLabaBersih.setForeground(new java.awt.Color(102, 102, 102));
        txtLabaBersih.setText("0");
        txtLabaBersih.setBorder(null);
        jPanel2.add(txtLabaBersih, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 170, 30));

        jSeparator18.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator18.setForeground(new java.awt.Color(242, 233, 242));
        jPanel2.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 170, 10));

        jPanel2.add(cmbKdMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 60, 30));

        txtRitBatubara.setBackground(new java.awt.Color(242, 233, 242));
        txtRitBatubara.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtRitBatubara.setForeground(new java.awt.Color(102, 102, 102));
        txtRitBatubara.setText("0");
        txtRitBatubara.setBorder(null);
        txtRitBatubara.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRitBatubaraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRitBatubaraFocusLost(evt);
            }
        });
        txtRitBatubara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRitBatubaraKeyReleased(evt);
            }
        });
        jPanel2.add(txtRitBatubara, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 170, 30));

        cmbDate.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(cmbDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        jLabel17.setBackground(new java.awt.Color(204, 204, 204));
        jLabel17.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("?");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, -1, 20));

        jLabel18.setBackground(new java.awt.Color(102, 102, 102));
        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Split");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, -1, -1));

        cbxPenghasilanTambahan.setBackground(new java.awt.Color(242, 233, 242));
        cbxPenghasilanTambahan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbxPenghasilanTambahan.setForeground(new java.awt.Color(102, 102, 102));
        cbxPenghasilanTambahan.setText("Penghasilan Tambahan");
        cbxPenghasilanTambahan.setBorder(null);
        cbxPenghasilanTambahan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbxPenghasilanTambahanStateChanged(evt);
            }
        });
        jPanel2.add(cbxPenghasilanTambahan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, -1, -1));

        jPanel1.setBackground(new java.awt.Color(61, 115, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("PENGHASILAN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 780, 280));

        jPanel7.setBackground(new java.awt.Color(61, 115, 80));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel7.setToolTipText("");

        btnBaru1.setBackground(new java.awt.Color(204, 204, 204));
        btnBaru1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBaru1.setForeground(new java.awt.Color(204, 204, 204));
        btnBaru1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBaru1.setText("Baru");
        btnBaru1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBaru1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBaru1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBaru1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

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

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 410, -1, -1));

        jPanel5.setBackground(new java.awt.Color(61, 115, 80));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.setToolTipText("");

        btnHapus1.setBackground(new java.awt.Color(204, 204, 204));
        btnHapus1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnHapus1.setForeground(new java.awt.Color(204, 204, 204));
        btnHapus1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHapus1.setText("Hapus");
        btnHapus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapus1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, -1, -1));

        cmbTGL.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(cmbTGL, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseClicked
        kosong();
        textboxOff();
    }//GEN-LAST:event_btnBatalMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.dispose();
        new mainMenu().setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
        try{
            String query = "select * from tb_penghasilan where kd_penghasilan = '"+txtKdPenghasilan.getText()+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(query);
            SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
            if(res.next()){
                String sql ="UPDATE tb_penghasilan SET tgl = '"+Date_Format.format(cmbDate.getDate())+"', kd_mobil = '"+cmbKdMobil.getSelectedItem()+"', batubara = '"+txtBatubara.getText()+"', ritase_batubara = '"+txtRitBatubara.getText()+"', split = '"+txtSplit.getText()+"', beskos = '"+txtBeskos.getText()+"', ritase_beskos = '"+txtRitBeskos.getText()+"', loading = '"+txtLoading.getText()+"', jumlah = '"+txtJumlah.getText()+"', sparepart = '"+txtSparepart.getText()+"', total = '"+txtTotal.getText()+"', pendapatan_kotor_rit = '"+txtLabaKotor.getText()+"', pendapatan_bersih_rit = '"+txtLabaBersih.getText()+"' WHERE kd_penghasilan = '"+txtKdPenghasilan.getText()+"'";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "data berhasil diedit");
            }else{
                String sql = "INSERT INTO tb_penghasilan VALUES ('"+txtKdPenghasilan.getText()+"','"+Date_Format.format(cmbDate.getDate())+"','"+cmbKdMobil.getSelectedItem()+"','"+txtBatubara.getText()+"', '"+txtRitBatubara.getText()+"', '"+txtSplit.getText()+"', '"+txtBeskos.getText()+"', '"+txtRitBeskos.getText()+"', '"+txtLoading.getText()+"', '"+txtJumlah.getText()+"', '"+txtSparepart.getText()+"', '"+txtTotal.getText()+"', '"+txtLabaKotor.getText()+"', '"+txtLabaBersih.getText()+"')";
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
    }//GEN-LAST:event_btnSimpanMouseClicked


    private void txtSparepartKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSparepartKeyReleased
        // TODO add your handling code here:
//        upah();
    }//GEN-LAST:event_txtSparepartKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String kdpenghasilan =jTable1.getValueAt(baris, 0).toString();
        txtKdPenghasilan.setText(kdpenghasilan);
        String tgl = jTable1.getValueAt(baris,1).toString();
        try {
            java.util.Date date;
            date = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
            cmbDate.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(frmOli.class.getName()).log(Level.SEVERE, null, ex);
        }
        String kd=jTable1.getValueAt(baris, 2).toString();
        cmbKdMobil.setSelectedItem(kd);
        String btbr =jTable1.getValueAt(baris, 3).toString();
        txtBatubara.setText(btbr);
        String ritbtbr = jTable1.getValueAt(baris, 4).toString();
        txtRitBatubara.setText(ritbtbr);
        String split = jTable1.getValueAt(baris,5).toString();
        txtSplit.setText(split);
        String beskos=jTable1.getValueAt(baris, 6).toString();
        txtBeskos.setText(beskos);
        String ritbeskos = jTable1.getValueAt(baris, 7).toString();
        txtRitBeskos.setText(ritbeskos);
        String loading =jTable1.getValueAt(baris, 8).toString();
        txtLoading.setText(loading);
        String jumlah = jTable1.getValueAt(baris,9).toString();
        txtJumlah.setText(jumlah);
        String sparepart=jTable1.getValueAt(baris, 10).toString();
        txtSparepart.setText(sparepart);
        String total = jTable1.getValueAt(baris, 11).toString();
        txtTotal.setText(total);
        String kotor = jTable1.getValueAt(baris, 12).toString();
        txtLabaKotor.setText(kotor);
        String bersih = jTable1.getValueAt(baris, 13).toString();
        txtLabaBersih.setText(bersih);
        textboxOn();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnBaru1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBaru1MouseClicked
        textboxOn();
        kosong();
        kdPenghasilan();
    }//GEN-LAST:event_btnBaru1MouseClicked

    private void btnCetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseClicked
        // TODO add your handling code here:
                    HashMap param = new HashMap();
                    param.put("tanggal",cmbTGL.getDate());
                try {
                        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("rptPenghasilan.jasper"), param, Config.configDB());
                        JasperViewer.viewReport(jp, false);
                        
                    } catch(Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }
    }//GEN-LAST:event_btnCetakMouseClicked

    private void btnHapus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapus1MouseClicked
        // TODO add your handling code here:
        int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data ini?", "Penghapusan Data", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.YES_OPTION){
            try {
                String sql ="delete from tb_penghasilan where kd_penghasilan='"+txtKdPenghasilan.getText()+"'";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            load_table();
            kosong();
            textboxOff();
        }else{
            JOptionPane.showMessageDialog(null, "Data batal dihapus.");
        }
    }//GEN-LAST:event_btnHapus1MouseClicked

    private void cmbDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cmbDatePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDatePropertyChange

    private void txtBatubaraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBatubaraKeyReleased
        
    }//GEN-LAST:event_txtBatubaraKeyReleased

    private void txtSplitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSplitKeyReleased
        matjumlah();
        getTotal();
        getLaba();
    }//GEN-LAST:event_txtSplitKeyReleased

    private void txtBeskosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBeskosKeyReleased
        matjumlah();
        getTotal();
        getLaba();
    }//GEN-LAST:event_txtBeskosKeyReleased

    private void txtLoadingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoadingKeyReleased
        matjumlah();
        getTotal();
        getLaba();
    }//GEN-LAST:event_txtLoadingKeyReleased

    private void txtRitBatubaraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRitBatubaraFocusGained
        if (txtRitBatubara.getText().equals("0")){
            txtRitBatubara.setText("");
        }
    }//GEN-LAST:event_txtRitBatubaraFocusGained

    private void txtRitBatubaraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRitBatubaraFocusLost
        if (txtRitBatubara.getText().equals("")){
            txtRitBatubara.setText("0");
        }
    }//GEN-LAST:event_txtRitBatubaraFocusLost

    private void txtSplitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSplitFocusGained
        if (txtSplit.getText().equals("0")){
            txtSplit.setText("");
        }
    }//GEN-LAST:event_txtSplitFocusGained

    private void txtSplitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSplitFocusLost
        if (txtSplit.getText().equals("")){
            txtSplit.setText("0");
        }
    }//GEN-LAST:event_txtSplitFocusLost

    private void txtBeskosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBeskosFocusGained
        if (txtBeskos.getText().equals("0")){
            txtBeskos.setText("");
        }
    }//GEN-LAST:event_txtBeskosFocusGained

    private void txtBeskosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBeskosFocusLost
        if (txtBeskos.getText().equals("")){
            txtBeskos.setText("0");
        }
    }//GEN-LAST:event_txtBeskosFocusLost

    private void txtRitBeskosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRitBeskosFocusGained
        if (txtRitBeskos.getText().equals("0")){
            txtRitBeskos.setText("");
        }
    }//GEN-LAST:event_txtRitBeskosFocusGained

    private void txtRitBeskosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRitBeskosFocusLost
        if (txtRitBeskos.getText().equals("")){
            txtRitBeskos.setText("0");
        }
    }//GEN-LAST:event_txtRitBeskosFocusLost

    private void txtLoadingFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoadingFocusGained
        if (txtLoading.getText().equals("0")){
            txtLoading.setText("");
        }
    }//GEN-LAST:event_txtLoadingFocusGained

    private void txtLoadingFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoadingFocusLost
        if (txtLoading.getText().equals("")){
            txtLoading.setText("0");
        }
    }//GEN-LAST:event_txtLoadingFocusLost


    private void txtRitBatubaraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRitBatubaraKeyReleased
        matjumlah();
        getTotal();
        getLaba();
    }//GEN-LAST:event_txtRitBatubaraKeyReleased

    private void txtRitBeskosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRitBeskosKeyReleased
        matjumlah();
        getTotal();
        getLaba();
    }//GEN-LAST:event_txtRitBeskosKeyReleased

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Form Penghasilan.\n - Mengisikan data: Klik 'Baru' lalu form akan tersedia untuk diisi.\n - Mengubah data: Piih data lalu klik pada tabel, data akan muncul di form untuk diubah.\n - Menghapus data: Piih data lalu klik pada tabel, lalu tekan tombol 'Hapus' data akan terhapus.\n - Mencetak laporan: Tentukan bulan apa yang akan dicetak lalu klik 'Cetak'");
    }//GEN-LAST:event_jLabel17MouseClicked

    private void cbxPenghasilanTambahanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbxPenghasilanTambahanStateChanged
        // TODO add your handling code here:
        if(cbxPenghasilanTambahan.isSelected()) {
        txtSplit.setVisible(true);
        txtBeskos.setVisible(true);
        txtRitBeskos.setVisible(true);
        txtLoading.setVisible(true);
        jLabel9.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);
        jLabel18.setVisible(true);
        jSeparator10.setVisible(true);
        jSeparator11.setVisible(true);
        jSeparator12.setVisible(true);
        jSeparator13.setVisible(true);
        } else {
        txtSplit.setVisible(false);
        txtBeskos.setVisible(false);
        txtRitBeskos.setVisible(false);
        txtLoading.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel18.setVisible(false);
        jSeparator10.setVisible(false);
        jSeparator11.setVisible(false);
        jSeparator12.setVisible(false);
        jSeparator13.setVisible(false);
        txtSplit.setText("0");
        txtBeskos.setText("0");
        txtRitBeskos.setText("0");
        txtLoading.setText("0");
        }
    }//GEN-LAST:event_cbxPenghasilanTambahanStateChanged
    

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
            java.util.logging.Logger.getLogger(frmPenghasilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPenghasilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPenghasilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPenghasilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPenghasilan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBaru1;
    private javax.swing.JLabel btnBatal;
    private javax.swing.JLabel btnCetak;
    private javax.swing.JLabel btnHapus1;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JCheckBox cbxPenghasilanTambahan;
    private com.toedter.calendar.JDateChooser cmbDate;
    private javax.swing.JComboBox<String> cmbKdMobil;
    private com.toedter.calendar.JDateChooser cmbTGL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBatubara;
    private javax.swing.JTextField txtBeskos;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKdPenghasilan;
    private javax.swing.JTextField txtLabaBersih;
    private javax.swing.JTextField txtLabaKotor;
    private javax.swing.JTextField txtLoading;
    private javax.swing.JTextField txtRitBatubara;
    private javax.swing.JTextField txtRitBeskos;
    private javax.swing.JTextField txtSparepart;
    private javax.swing.JTextField txtSplit;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
