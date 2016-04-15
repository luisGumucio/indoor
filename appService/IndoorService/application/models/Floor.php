<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Floor
 *
 * @author Unkon
 */
class Floor extends CI_Model {
   function __construct() {
        //call construct this model
        parent::__construct();
    }

    /**
     * metodo para insertar a la base datos
     */
    function insertFloor($id, $nombreEdificio, $idEdificio, $numeroPiso) {
        $this->db->set('idPiso', 3);
        $this->db->set('nombrePiso', "sala");
        $this->db->set('idEdificio', 1);
        $this->db->set('numeroPiso', 4);
        $this->db->insert('piso');
        return $id;
    }

    /**
     * metodo para obtener todos los edificios de la base de datos
     */
    function getFloor() {

        $query = $this->db->get('piso');
//        return $this->output
//            ->set_content_type('application/json')
//            ->set_status_header(500)
//            ->set_output(json_encode(array(
//                    'text' => 'Error 500',
//                    'type' => 'danger'
//            )));
        return $query->result_array();
    }
}
