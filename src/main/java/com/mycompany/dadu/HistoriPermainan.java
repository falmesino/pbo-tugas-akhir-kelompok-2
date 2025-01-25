/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dadu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author falmesino
 */
public class HistoriPermainan {
    
    private DefaultTableModel model;

    public HistoriPermainan(JTable jTableHistori) {

        // Membuat Table Model
        this.model = (DefaultTableModel) jTableHistori.getModel();

        // Menambahkan TableModel ke jTable1
        jTableHistori.setModel(model);
    }

    public void tambahHistoriPermainan(String jenis, int total, boolean menang, int taruhan, int saldo) {
        // Tanggal
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String tanggal = now.format(formatter);
        
        String hasil = (total > 6 ? "BESAR" : "KECIL") + " (" + total + ")";
        String kemenangan = ((menang ? "+" : "-") + CurrencyFormatter.formatAsRupiah(taruhan));

        // Simpan histori permainan ke dalam tabel
        Object[] data = {
            tanggal,
            jenis.toUpperCase(),
            hasil,
            kemenangan,
            CurrencyFormatter.formatAsRupiah(saldo)
        };
        model.addRow(data);
    }

    public void kosongkanHistoriPermainan() {
        model.setRowCount(0);
    }
}
