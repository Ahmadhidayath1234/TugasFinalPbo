package com.pti.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList jListMahasiswa;
    private JTextField textFieldNim;
    private JTextField textFieldNama;
    private JTextField textFieldIpk;
    private JButton buttonInsertFirst;
    private JButton buttonInsertLast;
    private JButton buttonClear;
    private JTextField textFieldFilter;
    private JButton buttonFilter;
    private JButton buttonDelet;
    private DefaultListModel defaultListModel = new DefaultListModel();
    private List<String > arrayListNamaMahasiswa = new ArrayList<>();

    private LinkedList<Mahasiswa> ListMahasiswa = new LinkedList<>();

    class Mahasiswa {
        private String nim;
        private String nama;
        private double ipk;

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public double getIpk() {
            return ipk;
        }

        public void setIpk(double ipk) {
            this.ipk = ipk;
        }
    }

public MainScreen () {
    this.setContentPane(panelMain);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack();
    buttonClear.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            clearform();
        }
    });
    buttonInsertFirst.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String nim = textFieldNim.getText();
            String nama = textFieldNama.getText();
            double ipk = Double.parseDouble(textFieldIpk.getText());

            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNim(nim);
            mahasiswa.setNama(nama);
            mahasiswa.setIpk(ipk);

            insertFirst(mahasiswa);
            clearform();
            refreshDataModel();

        }
    });
    buttonInsertLast.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String nim = textFieldNim.getText();
            String nama = textFieldNama.getText();
            double ipk = Double.parseDouble(textFieldIpk.getText());

            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNim(nim);
            mahasiswa.setNama(nama);
            mahasiswa.setIpk(ipk);

            insertLast(mahasiswa);
            clearform();
            refreshDataModel();
        }
    });
    jListMahasiswa.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int index = jListMahasiswa.getSelectedIndex();

            Mahasiswa hasilsearch = search(arrayListNamaMahasiswa.get(index));

            if (hasilsearch !=null) {
                textFieldNim.setText(hasilsearch.getNim());
                textFieldNama.setText(hasilsearch.getNama());
                textFieldIpk.setText(String.valueOf(hasilsearch.getIpk()));
            }
        }
    });
    buttonDelet.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = jListMahasiswa.getSelectedIndex();

            if (index < 0)
                return;
            String nama = jListMahasiswa.getSelectedValue().toString();

            for (int i = 0; i < arrayListNamaMahasiswa.size(); i++) {
                if (arrayListNamaMahasiswa.get(i).equals(nama)) {
                    arrayListNamaMahasiswa.remove(i);
                    break;
                }
            }
            clearform();
            fromListMahasiswaToDataModel();
        }
    });
    buttonFilter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyWord = textFieldFilter.getText();

            List<String> filtered = new ArrayList<>();

            for (int i = 0; i < arrayListNamaMahasiswa.size(); i++) {
                if (arrayListNamaMahasiswa.get(i).contains(keyWord)){
                    filtered.add(arrayListNamaMahasiswa.get(i));
                }
            }refreshDataModel(filtered);
        }
    });
}

    private void fromListMahasiswaToDataModel() {
        List<String> ListNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arrayListNamaMahasiswa.size(); i++) {
            ListNamaMahasiswa.add(
                    arrayListNamaMahasiswa.get(i)
            );
        }
        refreshDataModel(ListNamaMahasiswa);

    }
    private void refreshDataModel(List<String> List) {
        defaultListModel.clear();
        defaultListModel.addAll(List);
        jListMahasiswa.setModel(defaultListModel);
}

    private Mahasiswa search (String nama) {
        for (Mahasiswa mahasiswa : ListMahasiswa) {
            if (mahasiswa== null)
                break;

            if (mahasiswa.getNama().equals(nama))
                return mahasiswa;
        }

         return null;
}

    private void insertLast(Mahasiswa mahasiswa) {
        ListMahasiswa.insertLast(mahasiswa);
    }

    private void insertFirst (Mahasiswa mahasiswa) {
        ListMahasiswa.insertFirst(mahasiswa);
}
private void refreshDataModel() {
   arrayListNamaMahasiswa.clear();

   for (Mahasiswa mahasiswa : ListMahasiswa){
       if (mahasiswa == null)
           break;

       arrayListNamaMahasiswa.add(mahasiswa.getNama());

       defaultListModel.clear();

       for (String each : arrayListNamaMahasiswa){
           defaultListModel.addElement(each);
       }

       jListMahasiswa.setModel(defaultListModel);
   }
}

private void clearform(){
    textFieldIpk.setText(" ");
    textFieldNama.setText(" ");
    textFieldNim.setText(" ");
}

public static void main (String [] args) {
    MainScreen mainScreen = new MainScreen();
    mainScreen.setVisible(true);
}
}
