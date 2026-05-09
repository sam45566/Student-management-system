
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class registerFrame extends JFrame implements ActionListener{
    Rectangle bi = new Rectangle();
    JButton doneButton;
    JButton addButton;
    JLabel fnamelabel;
    JLabel lnamelabel;
    JLabel deplabel;
    JLabel agelabel;
    JLabel yearlabel;
    JTextField fname;
    JTextField lname;
    JComboBox<String> dep;
    JSpinner ageyear;
    JSpinner agemonth;
    JSpinner ageday;
    JSpinner entryYear;

    private void drawDesign() {
        this.setLayout((LayoutManager)null);
        this.bi.setBounds(80, 400, 230, 40);
        this.fnamelabel = new JLabel("First Name");
        this.fnamelabel.setBounds(this.bi.x - 70, this.bi.y - 380, this.bi.width - 100, this.bi.height - 10);
        this.fnamelabel.setVisible(true);
        this.add(this.fnamelabel);
        this.fname = new JTextField();
        this.fname.setBounds(this.bi.x, this.bi.y - 377, this.bi.width + 60, this.bi.height - 12);
        this.fname.setVisible(true);
        this.add(this.fname);
        this.lnamelabel = new JLabel("Last Name");
        this.lnamelabel.setBounds(this.bi.x - 70, this.bi.y - 330, this.bi.width - 100, this.bi.height - 10);
        this.lnamelabel.setVisible(true);
        this.add(this.lnamelabel);
        this.lname = new JTextField();
        this.lname.setBounds(this.bi.x, this.bi.y - 327, this.bi.width + 60, this.bi.height - 12);
        this.lname.setVisible(true);
        this.add(this.lname);
        this.deplabel = new JLabel("Department");
        this.deplabel.setBounds(this.bi.x - 70, this.bi.y - 280, this.bi.width - 100, this.bi.height - 10);
        this.deplabel.setVisible(true);
        this.add(this.deplabel);
        this.dep = new JComboBox<>();
        this.dep.setBounds(this.bi.x, this.bi.y - 277, this.bi.width + 60, this.bi.height - 12);
        this.dep.setVisible(true);
        this.dep.addItem("Computer Science");
        this.dep.addItem("Software Engineering");
        this.dep.addItem("Data Science");
        this.add(this.dep);
        this.agelabel = new JLabel("Date of Birth");
        this.agelabel.setBounds(this.bi.x - 72, this.bi.y - 230, this.bi.width - 100, this.bi.height - 10);
        this.agelabel.setVisible(true);
        this.add(this.agelabel);

        SpinnerNumberModel ageYearModel = new SpinnerNumberModel(2000, 1990, Integer.MAX_VALUE, 1);
        this.ageyear = new JSpinner(ageYearModel);
        this.ageyear.setBounds(this.bi.x, this.bi.y - 227, this.bi.width - 150, this.bi.height - 12);
        this.ageyear.setVisible(true);
        this.add(this.ageyear);

        SpinnerNumberModel ageMonthModel = new SpinnerNumberModel(1, 1, 12, 1);
        this.agemonth = new JSpinner(ageMonthModel);
        this.agemonth.setBounds(this.bi.x + this.bi.x + 10, this.bi.y - 227, this.bi.width - 180, this.bi.height - 12);
        this.agemonth.setVisible(true);
        this.add(this.agemonth);

        SpinnerNumberModel ageDayModel = new SpinnerNumberModel(1, 1, 30, 1);
        this.ageday = new JSpinner(ageDayModel);
        this.ageday.setBounds(this.bi.x + this.bi.x + this.bi.x + 10, this.bi.y - 227, this.bi.width - 180, this.bi.height - 12);
        this.ageday.setVisible(true);
        this.add(this.ageday);

        this.yearlabel = new JLabel("Entry Year");
        this.yearlabel.setBounds(this.bi.x - 70, this.bi.y - 180, this.bi.width - 100, this.bi.height - 10);
        this.yearlabel.setVisible(true);
        this.add(this.yearlabel);

        SpinnerNumberModel yearModel = new SpinnerNumberModel(2024, 2024, Integer.MAX_VALUE, 1);
        this.entryYear = new JSpinner(yearModel);
        this.entryYear.setBounds(this.bi.x, this.bi.y - 177, this.bi.width - 150, this.bi.height - 12);
        this.entryYear.setVisible(true);
        this.add(this.entryYear);

        this.addButton = new JButton("Register");
        this.addButton.setBounds(this.bi.x - 70, this.bi.y - 130, this.bi.width - 60, this.bi.height);
        this.addButton.setFocusable(false);
        this.addButton.setBackground(Color.LIGHT_GRAY);
        this.addButton.setForeground(Color.BLACK);
        this.addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.addButton.setVisible(true);
        this.addButton.addActionListener(this);
        this.add(this.addButton);

        this.doneButton = new JButton("Return");
        this.doneButton.setBounds(this.bi.x + 122, this.bi.y - 130, this.bi.width - 60, this.bi.height);
        this.doneButton.setFocusable(false);
        this.doneButton.setBackground(Color.LIGHT_GRAY);
        this.doneButton.setForeground(Color.BLACK);
        this.doneButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.doneButton.setVisible(true);
        this.doneButton.addActionListener(this);
        this.add(this.doneButton);
    }

    public void registerProcess() {
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "Alex@1234");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT id from stud_data_db");
            int id = 0;

            // Auto-incrementing ID
            while (rs.next()) {
                id = rs.getInt("id");
            }
            id++;

            // Insert the data into the database
            String sql = "INSERT INTO stud_data_db VALUES(" + id + ",'" + fname.getText() +
                    "','" + lname.getText() + "','" + dep.getSelectedItem() +
                    "','" + ageyear.getValue() + "-" + agemonth.getValue() +
                    "-" + ageday.getValue() + "','" + entryYear.getValue() + "');";
            int i = query.executeUpdate(sql);

            if (i > 0) {
                JOptionPane.showMessageDialog(this, "Registry successful\nYour ID is: " + id);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed");
            }

            // Close the connection
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public registerFrame() {
        drawDesign();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addButton)) {
            registerProcess();
        } else if (e.getSource().equals(doneButton)) {
            dispose();
            mainPage page = new mainPage();
            page.setTitle("Student Management");
            page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            page.setSize(400, 400);
            page.setLocationRelativeTo(null);
            page.setVisible(true);
        }
    }
}
