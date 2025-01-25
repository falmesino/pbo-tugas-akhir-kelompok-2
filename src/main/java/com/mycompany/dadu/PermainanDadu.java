/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.dadu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Wildan
 */
public class PermainanDadu extends javax.swing.JFrame {
    private int saldo = 10_000_000; // Saldo awal Rp10.000.000
    private int uangTerakhir = 0;
   
    public PermainanDadu() {
       setTitle("Tugas Game Dadu Rai Ramadhan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Set Form Background
        getContentPane().setBackground(new Color(20, 30, 50));

        // Top Panel: Angka Dadu
        JPanel daduPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        daduPanel.setBackground(new Color(20, 30, 50));
        dadu1Label = new JLabel("1", SwingConstants.CENTER);
        dadu2Label = new JLabel("1", SwingConstants.CENTER);
        styleDaduLabel(dadu1Label);
        styleDaduLabel(dadu2Label);
        daduPanel.add(dadu1Label);
        daduPanel.add(dadu2Label);
        daduPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Middle Panel: Buttons and Status
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(20, 30, 50));
        besarButton = new JButton("Besar");
        kecilButton = new JButton("Kecil");
        styleButton(besarButton);
        styleButton(kecilButton);
        statusLabel = new JLabel("Status: Belum Bermain");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(besarButton);
        buttonPanel.add(kecilButton);

        // Bottom Panel: Uang, Saldo, and Riwayat
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(20, 30, 50));
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(new Color(20, 30, 50));
        uangTerakhirLabel = new JLabel("Uang Didapat: Rp0", SwingConstants.CENTER);
        uangTerakhirLabel.setFont(new Font("Arial", Font.BOLD, 18));
        uangTerakhirLabel.setForeground(Color.CYAN);
        saldoLabel = new JLabel("Saldo: Rp" + formatRupiah(saldo), SwingConstants.CENTER);
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        saldoLabel.setForeground(Color.CYAN);
        riwayatArea = new JTextArea(8, 40);
        riwayatArea.setEditable(false);
        riwayatArea.setForeground(Color.WHITE);
        riwayatArea.setBackground(new Color(30, 40, 60));
        riwayatArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(riwayatArea);

