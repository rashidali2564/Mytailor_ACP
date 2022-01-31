/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytailor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Payment extends javax.swing.JFrame {
 
    /**
     * Creates new form Payment
     */
       
    public Payment() {
        initComponents();
        setBillNo();
       AssignmentForm as=new AssignmentForm();
       String o1=as.getORD();
       ordNo.setText(o1);
       setDate();
       setBillNo(); 
       showInfo();
       showList();
        amtCalculation();
        Home.setVisible(false);
       
       
         
    }
    
    
    
     //Delete data from temp table 
    public void deleteTempdata(){
        DBconnection db=new DBconnection();
        try {
            
            Statement st=db.conn.createStatement();
            st.executeUpdate("Delete * from temp");
             } catch (SQLException ex) {
            Logger.getLogger(AssignmentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
  
   
    ///Setting Payment date
    public  void setDate() //setting Assignment  date
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
        
        dt.setText(value);
        dt.setHorizontalAlignment(JLabel.CENTER);
        

    }
 
    //Setting calulated amount
   public void  setAmt(){
        Integer paid=Integer.valueOf(paidAmt.getText());
        Integer total=Integer.valueOf(totalAmt.getText());
        int bal;
        bal=total-paid;
        String sbal=String.valueOf(bal);
        if(paid>total){
            JOptionPane.showMessageDialog(null,"Balance amount must be \n less or equals to Total Amount");
            paidAmt.requestFocusInWindow();
        }
        else
        balAmt.setText(sbal);
   }
   
   
   
   
    //Amount calculation
    public void amtCalculation(){
    DBconnection db=new DBconnection();
    try{
    String q="select * from temp where ordno='"+ordNo.getText()+"' ";
   System.out.println("This is ord"+ordNo.getText());
        PreparedStatement pst = db.conn.prepareStatement(q);
          ResultSet rs = pst.executeQuery();
        int total=0;
        System.out.println("This is total:"+total);
    while(rs.next()){
            String price=rs.getString("cust_rate");
 /////////////////System.out.println("String prce:"+price);
      int cc=Integer.parseInt(price);
 /////////////////System.out.println("Int price is price"+cc);
    total=total+cc;
    
 ///////////////////////////////////////////////////// System.out.println("This is total:"+total);
        

    }
     totalAmt.setText(total+"");
     paidAmt.setText(total+"");
       
      
   }catch(NumberFormatException | SQLException e){}
    
   
    }
    
    
    
    
    //Fetch info for Displaying On Payment form
    public void showInfo(){
    DBconnection db=new DBconnection(); 
   
       
    try{
    String q="select * from temp where ordno='"+ordNo.getText()+"' ";
    String q2="select * from cust_info where ordno='"+ordNo.getText()+"' ";
        PreparedStatement pst = db.conn.prepareStatement(q);
        PreparedStatement pst2 = db.conn.prepareStatement(q2);
        ResultSet rs = pst.executeQuery();
        ResultSet rs2 = pst2.executeQuery();
        System.out.println("Aterresultset");
    while(rs2.next() && rs.next()){
    
    String cname=rs2.getString("cust_name");
  System.out.println(cname);
    n1.setText(cname);
    String cadd=rs2.getString("cust_add");
    n2.setText(cadd);
    String adate=rs.getString("assign_date");
      System.out.println(cname);
    n3.setText(adate);
    String delDate=rs.getString("delivey_date");
    delivery.setText(delDate);
    
    
    }  
      
    }catch(SQLException e){}
    
    
    }
    // Billno Generator
    public void setBillNo(){
    
    AutoIncreament obj=new AutoIncreament();
    int no2= obj.auto_BillnoForPayment();
    billNo.setText(""+no2);
    }
    
    //Show List Bill
    public void showList(){
    DBconnection db=new DBconnection(); 
   
       DefaultListModel l1=new DefaultListModel();
       DefaultListModel l2=new DefaultListModel();
       DefaultListModel l3=new DefaultListModel();
    try{
    String q="select * from temp where ordno='"+ordNo.getText()+"' ";
    //String q2="select * from cust_info where ordno='"+ordNo.getText()+"' ";
        PreparedStatement pst = db.conn.prepareStatement(q);
        //PreparedStatement pst2 = db.conn.prepareStatement(q2);
        ResultSet rs = pst.executeQuery();
        //ResultSet rs2 = pst2.executeQuery();
        int total=0;
    while(rs.next()){
    String item=rs.getString("assign_item");
    String qty=rs.getString("item_qty");
    String price=rs.getString("cust_rate");
    
    l1.addElement(item);
    l2.addElement(qty);
    l3.addElement(price);
    
    
    }
    
  jList1.setModel(l1);
  jList2. setModel(l2); 
  jList3.setModel(l3);
    
    
    }catch(Exception e){}
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dt = new javax.swing.JLabel();
        paymentPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        ordNo = new javax.swing.JLabel();
        n1 = new javax.swing.JLabel();
        n2 = new javax.swing.JLabel();
        n3 = new javax.swing.JLabel();
        billNo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        delivery = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        paidAmt = new javax.swing.JTextField();
        totalAmt = new javax.swing.JLabel();
        Home = new javax.swing.JButton();
        balAmt = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        calculateButton = new javax.swing.JButton();
        delivery1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        payButton1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        PrintButtn = new javax.swing.JButton();

        dt.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Payment || Billing");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paymentPanel.setBackground(java.awt.Color.white);
        paymentPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        paymentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(102, 102, 102));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("           Particular");
        jLabel4.setOpaque(true);
        paymentPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 290, 30));

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quantity");
        jLabel1.setOpaque(true);
        paymentPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 140, 30));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("        Price");
        jLabel3.setOpaque(true);
        paymentPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 250, 30));

        jList3.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        paymentPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 260, 190));

        jList2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        paymentPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 190, 190));

        jList1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        paymentPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 250, 190));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/LOGOdd.png"))); // NOI18N
        paymentPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, 70));

        ordNo.setBackground(new java.awt.Color(255, 255, 255));
        ordNo.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        paymentPanel.add(ordNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 70, 20));

        n1.setBackground(new java.awt.Color(255, 255, 255));
        n1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        paymentPanel.add(n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 110, 30));

        n2.setBackground(new java.awt.Color(255, 255, 255));
        n2.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        paymentPanel.add(n2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 240, 20));

        n3.setBackground(new java.awt.Color(255, 255, 255));
        n3.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        paymentPanel.add(n3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 70, 30));

        billNo.setBackground(new java.awt.Color(255, 255, 255));
        billNo.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        paymentPanel.add(billNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 110, 30));

        jLabel2.setFont(new java.awt.Font("Trajan Pro 3", 3, 24)); // NOI18N
        jLabel2.setText("My Tailor");
        paymentPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 300, 30));

        jLabel14.setText("A Semeter Project of ADvanced Programming  ");
        paymentPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 400, 30));

        jLabel15.setText("My Tailor");
        paymentPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 60, 30));

        jLabel16.setText("* Delivery Timings: Morning 10.00AM-2.00PM and Evening 5.00PM to 9.00 PM");
        paymentPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 390, -1));

        jLabel17.setText("*  Please Keep it At the time of Delivery");
        paymentPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 250, -1));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel18.setText("Ord No:");
        paymentPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 60, -1));

        delivery.setBackground(new java.awt.Color(255, 255, 255));
        delivery.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        paymentPanel.add(delivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 130, 30));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel20.setText("Name : Mr./Mrs./Ms. ");
        paymentPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 120, 30));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel21.setText("Address :");
        paymentPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 110, 20));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel22.setText("Date:");
        paymentPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 40, 30));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel23.setText("Delivery Date:");
        paymentPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 80, 30));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel19.setText("Bill No:");
        paymentPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 40, 30));

        jLabel24.setBackground(new java.awt.Color(102, 102, 102));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Total");
        jLabel24.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 204)));
        jLabel24.setOpaque(true);
        paymentPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, 110, 30));

        jLabel25.setBackground(new java.awt.Color(102, 102, 102));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Paid");
        jLabel25.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 204)));
        jLabel25.setOpaque(true);
        paymentPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, 110, 30));

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Balance");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 204)));
        jLabel7.setOpaque(true);
        paymentPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, 110, 30));

        paidAmt.setBackground(new java.awt.Color(102, 102, 102));
        paidAmt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        paidAmt.setForeground(new java.awt.Color(255, 255, 255));
        paidAmt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paidAmtMouseClicked(evt);
            }
        });
        paidAmt.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                paidAmtInputMethodTextChanged(evt);
            }
        });
        paidAmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidAmtActionPerformed(evt);
            }
        });
        paidAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                paidAmtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paidAmtKeyTyped(evt);
            }
        });
        paymentPanel.add(paidAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 400, 80, 30));

        totalAmt.setBackground(new java.awt.Color(102, 102, 102));
        totalAmt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalAmt.setForeground(new java.awt.Color(255, 255, 255));
        totalAmt.setOpaque(true);
        paymentPanel.add(totalAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 370, 80, 30));

        Home.setText("Pay");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });
        paymentPanel.add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 110, 30));

        balAmt.setBackground(new java.awt.Color(102, 102, 102));
        balAmt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        balAmt.setForeground(new java.awt.Color(255, 255, 255));
        balAmt.setText("00.00");
        balAmt.setOpaque(true);
        paymentPanel.add(balAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, 80, 30));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        paymentPanel.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 110, 30));

        calculateButton.setText("OK");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });
        paymentPanel.add(calculateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 110, 30));

        delivery1.setBackground(new java.awt.Color(255, 255, 255));
        delivery1.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        paymentPanel.add(delivery1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 130, 30));

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel26.setText("Name : Mr./Mrs./Ms. ");
        paymentPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 120, 30));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel27.setText("Address :");
        paymentPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 110, 20));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel28.setText("Delivery Date:");
        paymentPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 80, 30));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 12)); // NOI18N
        jLabel29.setText("Bill No:");
        paymentPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 40, 30));

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(null);
        paymentPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 680, 310));

        payButton1.setText("Pay");
        payButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payButton1ActionPerformed(evt);
            }
        });
        paymentPanel.add(payButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 110, 30));

        getContentPane().add(paymentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 820, 540));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        PrintButtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/Printer-icon.png"))); // NOI18N
        PrintButtn.setToolTipText("Print ");
        PrintButtn.setFocusable(false);
        PrintButtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PrintButtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PrintButtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintButtnActionPerformed(evt);
            }
        });
        jToolBar1.add(PrintButtn);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paidAmtInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_paidAmtInputMethodTextChanged
        
    }//GEN-LAST:event_paidAmtInputMethodTextChanged

    private void paidAmtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidAmtKeyTyped
