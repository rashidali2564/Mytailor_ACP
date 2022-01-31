/*
 * To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytailor;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author ADMIN
 */
public class customer_info extends javax.swing.JFrame {

    /**
     * Creates new form customer_info
     */
    //static int put;
    public customer_info() {
         initComponents();
         save.setVisible(false);
         myinitComponents();
         AutoIncreament obj=new AutoIncreament();
         int putOrd;
         putOrd = obj.auto_increment();
         cusOrdno.setText(""+putOrd);
        
        
       
    }
    
    
    ////////////////////Uddate query\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void upDate(){
       
    try{
     	
         
     String c_code=(String) countryCode.getSelectedItem();
     DBconnection DB=new DBconnection();
     String sql1 = "Update cust_info SET cust_name = '"+txtResult.getText()+"',cust_add = '"+cusAddr.getText()+"',countryCode = '"+c_code+"',cust_contact = '"+cusCont.getText()+"' where ordno='"+cusOrdno.getText()+"'";
     String sql2 = "Update measurement SET neck='"+neck.getText()+"',length='"+length.getText()+"',hip='"+hip.getText()+"',sleeve='"+sleeve.getText()+"',coller='"+coller.getText()+"',stomach='"+stomach.getText()+"',shoulder='"+shoulder.getText()+"',bicep='"+bicep.getText()+"',cuff='"+cuff.getText()+"',chest='"+chest.getText()+"',waist='"+waist.getText()+"',thigh='"+thigh.getText()+"',pant_length='"+pant_length.getText()+"',knee='"+knee.getText()+"',bottom='"+bottom.getText()+"',rise='"+rise.getText()+"',pnote='"+note.getText()+"' where ordno='"+cusOrdno.getText()+"'";
     Statement st = DB.conn.createStatement();
    int a=st.executeUpdate(sql1);
    int b=st.executeUpdate(sql2);

    if(a>0 && b>0){
    JOptionPane.showMessageDialog(null,"Updated successfully ");
    }
    }catch(SQLException e){
    
    }
     
    }
    /////////////////////////
    
    
    ///////////////////Panelhiding
    public void hidePane(){
    upDatePanel.setVisible(false);
    }
    
    /////////////hiding button
    public void hideButton()
    {
    saveButton.setVisible(true);
   //saveOnly.disable();
    
    }
   
    
   ////////////////Getting data From customer an measurement table/////////////////////// 
    void getData(){
          cusName.setVisible(false);
        try {
             
            
            
            DBconnection db=new DBconnection();
            String q="select * from cust_info where ordno='"+txtSearch.getText()+"'" ;
            String q2="select * from measurement where ordno='"+txtSearch.getText()+"'" ;
            PreparedStatement pst = db.conn.prepareStatement(q);
            PreparedStatement pst2 = db.conn.prepareStatement(q2);
            ResultSet rs = pst.executeQuery();
            ResultSet rs2 = pst2.executeQuery();
            
              while(rs.next()&& rs2.next()) {
                  //Customer info
                cusOrdno.setText(rs.getString("ordno"));
                txtResult.setText( rs.getString("cust_name"));
                cusAddr.setText(rs.getString("cust_add"));
                cusCont.setText( rs.getString("cust_contact"));
                //measurement info
                neck.setText(rs2.getString("neck"));
                length.setText(rs2.getString("length"));
                hip.setText(rs2.getString("hip"));
                sleeve.setText(rs2.getString("sleeve"));
                coller.setText(rs2.getString("coller"));
                stomach.setText(rs2.getString("stomach"));
                shoulder.setText(rs2.getString("shoulder"));
                bicep.setText(rs2.getString("bicep"));
                cuff.setText(rs2.getString("cuff"));
                chest.setText(rs2.getString("chest"));
                length.setText(rs2.getString("length"));
                waist.setText(rs2.getString("waist"));
                thigh.setText(rs2.getString("thigh"));
                pant_length.setText(rs2.getString("pant_length"));
                knee.setText(rs2.getString("knee"));
                bottom.setText(rs2.getString("bottom"));
                rise.setText(rs2.getString("rise"));
                note.setText(rs2.getString("pnote"));
                
                
            }
              
                      
        } catch (SQLException ex) {
            Logger.getLogger(customer_info.class.getName()).log(Level.SEVERE, null, ex);
           
        }

    }
    

    
     
    /////////////////Searching//////////////////
    public void searchByOrderNo( JTable info){
    
          DBconnection db=new DBconnection(); 
   
       String q="select * from cust_info where  ordno='"+txtSearch.getText()+"'" ;
               //'"+txt.getText()+"' ";
    try{
            PreparedStatement pst = db.conn.prepareStatement(q);
            pst.setString(1,txtSearch.getText());
            ResultSet rs = pst.executeQuery();
              DefaultTableModel model = new DefaultTableModel();
          String[] columnNames = {"ID", "Name", "Address", "Country","Contact"};
          model.setColumnIdentifiers(columnNames);
          info.setModel(model);
        String id = "";
        String name = "";
        String add = "";
        String cous = "";
         String contact = "";
   
         
            
           while(rs.next()) {
                id = rs.getString("ordno");
                name = rs.getString("cust_name");
                add = rs.getString("cust_add");
                cous = rs.getString("countryCode");
                contact = rs.getString("cust_contact");
                model.addRow(new Object[]{id,name, add, cous,contact});

            }
    }catch(SQLException e){}
    //JOptionPane.showMessageDialog(null,"Ord No. is required !");
      
    }
    
    
    ////////////SEARCH BY NAME
     public void searchByName( JTable info){
    
          DBconnection db=new DBconnection(); 
   
       String q="select * from cust_info where cust_name= ? "; 
    try{
            PreparedStatement pst = db.conn.prepareStatement(q);
            pst.setString(1,txtSearch.getText());
            ResultSet rs = pst.executeQuery();
             DefaultTableModel model = new DefaultTableModel();
          String[] columnNames = {"ID", "Name", "Address", "Country","Contact"};
          model.setColumnIdentifiers(columnNames);
          info.setModel(model);
        String id = "";
        String name = "";
        String add = "";
        String cous = "";
         String contact = "";     
   
         
            
           while(rs.next()) {
                id = rs.getString("ordno");
                name = rs.getString("cust_name");
                add = rs.getString("cust_add");
                cous = rs.getString("countryCode");
                contact = rs.getString("cust_contact");
                model.addRow(new Object[]{id,name, add, cous,contact});

    }
    }catch(SQLException e){}
             
    
  }
    
    
    
    //Fetch info for Displaying On Payment form
    public void showInfo( JTable info){
    try{
            DBconnection db=new DBconnection(); 
            String q="select * from cust_info ";
            Statement st=db.conn.createStatement();
            ResultSet rs=st.executeQuery(q);
          // info.setModel(DbUtils.resultSetToTableModel(rs));
          ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\\
           DefaultTableModel model = new DefaultTableModel();
          String[] columnNames = {"ID", "Name", "Address", "Country","Contact"};
          model.setColumnIdentifiers(columnNames);
          info.setModel(model);
        String id = "";
        String name = "";
        String add = "";
        String cous = "";
         String contact = "";
   
         
            
           while(rs.next()) {
                id = rs.getString("ordno");
                name = rs.getString("cust_name");
                add = rs.getString("cust_add");
                cous = rs.getString("countryCode");
                contact = rs.getString("cust_contact");
                model.addRow(new Object[]{id,name, add, cous,contact});
                
            
            
            }
         
    }catch(SQLException e){}
}
    
    
    ///Text Clear/////////////////////
    public void allClear(){
    
    
        cusName.setText(" ");
       //cusOrdno.setText("");
      cusAddr.setText("");
       cusCont.setText("");
       sleeve.setText("");
       hip.setText("");
       length.setText("");
       knee.setText("");     
       cuff.setText("");
       rise.setText("");
       neck.setText("");
       chest.setText("");
       pant_length.setText("");
       coller.setText("");
       shoulder.setText("");
       thigh.setText("");
       waist.setText("");
       bicep.setText("");
       bottom.setText("");
       stomach.setText("");
        note.setText("");
             
    }
    
    
    
    /////////////////Saving
    public void ShowSaveOnlyButton(){
    save.setVisible(true);
    }
    
    public void hideSButton(){
    saveButton.setVisible(false);
    }
    
    ////////////////////////////////////////////
    public void hideMeasurementPanel(){
    
    measureMentPane.setVisible(false);
    }
    
 private void myinitComponents()
 {
 ((AbstractDocument) cusName.getDocument()).setDocumentFilter(new MyDocumentFilter());
 
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MasterPanel = new javax.swing.JPanel();
        measureMentPane = new javax.swing.JPanel();
        length = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        stomach = new javax.swing.JTextField();
        cuff = new javax.swing.JTextField();
        bicep = new javax.swing.JTextField();
        shoulder = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        waist = new javax.swing.JTextField();
        thigh = new javax.swing.JTextField();
        bottom = new javax.swing.JTextField();
        pant_length = new javax.swing.JTextField();
        knee = new javax.swing.JTextField();
        rise = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        neck = new javax.swing.JTextField();
        chest = new javax.swing.JTextField();
        hip = new javax.swing.JTextField();
        sleeve = new javax.swing.JTextField();
        coller = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        note = new javax.swing.JTextArea();
        customerInfoPane = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        cusOrdno = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        cusCont = new javax.swing.JTextField();
        countryCode = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        cusAddr = new javax.swing.JTextArea();
        cusName = new javax.swing.JTextField();
        txtResult = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        upDatePanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        clearButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Assing = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Measurement || update || Customer Information");

        MasterPanel.setBackground(new java.awt.Color(204, 204, 255));

        measureMentPane.setBackground(new java.awt.Color(255, 255, 204));
        measureMentPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Measurement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        measureMentPane.setToolTipText("");
        measureMentPane.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        length.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        length.setNextFocusableComponent(chest);
        length.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lengthActionPerformed(evt);
            }
        });
        length.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lengthKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/pant.png"))); // NOI18N
        jLabel1.setText("Lower Body");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/chest.png"))); // NOI18N
        jLabel2.setText("Chest");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/hipman.png"))); // NOI18N
        jLabel3.setText("      Hip");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/Sleeve-Length-Measurement.png"))); // NOI18N
        jLabel4.setText("   Sleeve");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/coller.png"))); // NOI18N
        jLabel5.setText("Collar");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Note :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/shirt chest.png"))); // NOI18N
        jLabel7.setText("Stomach");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/shoulder6.png"))); // NOI18N
        jLabel8.setText("Shoulder");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/Bisep.png"))); // NOI18N
        jLabel9.setText("Bicep ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/cuff.png"))); // NOI18N
        jLabel10.setText("Cuff");

        stomach.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        stomach.setNextFocusableComponent(shoulder);
        stomach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stomachActionPerformed(evt);
            }
        });
        stomach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stomachKeyTyped(evt);
            }
        });

        cuff.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cuff.setNextFocusableComponent(pant_length);
        cuff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuffActionPerformed(evt);
            }
        });
        cuff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuffKeyTyped(evt);
            }
        });

        bicep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bicep.setNextFocusableComponent(coller);
        bicep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bicepActionPerformed(evt);
            }
        });
        bicep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bicepKeyTyped(evt);
            }
        });

        shoulder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        shoulder.setNextFocusableComponent(sleeve);
        shoulder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shoulderActionPerformed(evt);
            }
        });
        shoulder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shoulderKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/waistman.png"))); // NOI18N
        jLabel11.setText("Waist");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/thigh.png"))); // NOI18N
        jLabel12.setText("Thigh");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/bottom copy.png"))); // NOI18N
        jLabel13.setText("Bottom");

        waist.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        waist.setNextFocusableComponent(thigh);
        waist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waistActionPerformed(evt);
            }
        });
        waist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                waistKeyTyped(evt);
            }
        });

        thigh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        thigh.setNextFocusableComponent(knee);
        thigh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                thighKeyTyped(evt);
            }
        });

        bottom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bottom.setNextFocusableComponent(note);
        bottom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bottomKeyTyped(evt);
            }
        });

        pant_length.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pant_length.setNextFocusableComponent(waist);
        pant_length.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pant_lengthActionPerformed(evt);
            }
        });
        pant_length.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pant_lengthKeyTyped(evt);
            }
        });

        knee.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        knee.setNextFocusableComponent(hip);
        knee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kneeActionPerformed(evt);
            }
        });
        knee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kneeKeyTyped(evt);
            }
        });

        rise.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rise.setNextFocusableComponent(bottom);
        rise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riseActionPerformed(evt);
            }
        });
        rise.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                riseKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/pant lenght copy.png"))); // NOI18N
        jLabel16.setText("Length");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/knee.png"))); // NOI18N
        jLabel17.setText("Knee");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/rise copy.png"))); // NOI18N
        jLabel18.setText("Rise");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/neck.png"))); // NOI18N
        jLabel14.setText("Neck");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/mansuit.png"))); // NOI18N
        jLabel15.setText("Upper Body");

        neck.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        neck.setNextFocusableComponent(cuff);
        neck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neckActionPerformed(evt);
            }
        });
        neck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                neckKeyTyped(evt);
            }
        });

        chest.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chest.setNextFocusableComponent(stomach);
        chest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chestActionPerformed(evt);
            }
        });
        chest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                chestKeyTyped(evt);
            }
        });

        hip.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        hip.setNextFocusableComponent(rise);
        hip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hipActionPerformed(evt);
            }
        });
        hip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hipKeyTyped(evt);
            }
        });

        sleeve.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sleeve.setNextFocusableComponent(bicep);
        sleeve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sleeveActionPerformed(evt);
            }
        });
        sleeve.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sleeveKeyTyped(evt);
            }
        });

        coller.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        coller.setNextFocusableComponent(neck);
        coller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collerActionPerformed(evt);
            }
        });
        coller.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                collerKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/Shirt length.png"))); // NOI18N
        jLabel19.setText("Length");

        note.setColumns(20);
        note.setRows(5);
        note.setNextFocusableComponent(saveButton);
        jScrollPane1.setViewportView(note);

        javax.swing.GroupLayout measureMentPaneLayout = new javax.swing.GroupLayout(measureMentPane);
        measureMentPane.setLayout(measureMentPaneLayout);
        measureMentPaneLayout.setHorizontalGroup(
            measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(measureMentPaneLayout.createSequentialGroup()
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(length, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stomach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sleeve, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(coller, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chest, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shoulder, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bicep, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(neck, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pant_length, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thigh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hip, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, measureMentPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(waist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(knee, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rise, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(measureMentPaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(cuff, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(251, 251, 251)
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        measureMentPaneLayout.setVerticalGroup(
            measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(measureMentPaneLayout.createSequentialGroup()
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(measureMentPaneLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(length, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(stomach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(sleeve, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(coller, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(measureMentPaneLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(measureMentPaneLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(chest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(shoulder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(bicep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(neck, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(pant_length, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(thigh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(hip, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(measureMentPaneLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel17))))
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(waist, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(knee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rise, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 2, 2)
                .addGroup(measureMentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuff, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(measureMentPaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        customerInfoPane.setBackground(new java.awt.Color(255, 255, 153));
        customerInfoPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        customerInfoPane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customerInfoPane.setPreferredSize(new java.awt.Dimension(1380, 770));
        customerInfoPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setText("Customer Name");
        customerInfoPane.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 22, 110, 30));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setText("Address");
        customerInfoPane.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 63, 90, 24));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel64.setText("Ordre No.");
        customerInfoPane.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 90, 40));

        cusOrdno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cusOrdno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        customerInfoPane.add(cusOrdno, new org.netbeans.lib.awtextra.AbsoluteConstraints(684, 26, 179, 30));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel65.setText("Contact number");
        customerInfoPane.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 141, -1, 24));

        cusCont.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cusCont.setNextFocusableComponent(length);
        cusCont.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cusContFocusLost(evt);
            }
        });
        cusCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusContActionPerformed(evt);
            }
        });
        cusCont.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cusContKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cusContKeyTyped(evt);
            }
        });
        customerInfoPane.add(cusCont, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 130, 30));

        countryCode.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        countryCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PK+92" }));
        customerInfoPane.add(countryCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 70, 30));

        cusAddr.setColumns(20);
        cusAddr.setLineWrap(true);
        cusAddr.setRows(5);
        cusAddr.setWrapStyleWord(true);
        cusAddr.setNextFocusableComponent(cusCont);
        cusAddr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cusAddrKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(cusAddr);

        customerInfoPane.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 200, 70));

        cusName.setNextFocusableComponent(cusAddr);
        cusName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusNameActionPerformed(evt);
            }
        });
        customerInfoPane.add(cusName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 200, 30));

        txtResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResultActionPerformed(evt);
            }
        });
        customerInfoPane.add(txtResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 200, 30));

        save.setBackground(new java.awt.Color(0, 102, 102));
        save.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/next_press.png"))); // NOI18N
        save.setText("Save");
        save.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        save.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        save.setNextFocusableComponent(clearButton);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(0, 102, 102));
        saveButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/next_press.png"))); // NOI18N
        saveButton.setText("Next");
        saveButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        saveButton.setIconTextGap(0);
        saveButton.setNextFocusableComponent(clearButton);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        upDatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fashion/designing/system/image/viewVER SMALL.png"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        upDatePanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 30));

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        upDatePanel.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 0, 126, -1));

        updateButton.setBackground(new java.awt.Color(153, 153, 255));
        updateButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        upDatePanel.add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 317, 81, 31));

        txtSearch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        upDatePanel.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 1, 200, -1));

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

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
        jScrollPane3.setViewportView(jTable1);

        upDatePanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 364, 272));

        clearButton.setBackground(new java.awt.Color(153, 153, 255));
        clearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        clearButton.setText("Clear");
        clearButton.setNextFocusableComponent(cusName);
        clearButton.setOpaque(false);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        upDatePanel.add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 317, 71, 31));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Assing.setBackground(new java.awt.Color(0, 102, 102));
        Assing.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Assing.setText("Assing");
        Assing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MasterPanelLayout = new javax.swing.GroupLayout(MasterPanel);
        MasterPanel.setLayout(MasterPanelLayout);
        MasterPanelLayout.setHorizontalGroup(
            MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MasterPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Assing, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(saveButton)
                        .addGap(40, 40, 40)
                        .addComponent(save)
                        .addGap(659, 659, 659))
                    .addGroup(MasterPanelLayout.createSequentialGroup()
                        .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(customerInfoPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(measureMentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(upDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        MasterPanelLayout.setVerticalGroup(
            MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MasterPanelLayout.createSequentialGroup()
                        .addComponent(customerInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(measureMentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(upDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(MasterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Assing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        measureMentPane.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MasterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MasterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

        txtResult.setText("");
        cusName.setText("");
        //cusOrdno.setText("");
        cusAddr.setText("");
        cusCont.setText("");
        sleeve.setText("");
        hip.setText("");
        length.setText("");
        knee.setText("");
        cuff.setText("");
        rise.setText("");
        neck.setText("");
        chest.setText("");
        pant_length.setText("");
        coller.setText("");
        shoulder.setText("");
        thigh.setText("");
        waist.setText("");
        bicep.setText("");
        bottom.setText("");
        stomach.setText("");
        note.setText("");

    }//GEN-LAST:event_clearButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
              String sneck,schest,ship,ssleeve,scoller,slength,sstomach,sshoulder,sbicep,scuff;
        String pwaist,pthigh,pbottom,Pant_length,pknee,prise;
        String pnote,c_code;
        String cusID;
        //Upper body measurement
        sneck=neck.getText();
        schest=chest.getText();
        ship=hip.getText();
        ssleeve=sleeve.getText();
        scoller=coller.getText();
        slength=length.getText();
        sstomach=stomach.getText();
        sshoulder=shoulder.getText();
        sbicep=bicep.getText();
        scuff=cuff.getText();
        //Lower body measurement
        pwaist=waist.getText();
        pthigh=thigh.getText();
        pbottom=bottom.getText();
        Pant_length=pant_length.getText();
        pknee=knee.getText();
        prise=rise.getText();

        //Notes
        pnote=note.getText();
        //Customer Information
        String c_name,c_addr,c_ordno,c_contact;
        c_name=cusName.getText();
        c_addr=cusAddr.getText();
        c_code=(String) countryCode.getSelectedItem();
        c_ordno=cusOrdno.getText();
        

        c_contact=cusCont.getText();
        //int cus_contact=Integer.parseInt(c_contact);
        Connection conn;
        conn=null;
        //PreparedStatement pst=null;
        ResultSet rs=null;

        //Customer info  validations
        if(c_name.equals("")  ){
            JOptionPane.showMessageDialog(null,"Enter name !"); //customer name validation

            cusName.requestFocusInWindow();
            cusName.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
        }
        else if (c_addr.equals("") )
        {
            JOptionPane.showMessageDialog(null,"Fill Address !"); //customer address validation
            cusAddr.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
            cusAddr.requestFocusInWindow();
        }
        else if (c_contact.equals("") )
        {
            JOptionPane.showMessageDialog(null,"Check Contact Number !");//customer contact validation
            cusCont.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
            cusCont.requestFocusInWindow();
        }
        else if (c_contact.length()<10 ){
            JOptionPane.showMessageDialog(null,"Check Contact Number !");//customer contact validation
            cusCont.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
            cusCont.requestFocusInWindow();
        }

        else{

            //Database Connectivity and insertion query //

            try {
                //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mytailor","root","");
                Statement st=conn.createStatement();
                Statement st1=conn.createStatement();

                rs=st.executeQuery("select * from cust_info");
                while(rs.next())
                {
                    //Fetching data from database
                    cusID = rs.getString("ordno");
                    // JOptionPane.showMessageDialog(null,"Records saved!"+cusID);
                }

                int a=st.executeUpdate("insert into cust_info(ordno,cust_name,cust_add,countryCode,cust_contact)" +
                    "values ('"+c_ordno+"','"+c_name+"','"+c_addr+"','"+c_code+"','"+c_contact+"') ");
                String m_ordno=c_ordno;

                int b=st1.executeUpdate("insert into measurement(neck,length,hip,sleeve,coller,stomach,shoulder,bicep,cuff,chest,waist,thigh,pant_length,knee,bottom,rise,pnote,ordno)" +
                    "values ('"+sneck+"','"+slength+"','"+ship+"','"+ssleeve+"','"+scoller+"','"+sstomach+"','"+sshoulder+"','"+sbicep+"','"+scuff+"','"+schest+"','"+pwaist+"','"+pthigh+"','"+Pant_length+"','"+pknee+"','"+pbottom+"','"+prise+"','"+pnote+"','"+m_ordno+"')");
                if (a==1 && b==1)
                {
                    JOptionPane.showMessageDialog(null,"Records saved!");
                   
                }
                else
                {

                    JOptionPane.showMessageDialog(null,"Something were wrong!");
                }
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null,"Check entered information !");
            }

        }



    }//GEN-LAST:event_saveButtonActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String sneck,schest,ship,ssleeve,scoller,slength,sstomach,sshoulder,sbicep,scuff;
        String pwaist,pthigh,pbottom,Pant_length,pknee,prise;
        String pnote,c_code;
               String cusID;
        //Upper body measurement
        sneck=neck.getText();
        schest=chest.getText();
        ship=hip.getText();
        ssleeve=sleeve.getText();
        scoller=coller.getText();
        slength=length.getText();
        sstomach=stomach.getText();
        sshoulder=shoulder.getText();
        sbicep=bicep.getText();
        scuff=cuff.getText();
        //Lower body measurement
        pwaist=waist.getText();
        pthigh=thigh.getText();
        pbottom=bottom.getText();
        Pant_length=pant_length.getText();
        pknee=knee.getText();
        prise=rise.getText();

        //Notes
        pnote=note.getText();
        //Customer Information
        String c_name,c_addr,c_ordno,c_contact;
        c_name=cusName.getText();
        c_addr=cusAddr.getText();
        c_code=(String) countryCode.getSelectedItem();
        c_ordno=cusOrdno.getText();
        // int cus_ordno=Integer.parseInt(c_ordno);

        c_contact=cusCont.getText();
        //int cus_contact=Integer.parseInt(c_contact);
        Connection conn;
        conn=null;
        //PreparedStatement pst=null;
        ResultSet rs=null;

        //Customer info  validations
        if(c_name.equals("")  ){
            JOptionPane.showMessageDialog(null,"Enter name !"); //customer name validation

            cusName.requestFocusInWindow();
            cusName.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
        }
        else if (c_addr.equals("") )
        {
            JOptionPane.showMessageDialog(null,"Fill Address !"); //customer address validation
            cusAddr.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
            cusAddr.requestFocusInWindow();
        }
        else if (c_contact.equals("") )
        {
            JOptionPane.showMessageDialog(null,"Check Contact Number !");//customer contact validation
            cusCont.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
            cusCont.requestFocusInWindow();
        }
        else if (c_contact.length()<10 ){
            JOptionPane.showMessageDialog(null,"Check Contact Number !");//customer contact validation
            cusCont.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
            cusCont.requestFocusInWindow();
        }

        else{

            //Database Connectivity and insertion query //

            try {
                //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mytailor","root","");
                Statement st=conn.createStatement();
                Statement st1=conn.createStatement();

                rs=st.executeQuery("select * from cust_info");
                while(rs.next())
                {
                    //Fetching data from database
                    cusID = rs.getString("ordno");
                    // JOptionPane.showMessageDialog(null,"Records saved!"+cusID);
                }

                int a=st.executeUpdate("insert into cust_info(ordno,cust_name,cust_add,countryCode,cust_contact)" +
                    "values ('"+c_ordno+"','"+c_name+"','"+c_addr+"','"+c_code+"','"+c_contact+"') ");
                String m_ordno=c_ordno;

                int b=st1.executeUpdate("insert into measurement(neck,length,hip,sleeve,coller,stomach,shoulder,bicep,cuff,chest,waist,thigh,pant_length,knee,bottom,rise,pnote,ordno)" +
                    "values ('"+sneck+"','"+slength+"','"+ship+"','"+ssleeve+"','"+scoller+"','"+sstomach+"','"+sshoulder+"','"+sbicep+"','"+scuff+"','"+schest+"','"+pwaist+"','"+pthigh+"','"+Pant_length+"','"+pknee+"','"+pbottom+"','"+prise+"','"+pnote+"','"+m_ordno+"')");
                if (a==1 && b==1)
                {
                    JOptionPane.showMessageDialog(null,"Records saved!");
                    AssignmentForm asignForm=new AssignmentForm();
                    asignForm.setVisible(true);
                    

                }
                else
                {

                    JOptionPane.showMessageDialog(null,"Something were wrong!");
                }
            }catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null,e);
            }

        }
    }//GEN-LAST:event_saveActionPerformed

    private void cusAddrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cusAddrKeyPressed
        int key = evt.getKeyCode();

        if (key == 9) //Avoiding tab in textArea
        {
            cusCont.requestFocusInWindow();
        }
    }//GEN-LAST:event_cusAddrKeyPressed

    private void cusContKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cusContKeyTyped
        cusCont.setBorder(BorderFactory.createLineBorder(Color.decode("#808080")));
        char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = cusCont.getText();
        int length = text1.length();
        if (length == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_cusContKeyTyped

    private void cusContKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cusContKeyPressed

    }//GEN-LAST:event_cusContKeyPressed

    private void cusContFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cusContFocusLost
        String text1 = cusCont.getText();
        int lengt = text1.length();
        if (lengt < 10) {
            JOptionPane.showMessageDialog(null,"Check Contact Number !");//customer contact validation
            cusCont.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
            cusCont.requestFocusInWindow();
        }
    }//GEN-LAST:event_cusContFocusLost

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void collerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_collerActionPerformed

    private void sleeveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sleeveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sleeveActionPerformed

    private void hipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hipActionPerformed

    private void chestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chestActionPerformed

    private void neckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_neckActionPerformed

    private void riseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_riseActionPerformed

    private void kneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kneeActionPerformed

    private void pant_lengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pant_lengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pant_lengthActionPerformed

    private void waistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_waistActionPerformed

    private void shoulderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shoulderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shoulderActionPerformed

    private void bicepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bicepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bicepActionPerformed

    private void cuffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuffActionPerformed

    private void stomachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stomachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stomachActionPerformed

    private void lengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lengthActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
          searchByOrderNo(jTable1);
           /////getting data from customer table into textfields
            getData();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchByName(jTable1);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
              getData();        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
           if(txtResult.getText().length()!=0)
           {
         upDate();
           }
           else
           {
               JOptionPane.showMessageDialog(null,"Give input to update !");
           }
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        cusName.setVisible(false);
           DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
             
            int SelectedRowIndex = jTable1.getSelectedRow();      
            String rowIndex=(model.getValueAt(SelectedRowIndex, 0).toString());
            System.out.println("This is selected row: "+rowIndex);
            
            DBconnection db=new DBconnection();
            String q="select * from cust_info where ordno='"+rowIndex+"'";
            String q2="SELECT * FROM `measurement` where ordno='"+rowIndex+"'";
            PreparedStatement pst = db.conn.prepareStatement(q);
            ResultSet rs = pst.executeQuery();           
            PreparedStatement pst2 = db.conn.prepareStatement(q2);
            ResultSet rs2 = pst2.executeQuery();
            while(rs.next() && rs2.next()) {
                cusOrdno.setText(rs.getString("ordno"));
                txtResult.setText( rs.getString("cust_name"));
                cusAddr.setText(rs.getString("cust_add"));
                cusCont.setText( rs.getString("cust_contact"));
                
                //measurement info
                
                neck.setText(rs2.getString("neck"));
                length.setText(rs2.getString("length"));
                hip.setText(rs2.getString("hip"));
                sleeve.setText(rs2.getString("sleeve"));
                coller.setText(rs2.getString("coller"));
                stomach.setText(rs2.getString("stomach"));
                shoulder.setText(rs2.getString("shoulder"));
                bicep.setText(rs2.getString("bicep"));
                cuff.setText(rs2.getString("cuff"));
                chest.setText(rs2.getString("chest"));
                length.setText(rs2.getString("length"));
                waist.setText(rs2.getString("waist"));
                thigh.setText(rs2.getString("thigh"));
                pant_length.setText(rs2.getString("pant_length"));
                knee.setText(rs2.getString("knee"));
                bottom.setText(rs2.getString("bottom"));
                rise.setText(rs2.getString("rise"));
                note.setText(rs2.getString("pnote"));
              
            }
             
            
        } catch (SQLException ex) {
            Logger.getLogger(customer_info.class.getName()).log(Level.SEVERE, null, ex);
        }

         
    }//GEN-LAST:event_jTable1MouseClicked

    private void cusNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusNameActionPerformed

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
       
    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void txtResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultActionPerformed

    private void cusContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusContActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusContActionPerformed

    private void lengthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lengthKeyTyped

            char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = length.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_lengthKeyTyped

    private void stomachKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stomachKeyTyped
        char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = stomach.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_stomachKeyTyped

    private void sleeveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sleeveKeyTyped
         char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = sleeve.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_sleeveKeyTyped

    private void collerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_collerKeyTyped
         char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = coller.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_collerKeyTyped

    private void cuffKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuffKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 =cuff.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_cuffKeyTyped

    private void chestKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chestKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = chest.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_chestKeyTyped

    private void shoulderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shoulderKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = shoulder.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_shoulderKeyTyped

    private void bicepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bicepKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = bicep.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_bicepKeyTyped

    private void neckKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_neckKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = neck.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_neckKeyTyped

    private void pant_lengthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pant_lengthKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = pant_length.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_pant_lengthKeyTyped

    private void thighKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thighKeyTyped
         char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = thigh.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_thighKeyTyped

    private void hipKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hipKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = hip.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_hipKeyTyped

    private void bottomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bottomKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = bottom.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_bottomKeyTyped

    private void waistKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_waistKeyTyped
         char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = waist.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_waistKeyTyped

    private void kneeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kneeKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = knee.getText();
        int length = text1.length();
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_kneeKeyTyped

    private void riseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_riseKeyTyped
          char c = evt.getKeyChar();
        if((!(Character.isDigit(c))))
        {
            evt.consume();
        }
        String text1 = rise.getText();
        int length = text1.length();
        
        if (length == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_riseKeyTyped

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        showInfo(jTable1);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       HOME hm=new HOME();
        hm.setVisible(true);
            dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AssingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssingActionPerformed
        // TODO add your handling code here:
         AssignmentForm asignForm=new AssignmentForm();
                    asignForm.setVisible(true);
                    this.hide();
    }//GEN-LAST:event_AssingActionPerformed

            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      /*AutoIncreament obj=new AutoIncreament();
      int putOrd;
        putOrd = obj.auto_increment();*/
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
            java.util.logging.Logger.getLogger(customer_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customer_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customer_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customer_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new customer_info().setVisible(true);
                
            }
        });
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Assing;
    private javax.swing.JPanel MasterPanel;
    private javax.swing.JTextField bicep;
    private javax.swing.JTextField bottom;
    private javax.swing.JTextField chest;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField coller;
    private javax.swing.JComboBox<String> countryCode;
    private javax.swing.JTextField cuff;
    private javax.swing.JTextArea cusAddr;
    private javax.swing.JTextField cusCont;
    private javax.swing.JTextField cusName;
    private javax.swing.JTextField cusOrdno;
    private javax.swing.JPanel customerInfoPane;
    private javax.swing.JTextField hip;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField knee;
    private javax.swing.JTextField length;
    public javax.swing.JPanel measureMentPane;
    private javax.swing.JTextField neck;
    private javax.swing.JTextArea note;
    private javax.swing.JTextField pant_length;
    private javax.swing.JTextField rise;
    private javax.swing.JButton save;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField shoulder;
    private javax.swing.JTextField sleeve;
    private javax.swing.JTextField stomach;
    private javax.swing.JTextField thigh;
    private javax.swing.JTextField txtResult;
    public javax.swing.JTextField txtSearch;
    private javax.swing.JPanel upDatePanel;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField waist;
    // End of variables declaration//GEN-END:variables
}
class MyDocumentFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
        for (int n = string.length(); n > 0; n--) {//an inserted string may be more than a single character i.e a copy and paste of 'aaa123d', also we iterate from the back as super.XX implementation will put last insterted string first and so on thus 'aa123d' would be 'daa', but because we iterate from the back its 'aad' like we want
            char c = string.charAt(n - 1);//get a single character of the string
            
            if (Character.isAlphabetic(c) || c == ' ') {//if its an alphabetic character or white space
                super.replace(fb, i, i1, String.valueOf(c), as);//allow update to take place for the given character
            } else {//it was not an alphabetic character or white space
                JOptionPane.showMessageDialog(null,"Numbers Not Allowed!","CONSIDER", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void remove(FilterBypass fb, int i, int i1) throws BadLocationException {
        super.remove(fb, i, i1);
    }

    @Override
    public void insertString(FilterBypass fb, int i, String string, AttributeSet as) throws BadLocationException {
        super.insertString(fb, i, string, as);

    }
}
