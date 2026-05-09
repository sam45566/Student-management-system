import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class searchStudentFrame extends JFrame implements ActionListener {
    JTextField searchField;
    JButton searchButton;
    JTable resultTable;
    Connection con;

    public searchStudentFrame() {
        // Set the layout
        this.setLayout(null);

        // Create and add a label for the search input
        JLabel searchLabel = new JLabel("Enter Student First/Last Name:");
        searchLabel.setBounds(50, 50, 200, 30);
        this.add(searchLabel);

        // Create and add a text field to input the name
        searchField = new JTextField();
        searchField.setBounds(250, 50, 150, 30);
        this.add(searchField);

        // Create and add a search button
        searchButton = new JButton("Search");
        searchButton.setBounds(410, 50, 100, 30);
        searchButton.addActionListener(this);
        this.add(searchButton);

        // Create a table to display search results
        resultTable = new JTable();
        JScrollPane pane = new JScrollPane(resultTable);
        pane.setBounds(50, 100, 500, 300);
        this.add(pane);

        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "Alex@1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchQuery = searchField.getText();
            searchStudents(searchQuery);
        }
    }

    private void searchStudents(String searchQuery) {
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM stud_data_db WHERE firstName LIKE '%" + searchQuery + "%' OR lastName LIKE '%" + searchQuery + "%'";
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
            int cols = rsmd.getColumnCount();
            String[] colname = new String[cols];

            // Get column names and set them in the table model
            for (int i = 0; i < cols; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colname);

            // Clear previous rows
            model.setRowCount(0);

            // Populate table with search results
            while (rs.next()) {
                Object[] row = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error retrieving data: " + e.getMessage());
        }
    }
}
