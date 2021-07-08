package pkg00p_17111067;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewMhs extends JPanel {

    String[] header = {"NIM", "Nama"};
    JTable table_mhs = new JTable();
    JScrollPane scrollTable = new JScrollPane();
    public Object[][] isiTable = null;
    JLabel labell = new JLabel("17111067 Muhammad Bagasjati Pratiwo");

    ViewMhs() {
        Koneksi open = new Koneksi();
        Connection mysql = open.getConnection();
        try {
            Statement stmt = mysql.createStatement();
            String sql = "SELECT * FROM siswa_17111067";
            ResultSet res = stmt.executeQuery(sql);
            ResultSetMetaData meta = res.getMetaData();
            int kolom = meta.getColumnCount();
            int baris = 0;
            while (res.next()) {
                baris = res.getRow();
            }
            isiTable = new Object[baris][kolom];
            int x = 0;
            res.beforeFirst();
            while (res.next()) {
                isiTable[x][0] = res.getString("NIM");
                isiTable[x][1] = res.getString("Nama");
                x++;
            }
            scrollTable.setViewportView(table_mhs);
            table_mhs.setModel(new DefaultTableModel(isiTable, header));
            add(scrollTable, BorderLayout.NORTH);
            stmt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    void Layout() {
        JFrame frame = new JFrame("Data Mahasiswa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ViewMhs content = new ViewMhs();
        content.setOpaque(true);
        frame.setContentPane(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(labell);
        labell.setBounds(0, -400, 300, 14);

        JMenu mnTools = new JMenu("Tools");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem ViewMhs = new JMenuItem("View Data Mahasiswa");
        JMenuItem EntryMhs = new JMenuItem("Entry Data Mahasiswa");

        frame.setJMenuBar(menuBar);
        menuBar.add(mnTools);

        EntryMhs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrudMhs me = new CrudMhs();
                me.Layout();
                me.Action();
            }
        });
        mnTools.add(EntryMhs);

        ViewMhs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        mnTools.add(ViewMhs);

        JMenu mnOther = new JMenu("Other");
        menuBar.add(mnOther);
        JMenuItem mntmAbout = new JMenuItem("About");
        mntmAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Proyek OOP \n"
                        + "Muhammad Bagasjati Pratiwo \n"
                        + "17111067"
                );
            }
        });
        mnOther.add(mntmAbout);

    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewMhs frame = new ViewMhs();
                    frame.Layout();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
