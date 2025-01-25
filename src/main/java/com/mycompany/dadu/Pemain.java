/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dadu;

import javax.swing.JLabel;

/**
 *
 * @author falmesino
 */
public class Pemain {
    private int saldo;

    public Pemain(int saldoAwal) {
        this.saldo = saldoAwal;
    }

    public void updateSaldo(JLabel labelSaldo) {
        labelSaldo.setText("Saldo: " + CurrencyFormatter.formatAsRupiah(saldo));
    }

    public void tambahSaldo(int jumlah) {
        saldo += jumlah;
    }

    public void kurangiSaldo(int jumlah) {
        saldo -= jumlah;
    }

    public void setSaldo(int jumlah) {
        saldo = jumlah;
    }

    public int getSaldo() {
        return saldo;
    }

    public boolean masihPunyaSaldo () {
        // Jika saldonya lebih besar dari nol, berarti masih punya saldo
        return saldo > 0;
    }
}
