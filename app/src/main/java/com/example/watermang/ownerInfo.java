package com.example.watermang;

import java.io.Serializable;

public class ownerInfo implements Serializable
{
    int totCap1,totCap2,totCap3;
    int avaCap1,avaCap2,avaCap3;

    public ownerInfo() {
    }

    public ownerInfo(int totCap1, int totCap2, int totCap3, int avaCap1, int avaCap2, int avaCap3) {
        this.totCap1 = totCap1;
        this.totCap2 = totCap2;
        this.totCap3 = totCap3;
        this.avaCap1 = avaCap1;
        this.avaCap2 = avaCap2;
        this.avaCap3 = avaCap3;
    }

    public int getTotCap1() {
        return totCap1;
    }

    public void setTotCap1(int totCap1) {
        this.totCap1 = totCap1;
    }

    public int getTotCap2() {
        return totCap2;
    }

    public void setTotCap2(int totCap2) {
        this.totCap2 = totCap2;
    }

    public int getTotCap3() {
        return totCap3;
    }

    public void setTotCap3(int totCap3) {
        this.totCap3 = totCap3;
    }

    public int getAvaCap1() {
        return avaCap1;
    }

    public void setAvaCap1(int avaCap1) {
        this.avaCap1 = avaCap1;
    }

    public int getAvaCap2() {
        return avaCap2;
    }

    public void setAvaCap2(int avaCap2) {
        this.avaCap2 = avaCap2;
    }

    public int getAvaCap3() {
        return avaCap3;
    }

    public void setAvaCap3(int avaCap3) {
        this.avaCap3 = avaCap3;
    }
}
