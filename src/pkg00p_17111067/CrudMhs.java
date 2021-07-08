package pkg00p_17111067;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CrudMhs extends JFrame {

    JLabel label_NIM = new JLabel("NIM");
    JTextField txt_NIM = new JTextField(30);
    JLabel label_Nama = new JLabel("Name");
    JLabel labell = new JLabel("17111067 Muhammad Bagasjati Pratiwo");
    JTextField txt_Nama = new JTextField(50);
    JButton buttonSearch = new JButton("Search");
    JButton buttonSave = new JButton("Save");
    JButton buttonUpdate = new JButton("Update");
    JButton buttonDelete = new JButton("Delete");
    private JPanel contentPane;

    CrudMhs() {
        setLocation(200, 200);
        setSize(440, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Layout();
    }

    void Layout() {
        getContentPane().setLayout(null);

        getContentPane().add(labell);
        labell.setBounds(100, 11, 300, 14);
        getContentPane().add(label_NIM);
        label_NIM.setBounds(49, 60, 65, 14);
        getContentPane().add(label_Nama);
        label_Nama.setBounds(49, 108, 46, 14);

        getContentPane().add(txt_NIM);
        txt_NIM.setBounds(150, 57, 133, 20);
        getContentPane().add(txt_Nama);
        txt_Nama.setBounds(150, 105, 133, 20);

        getContentPane().add(buttonSearch);
        buttonSearch.setBounds(300, 57, 89, 20);

        getContentPane().add(buttonSave);
        buttonSave.setBounds(58, 188, 90, 23);

        getContentPane().add(buttonUpdate);
        buttonUpdate.setBounds(159, 188, 110, 23);

        getContentPane().add(buttonDelete);
        buttonDelete.setBounds(279, 188, 89, 23);
        JMenu mnTools = new JMenu("Tools");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem ViewMhs = new JMenuItem("View Data Mahasiswa");
        JMenuItem EntryMhs = new JMenuItem("Entry Data Mahasiswa");

        setJMenuBar(menuBar);
        menuBar.add(mnTools);

        EntryMhs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        mnTools.add(EntryMhs);

        ViewMhs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewMhs ne = new ViewMhs();
                ne.Layout();
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

        setVisible(true);
    }

    void Action() {
        //button Save
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Koneksi open = new Koneksi();
                Connection mysql = open.getConnection();
                try {
                    Statement stmt = mysql.createStatement();
                    String sql = "INSERT INTO siswa_17111067 VALUES ('"
                            + txt_NIM.getText() + "','"
                            + txt_Nama.getText() + "');";
                    int i = stmt.executeUpdate(sql);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "Data Sudah Disimpan");
                        txt_NIM.setText("");
                        txt_Nama.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // button search
        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String search;
                Koneksi open = new Koneksi();
                Connection mysql = open.getConnection();
                try {
                    search = txt_NIM.getText();
                    Statement stmt = mysql.createStatement();
                    String sql = "SELECT * FROM siswa_17111067 "
                            + "WHERE NIM like '" + search + "'";
                    ResultSet res = stmt.executeQuery(sql);
                    if (res.next()) {
                        txt_NIM.setText(res.getString(1));
                        txt_Nama.setText(res.getString(2));
                    } else {
                        JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
                        txt_NIM.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        // button update
        buttonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Koneksi open = new Koneksi();
                Connection mysql = open.getConnection();
                try {
                    Statement stmt = mysql.createStatement();
                    String sql = "UPDATE siswa_17111067 SET "
                            + "Nama='" + txt_Nama.getText() + "'"
                            + "WHERE "
                            + "NIM='" + txt_NIM.getText() + "'";
                    int i = stmt.executeUpdate(sql);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "Data Sudah Diupdate");
                        txt_NIM.setText("");
                        txt_Nama.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        // button delete
        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Koneksi open = new Koneksi();
                Connection mysql = open.getConnection();
                try {
                    Statement stmt = mysql.createStatement();
                    String sql = "DELETE FROM siswa_17111067 "
                            + "WHERE NIM='" + txt_NIM.getText() + "'";
                    int i = stmt.executeUpdate(sql);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "Data Sudah Dihapus");
                        txt_NIM.setText("");
                        txt_Nama.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CrudMhs frame = new CrudMhs();
                    frame.setVisible(true);
                    frame.Action();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
