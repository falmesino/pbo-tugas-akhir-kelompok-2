/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dadu;

import java.util.Random;

/**
 *
 * @author falmesino
 */
public class DaduObject {
    
    private final Random random;

    public DaduObject() {
        this.random = new Random();
    }

    public int[] kocokDadu() {
        int dadu1 = random.nextInt(6) + 1;
        int dadu2 = random.nextInt(6) + 1;
        int total = dadu1 + dadu2;
        return new int[]{ dadu1, dadu2, total };
    }

    public boolean isMenang(String jenis, int total) {
        return ("besar".equals(jenis) && total > 6) || ("kecil".equals(jenis) && total <= 6);
    }
}
