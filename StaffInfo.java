package mytailor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.swing.text.AbstractDocument;

/**
 *
 * @author ADMIN
 */
public class StaffInfo extends javax.swing.JFrame {

    /**
     * Creates new form StaffInfo
     */
    public StaffInfo() {
           initComponents();
          // myinitComponents();
          
           CheckNull();
           setID();
            showInfo(stafftable);
           setDate();
          
    }
   ////////////////////////////////Show on table////////////
    public void showInfo( JTable info){
    
            DBconnection db=new DBconnection(); 
            
         String q="select * from staff";// where staff_id='"+txtSearch.getText()+"'" ;
                   //'"+txt.getText()+"' ";
         try{
            PreparedStatement pst = db.conn.prepareStatement(q);
           
            ResultSet rs = pst.executeQuery();
              DefaultTableModel model = new DefaultTableModel();
          String[] columnNames = {"ID","Staff name","Address", "Contact", "CNIC No","Specialist","Date of joining"};
          model.setColumnIdentifiers(columnNames);
          info.setModel(model);
         String id = "";
         String name = "";
         String add = "";
         String con = "";
         String Cno= "";
         String spl = "";
         String doj = "";
   
         
            
           while(rs.next()) {
                 id = rs.getString("staff_id");
                 name=rs.getString("staff_name");
                 add= rs.getString("staff_addr");
                 con = rs.getString("staff_contact");
                 Cno = rs.getString("staff_cnic");
                 spl = rs.getString("specialist");
                 doj=rs.getString("staff_doj");
                 
                 System.out.println(doj);
                 model.addRow(new Object[]{id,name,add,con,Cno,spl,doj});
                 /////////////////////////////////////////////////
                 ///set data into fields/////
                
                 }
         
    }catch(SQLException e){}
 
}


////////////////////////////
/////set today's date  
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
    
    
    ///searching and showinh in table
     public void search(JTable info){
    
        DBconnection db=new DBconnection(); 
   
         String q="select * from staff where staff_id='"+txtSearch.getText()+"'" ;
                   //'"+txt.getText()+"' ";
         try{
            PreparedStatement pst = db.conn.prepareStatement(q);
           
            ResultSet rs = pst.executeQuery();
              DefaultTableModel model = new DefaultTableModel();
          String[] columnNames = {"ID","Staff name","Address", "Contact", "CNIC No","Specialist","Date of joining"};
          model.setColumnIdentifiers(columnNames);
          info.setModel(model);
         String id = "";
         String name = "";
         String add = "";
         String con = "";
         String Cno= "";
         String spl = "";
         String doj = "";
   
         
            
           while(rs.next()) {
                 id = rs.getString("staff_id");
                 name=rs.getString("staff_name");
                 add= rs.getString("staff_addr");
                 con = rs.getString("staff_contact");
                 Cno = rs.getString("staff_cini");
                 spl = rs.getString("specialist");
                 doj=rs.getString("staff_doj");
                 
                 System.out.println(doj);
                 model.addRow(new Object[]{id,name,add,con,Cno,spl,doj});
                 /////////////////////////////////////////////////
                 ///set data into fields/////
                
                 }
           staffID.setText(id);
           staffName.setText(name);
           staffAddr.setText(add);
           staffCont.setText(con);
           staffCnic.setText(Cno);
           spacialist.addItem(spl);
           dt.setText(doj);
           
    }catch(SQLException e){}
       }
   
    /////hide update button
    public void hideUpdateButton(){
    updateButton.setVisible(false);
    
    }
    //////////hide save button
    public void hideSaveButton(){
    saveButton.setVisible(false);
    
    }
    
    
    
    
    ////update method
    public void upDate(){
       
    try{
        String selected = (String)spacialist.getSelectedItem();
        
     DBconnection DB=new DBconnection();
     String sql = "Update staff SET staff_name = '"+staffName.getText()+"',staff_addr = '"+staffAddr.getText()+"',staff_contact = '"+staffCont.getText()+"',staff_cnic = '"+staffCnic.getText()+"',specialist = '"+selected+"' ,staff_doj = '"+dt.getText()+"' Where staff_id = '"+txtSearch.getText()+"'";
////     
Statement st = DB.conn.createStatement();
    int a=st.executeUpdate(sql);
    if(a>0){
    JOptionPane.showMessageDialog(null,"Updated successfully ");
    }
    }catch(SQLException e){
    
    }
     
    }
    