Home.setVisible(false);
        calculateButton.setVisible(true);    

       
    }//GEN-LAST:event_paidAmtKeyTyped

    private void paidAmtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paidAmtMouseClicked
    Home.setVisible(false);
        calculateButton.setVisible(true);
        paidAmt.requestFocus();
    paidAmt.selectAll();       
    }//GEN-LAST:event_paidAmtMouseClicked

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
         
        calculateButton.setVisible(false);
        setAmt();
        System.out.println(balAmt.getText());
        System.out.println(paidAmt.getText());
        ///////////////////////////////////////////////
        AutoIncreament obj=new AutoIncreament();
       int idd=obj.auto_forTransactionID();
       System.out.println(idd);
       String transID=""+idd;
       System.out.println("this is transaction id"+transID);
       AddStaffPayment dt2=new AddStaffPayment();
       String dte=dt2.setDate();
       System.out.println("date"+dte);
      
        
       ///Insertion in table       
    DBconnection db=new DBconnection();
   try{
    
        Statement st=db.conn.createStatement();
        System.out.println(balAmt.getText());
    
             int a=st.executeUpdate("insert into payment(billno,ordno,cusname,totalamt,balamt,paidamt,pay_date,assignDate,deliveryDate)" +
            "values ('"+billNo.getText() +"','"+ordNo.getText()+"','"+n1.getText()+"','"+totalAmt.getText()+"','"+balAmt.getText()+"','"+ paidAmt.getText() +"','"+ n3.getText() +"','"+ delivery.getText() +"','"+ dt.getText() +"' )");
             
             /////ORDER COUNT
              int countDelivery=st.executeUpdate("insert into OrdrDeliveryCount(count_date,no_of_order,cost_of_order,no_of_delivery,cost_of_delivery)"
       +"values ( '"+dte+"',1,'"+paidAmt.getText()+"',0,0)");
      
      if(countDelivery>0){
                 System.out.println("Save into Delivery count");
                 }
             
             String s="Management";
             String paidFor="Dress of "+n1.getText();
             int a2=st.executeUpdate("insert into paymentHistory(tranID,amt,paid_to,paid_for,paid_date)"
                         + "values('"+transID+"','"+paidAmt.getText()+"','"+s+"','"+paidFor+"','"+dte+"')");
               
             if(a==1){
            JOptionPane.showMessageDialog(null,"Saved","Message",JOptionPane.INFORMATION_MESSAGE);
              if(a2>0){
                 System.out.println("Save into transaction");
                 deleteTempdata();
                 }   
    } 
            
    
        } catch (SQLException ex) {
            Logger.getLogger(StaffInfo.class.getName()).log(Level.SEVERE, null, ex);
         //   JOptionPane.showMessageDialog(null,ex);    
           System.out.println(ex);         
        }
        Home.setVisible(false);
        cancelButton.setVisible(false);
        paidAmt.setEditable(false);
        
    }//GEN-LAST:event_HomeActionPerformed

    private void paidAmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paidAmtKeyPressed
        Home.setVisible(false);
        calculateButton.setVisible(true);       
    }//GEN-LAST:event_paidAmtKeyPressed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        HOME hm=new HOME();
        hm.setVisible(true);
            dispose();
            
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void paidAmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidAmtActionPerformed
        Home.setVisible(false);
        calculateButton.setVisible(true);
        
    }//GEN-LAST:event_paidAmtActionPerformed

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        setAmt();
       calculateButton.setVisible(false);
       Home.setVisible(true);
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void PrintButtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintButtnActionPerformed
        Home.setVisible(false);
        cancelButton.setVisible(false);
        calculateButton.setVisible(false);
       
        PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Print Data");
            
            job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                    pf.setOrientation(PageFormat.LANDSCAPE);
                 if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.75,0.70);
                
               paymentPanel.paint(g2);   ////////////change 
//          
               
                return Printable.PAGE_EXISTS;
                         
                
            }
    });
         
        boolean ok = job.printDialog();
        if(ok){
        try{
            
        job.print();
        }
        catch (PrinterException ex){}
        }        // TODO add your handling code here:
    }//GEN-LAST:event_PrintButtnActionPerformed

    private void payButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton PrintButtn;
    private javax.swing.JLabel balAmt;
    private javax.swing.JLabel billNo;
    private javax.swing.JButton calculateButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel delivery;
    private javax.swing.JLabel delivery1;
    private javax.swing.JLabel dt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel n1;
    private javax.swing.JLabel n2;
    private javax.swing.JLabel n3;
    private javax.swing.JLabel ordNo;
    private javax.swing.JTextField paidAmt;
    private javax.swing.JButton payButton1;
    private javax.swing.JPanel paymentPanel;
    private javax.swing.JLabel totalAmt;
    // End of variables declaration//GEN-END:variables
}
