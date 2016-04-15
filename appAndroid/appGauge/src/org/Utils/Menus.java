/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Utils;

/**
 *
 * @author Unkon
 */
public enum Menus {

    ADD(1),
    UPDATE(2),
    DELETE(3);

    private final int idMenu;

    Menus(int idMenu) {
        this.idMenu = idMenu;
    }
}
