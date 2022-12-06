import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class electronicSystem {
    private JTextField textNamaKustomer;
    private JTextField textTotal;
    private JComboBox comboJenisProduk;
    private JComboBox comboTipeDelivery;
    private JRadioButton ATMRadioButton;
    private JRadioButton CODRadioButton;
    private JTextField textAlamat;
    private JTable tableOutput;
    private JButton saveDataPesananButton;
    private JPanel rootPanel;
    private JLabel labelJenisProduk;
    private JLabel labelTipeDelivery;
    private EleBackend eleBackend;
    private DefaultTableModel tableModel;
    private String calculateTotalAkhir;

    public void setLabelKategoriPaket(String hargaPaket) {
        labelTipeDelivery.setText("+Rp." + hargaPaket);
    }

    public void setLabelJenisLayanan(String hargaLayanan) {
        labelJenisProduk.setText("Rp." + hargaLayanan );
    }

    public electronicSystem() throws FileNotFoundException {

        this.eleBackend = new EleBackend();
        this.initComponents();

        saveDataPesananButton.addActionListener(e -> {

            if (textNamaKustomer.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Masukan Nama Kustomer Terlebih Dahulu!");
                return;
            } else if (comboJenisProduk.getSelectedItem().toString().equals("-Pilih Produk-")){
                JOptionPane.showMessageDialog(null, "Pilih Jenis Produk Terlebih Dahulu!");
                return;
            } else if (comboTipeDelivery.getSelectedItem().toString().equals("-Pilih Kategori-")){
                JOptionPane.showMessageDialog(null, "Pilih Tipe Delivery Terlebih Dahulu!");
                return;
            } else if (textAlamat.getText().isBlank()) {
                textAlamat.setText("Tidak Ada");
            }

            this.eleBackend.setNamaKustomer(textNamaKustomer.getText());

            try {
                this.eleBackend.setTotal(Integer.parseInt(textTotal.getText()));
            } catch (NumberFormatException ec){
                JOptionPane.showMessageDialog(rootPanel,"Masukan nilai total berupa angka, bukan teks!","Perhatikan Field Input Total",JOptionPane.WARNING_MESSAGE);
                this.textTotal.setText("");
                return;
            }

            if(CODRadioButton.isSelected()) {
                this.eleBackend.setTipePengantaran("Antar Kerumah");
            }else{
                this.eleBackend.setTipePengantaran("Jemput Langsung");
            }

            this.eleBackend.setAlamat(textAlamat.getText());

            calculateTotalAkhir = this.eleBackend.calculateTotalAkhir();

            tableModel.addRow(new Object[]{ this.eleBackend.getNamaKustomer(),
                                            String.valueOf(this.eleBackend.getTotal()),
                                            this.eleBackend.getJenisLayanan(),
                                            this.eleBackend.getKategoriPaket(),
                                            this.eleBackend.getTipePengantaran(),
                                            this.eleBackend.getAlamat(),
                                            calculateTotalAkhir});

            String writingForResult  = String.format("Nama Kustomer : %s\nTotal : %s\nJenis Layanan : %s\nKategori Paket : %s\nTipe Pengantaran : %s\nCatatan Khusus : %s\nTotal Harga : %s\n\n",
                                                    this.eleBackend.getNamaKustomer(),
                                                    String.valueOf(this.eleBackend.getTotal()),
                                                    this.eleBackend.getJenisLayanan(),
                                                    this.eleBackend.getKategoriPaket(),
                                                    this.eleBackend.getTipePengantaran(),
                                                    this.eleBackend.getAlamat(),
                                                    calculateTotalAkhir);

            String stringTempArray = String.format("%s#%s#%s#%s#%s#%s#%s\n",
                                            this.eleBackend.getNamaKustomer(),
                                            String.valueOf(this.eleBackend.getTotal()),
                                            this.eleBackend.getJenisLayanan(),
                                            this.eleBackend.getKategoriPaket(),
                                            this.eleBackend.getTipePengantaran(),
                                            this.eleBackend.getAlamat(),
                                            calculateTotalAkhir);

            try {
                new Data().insertingDataToFile(writingForResult);
                new Data().storedDataToTempFile(stringTempArray);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(null, "Pesanan Anda Berhasil Disimpan!");

            textNamaKustomer.setText("");
            textTotal.setText("");
            comboJenisProduk.setSelectedIndex(0);
            comboTipeDelivery.setSelectedIndex(0);
            textAlamat.setText("");

        });
        comboJenisProduk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eleBackend.setJenisLayanan(comboJenisProduk.getSelectedItem().toString());
                setLabelJenisLayanan(eleBackend.getHargaJenisLayananString());
            }
        });
        comboTipeDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eleBackend.setKategoriPaket(comboTipeDelivery.getSelectedItem().toString());
                setLabelKategoriPaket(eleBackend.getHargaKategoriPaketString());
            }
        });
    }

    private void initComponents() throws FileNotFoundException {

        Object[] tableColumn = {"Nama Kustomer",
                                "Total",
                                "Jenis Produk",
                                "Tipe Delivery",
                                "Pembayaran",
                                "Alamat",
                                "Total Biaya"};

        tableModel = new DefaultTableModel(new Data().getmObject(), tableColumn);
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