    ///////////////////////////////////AUTO INCREMENT////////////////////
    private void setID()
    {
        AutoIncreament obj=new AutoIncreament();
       int id=obj.auto_incrementForStaff();
       System.out.println(id);
       staffID.setText(""+id);
       
    }
    
    
    
    
    
    
    //////////////////////////////////////
    private void myinitComponents()
 { //this is for validation of Saff name entry
 ((AbstractDocument) staffName.getDocument()).setDocumentFilter(new MyDocumentFilter());
 
 }
/* This method is used to check
 * wheather database null or not!
 *   */ 
    public void CheckNull(){
     Connection con = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytailor","root","");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * from staff");

      if (rs.next() == false) {
        System.out.println("ResultSet in empty in Database");
        staffID.setText(""+101);
      } 
      else {

        do {
          String data = rs.getString("staff_name");
          System.out.println(data);
        } while (rs.next());
      }

    } catch (ClassNotFoundException | SQLException e) {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        staffName = new javax.swing.JTextField();
        staffID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        staffCont = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        staffCnic = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        staffAddr = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        spacialist = new javax.swing.JComboBox<>();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        stafftable = new javax.swing.JTable();
        searchButton = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        dt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        HOME = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Staff ");

        jPanel1.setBackground(new java.awt.Color(255, 172, 37));
        jPanel1.setAutoscrolls(true);
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 153, 0));
        jLabel1.setFont(new java.awt.Font("Trajan Pro 3", 1, 18)); // NOI18N
        jLabel1.setText("Add STAFF details ");
        jLabel1.setOpaque(true);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/Staff Prfile.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Staff ID");

        staffName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffNameActionPerformed(evt);
            }
        });
        staffName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staffNameKeyTyped(evt);
            }
        });

        staffID.setEditable(false);
        staffID.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Full Name");

        staffCont.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                staffContFocusLost(evt);
            }
        });
        staffCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffContActionPerformed(evt);
            }
        });
        staffCont.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staffContKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Address");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("CNIC number");

        staffCnic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffCnicActionPerformed(evt);
            }
        });
        staffCnic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staffCnicKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/Contact.png"))); // NOI18N

        staffAddr.setColumns(20);
        staffAddr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        staffAddr.setRows(3);
        staffAddr.setTabSize(4);
        jScrollPane1.setViewportView(staffAddr);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Specialist");

        spacialist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pant ", "Shirt", "Coat", "Safari Shirt", "Sherwani", "Pathani", "Pant & Shirt", "Shirt & Pathani", "Pant,Shirt & Pathani", "Pant,Shirt & Sherwani", "Pant,Shirt & Coat", "Pant,Shirt & Safari shirt", " ", " ", " " }));
        spacialist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spacialistActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(0, 102, 102));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(0, 102, 102));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(0, 102, 102));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        stafftable.setModel(new javax.swing.table.DefaultTableModel(
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
        stafftable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stafftableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(stafftable);

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/search.png"))); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/viewVER SMALL.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        HOME.setBackground(new java.awt.Color(0, 102, 102));
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(staffCnic, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(staffCont, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spacialist, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(67, 67, 67))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtSearch)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(staffID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dt, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(56, 56, 56)
                                        .addComponent(staffName, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(HOME, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(554, 554, 554)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(HOME)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addComponent(staffID, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(dt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(staffName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(staffCont, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(staffCnic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spacialist, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateButton)
                        .addComponent(saveButton))
                    .addComponent(cancelButton))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
      DBconnection db=new DBconnection();
      Exception e=null;
   try{
        Statement st=db.conn.createStatement();
        String selected = (String)spacialist.getSelectedItem();
        System.out.println(selected);
     
//            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//    String dte=(formater.format(staffDoj.getDate()));
    
            
            //System.out.println(dte);
    if(staffName.getText().length()!=0){
             int a=st.executeUpdate("insert into staff(staff_id,staff_name,staff_addr,staff_contact,staff_cnic,specialist,staff_doj)" +
            "values ('"+staffID.getText() +"','"+staffName.getText()+"','"+staffAddr.getText()+"','"+staffCont.getText()+"','"+staffCnic.getText()+"','"+ selected +"','"+ dt.getText()+"') ");
            if(a==1){
            JOptionPane.showMessageDialog(null,"Saved","Message",JOptionPane.INFORMATION_MESSAGE);
            showInfo(stafftable);
            }   
    
    } 
    
    
    else{
     JOptionPane.showMessageDialog(null,"Enter Name","Message",JOptionPane.INFORMATION_MESSAGE);
     staffName.requestFocusInWindow();
    }
        } catch (SQLException ex) {
            Logger.getLogger(StaffInfo.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,"Duplicate Entry!");
                    
        }
      
    }//GEN-LAST:event_saveButtonActionPerformed

    private void staffNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staffNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_staffNameKeyTyped

    private void staffContKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staffContKeyTyped
                // staffCont.setBorder(BorderFactory.createLineBorder(Color.decode("#808080")));
         char c = evt.getKeyChar(); 
if((!(Character.isDigit(c))))
{ 
evt.consume(); 
} 
String text1 = staffCont.getText();
            int length = text1.length();
            if (length == 10) {
                evt.consume();
            } else if (length > 10) {
                System.err.println("Character length is greater then specified in the database.");
            }

    }//GEN-LAST:event_staffContKeyTyped

    private void staffCnicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffCnicActionPerformed
        
    }//GEN-LAST:event_staffCnicActionPerformed

    private void staffCnicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staffCnicKeyTyped
char c = evt.getKeyChar(); 
if((!(Character.isDigit(c))))
{ 
evt.consume(); 
} 
String text1 = staffCnic.getText();
            int length = text1.length();
            if (length == 12) {
                evt.consume();
            } else if (length > 12) {
                System.err.println("Character length is greater then specified in the database.");
            }
    }//GEN-LAST:event_staffCnicKeyTyped

    private void spacialistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spacialistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spacialistActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
                    
             upDate(); //Update 
            showInfo(stafftable);
             
    }//GEN-LAST:event_updateButtonActionPerformed

    private void staffContFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staffContFocusLost
        String text1 = staffCont.getText();
            int length = text1.length();
            if (length <10) {
                JOptionPane.showMessageDialog(null, "Enter valid Mobile number!");
               staffCont.requestFocusInWindow();
            }
            
    }//GEN-LAST:event_staffContFocusLost

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
      dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
             
        
       search(stafftable);                 // TODO add your handling code here:

    }//GEN-LAST:event_searchButtonActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyTyped

    private void stafftableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stafftableMouseClicked
           DefaultTableModel model = (DefaultTableModel) stafftable.getModel();
        try {
             
            int SelectedRowIndex = stafftable.getSelectedRow();      
            String rowIndex=(model.getValueAt(SelectedRowIndex, 0).toString());
            System.out.println("This is selected row: "+rowIndex);
            
            DBconnection db=new DBconnection();
            String q="select * from staff where staff_id='"+rowIndex+"'";// OR ordno='"+txtSearch.getText()+"'" ;
            PreparedStatement pst = db.conn.prepareStatement(q);
            ResultSet rs = pst.executeQuery();
                     String id = "";
                     String name = "";
                     String add = "";
                     String con = "";
                     String Cno= "";
                     String spl = "";
                     String doj = "";  
            while(rs.next()) {
                 id = rs.getString("staff_id");
                 name=rs.getString("staff_name");
                 add= rs.getString("staff_addr");
                 con = rs.getString("staff_contact");
                 Cno = rs.getString("staff_cnic");
                 spl = rs.getString("specialist");
                 doj=rs.getString("staff_doj");
            }
             staffID.setText(id);
           staffName.setText(name);
           staffAddr.setText(add);
           staffCont.setText(con);
           staffCnic.setText(Cno);
           spacialist.addItem(spl);
           dt.setText(doj);
           txtSearch.setText(rowIndex);
            
        } catch (SQLException ex) {
            Logger.getLogger(customer_info.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_stafftableMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        showInfo(stafftable);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void staffContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffContActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffContActionPerformed

    private void staffNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffNameActionPerformed

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
            java.util.logging.Logger.getLogger(StaffInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HOME;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel dt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> spacialist;
    private javax.swing.JTextArea staffAddr;
    private javax.swing.JTextField staffCnic;
    private javax.swing.JTextField staffCont;
    private javax.swing.JTextField staffID;
    private javax.swing.JTextField staffName;
    private javax.swing.JTable stafftable;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
