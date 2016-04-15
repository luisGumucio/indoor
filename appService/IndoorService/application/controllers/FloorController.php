<?php

defined('BASEPATH') OR exit('No direct script access allowed');
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of EdificeController
 *
 * @author Unkon
 */
class FloorController extends CI_Controller {

    public function getFloor() {

        $this->load->model('Floor');
        $data = $this->Floor->getFloor();
        header('Content-Type: application/json');
        $datares = array('status' => 200, 'Registros' => $data);
        $datares1 = array('status' => $data);
        echo json_encode($data);
    }

    public function insertFloor($id, $nombreEdificio, $idEdificio, $numeroPiso) {
        $this->load->model('Floor');
        $data = $this->Floor->insertFloor($id, $nombreEdificio, $idEdificio, $numeroPiso);
        header('Content-Type: application/json');
//        $datares = array('status' => 200,$data);
//            echo json_encode($data);
        $datares1 = array('status' => $data);
        echo json_encode($datares1);
//        $method = $_SERVER['REQUEST_METHOD'];
//        if ($method != 'POST') {
//            json_output(400, array('status' => 400, 'message' => 'Bad request.'));
//        } else {
//            $check_auth_client = $this->load->model('Edifice');
//            $params = json_decode(file_get_contents('php://input'), TRUE);
//            $data = $this->Edifice->insertEdifice($edifice);
//            $datares = array('status' => 200,$data);
//            echo json_encode($datares);
//        }
    }

}
