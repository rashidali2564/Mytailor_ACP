/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytailor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class AssignmentForm extends javax.swing.JFrame {
 
    /**
     * Creates new form AssignmentForm
     */
    public AssignmentForm() {
        initComponents();
        qty.setText("1");
        setID();
        setDate();
        addButton.setVisible(false);
        saveButton.setVisible(false);
         comboRetrieve();
         
         //itemRetrieve(); 
     
    }

//////////////////Insert Data into staff salary table for make a salary report and pay it to staff
    public void insertIntoStaffsalay(){
     DBconnection db=new DBconnection();

     String selectItem = (String) selectProduct.getSelectedItem();
     
     String selectstaffName = (String) selectStaff.getSelectedItem();
     
     try {
     Statement st=db.conn.createStatement();
            String q="select * from staff where staff_name= '"+selectstaffName+"'";
            PreparedStatement pst = db.conn.prepareStatement(q);
           // pst.setString(1,txtSearch.getText());
            ResultSet rs = pst.executeQuery();
            String   ID = "";
            //***********************************************
            //Calculating amount*****************************
             int count=Integer.parseInt(qty.getText());      //** 
             int r1=Integer.parseInt(txt1.getText());    //**   
                                                               //**   
            String sRate=String.valueOf(count*r1);           //**
                      //**
            //***********************************************
            //*********************************************** 
            while(rs.next()) {
                
                ID = rs.getString("staff_id");
                
            }
     
               int a=st.executeUpdate("insert into staffSalary(staff_id,staff_name,item,item_count,staff_salary,assign_date)"
                       + " values('"+ID+"','"+selectstaffName+"','"+selectItem+"','"+qty.getText()+"','"+sRate+"','"+dt.getText()+"')");
     if(a>0)
         System.out.println("CONGo....!Record inserted into Staffsalary Table");
     
     } catch (SQLException ex) {
            Logger.getLogger(AssignmentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                  
    }

//////////////////Insert data into Asignment table and  Delete data From temp table
    public void assignData(){
            DBconnection db=new DBconnection();
            
            int n=selectProduct.getSelectedIndex();
            int m=selectStaff.getSelectedIndex();
            String s1=(String) selectStaff.getSelectedItem();
              String s4=(String) selectProduct.getSelectedItem();
            String s2="Select staff";
            String s3="Select item";
      if (n==0){
       JOptionPane.showMessageDialog(null,"Select item!","Message",JOptionPane.INFORMATION_MESSAGE);
      }
            else if(qty.getText().length()==0){
                
            JOptionPane.showMessageDialog(null,"Enter quantity","Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(m==0){
                
            JOptionPane.showMessageDialog(null,"Select staff!","Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else{   
           try{
            //***********************************************
            //Calculating amount*****************************
             int q=Integer.parseInt(qty.getText());      //** 
             int r1=Integer.parseInt(txt1.getText());    //**   
             int r2=Integer.parseInt(txt2.getText());    //**   
            String sRate=String.valueOf(q*r1);           //**
            String cRate=String.valueOf(q*r2);           //**
            //***********************************************
            //*********************************************** 
                     
               
               
                    Statement st=db.conn.createStatement();
                     String selected1 = (String) selectProduct.getSelectedItem();
                    System.out.println(selected1);
                     String selected2 = (String) selectStaff.getSelectedItem();
                    System.out.println(selected2);
                  //  Calendar cals = Calendar.getInstance();
                     SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                     String DeliveryDate=formater.format(jDateChooser.getDate());
                    
                    
                  

                        
                     System.out.println(DeliveryDate);
   
         
            int a=st.executeUpdate("insert into assignment(assign_item,item_qty,assign_staff,assign_date,delivey_date,ordno,status,staff_rate,cust_rate)" +
                    "values ('"+selected1+"','"+qty.getText()+"','"+ selected2 +"','"+dt.getText()+"','"+DeliveryDate+"','"+ ID.getText() +"','"+Status.getText()+"','"+sRate+"','"+cRate+"')");
           //******************************************************************************************************************************************************************************************
          //__________________________________________________________________________________________________________________________________________________________________________________________
            int temp=st.executeUpdate("insert into temp(assign_item,item_qty,assign_staff,assign_date,delivey_date,ordno,status,staff_rate,cust_rate)" +
                    "values ('"+selected1+"','"+qty.getText()+"','"+ selected2 +"','"+dt.getText()+"','"+DeliveryDate+"','"+ ID.getText() +"','"+Status.getText()+"','"+sRate+"','"+cRate+"')");

            
            if(a==1 && temp==1){ 
                insertIntoStaffsalay();
            JOptionPane.showMessageDialog(null,"Saved!","Message",JOptionPane.INFORMATION_MESSAGE);
            //Enabeling Gobutton//\\//\\//\\//\\//\\//\\//\\
                        saveButton.setVisible(true);   //\//\\
           //\\//\\//\\//\//\\//\\//\\//\\//\\//\\/////\//\\
            showInfo(assignmentTabe); 
           clearDate();
            }
     
        } catch (SQLException ex) {
              
            Logger.getLogger(StaffInfo.class.getName()).log(Level.SEVERE, null, ex);
                
                
        }
            }         
     
    
    }







    
/////////////////////////////////
     public void showInfo( JTable info){
    try{
            DBconnection db=new DBconnection(); 
            String q="select * from temp ";
            Statement st=db.conn.createStatement();
            ResultSet rs=st.executeQuery(q);
          // info.setModel(DbUtils.resultSetToTableModel(rs));
          ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\\
           DefaultTableModel model = new DefaultTableModel();
          String[] columnNames = {"Item", "Quantity", "Assign to", "Delivery date"};
          model.setColumnIdentifiers(columnNames);
          info.setModel(model);
        String item = "";
        String qtty = "";
        String assTo = "";
        String dDate= "";
         //String contact = "";
   
         
            
           while(rs.next()) {
                item = rs.getString("assign_item");
                qtty = rs.getString("item_qty");
                assTo= rs.getString("assign_staff");
                dDate= rs.getString("delivey_date");
                //contact = rs.getString("cust_contact");
                model.addRow(new Object[]{item,qtty, assTo,dDate});
                
            
            
            }
         
    }catch(SQLException e){}
}
/////////////////////////////////    
    



//This method is used for referencing Id to payment form
    public String getORD(){
    
        return ID.getText();
    }
      
    
    
    //***************************************************
    //Clear Data ************************************//**
     public void clearDate(){                        //**
     if(selectProduct.getSelectedIndex()>0){         //**
                                                     //**
     selectProduct.setSelectedItem("Select item");   //**
     }                                               //**
     else if(selectStaff.getSelectedIndex()>0){      //**
                                                     //**
     selectStaff.setSelectedItem("Select Staff");    //**
     }                                               //**
      qty.setText("1");                              //**
      txt1.setText("");                              //**
      txt2.setText("");                              //**
     }                                               //**
    //***********************************************//**
    //***************************************************
    
    //Retrieving rates from database according to item selection
    public void setRate(){
       DBconnection db=new DBconnection();
       String s1=(String) selectProduct.getSelectedItem();
       switch(s1){
           case "Shirt":  
                        try{
                            Statement stmt = db.conn.createStatement();
                            ResultSet rs = stmt.executeQuery("select staff_rate,cust_rate from rates where item_name='"+s1+"' ");
                            while(rs.next()){
                            int i1=rs.getShort("staff_rate");
                            int i2=rs.getShort("cust_rate");
                            
                             
                           
                            String getStaffRate=String.valueOf(i1);                            
                            String getCusRate=String.valueOf(i2);
                            
                            txt1.setText(getStaffRate);
                            txt2.setText(getCusRate);
                            }
                           }catch(SQLException e){}  
            case "Pant":  try{
                            Statement stmt = db.conn.createStatement();
                            ResultSet rs = stmt.executeQuery("select staff_rate,cust_rate from rates where item_name='"+s1+"' ");
                             while(rs.next()){
                            int i1=rs.getShort("staff_rate");
                            int i2=rs.getShort("cust_rate");
                                                               
                            String getStaffRate=String.valueOf(i1);                            
                            String getCusRate=String.valueOf(i2);
                            
                            txt1.setText(getStaffRate);
                            txt2.setText(getCusRate);
                             }
                           }catch(SQLException e){}
             case "Sherwani":try{
                            Statement stmt = db.conn.createStatement();
                            ResultSet rs = stmt.executeQuery("select staff_rate,cust_rate from rates where item_name='"+s1+"' ");
                             while(rs.next()){
                           int i1=rs.getShort("staff_rate");
                            int i2=rs.getShort("cust_rate");
                            
                             
                           
                            String getStaffRate=String.valueOf(i1);                            
                            String getCusRate=String.valueOf(i2);
                            
                            txt1.setText(getStaffRate);
                            txt2.setText(getCusRate);
                             }
                           }catch(SQLException e){}
              case "Pathani":  try{
                            Statement stmt = db.conn.createStatement();
                            ResultSet rs = stmt.executeQuery("select staff_rate,cust_rate from rates where item_name='"+s1+"' ");
                             while(rs.next()){
                            int i1=rs.getShort("staff_rate");
                            int i2=rs.getShort("cust_rate");
                            
                             
                           
                            String getStaffRate=String.valueOf(i1);                            
                            String getCusRate=String.valueOf(i2);
                            
                            txt1.setText(getStaffRate);
                            txt2.setText(getCusRate);
                             }
                           }catch(SQLException e){}
               case "Suit":  try{
                            Statement stmt = db.conn.createStatement();
                            ResultSet rs = stmt.executeQuery("select staff_rate,cust_rate from rates where item_name='"+s1+"' ");
                            
                            int c1=Integer.parseInt(qty.getText());  
                            
                            while(rs.next()){
                            int i1=rs.getShort("staff_rate");
                            int i2=rs.getShort("cust_rate");
                            
                             
                           
                            String getStaffRate=String.valueOf(i1);                            
                            String getCusRate=String.valueOf(i2);
                            
                            txt1.setText(getStaffRate);
                            txt2.setText(getCusRate);
//                            
                            }
                           }catch(SQLException e){}
               case "Safari":  try{
                            Statement stmt = db.conn.createStatement();
                            ResultSet rs = stmt.executeQuery("select staff_rate,cust_rate from rates where item_name='"+s1+"' ");
                             while(rs.next()){
//                            
                            int i1=rs.getShort("staff_rate");
                            int i2=rs.getShort("cust_rate");
                            
                             
                           
                            String getStaffRate=String.valueOf(i1);                            
                            String getCusRate=String.valueOf(i2);
                            
                            txt1.setText(getStaffRate);
                            txt2.setText(getCusRate);
                             }
                           }catch(SQLException e){}
 
             
                   }
       try{
        Statement stmt = db.conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from rates");
       }catch(SQLException e){
       
       }    
    }
    //Retrieving Item from database
     public void itemRetrieve()
    {
    DBconnection db=new DBconnection();
    try {
        Statement stmt = db.conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from rates");
        
        
          
        while (rs.next()) {
           String pat = rs.getString("item_name");
            selectProduct.addItem(pat);
            
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null, e);
    }
    }
    //Retrieving staff name into combobox
    
    public void comboRetrieve()
    {
    DBconnection db=new DBconnection();
    try {
             Statement stmt = db.conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from staff");
                while (rs.next()) {
            String pat = rs.getString("staff_name");
            selectStaff.addItem(pat);
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null, e);
    }
    }
    
//Generating or stting Customer id 
    public void setID() {
        AutoIncreament obj = new AutoIncreament();
        int putOrd;
        putOrd = obj.auto_increment();
        int set=putOrd-1;
        ID.setText("" + set);
        ID.setHorizontalAlignment(JLabel.CENTER);
    }

    public  void setDate() //setting Assignment  date
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
    Date date = new Date();  
        String value = formatter.format(date);  
        dt.setText(value);
        dt.setHorizontalAlignment(JLabel.CENTER);
        

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
        ID = new javax.swing.JLabel();
        dt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        selectStaff = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        selectProduct = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        Status = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        assignmentTabe = new javax.swing.JTable();
        CancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(790, 320));
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 172, 37));

        ID.setBackground(new java.awt.Color(255, 255, 255));
        ID.setBorder(new javax.swing.border.MatteBorder(null));
        ID.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ID.setOpaque(true);

        dt.setBackground(new java.awt.Color(255, 255, 255));
        dt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dt.setOpaque(true);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/delivery date  copy.png"))); // NOI18N
        jLabel2.setText("Delivery Date");

        selectStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Staff" }));
        selectStaff.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectStaffItemStateChanged(evt);
            }
        });
        selectStaff.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                selectStaffMouseDragged(evt);
            }
        });
        selectStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectStaffMouseClicked(evt);
            }
        });
        selectStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectStaffActionPerformed(evt);
            }
        });

        jLabel3.setText("Qty");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/assignment date icon copy.png"))); // NOI18N
        jLabel4.setText("Assignment Date");

        qty.setText("1");
        qty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qtyMouseClicked(evt);
            }
        });
        qty.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                qtyInputMethodTextChanged(evt);
            }
        });
        qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyActionPerformed(evt);
            }
        });
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qtyKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        jLabel5.setText("Order ID:");

        jSeparator1.setBackground(new java.awt.Color(96, 203, 135));
        jSeparator1.setOpaque(true);

        jLabel6.setText("Assign to");

        selectProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select item" }));
        selectProduct.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectProductFocusGained(evt);
            }
        });
        selectProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectProductMouseClicked(evt);
            }
        });
        selectProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectProductActionPerformed(evt);
            }
        });

        jLabel7.setText("Status");

        Status.setText("Under Proccess");
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(255, 172, 37));
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/plus.png"))); // NOI18N
        addButton.setToolTipText("");
        addButton.setBorder(null);
        addButton.setOpaque(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        assignmentTabe.setBackground(new java.awt.Color(255, 172, 37));
        assignmentTabe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Quantity", "Staff", "Delivery Date"
            }
        ));
        assignmentTabe.setGridColor(new java.awt.Color(255, 181, 110));
        assignmentTabe.setSelectionBackground(new java.awt.Color(255, 172, 37));
        jScrollPane1.setViewportView(assignmentTabe);

        CancelButton.setBackground(new java.awt.Color(255, 172, 37));
        CancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/CancelB.png"))); // NOI18N
        CancelButton.setText("Cancel");
        CancelButton.setOpaque(false);
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(255, 172, 37));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/save.png"))); // NOI18N
        saveButton.setText("Go to Bill");
        saveButton.setAlignmentY(0.0F);
        saveButton.setOpaque(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });

        jDateChooser.setDateFormatString("dd-MM-yyyy");
        jDateChooser.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jDateChooserAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jButton1.setText("Add Date");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(650, 650, 650)
                .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(540, 540, 540)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jLabel4))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(dt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(selectProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(selectStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(dt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(selectProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(selectStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectStaffActionPerformed
       comboRetrieve();
    }//GEN-LAST:event_selectStaffActionPerformed

    private void selectProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectProductActionPerformed
   
        setRate();             
    }//GEN-LAST:event_selectProductActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        HOME hm=new HOME();
        hm.setVisible(true);
            dispose();
        
        
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
       
        Payment obj=new Payment();
        obj.setVisible(true);
        
    }//GEN-LAST:event_saveButtonActionPerformed

    private void selectStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectStaffMouseClicked
           
     
    }//GEN-LAST:event_selectStaffMouseClicked

    private void selectStaffItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectStaffItemStateChanged
        
    }//GEN-LAST:event_selectStaffItemStateChanged

    private void selectStaffMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectStaffMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_selectStaffMouseDragged

    private void selectProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectProductMouseClicked
          itemRetrieve();   
    }//GEN-LAST:event_selectProductMouseClicked

    private void qtyInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_qtyInputMethodTextChanged
                          
                       
       
                       
    }//GEN-LAST:event_qtyInputMethodTextChanged

    private void qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyActionPerformed
        
        
    }//GEN-LAST:event_qtyActionPerformed

    private void qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyPressed
    
    }//GEN-LAST:event_qtyKeyPressed

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
    
    }//GEN-LAST:event_qtyKeyTyped

    private void qtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyMouseClicked
    
    }//GEN-LAST:event_qtyMouseClicked

    private void selectProductFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectProductFocusGained
         itemRetrieve();
    }//GEN-LAST:event_selectProductFocusGained

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed

    private void deliveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveyDateActionPerformed
        
    }//GEN-LAST:event_deliveyDateActionPerformed

    private void deliveyDateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deliveyDateMouseEntered

    }//GEN-LAST:event_deliveyDateMouseEntered

    private void deliveyDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deliveyDateFocusGained

    }//GEN-LAST:event_deliveyDateFocusGained

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

    private void deliveyDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deliveyDateMouseClicked
        
      
    }//GEN-LAST:event_deliveyDateMouseClicked

    private void jDateChooserAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jDateChooserAncestorAdded
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jDateChooserAncestorAdded

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        assignData();
    }//GEN-LAST:event_addButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            //Calendar cals = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
            formater.format(jDateChooser.getDate());
            String d1=(formater.format(jDateChooser.getDate()));
            
            ///System Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String d2=(formatter.format(date));
            //////Comparison/////
            Date aDate=formater.parse(d1);
            Date sDate=formater.parse(d2);
            if(aDate.after(sDate) ||aDate.equals(sDate) ){
               
              System.out.println("Allowed");
              addButton.setVisible(true);
            }
            else
            {
             JOptionPane.showMessageDialog(null, "<html><body><b>Select valid delivery date ..!!</b></body></html>","Message",JOptionPane.ERROR_MESSAGE);
             addButton.setVisible(false);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(AssignmentForm.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AssignmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssignmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssignmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssignmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssignmentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel ID;
    private javax.swing.JTextField Status;
    private javax.swing.JButton addButton;
    private javax.swing.JTable assignmentTabe;
    private javax.swing.JLabel dt;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField qty;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> selectProduct;
    private javax.swing.JComboBox<String> selectStaff;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    // End of variables declaration//GEN-END:variables
}
