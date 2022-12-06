import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class ElectronicOnlineStore {
    private JPanel rootPanel;
    private JTextField textCustomerName;
    private JTextField textKuantitas;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton pesanButton;
    private JTable tableOutput;
    private JLabel labelJenis;
    private JLabel labelDelivery;
    private JComboBox comboBox3;
    private DefaultTableModel tableModel;



    private void initComponents() throws FileNotFoundException {

        Object[] tableColumn = {"Nama Kustomer",
                "Kuantitas",
                "Jenis Layanan",
                "Kategori Paket",
                "Tipe Pengantaran",
                "Catatan Khusus",
                "Total Biaya"};

        tableModel = new DefaultTableModel(new DataMechanism().getmObject(), tableColumn);
        tableOutput.setModel(tableModel);
        tableOutput.setAutoCreateRowSorter(true);

        for(int i = 0; i < 7; i++) {
            TableColumn col = tableOutput.getColumnModel().getColumn(i);
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
            dtcr.setHorizontalAlignment(SwingConstants.CENTER);
            col.setCellRenderer(dtcr);
        }

    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