        infoPanel.add(uangTerakhirLabel);
        infoPanel.add(saldoLabel);
        bottomPanel.add(infoPanel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Add Panels to Frame
        add(daduPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        besarButton.addActionListener(e -> processGame(true));
        kecilButton.addActionListener(e -> processGame(false));

        setVisible(true);
    }
    
    private void processGame(boolean isBesar) {
       // Roll the dice
        int dadu1 = new Random().nextInt(6) + 1;
        int dadu2 = new Random().nextInt(6) + 1;
        int total = dadu1 + dadu2;

        // Update dice labels
        dadu1Label.setText(String.valueOf(dadu1));
        dadu2Label.setText(String.valueOf(dadu2));

        // Game logic
        if ((isBesar && total > 6) || (!isBesar && total <= 6)) {
            uangTerakhir = 1_000_000; // Hadiah Rp1.000.000
            saldo += uangTerakhir;
            statusLabel.setText("Status: Anda menang! Total: " + total);
            uangTerakhirLabel.setForeground(Color.GREEN);
        } else {
            uangTerakhir = -500_000; // Kerugian Rp500.000
            saldo += uangTerakhir;
            statusLabel.setText("Status: Anda kalah! Total: " + total);
            uangTerakhirLabel.setForeground(Color.RED);
        }

        // Update UI
        uangTerakhirLabel.setText("Uang Didapat: Rp" + formatRupiah(uangTerakhir));
        saldoLabel.setText("Saldo: Rp" + formatRupiah(saldo));
        riwayatArea.append("Kocok: " + total + " | Pilihan: " + (isBesar ? "Besar" : "Kecil") +
                " | " + (uangTerakhir > 0 ? "Menang" : "Kalah") + " | Uang: Rp" + formatRupiah(uangTerakhir) + "\n");
    }

    
    private void styleDaduLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 48));
        label.setOpaque(true);
        label.setBackground(new Color(30, 40, 60));
        label.setForeground(Color.CYAN);
        label.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        label.setPreferredSize(new Dimension(100, 100));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(40, 60, 90));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(80, 120, 180));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(40, 60, 90));
            }
        });
    }

    private String formatRupiah(int value) {
        return String.format(Locale.getDefault(), "%,d", value).replace(',', '.');
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dadu1Label = new javax.swing.JLabel();
        dadu2Label = new javax.swing.JLabel();
        besarButton = new javax.swing.JButton();
        kecilButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        riwayatArea = new javax.swing.JTextArea();
        uangTerakhirLabel = new javax.swing.JLabel();
        saldoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dadu1Label.setText("Dadu 1: 0");
        dadu1Label.setName(""); // NOI18N

        dadu2Label.setText("Dadu 2: 0");

        besarButton.setText("Besar");
        besarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                besarButtonActionPerformed(evt);
            }
        });

        kecilButton.setText("Kecil");
        kecilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kecilButtonActionPerformed(evt);
            }
        });

        riwayatArea.setEditable(false);
        riwayatArea.setColumns(20);
        riwayatArea.setRows(5);
        jScrollPane1.setViewportView(riwayatArea);

        uangTerakhirLabel.setText("Hadiah  : 1000");

        saldoLabel.setText("Saldo :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(besarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(dadu1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kecilButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dadu2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saldoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uangTerakhirLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dadu2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dadu1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(saldoLabel)
                .addGap(18, 18, 18)
                .addComponent(uangTerakhirLabel)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(besarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kecilButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  


    
    private void besarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_besarButtonActionPerformed
        // TODO add your handling code here:
        int dadu1 = new Random().nextInt(6) + 1;
    int dadu2 = new Random().nextInt(6) + 1;
    int total = dadu1 + dadu2;

    

    // Logika permainan
    if (total > 6) { // Tebakan benar
        uangTerakhir = 100; // Uang yang didapat
        saldo += uangTerakhir; // Tambah saldo
        statusLabel.setText("Status: Anda menang! Total: " + total);
        riwayatArea.append("Kocok: " + total + " | Pilihan: Besar | Menang | Uang: +" + uangTerakhir + "\n");
    } else { // Tebakan salah
        uangTerakhir = -50; // Uang yang hilang
        saldo += uangTerakhir; // Kurangi saldo
        statusLabel.setText("Status: Anda kalah! Total: " + total);
        riwayatArea.append("Kocok: " + total + " | Pilihan: Besar | Kalah | Uang: " + uangTerakhir + "\n");
    }

    // Perbarui label uang terakhir dan saldo
    uangTerakhirLabel.setText("Uang Didapat: " + uangTerakhir);
    saldoLabel.setText("Saldo: " + saldo);




    }//GEN-LAST:event_besarButtonActionPerformed

    private void kecilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kecilButtonActionPerformed
        // Mengocok dadu
    int dadu1 = new Random().nextInt(6) + 1;
    int dadu2 = new Random().nextInt(6) + 1;
    int total = dadu1 + dadu2;

   

    // Logika permainan
    if (total <= 6) { // Tebakan benar
        uangTerakhir = 100; // Uang yang didapat
        saldo += uangTerakhir; // Tambah saldo
        statusLabel.setText("Status: Anda menang! Total: " + total);
        riwayatArea.append("Kocok: " + total + " | Pilihan: Kecil | Menang | Uang: +" + uangTerakhir + "\n");
    } else { // Tebakan salah
        uangTerakhir = -50; // Uang yang hilang
        saldo += uangTerakhir; // Kurangi saldo
        statusLabel.setText("Status: Anda kalah! Total: " + total);
        riwayatArea.append("Kocok: " + total + " | Pilihan: Kecil | Kalah | Uang: " + uangTerakhir + "\n");
    }

    // Perbarui label uang terakhir dan saldo
    uangTerakhirLabel.setText("Uang Didapat: " + uangTerakhir);
    saldoLabel.setText("Saldo: " + saldo);



    }//GEN-LAST:event_kecilButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PermainanDadu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PermainanDadu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PermainanDadu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PermainanDadu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PermainanDadu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton besarButton;
    private javax.swing.JLabel dadu1Label;
    private javax.swing.JLabel dadu2Label;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kecilButton;
    private javax.swing.JTextArea riwayatArea;
    private javax.swing.JLabel saldoLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel uangTerakhirLabel;
    // End of variables declaration//GEN-END:variables
}
