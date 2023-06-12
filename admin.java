import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;  
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.Timer;
import java.util.TimerTask;
public class Admin {
    static final String DB_URL="jdbc:mysql://localhost/Project1";
    static final String USER="root";
    static final String PASS="pass";
    private static JTable voterListTable;
    private static JPanel panel;
    private static JLabel votesPolledLabel;
    private JLabel totalVotesLabel;
    private JLabel votingPercentageLabel;
    private JLabel oldNameLabel, newNameLabel;
    private JTextField oldNameField, newNameField;
    JFrame frame3=new JFrame();
    JFrame frame5=new JFrame();
    JFrame frame2=new JFrame();
    static JFrame frame_del=new JFrame();
    private JButton changeButton;
    static JFrame frame6=new JFrame();
    static int data1=0;
    static int countdownSeconds = 10;
public void palagarismlist() throws SQLException{
    JFrame frame=new JFrame();
    String[] columnNames = {"Epic Number", "First Name", "Last Name", "Date of Birth", "Gender", "Religion",
    "Aadhar Number", "Father Name", "Native Place", "Address", "Total Attempts"};
Object[][] data = {};
DefaultTableModel model = new DefaultTableModel(data, columnNames);
voterListTable = new JTable(model);
Connection con1 = DriverManager.getConnection(DB_URL, USER, PASS);
PreparedStatement stmt = con1.prepareStatement("Select * from voterlist where vote=? ");
stmt.setString(1, "Plagarism");
ResultSet rs = stmt.executeQuery();
if(rs.next()!=false){
try {
while (rs.next()) {
String epicNumber = rs.getString("Epic_No");
String firstName = rs.getString("First_name");
String lastName = rs.getString("Last_name");
String dob = rs.getString("Date_Of_Birth");
String gender = rs.getString("Gender");
String religion = rs.getString("RELIGION_NAME");
String aadharNumber = rs.getString("Aadhar_Number");
String fatherName = rs.getString("Father_Name");
String nativePlace = rs.getString("NATIVEPLACE");
String address = rs.getString("Adress");
int totalAttempts = rs.getInt("count");
Object[] row = {epicNumber, firstName, lastName, dob, gender, religion, aadharNumber,
    fatherName, nativePlace, address, totalAttempts};
model.addRow(row);
}
} catch (SQLException e) {
e.printStackTrace();
}
JButton previous = new JButton("Exit");
frame.add(previous);
previous.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            User.home();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
});
JScrollPane scrollPane = new JScrollPane(voterListTable);
frame.add(scrollPane);
frame.setTitle("Tampered Votes");
frame.setSize(800, 400);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
    }
    else{
        JOptionPane.showMessageDialog(null,"No Tampered Votes found.");  
    }
}
public void caluculate_percentage() throws SQLException{
    JFrame Frame1 = new JFrame("Calculate Votes");
    panel = new JPanel(new GridLayout(4, 2, 5, 5));
    votesPolledLabel = new JLabel("Votes Polled:");
    totalVotesLabel = new JLabel("Total Votes:");
    votingPercentageLabel = new JLabel("Voting Percentage:");
    try {
        int votespolled=0,totalvotes=0;
    Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
    PreparedStatement stmt=con1.prepareStatement("SELECT * FROM voterlist WHERE vote IN (?, ?)");
    stmt.setString(1,"Done");
    stmt.setString(2,"Plagarism");
    ResultSet rs=stmt.executeQuery();
    PreparedStatement stmt1=con1.prepareStatement("SELECT COUNT(*) FROM voterlist");
    ResultSet rs1=stmt1.executeQuery();
    if (rs.next()) {
        votespolled = rs.getInt(1);
    }
    if(rs1.next()){
    totalvotes=rs1.getInt(1);
    } 
        int votingPercentage = (votespolled * 100) / totalvotes;
        votesPolledLabel.setText("The votes polled:\t"+votespolled);
        totalVotesLabel.setText("Total Votes: " + totalvotes);
        votingPercentageLabel.setText("Voting Percentage: " + votingPercentage + "%");
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    JButton previous = new JButton("Exit");
Frame1.add(previous);
previous.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            User.home();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
});
    panel.add(votesPolledLabel);
    panel.add(totalVotesLabel);
    panel.add(votingPercentageLabel);
    Frame1.add(panel);
    Frame1.setSize(300, 150);
    Frame1.setLocationRelativeTo(null);
    Frame1.setVisible(true);
}
public static void display() throws SQLException{
        JFrame frame=new JFrame();
        String[] columnNames = {"Epic Number", "First Name", "Last Name", "Date of Birth", "Gender", "Religion",
        "Aadhar Number", "Father Name", "Native Place", "Address", "Total Attempts"};
    Object[][] data = {};
    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    voterListTable = new JTable(model);
    Connection con1 = DriverManager.getConnection(DB_URL, USER, PASS);
    PreparedStatement stmt = con1.prepareStatement("Select * from voterlist ");
    ResultSet rs = stmt.executeQuery();
    if(rs.next()!=false){
    try {
    while (rs.next()) {
    String epicNumber = rs.getString("Epic_No");
    String firstName = rs.getString("First_name");
    String lastName = rs.getString("Last_name");
    String dob = rs.getString("Date_Of_Birth");
    String gender = rs.getString("Gender");
    String religion = rs.getString("RELIGION_NAME");
    String aadharNumber = rs.getString("Aadhar_Number");
    String fatherName = rs.getString("Father_Name");
    String nativePlace = rs.getString("NATIVEPLACE");
    String address = rs.getString("Adress");
    int totalAttempts = rs.getInt("count");
    Object[] row = {epicNumber, firstName, lastName, dob, gender, religion, aadharNumber,
        fatherName, nativePlace, address, totalAttempts};
    model.addRow(row);
    }
    } catch (SQLException e) {
    e.printStackTrace();
    }
    JScrollPane scrollPane = new JScrollPane(voterListTable);
frame.add(scrollPane);
frame.setTitle("voters List");
frame.setSize(800, 400);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
}
else{
    JOptionPane.showMessageDialog(null,"No voters ");  
}
}
public void start() throws SQLException{
    Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
    PreparedStatement stmt1=con1.prepareStatement("select stop_fun from func_list where stop_fun=?");
stmt1.setInt(1, 1);
ResultSet rs=stmt1.executeQuery();
int data=0;
if(rs.next()){
data=rs.getInt("stop_fun");
}
if(data==1){
    Statement stmt = con1.createStatement();
        String sql1 = "ALTER TABLE voterlist drop column vote";
        String sql2 = "ALTER TABLE voterlist ADD vote varchar(20) DEFAULT 'notdone'";
        String sql3 = "ALTER TABLE voterlist drop column party";
        String sql4 = "ALTER TABLE voterlist ADD party varchar(20) DEFAULT 'None'";   
        String sql5 = "ALTER TABLE voterlist drop column count";
        String sql6 = "ALTER TABLE voterlist ADD count int(1) DEFAULT 0";
        String sql7="Alter table winlist drop column count";
        String sql8="Alter table winlist ADD count int(1) DEFAULT 0";
        String sql9="drop table func_list";
        String sql10="create table func_list(stop_fun int(1) DEFAULT 0)";
        stmt.executeUpdate(sql1);
        stmt.executeUpdate(sql2);
        stmt.executeUpdate(sql3);
        stmt.executeUpdate(sql4);
        stmt.executeUpdate(sql5);
        stmt.executeUpdate(sql6);
        stmt.executeUpdate(sql7);
        stmt.executeUpdate(sql8);
        stmt.executeUpdate(sql9);
        stmt.executeUpdate(sql10);
    JOptionPane.showMessageDialog(null,"Voting has been Started!");  
}
else{
        
        JOptionPane.showMessageDialog(null,"Already Voting is in progress!");  
    }
}
public static void insertparty()throws SQLException{
Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
PreparedStatement stmt1=con1.prepareStatement("select stop_fun from func_list where stop_fun=?");
stmt1.setInt(1, 1);
ResultSet rs=stmt1.executeQuery();
int data=0;
if(rs.next()){
data=rs.getInt("stop_fun");
}
if(data==1){
    JFrame frame2=new JFrame();
    JTextField t1;  
    panel = new JPanel(new GridLayout(4, 2, 5, 5));
    votesPolledLabel = new JLabel("Enter new Party Name:");
    votesPolledLabel.setBounds(50,50, 250,20);      
    frame2.add(votesPolledLabel);
    t1=new JTextField("");  
    t1.setBounds(50,100, 250,40);  
    frame2.add(t1);
    JButton pre=new JButton("Back");
    pre.setBounds(150,250, 80,30);
    frame2.add(pre);
    JButton b = new JButton("submit");  
    b.setBounds(250,250, 80,30); 
    frame2.add(b);
    frame2.setSize(400,400);  
    frame2.setLayout(null);  
    frame2.setVisible(true);  
    pre.addActionListener((ActionListener) new ActionListener() {  
        public void actionPerformed(ActionEvent e) {     
            frame2.setVisible(false);
            User.home1();  
        }
    });
        b.addActionListener((ActionListener) new ActionListener() {  
        public void actionPerformed(ActionEvent e) {       
        String name=t1.getText();
        int count1=0;
        try{
        Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt_arr =con1.createStatement();
        ResultSet rs1=stmt_arr.executeQuery("Select party_name from winlist");
        PreparedStatement stmt_i=con1.prepareStatement("SELECT COUNT(*) FROM winlist");
        ResultSet rs_i=stmt_i.executeQuery();
        if(rs_i.next()){
        count1=rs_i.getInt(1);
        }
        String arr[]=new String[count1];
        int i=0;
        int c=0;
        while(rs1.next()){
            String s=rs1.getString("party_name");
            arr[i]=s;
            i++;
        }
        for( i=0;i<count1;i++){
            if(arr[i].toUpperCase().equals(name.toUpperCase())){
                c=1;
            }
        }
        if(c==0){
        PreparedStatement stmt=con1.prepareStatement("Insert into winlist values(?,?)");
        stmt.setString(1, name);
        stmt.setInt(2,0);
        int rs=stmt.executeUpdate();
        if(rs>0){
        JOptionPane.showMessageDialog(null,"Party name added sucessfully");  
        }
        else{
            JOptionPane.showMessageDialog(null,"cannot add Party name");  
        }
        frame2.setVisible(false);
        User.home1();
        }
        else{
            frame2.setVisible(false);  
            JOptionPane.showMessageDialog(null,"Party name already exists!");  
            insertparty();
        }
    }
    catch(Exception ex){
        ex.printStackTrace();
}
}
 });
}
else{
    JOptionPane.showMessageDialog(null,"Already Voting is in progress You Cant Insert a new Party!");  
}
}
public void change()throws SQLException{
    Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
PreparedStatement stmt1=con1.prepareStatement("select stop_fun from func_list where stop_fun=?");
stmt1.setInt(1, 1);
ResultSet rs=stmt1.executeQuery();
int data=0;
if(rs.next()){
data=rs.getInt("stop_fun");
}
if(data==1){
    Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
    oldNameLabel = new JLabel("Enter Old name:");
    oldNameLabel.setBounds(250, 150, 100, 30);
    frame3.add(oldNameLabel);
    newNameLabel = new JLabel("Enter New name:");
    newNameLabel.setBounds(250, 550, 100, 30);
    frame3.add(newNameLabel);
    oldNameField = new JTextField(20);
    oldNameField.setBounds(350,150, 100,30);
    frame3.add(oldNameField);
    newNameField = new JTextField(20);
    newNameField.setBounds(350, 250, 100,30);
    changeButton = new JButton("changename");
    JButton previous = new JButton("Back");
    frame3.add(previous);
previous.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            User.home();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
});
    changeButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                int count1=0;
                Statement stmt_arr =conn.createStatement();
        ResultSet rs1=stmt_arr.executeQuery("Select party_name from winlist");
        PreparedStatement stmt_i=conn.prepareStatement("SELECT COUNT(*) FROM winlist");
        ResultSet rs_i=stmt_i.executeQuery();
        String oldName = oldNameField.getText();
        String newName = newNameField.getText();
        if(rs_i.next()){
        count1=rs_i.getInt(1);
        }
        String arr[]=new String[count1];
        int i=0;
        int c=0;
        int d=1;
        int e1=0;
        while(rs1.next()){
            String s=rs1.getString("party_name");
            arr[i]=s;
            i++;
        }
        for( i=0;i<count1;i++){
            if(arr[i].toUpperCase().equals(oldName.toUpperCase()) ){
                c=1;
            }
        }
        for( i=0;i<count1;i++){
            if(arr[i].toUpperCase().equals(newName.toUpperCase()) ){
                d=0;
            }
        }
        if(oldName.toUpperCase().equals(newName.toUpperCase())){
            e1=1;
        }
    if (oldName.isEmpty() || newName.isEmpty()) {
        JOptionPane.showMessageDialog(frame3, "Please fill the details");
        return;
    }
    if(e1!=1){
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM winlist WHERE party_name = ?");
        stmt.setString(1, newName.toUpperCase());
        ResultSet rs = stmt.executeQuery();
        if(c==1){
        if(d==1){
        stmt = conn.prepareStatement("SELECT count FROM winlist WHERE party_name = ?");
        stmt.setString(1, oldName.toUpperCase());
        rs = stmt.executeQuery();
        int voteCount = 0;
        if (rs.next()) {
            voteCount = rs.getInt("count");
        }
        stmt = conn.prepareStatement("DELETE FROM winlist WHERE party_name = ?");
        stmt.setString(1, oldName.toUpperCase());
        stmt.executeUpdate();
        stmt = conn.prepareStatement("INSERT INTO winlist (party_name, count) VALUES (?, ?)");
        stmt.setString(1, newName.toUpperCase());
        stmt.setInt(2, voteCount);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(frame3, "Party name changed successfully!");
    }
    else{
        JOptionPane.showMessageDialog(frame3, "new party already exists!");
    }
}
else{
    JOptionPane.showMessageDialog(frame3, "Old party not exists!");
}
        }
        else{
            JOptionPane.showMessageDialog(frame3, "old party name and new party name is same! ");
        }
    }
        catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame3, "Error changing party name: " + ex.getMessage());
        }
    }
});
    JPanel panel=new JPanel();
    panel.setLayout(new GridLayout(3, 2));
    panel.add(oldNameLabel);
    panel.add(oldNameField);
    panel.add(newNameLabel);
    panel.add(newNameField);
    panel.add(new JLabel()); 
    panel.add(changeButton);
    frame3.add(panel);
    frame3.setTitle("Party Name Change");
    frame3.setSize(400, 150);
    frame3.setVisible(true);
    frame3.setLocationRelativeTo(null); 
}
else{
    JOptionPane.showMessageDialog(null,"Already Voting is in progress You Cant change the Party name");  
}
}
public static void countpartyvotes() throws SQLException{
    HashMap<String, Integer> hashMap = new HashMap<>();
    int count1 = 0;
    Connection con1 = DriverManager.getConnection(DB_URL, USER, PASS);
    PreparedStatement stmt_i = con1.prepareStatement("SELECT COUNT(*) FROM winlist");
    ResultSet rs_i = stmt_i.executeQuery();
    if (rs_i.next()) 
    {
        count1 = rs_i.getInt(1);
    }
    Statement stmt = con1.createStatement();
    ResultSet rs1 = stmt.executeQuery("SELECT party_name FROM winlist");
    String arr[] = new String[count1];
    int i = 0;
    int count[] = new int[count1];
    while (rs1.next()) {
        String s = rs1.getString("party_name");
        arr[i] = s;
        i++;
    }
    PreparedStatement stmt1 = con1.prepareStatement("SELECT COUNT(*) FROM voterlist WHERE party=?");
    for (int j = 0; j < arr.length; j++) {
        stmt1.setString(1, arr[j]);
        ResultSet rs = stmt1.executeQuery();
        if (rs.next()) {
            count[j] = rs.getInt(1);
        }
    }
    for (int k = 0; k < arr.length; k++) {
        hashMap.put(arr[k], count[k]);
    }
    List<Map.Entry<String, Integer>> list = new ArrayList<>(hashMap.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    });
    LinkedHashMap<String, Integer> sortedHashMap = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : list) {
        sortedHashMap.put(entry.getKey(), entry.getValue());
    }
    JFrame fr_1 = new JFrame();
        JPanel panel = new JPanel();
                DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Party Name", "Vote Count"}
        );
        JTable table = new JTable(tableModel);
                for (Map.Entry<String, Integer> entry : sortedHashMap.entrySet()) {
            tableModel.addRow(new Object[] {entry.getKey(), entry.getValue()});
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        fr_1.getContentPane().add(panel);
        fr_1.setTitle("Party votes");
        fr_1.pack();
        fr_1.setVisible(true);
}
public void Stop() throws SQLException{
Connection conn=DriverManager.getConnection(DB_URL, USER, PASS);
PreparedStatement stmt=conn.prepareStatement("select stop_fun from func_list where stop_fun=?");
stmt.setInt(1, 1);
ResultSet rs=stmt.executeQuery();
int data1=0;
if(rs.next()){
data1=rs.getInt("stop_fun");
}
if(data1==1){
    JOptionPane.showMessageDialog(null,"Already stopped!");  
}
else{
    int data=1;
    Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
    PreparedStatement stmt1=con1.prepareStatement("Insert into func_list values (?)");
    stmt1.setInt(1,data);
    stmt1.executeUpdate();
    JOptionPane.showMessageDialog(null,"Elections has been stopeed No one can vote");  
}
}
public static void delete() throws SQLException{
Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
PreparedStatement stmt1=con1.prepareStatement("select stop_fun from func_list where stop_fun=?");
stmt1.setInt(1, 1);
ResultSet rs=stmt1.executeQuery();
int data=0;
if(rs.next()){
data=rs.getInt("stop_fun");
}

if(data==1){
    JTextField t1;  
    panel = new JPanel(new GridLayout(4, 2, 5, 5));
    votesPolledLabel = new JLabel("Enter party name to delete: ");
    votesPolledLabel.setBounds(50,50, 250,20);      
    frame_del.add(votesPolledLabel);
    t1=new JTextField("");  
    t1.setBounds(50,100, 200,30);  
    frame_del.add(t1);
    JButton pre=new JButton("Back");
    pre.setBounds(150,250,80,30);
    frame_del.add(pre);    
    JButton b = new JButton("submit");  
    b.setBounds(250,250, 80,30); 
    frame_del.add(b);
    frame_del.setSize(400,400);  
    frame_del.setLayout(null);  
    b.addActionListener((ActionListener) new ActionListener() {  
        public void actionPerformed(ActionEvent e) {       
        String name=t1.getText();
        int count1=0;
        pre.addActionListener((ActionListener) new ActionListener() {  
            public void actionPerformed(ActionEvent e) {     
                frame_del.setVisible(false);
                User.home1();  
            }
        });
        try (Connection con1 = DriverManager.getConnection(DB_URL,USER,PASS)) {
            Statement stmt_arr =con1.createStatement();
            ResultSet rs1=stmt_arr.executeQuery("Select party_name from winlist");
            PreparedStatement stmt_i=con1.prepareStatement("SELECT COUNT(*) FROM winlist");
            ResultSet rs_i=stmt_i.executeQuery();
            if(rs_i.next()){
            count1=rs_i.getInt(1);
            }
            String arr[]=new String[count1];
            int i=0;
            int c=0;
            while(rs1.next()){
                String s=rs1.getString("party_name");
                arr[i]=s;
                i++;
            }
            for( i=0;i<count1;i++){
                if(arr[i].toUpperCase().equals(name.toUpperCase())){
                    c=1;
                }
            }
            if(c==1){
            PreparedStatement stmt=con1.prepareStatement("delete from winlist where party_name=?");
            stmt.setString(1, name);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame_del,"Party deleted sucessfully");  
            JButton previous = new JButton("previous");
            frame_del.add(previous);
            previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    User.home();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
            }
            else{
                JOptionPane.showMessageDialog(frame_del,"Party does not exists!");  
                frame_del.setVisible(false);
                delete();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
}
    });
    frame_del.setVisible(true);  
}
else{
    JOptionPane.showMessageDialog(null,"Already Voting is in progress You Cant Delete a existing Party!");  
}
}
public static void result() throws SQLException{
    JLabel label=new JLabel();
    Connection conn=DriverManager.getConnection(DB_URL, USER, PASS);
    PreparedStatement stmt_i1=conn.prepareStatement("select stop_fun from func_list where stop_fun=?");
    stmt_i1.setInt(1, 1);
    ResultSet rs=stmt_i1.executeQuery();
    HashMap<String, Integer> hashMap = new HashMap<>();
    int count1 = 0;
    Connection con1 = DriverManager.getConnection(DB_URL, USER, PASS);
    PreparedStatement stmt_i = con1.prepareStatement("SELECT COUNT(*) FROM winlist");
    ResultSet rs_i = stmt_i.executeQuery();
    if (rs_i.next()) {
        count1 = rs_i.getInt(1);
    }
    Statement stmt = con1.createStatement();
    ResultSet rs1 = stmt.executeQuery("SELECT party_name FROM winlist");
    String arr[] = new String[count1];
    int i = 0;
    int count[] = new int[count1];
    while (rs1.next()) {
        String s = rs1.getString("party_name");
        arr[i] = s;
        i++;
    }
    PreparedStatement stmt1 = con1.prepareStatement("SELECT COUNT(*) FROM voterlist WHERE party=?");
    for (int j = 0; j < arr.length; j++) {
        stmt1.setString(1, arr[j]);
        ResultSet rs_i1 = stmt1.executeQuery();
        if (rs_i1.next()) {
            count[j] = rs_i1.getInt(1);
        }
    }
    for (int k = 0; k < arr.length; k++) {
        hashMap.put(arr[k], count[k]);
    }
    List<Map.Entry<String, Integer>> list = new ArrayList<>(hashMap.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String,Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    });
    LinkedHashMap<String, Integer> sortedHashMap = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> entry : list) {
        sortedHashMap.put(entry.getKey(), entry.getValue());
    }
    JLabel timerLabel = new JLabel("Timer: 0 seconds");
    frame6.getContentPane().add(timerLabel);
    if(rs.next()){
    data1=rs.getInt("stop_fun");
    }
    Timer timer = new Timer();
    JLabel countdownLabel = new JLabel("The winner will be declared in " + countdownSeconds + " seconds");
    countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
    countdownLabel.setBounds(200, 70, 500, 30);
    frame6.add(countdownLabel);
    Map.Entry<String, Integer> firstEntry = sortedHashMap.entrySet().iterator().next();
    if(data1==0){
        JOptionPane.showMessageDialog(null,"Please stop the voting and come back to see winning party");  
    }
    else{
    timer.schedule(new TimerTask() {
        public void run() {
            countdownSeconds--; 
            if (countdownSeconds == 0) {
                if(data1==1){
                    label.setText("The winner is " + firstEntry.getKey()+" and won by the majority of  " +firstEntry.getValue()+" votes");
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBounds(200,100, 500,30);  
                    frame6.add(label);
                }
                frame6.remove(countdownLabel);
                frame6.setSize(400,400);
                frame6.setVisible(true);
                cancel(); 
            } else {
                countdownLabel.setText("The winner will be declared in " + countdownSeconds + " seconds");
            }
        }
    }, 0, 1000);
        frame6.setSize(400, 400);
    frame6.setVisible(true);   
}
}
}
class User  {
    static final String DB_URL="jdbc:mysql://localhost/Project1";
    static final String USER="root";
    static final String PASS="sai@53755375";
    static JFrame f=new JFrame("User"); //selection of user or admin
    static JFrame f_i=new JFrame("ONline voting System"); //admin paasword and username      
    static JFrame f1 = new JFrame("Input");//aadhar and epic
    static JFrame f2 = new JFrame("Output");//go and vote
    static JFrame f_i3 = new JFrame("Result");//admin actions
    final JLabel label = new JLabel();  
    private static JRadioButton[] radioButtons;
    public static JButton submitbutton;
    String arr1[]=new String[100];
    int count=0;
public int check1(String Epic){
        try{
        f2.setSize(700, 700);
        f2.setLayout(new BorderLayout());
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        Connection con1 =DriverManager.getConnection(DB_URL,USER,PASS);
        PreparedStatement stmt = con1.prepareStatement("SELECT * FROM voterlist WHERE Epic_no = ?");
        stmt.setString(1, Epic);
        ResultSet rs = stmt.executeQuery();
        String vote_data1="";
        if(rs.next()){
            vote_data1=rs.getString("vote");
        }
        if((vote_data1.equals("notdone")) && (vote_data1!=("Plagarism"))){
        int count1=0;
        Statement stmt_arr =con1.createStatement();
         ResultSet rs1=stmt_arr.executeQuery("Select party_name from winlist");
         PreparedStatement stmt_i=con1.prepareStatement("SELECT COUNT(*) FROM winlist");
         ResultSet rs_i=stmt_i.executeQuery();
         if(rs_i.next()){
         count1=rs_i.getInt(1);
         }
         String arr[]=new String[count1];
         int i=0;
         while(rs1.next()){
             String s=rs1.getString("party_name");
             arr[i]=s;
             i++;
         }
         for(i=0;i<arr.length;i++){
            arr1[i]=arr[i];
         }
         count=count1;
         radioButtons = new JRadioButton[count1];
         ButtonGroup group = new ButtonGroup();
         for (i = 0; i < radioButtons.length; i++) {
             radioButtons[i] = new JRadioButton(arr1[i]);
             group.add(radioButtons[i]);
             radioPanel.add(radioButtons[i]);
             f2.add(radioButtons[i]);
             radioButtons[i].setBounds(300, 50*(i+2), 100, 30);
         }
         f2.add(radioPanel, BorderLayout.CENTER);
         submitbutton = new JButton("Submit");
         submitbutton.setBounds(500,250,100,30);
         f2.add(submitbutton,BorderLayout.SOUTH);
         f2.setVisible(true);
         submitbutton.addActionListener((ActionListener) new ActionListener() {  
         public void actionPerformed(ActionEvent e) {
            String data="";
            if (e.getSource() == submitbutton) {
                for (int i = 0; i < radioButtons.length; i++) {
                    if (radioButtons[i].isSelected()) {
                        JOptionPane.showMessageDialog(null,"Sucessfully Voted!");
                        data=arr1[i];
                        break;
                    }
                }
        String voter=data;
        int c=0;
        for(int i=0;i<count;i++){
            if(arr[i].toUpperCase().equals(voter.toUpperCase())){
                c=1;
            }
        }
         if(c==1){
            try{
            String vote_data="Done";
           String sql1 = "UPDATE  voterlist set vote = ? where Epic_no= ? ";
           String sql2 = "UPDATE  voterlist set count = ? where Epic_no= ? ";
           PreparedStatement stmt1= con1.prepareStatement(sql1);
           PreparedStatement stmt2= con1.prepareStatement(sql2);
           stmt1.setString(1,vote_data);
           stmt1.setString(2,Epic);
           stmt2.setInt(1,rs.getInt("count")+1);
           stmt2.setString(2,Epic);
           PreparedStatement stmt_for1= con1.prepareStatement("UPDATE voterlist set party = ? WHERE Epic_no = ?");
           stmt_for1.setString(1,voter);
           stmt_for1.setString(2,Epic);
           stmt1.executeUpdate();
           stmt2.executeUpdate();
           stmt_for1.executeUpdate();
           f2.setVisible(false);
           home();
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
        }
        }
   }
});
        }
        }
        catch(Exception ex){
            ex.printStackTrace();
         }
        return 0;
    }
public void check () throws SQLException
    { 
    Connection conn =DriverManager.getConnection(DB_URL,USER,PASS);
    JTextField t1,t2;  
    f.setVisible(false);
    JLabel l=new JLabel("Enter your Aadhar Number:");  
    l.setBounds(650,250, 250,40);      
    f1.add(l);
    t1=new JTextField("");  
    t1.setBounds(650,300, 250,40);  
    JLabel l1=new JLabel("Enter your Epic Number:"); 
    l1.setBounds(650, 350, 250, 40);
    f1.add(l1); 
    t2=new JTextField("");  
    t2.setBounds(650,400, 250,40);  
    f1.add(t1); 
    f1.add(t2);  
    JButton b = new JButton("Submit");  
    b.setBounds(800,500, 100,40); 
    f1.add(b);
    f1.setSize(1800,1800);  
    f1.setLayout(null);  
    f1.setVisible(true);  
    b.addActionListener((ActionListener) new ActionListener() {  
        public void actionPerformed(ActionEvent e) {       
            f1.setVisible(true);
           String aadhar_num = t1.getText();  
           String Epic_num= t2.getText();   
           try{ 
            PreparedStatement stmt_for= conn.prepareStatement("SELECT * FROM voterlist WHERE Epic_no = ?");
            stmt_for.setString(1, Epic_num);
            ResultSet rs_for= stmt_for.executeQuery();
            String checkString="";
            String vote="";
            if(rs_for.next()){
             vote=rs_for.getString("vote");
             checkString=rs_for.getString("Aadhar_Number");
         }
         if((t2.getText()).isEmpty() || (t1.getText()).isEmpty() ){
            JOptionPane.showMessageDialog(f2,"Please fill the details");  
         }
         else{
         if(checkString.equals(aadhar_num)){
            if((vote.equals("Done")) || (vote.equals("Plagarism"))){
                JOptionPane.showMessageDialog(f,"Plagarism");
                String vote_data="Plagarism";
                String sql_i = "UPDATE  voterlist set vote = ? where Epic_no= ? ";
                String sql_i1 = "UPDATE  voterlist set count = ? where Epic_no= ? ";
                PreparedStatement stmt_i= conn.prepareStatement(sql_i);
                PreparedStatement stmt_i1= conn.prepareStatement(sql_i1);
                stmt_i.setString(1,vote_data);
                stmt_i.setString(2,Epic_num);
                stmt_i1.setInt(1,rs_for.getInt("count")+1);
                stmt_i1.setString(2,Epic_num);
                stmt_i.executeUpdate();
                stmt_i1.executeUpdate(); 
                home();
            }
            else{
            JOptionPane.showMessageDialog(f2,"Sucessfully Registered");
            f1.setVisible(false);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM voterlist WHERE Epic_no = ?");
            stmt.setString(1, Epic_num);
            ResultSet rs = stmt.executeQuery();
            JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13;  
            if(rs.next()){
            l1=new JLabel("\t\t\t Welcome "+rs.getString("First_name")+" "+rs.getString("Last_name"));  
            l1.setBounds(1050,80, 250,30);  
            l2=new JLabel("Epic Number: \t"+rs.getString("Epic_No"));  
            l2.setBounds(1050,130, 250,30); 
            l3=new JLabel("First Name: \t"+rs.getString("First_name"));  
            l3.setBounds(1050,180, 250,30); 
            l4=new JLabel("Last Name: \t"+rs.getString("Last_name"));  
            l4.setBounds(1050,230, 250,30); 
            l5=new JLabel("DATE OF BIRTH: \t"+rs.getString("Date_Of_Birth"));  
            l5.setBounds(1050,280, 250,30); 
            l6=new JLabel("GENDER: \t"+rs.getString("Gender"));  
            l6.setBounds(1050,330, 250,30); 
            l7=new JLabel("RELIGION: \t"+rs.getString("RELIGION_NAME"));  
            l7.setBounds(1050,380, 250,30); 
            l8=new JLabel("AADHAR NUMBER: \t"+rs.getString("Aadhar_Number"));  
            l8.setBounds(1050,430, 250,30); 
            l9=new JLabel("FATHER NAME: \t"+rs.getString("Father_Name"));  
            l9.setBounds(1050,480, 250,30);  
            l10=new JLabel("NATIVEPLACE: \t"+rs.getString("NATIVEPLACE"));  
            l10.setBounds(1050,530, 250,30); 
            l11=new JLabel("ADRESS: \t"+rs.getString("Adress"));  
            l11.setBounds(1050,580, 350,30); 
            l12=new JLabel("NATIONALITY: \t"+rs.getString("NATIONALITY"));  
            l12.setBounds(1050,630, 250,30); 
            l13=new JLabel("STATE: \t"+rs.getString("state"));  
            l13.setBounds(1050,680, 250,30); 
            f2.add(l1);
            f2.add(l2);
            f2.add(l3);
            f2.add(l4);
            f2.add(l5);
            f2.add(l6);
            f2.add(l7);
            f2.add(l8);
            f2.add(l9);
            f2.add(l10);
            f2.add(l11);
            f2.add(l12);
            f2.add(l13);
            User obj= new User();
            obj.check1(Epic_num);
            }
            f2.setLayout(null);
            f2.setVisible(true);
          }
    }
    else{
        JOptionPane.showMessageDialog(f2,"Invalid details");  
      try{  check();
      } 
    catch(Exception ex){
        ex.printStackTrace();
     }
    }
}  
}
catch(Exception ex){
    ex.printStackTrace();
}
}
});
}
public static void home1(){
    Admin obj=new Admin();
    JPanel panel = new JPanel(new GridLayout(3,3));
    JButton but1=new JButton("Plagarismlist");
    JButton but2=new JButton("Voting Percentage");
    JButton but3=new JButton("Start");
    JButton but4=new JButton("Insert");
    JButton but5=new JButton("Change");
    JButton but6=new JButton("Total no:of votes polled for a party");
    JButton but7=new JButton("Delete");
    JButton but8=new JButton("Stop");
    JButton but9=new JButton("Home");
    JButton but10=new JButton("Results");
    JButton but11=new JButton("Voter's List");
    but1.setBounds(200,150,100, 30);
    but2.setBounds(250,200,100, 30);
    but3.setBounds(300,250,100, 30);
    but4.setBounds(350,300,100, 30);
    but5.setBounds(400,350,100, 30);
    but6.setBounds(450,400,100, 30);
    but7.setBounds(500,450,100, 30);
    but8.setBounds(550,500,100, 30);
    but8.setBounds(600,550,100, 30);
    f_i.setVisible(false);
    but1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           try {
               obj.palagarismlist();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           try {
               obj.caluculate_percentage();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           try {
               obj.start();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           try {
               Admin.insertparty();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but5.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           try {
               obj.change();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but6.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           try {
               Admin.countpartyvotes();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but7.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
           try {
               Admin.delete();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but8.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
           try {
               obj.Stop();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but10.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
           try {
           Admin.result();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but9.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           try {
            f_i3.setVisible(false);
            home();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     but11.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           try {
            Admin.display();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
        }
     });
     panel.add(but1);
     panel.add(but2);
     panel.add(but3);
     panel.add(but4);
     panel.add(but5);
     panel.add(but6);
     panel.add(but7);
     panel.add(but8);
     panel.add(but9);
     panel.add(but10);
     panel.add(but11);
     f_i3.add(panel);
     f_i3.setSize(1800, 1800);
     f_i3.setVisible(true);
   }
public void user1(){
    final JPasswordField value = new JPasswordField();   
    final JTextField text = new JTextField(); 
     JLabel l1=new JLabel("Enter Username:");    
     l1.setBounds(650,250, 100,30);     
        text.setBounds(750,250, 250,30);      
        JLabel l2=new JLabel("Enter Password:");    
        l2.setBounds(650,350, 100,40);      
        value.setBounds(750,350, 250,40);      
        JButton b = new JButton("Login");  
        b.setBounds(900,450, 80,30);    
        f_i.add(value); 
        f_i.add(l1);
        f_i.add(label);
        f_i.add(l2);
        f_i.add(b);
        f_i.add(text);  
        f_i.setSize(1800,1800);    
        f_i.setLayout(null);    
        f_i.setVisible(true);     
        f.setVisible(false);
        b.addActionListener((ActionListener) new ActionListener() {  
        public void actionPerformed(ActionEvent e) {       
           String data = text.getText();  
           String data1 = new String(value.getPassword());   
           if(data.equals("Admin") && data1.equals("12345")){
            JOptionPane.showMessageDialog(null,"Sucessfully Logged in!");  
                home1();
           }
           else{
            JOptionPane.showMessageDialog(f,"Invalid details");  
            user1();
           }
        }  
     });   
    }
public static void home() throws SQLException{
    User obj=new User();
    JPanel panel=new JPanel();
    JButton userButton = new JButton("User");
    userButton.setBounds(100,550,200,200);
    JButton adminButton = new JButton("Admin");
    adminButton.setBounds(900,600,120,40);
    JButton exit = new JButton("Exit");
    exit.setBounds(100,30,120,40);
    panel.add(adminButton);
    panel.add(userButton);
    panel.add(exit);
    panel.setBounds(100,100,500,500);
    f.add(panel);
    f.setSize(1500,1500);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
  PreparedStatement stmt1=con1.prepareStatement("select stop_fun from func_list");
  ResultSet rs_i1=stmt1.executeQuery();
  int data=0;
  if(rs_i1.next()){
      data=rs_i1.getInt("stop_fun");
  }
  if(data==1){
      JOptionPane.showMessageDialog(null,"Voting time has been done no one is allowed to vote");  
  }
  else{
    userButton.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          try {
              obj.check();
          } catch (SQLException e1) {
              e1.printStackTrace();
          }
       }
    });
}
adminButton.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          obj.user1();
       }
    });
exit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            f.setVisible(false);
        }
    });
}
public static void main(String[] args) throws SQLException {
    home();
}
}
