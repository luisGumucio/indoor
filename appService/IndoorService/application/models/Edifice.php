<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Edifice
 *
 * @author Luis Gumucio
 */
class Edifice extends CI_Model {

    function __construct() {
        //call construct this model
        parent::__construct();
    }

    /**
     * metodo para insertar a la base datos
     */
    function insertEdifice($id, $edifice,$photo, $idFloor, $nameFloor, $numberFloor) {
        $this->db->set('idEdifice', $id);
        $this->db->set('NameEdifice', $edifice);
        $this->db->set('photo', $photo);
        $this->db->insert('edifice');

        $this->db->set('idFloor', $idFloor);
        $this->db->set('NameFloor', $nameFloor);
        $this->db->set('NumberFloor', $numberFloor);
        $this->db->set('Edifice_idEdifice', $id);
        $this->db->insert('floor');
        return $id;
    }

    /**
     * metodo para obtener todos los edificios de la base de datos
     */
    function getEdifice() {
        $edifice['edifice'] = array();
        $query = $this->db->get('edifice');
        $result = $query->result_array();
        foreach ($query->result_array() as $row) {
            array_push($edifice['edifice'], array(
                'idEdifice' => $row['idEdifice'],
                'NameEdifice' => $row['NameEdifice'],
                'photo' => base64_encode($row['photo'])
            ));
        }
        return $edifice;
    }

}
