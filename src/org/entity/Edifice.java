/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entity;

/**
 *
 * @author Luis Gumucio
 */
public class Edifice {

    private String idEdifice;
    private String nameEdifice;

    public Edifice(String idEdifice, String nameEdifice) {
        this.idEdifice = idEdifice;
        this.nameEdifice = nameEdifice;
    }

    public String getIdEdifice() {
        return idEdifice;
    }

    public void setIdEdifice(String idEdifice) {
        this.idEdifice = idEdifice;
    }

    public String getNameEdifice() {
        return nameEdifice;
    }

    public void setNameEdifice(String nameEdifice) {
        this.nameEdifice = nameEdifice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.idEdifice != null ? this.idEdifice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edifice other = (Edifice) obj;
        if ((this.idEdifice == null) ? (other.idEdifice != null) : !this.idEdifice.equals(other.idEdifice)) {
            return false;
        }
        return true;
    }
}
