package com.kellima.pemdal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class MEOnlineStore {
    private JTextField textbrandproduk;
    private JTextField textKustomer;
    private JTextField textharga;
    private JTextField textjumlah;
    private JButton buttonsave;
    private JTable tabelData;
    private JComboBox comboboxjenisproduk;
    private JPanel root;

    private DataMEOstore objMEO;

    private DefaultTableModel tableModel;

    public MEOnlineStore() {

        this.objMEO = new DataMEOstore();
        this.initComponents();

        buttonsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getmerekproduk = textbrandproduk.getText();
                String getkustomer = textKustomer.getText();
                String getjenisproduk = comboboxjenisproduk.getSelectedItem().toString();
                String getharga = textharga.getText();
                String getjumlahbarang = textjumlah.getText();

                objMEO.setMerek(getmerekproduk);
                objMEO.setKustomer(getkustomer);
                objMEO.setJenisproduk(getjenisproduk);
                objMEO.setHarga(getharga);
                objMEO.setJumlah(getjumlahbarang);

                tableModel.addRow(new Object[] {getmerekproduk, getkustomer, getjenisproduk, getharga, getjumlahbarang});

                String dataInput = "\nMerek Produk : " +getmerekproduk + "\nKustomer :" +getkustomer + "\nJenis Produk : " +getjenisproduk + "\nHarga Produk : " +getharga +"\nKuantitas Pesanan : " +getjumlahbarang + "\n\n";
                FileWriter fw = null;
                try {
                    fw = new FileWriter("StoredTextData.txt", true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fw.write(dataInput);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
        private void initComponents(){

            Object[] tableColumn = {"Merek Produk",
                    "Kustomer",
                    "Jenis Produk",
                    "Harga",
                    "Kuantitas Pesanan"};

            Object[][] row = {};

            tableModel = new DefaultTableModel(row, tableColumn);
            tabelData.setModel(tableModel);
            tabelData.setAutoCreateRowSorter(true);

        }

    public JPanel getRoot() {
        return root;
    }
}