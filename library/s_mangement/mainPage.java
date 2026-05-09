
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class mainPage extends JFrame implements ActionListener {
    // Defines dimensions
    Rectangle bi = new Rectangle();
    JButton registerButton;
    JButton SLButton;
    JButton searchButton;
    JButton exitButton;

    public mainPage() {
        this.setLayout(null);
        this.bi.setBounds(80, 400, 230, 40);
        JLabel title = new JLabel("Student Management");
        title.setFont(new Font("Serif", Font.BOLD, 17));
        title.setBounds(90, 50, 250, 40);
        title.setVisible(true);
        this.add(title, "Center");

        this.registerButton = new JButton("Register");
        this.registerButton.setBounds(this.bi.x, this.bi.y - 300, this.bi.width, this.bi.height);
        this.registerButton.setFocusable(false);
        this.registerButton.setBackground(Color.LIGHT_GRAY);
        this.registerButton.setForeground(Color.BLACK);
        this.registerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.registerButton.setVisible(true);
        this.registerButton.addActionListener(this);
        this.add(this.registerButton);

        this.SLButton = new JButton("List All Students");
        this.SLButton.setBounds(this.bi.x, this.bi.y - 250, this.bi.width, this.bi.height);
        this.SLButton.setFocusable(false);
        this.SLButton.setBackground(Color.LIGHT_GRAY);
        this.SLButton.setForeground(Color.BLACK);
        this.SLButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.SLButton.setVisible(true);
        this.SLButton.addActionListener(this);
        this.add(this.SLButton);

        this.searchButton = new JButton("Search & Remove");
        this.searchButton.setBounds(this.bi.x, this.bi.y - 200, this.bi.width, this.bi.height);
        this.searchButton.setFocusable(false);
        this.searchButton.setBackground(Color.LIGHT_GRAY);
        this.searchButton.setForeground(Color.BLACK);
        this.searchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.searchButton.setVisible(true);
        this.searchButton.addActionListener(this);
        this.add(this.searchButton);

        this.exitButton = new JButton("EXIT");
        this.exitButton.setBounds(this.bi.x, this.bi.y - 150, this.bi.width, this.bi.height);
        this.exitButton.setFocusable(false);
        this.exitButton.setBackground(Color.LIGHT_GRAY);
        this.exitButton.setForeground(Color.BLACK);
        this.exitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.exitButton.setVisible(true);
        this.exitButton.addActionListener(this);
        this.add(this.exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.registerButton) {
            this.dispose();
            registerFrame regpage = new registerFrame();
            regpage.setTitle("Student Management");
            regpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            regpage.setSize(400, 400);
            regpage.setLocationRelativeTo(null);
            regpage.setVisible(true);
        } else if (e.getSource() == this.SLButton) {
            this.dispose();
            showAllStudents showpage = new showAllStudents();
            showpage.setTitle("Student Management");
            showpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            showpage.setSize(900, 700);
            showpage.setLocationRelativeTo(null);
            showpage.setVisible(true);
        } else if (e.getSource() == this.searchButton) {
            this.dispose();
            searchRemoveStudent searchpage = new searchRemoveStudent();
            searchpage.setTitle("Search & Remove Student");
            searchpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            searchpage.setSize(400, 200);
            searchpage.setLocationRelativeTo(null);
            searchpage.setVisible(true);
        } else if (e.getSource() == this.exitButton) {
            this.dispose();
        }
    }
}

class searchRemoveStudent extends JFrame implements ActionListener {
    JTextField searchField;
    JButton searchButton;
    Connection con;

    public searchRemoveStudent() {
        this.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Enter Student ID:");
        this.add(searchLabel);

        this.searchField = new JTextField(15);
        this.add(this.searchField);

        this.searchButton = new JButton("Search");
        this.searchButton.addActionListener(this);
        this.add(this.searchButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            String studentID = searchField.getText();
            searchAndRemove(studentID);
        }
    }

    private void searchAndRemove(String studentID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "Alex@1234");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM stud_data_db WHERE id = ?");
            pst.setInt(1, Integer.parseInt(studentID));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Student Found:\n\nName: " + rs.getString("firstName") + " " + rs.getString("lastName") +
                                "\nDepartment: " + rs.getString("department") +
                                "\nDOB: " + rs.getString("DOB") +
                                "\nEntry Year: " + rs.getString("entryYear") +
                                "\n\nDo you want to Edit or Delete this student?",
                        "Edit/Delete Student", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    // Logic for editing (not implemented in this example)
                    JOptionPane.showMessageDialog(null, "Edit functionality is not implemented.");
                } else if (response == JOptionPane.NO_OPTION) {
                    int deleteResponse = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete this student?",
                            "Delete Student", JOptionPane.YES_NO_OPTION);

                    if (deleteResponse == JOptionPane.YES_OPTION) {
                        pst = con.prepareStatement("DELETE FROM stud_data_db WHERE id = ?");
                        pst.setInt(1, Integer.parseInt(studentID));
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Student deleted successfully.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student not found.");
            }

            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
