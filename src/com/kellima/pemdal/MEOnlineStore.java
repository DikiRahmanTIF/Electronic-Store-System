package com.kellima.pemdal;

//PERSISTENCE LEBIH GAMPANG KALO PAKE CSV Writer dan CSV READER
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class MEOnlineStore{
    private JTextField textbrandproduk;
    private JTextField textKustomer;
    private JTextField textharga;
    private JTextField textjumlah;
    private JButton buttonsave;
    private JTable tabelData;
    private JComboBox comboboxjenisproduk;
    private JPanel rootPanel;
    private JButton reset;
    private final DataMEOstore objMEO;
    private String totalHarga;
    private DefaultTableModel tableModel;
    public MEOnlineStore() {

        this.objMEO = new DataMEOstore();
        this.initComponents();

        buttonsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getmerekproduk = textbrandproduk.getText();
                String getkustomer = textKustomer.getText();
                String getjenisproduk = Objects.requireNonNull(comboboxjenisproduk.getSelectedItem()).toString();
                int getharga = Integer.parseInt(textharga.getText());
                int getjumlahbarang = Integer.parseInt(textjumlah.getText());

                objMEO.setMerek(getmerekproduk);
                objMEO.setKustomer(getkustomer);
                objMEO.setJenisproduk(getjenisproduk);
                objMEO.setHarga(Integer.parseInt(String.valueOf(getharga)));
                objMEO.setJumlah(Integer.parseInt(String.valueOf(getjumlahbarang)));

                //Penjumlahan Harga Total
                totalHarga = String.valueOf(getharga * getjumlahbarang);

                //Menambah data di row table sesuai data yang telah diisi user
                tableModel.addRow(new Object[]{getkustomer, getmerekproduk, getjenisproduk, getharga, getjumlahbarang, totalHarga});

                //data yg akan diinput ke Database
                String dataInput = "\nKustomer :" + getkustomer + "\nMerek Produk : " + getmerekproduk + "\nJenis Produk : " + getjenisproduk + "\nHarga Produk : " + getharga + "\nKuantitas Pesanan : " + getjumlahbarang + "\nTotal Harga : " + totalHarga + "\n\n";
                String Temp = objMEO.getKustomer() + "#" + objMEO.getMerek() + "#" + objMEO.getJenisproduk() + "#" + objMEO.getHarga() + "#" + objMEO.getJumlah() + "#" + objMEO.hargaAkhir() + "\n";

                //Write data ke File StoredTextDatabase.txt dan TempStrArr.txt
                FileWriter fw;
                try {
                    fw = new FileWriter("StoredTextDatabase.txt", true);
                    fw.write(dataInput);
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fw = new FileWriter(System.getProperty("user.dir") + "/src/TEMPStrArr.txt", true);
                    fw.write(Temp);
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    FileWriter fw = new FileWriter("StoredTextDatabase.txt", false);
                    PrintWriter pw = new PrintWriter(fw, false);
                    pw.flush();
                    pw.close();
                    fw.close();
                }catch(Exception exception) {
                    System.out.println("Exception have been caught");
                }
                try{
                    FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/src/TEMPStrArr.txt", false);
                    PrintWriter pw = new PrintWriter(fw, false);
                    pw.flush();
                    pw.close();
                    fw.close();
                }catch(Exception exception) {
                    System.out.println("Exception have been caught");
                }
                Object[] tableColumn = {"Kustomer",
                        "Merek Produk",
                        "Jenis Produk",
                        "Harga",
                        "Kuantitas Pesanan",
                        "Total Harga"};
                try {
                    tableModel = new DefaultTableModel(new DataMEOstore().getmObject(), tableColumn);
                } catch (FileNotFoundException a) {
                    throw new RuntimeException(a);
                }
                tabelData.setModel(tableModel);
                tabelData.setAutoCreateRowSorter(true);
            }
        });
    }

    //Initializer Komponen Tabel
    private void initComponents() {

        Object[] tableColumn = {"Kustomer",
                "Merek Produk",
                "Jenis Produk",
                "Harga",
                "Kuantitas Pesanan",
                "Total Harga"};
        /*Membuat tabel dan munculkan data di TempStrArr.txt ke tabel
        Serta membuat data tetap di tabel walau aplikasi telah di close
         */
        try {
            tableModel = new DefaultTableModel(new DataMEOstore().getmObject(), tableColumn);
        } catch (FileNotFoundException a) {
            throw new RuntimeException(a);
        }
        tabelData.setModel(tableModel);
        tabelData.setAutoCreateRowSorter(true);
    }
    //RootPanel
    public JPanel getRoot() {
        return rootPanel;
    }
}