/*
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class showAllStudents extends JFrame {
    JTable table;
    Connection con;
    String b = "";
    String c = "";
    String d = "";
    String f = "";
    String g = "";
    int a;

    showAllStudents() {
        this.setLayout(null);
        this.table = new JTable();
        this.table.setBounds(23, 250, 800, 300);
        JScrollPane pane = new JScrollPane(this.table);
        pane.setBounds(20, 120, 850, 500);
        this.add(pane);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "Alex@1234");
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from stud_data_db");
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel)this.table.getModel();
            int cols = rsmd.getColumnCount();
            String[] colname = new String[cols];

            for(int i = 0; i < cols; ++i) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            model.setColumnIdentifiers(colname);

            while(rs.next()) {
                this.a = rs.getInt("id");
                this.b = rs.getString("firstName");
                this.c = rs.getString("lastName");
                this.d = rs.getString("department");
                this.f = rs.getString("DOB");
                this.g = rs.getString("entryYear");
                String[] row = new String[]{Integer.toString(this.a), this.b, this.c, this.d, this.f, this.g};
                model.addRow(row);
            }

            st.close();
            this.con.close();
        } catch (Exception var9) {
            System.out.println(var9);
        }

    }
}
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class showAllStudents extends JFrame implements ActionListener {
    JTable table;
    Connection con;
    JButton returnButton;

    showAllStudents() {
        // Set layout
        this.setLayout(null);

        // Create and add table
        this.table = new JTable();
        this.table.setBounds(23, 250, 800, 300);
        JScrollPane pane = new JScrollPane(this.table);
        pane.setBounds(20, 100, 850, 400);
        this.add(pane);

        // Create and add return button
        this.returnButton = new JButton("Return");
        this.returnButton.setBounds(380, 550, 100, 40);
        this.returnButton.setFocusable(false);
        this.returnButton.setBackground(Color.LIGHT_GRAY);
        this.returnButton.setForeground(Color.BLACK);
        this.returnButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.returnButton.setVisible(true);
        this.returnButton.addActionListener(this);
        this.add(this.returnButton);

        // Load students from database
        loadStudents();
    }

    private void loadStudents() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "Alex@1234");
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from stud_data_db");
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) this.table.getModel();

            int cols = rsmd.getColumnCount();
            String[] colname = new String[cols];

            // Set column headers
            for (int i = 0; i < cols; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colname);

            // Clear existing rows
            model.setRowCount(0);

            // Add rows from the database
            while (rs.next()) {
                Object[] row = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }

            st.close();
            this.con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            this.dispose();
            mainPage page = new mainPage();
            page.setTitle("Student Management");
            page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            page.setSize(400, 400);
            page.setLocationRelativeTo(null);
            page.setVisible(true);
        }
    }
}
