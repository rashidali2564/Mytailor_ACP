/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytailor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class AddStaffPayment extends javax.swing.JFrame {

    /**
     * Creates new form AddStaffPayment
     */
    public AddStaffPayment() {
        initComponents();
        staffCombo();
        jButton1.setVisible(false);
    }
  public  String setDate() //setting Assignment  date
    {
        LocalDate dd = LocalDate.now();
        int y = dd.getYear();
        int d = dd.getDayOfMonth();
        int m = dd.getMonthValue();

        String myArray[] = new String[3];
        myArray[0] = "" + d;
        myArray[1] = "-" + m;
        myArray[2] = "-" + y;
        String value = "";
        for (int i = 0; i < myArray.length; i++) {

            value += myArray[i];
            System.out.println(value);
        }
        
        return value;
        

    }    
    ///////////////////inserting into payment history for tracnsaction details
    public void tranHistory()
    {
    
    
   
       
       
       
    }
  
    //////////////Send paid Amount to paidStaff table And delete staffSalary record
        public void paidAmount(){
            
       AutoIncreament obj=new AutoIncreament();
       int idd=obj.auto_forTransactionID();
       System.out.println(idd);
       String transID=""+idd;
       System.out.println("this is transaction id"+transID);
       String dte=setDate();
       System.out.println("date"+dte);
       String paidFor="Sewing";
        
   try{
       
           DBconnection db=new DBconnection(); 
            String q="select * from staffSalary where staff_id='"+ID.getText()+"'";
            Statement st=db.conn.createStatement();
            ResultSet rs=st.executeQuery(q);
                  String id="";
                  String name="";
                  String item = "";
                  String qty = "";
                  String  amt= "";
                  String dt = "";
     while(rs.next()) {
                id = rs.getString("staff_id");
                name=rs.getString("staff_name");
                item= rs.getString("item");
                qty = rs.getString("item_count");
                amt= rs.getString("staff_salary");
                dt = rs.getString("assign_date");
                
                 int a=st.executeUpdate("insert into PaidSalary(staff_id,staff_name,item,item_count,staff_salary,assign_date)"
                       + " values('"+id+"','"+name+"','"+item+"','"+ qty+"','"+amt+"','"+dt+"')");
                 int a2=st.executeUpdate("insert into paymentHistory(tranID,amt,paid_to,paid_for,paid_date)"
                         + "values('"+transID+"','"+amt+"','"+name+"','"+paidFor+"','"+dte+"')");
                 if(a2>0){
                 System.out.println("Save into transaction");
                 }
            if(a==1){
            System.out.println("Save into PaidSalary table");
            }
            ////Deleting data from staff salary
           int b= st.executeUpdate("Delete  from staffSalary where staff_id='"+ID.getText()+"'");  
         if(b>0){
         System.out.println("Delete record from staffSalary table");
         JOptionPane.showMessageDialog(null,"DONE !");
         getPayment(jTable1);
         staffCombo();
         jButton1.setVisible(false);
         break;
         }
                 
    }
        
   } catch (SQLException ex) {
            Logger.getLogger(StaffInfo.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,ex);
             
                    
        }
        }
    
    
    
    ////////////SetCombobox List._________________
    
       
    public void staffCombo()
    {
    DBconnection db=new DBconnection();
    try {
             Statement stmt = db.conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from staffSalary");
                while (rs.next()) {
            String pat = rs.getString("staff_name");
            staffCombo.addItem(pat);
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null, e);
    }
    }
    
  
    
    
    
/////////////Jtable information//////////////////
    public void getPayment( JTable info){
        String s1=(String)staffCombo.getSelectedItem();
    try{
            DBconnection db=new DBconnection(); 
            String q="select * from staffSalary where staff_name='"+s1+"'";
            Statement st=db.conn.createStatement();
            ResultSet rs=st.executeQuery(q);
         
           DefaultTableModel model = new DefaultTableModel();
                 String[] columnNames = {"Item","Quantity","Amount","Date"};
                 model.setColumnIdentifiers(columnNames);
                 info.setModel(model);
                 String id = "";

                 String item = "";
                 String qty = "";
                 String  amt= "";
                 String dt = "";
                 int tot=0;
                 
   
         
            
           while(rs.next()) {
                id = rs.getString("staff_id");
                item= rs.getString("item");
                qty = rs.getString("item_count");
                amt= rs.getString("staff_salary");
                dt = rs.getString("assign_date");
                tot=tot+Integer.parseInt(amt);
                        
                model.addRow(new Object[]{item,qty,amt,dt});
                       
               }
           ID.setText(id);
           total.setText("Total Amount ₹ "+tot+".00");
    }catch(SQLException e){}
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
        jPanel2 = new javax.swing.JPanel();
        ID = new javax.swing.JLabel();
        staffCombo = new javax.swing.JComboBox<>();
        total = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        HOME = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setBackground(new java.awt.Color(204, 102, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Add Staff Payment");
        jLabel1.setOpaque(true);

        ID.setText("ID");

        staffCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select staff" }));
        staffCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffComboMouseClicked(evt);
            }
        });
        staffCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffComboActionPerformed(evt);
            }
        });

        total.setBackground(new java.awt.Color(0, 0, 255));
        total.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setText("Total");
        total.setOpaque(true);

        jTable1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item", "Quantity", "Date", "Amount"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 488, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staffCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jButton1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton1.setText("Pay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        HOME.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        HOME.setText("Home");
        HOME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HOMEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(HOME, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(HOME))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        paidAmount();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void staffComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffComboMouseClicked
     
    }//GEN-LAST:event_staffComboMouseClicked

    private void staffComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffComboActionPerformed
      getPayment(jTable1);
      if(ID.getText().length()>0){
      jButton1.setVisible(true);
      }
      else 
      {   jButton1.setVisible(false);}
    }//GEN-LAST:event_staffComboActionPerformed

    private void HOMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HOMEActionPerformed
        // TODO add your handling code here:
 HOME hm=new HOME();
        hm.setVisible(true);
            dispose();        
    }//GEN-LAST:event_HOMEActionPerformed

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
            java.util.logging.Logger.getLogger(AddStaffPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddStaffPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddStaffPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddStaffPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddStaffPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HOME;
    private javax.swing.JLabel ID;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> staffCombo;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
