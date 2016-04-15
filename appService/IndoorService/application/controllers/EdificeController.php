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
class EdificeController extends CI_Controller {

    //put your code here

    public function getEdifice() {

        $this->load->model('Edifice');
        $data = $this->Edifice->getEdifice();
        header('Content-Type: application/json');
        $datares = array('Registros' => $data);
        $datares1 = array('status' => $data);
        echo json_encode($data);
    }

    public function create() {
        $method = $_SERVER['REQUEST_METHOD'];
        if ($method != 'POST') {
            json_output(400, array('status' => 400, 'message' => 'Bad request.'));
        } else {
//            $check_auth_client = $this->Edifice->check_auth_client();
//            if ($check_auth_client == true) {
//                $response = $this->Edifice->auth();
//                $respStatus = $response['status'];
//                if ($response['status'] == 200) {
            $params = json_decode(file_get_contents('php://input'), TRUE);
            if ($params['operacion'] == "") {
//                $respStatus = 400;
                $resp = array('status' => 400, 'message' => 'Title & Author can\'t empty');
            } else {
                $string = $params['operacion'];
                echo $string;
            }
        }
        echo $string;
    }

    function insertEdifice() {
        $method = $_SERVER['REQUEST_METHOD'];
        if ($method != 'POST') {
            json_output(400, array('status' => 400, 'message' => 'Bad request.'));
        } else {
            $this->load->model('Edifice');
            $params = json_decode(file_get_contents('php://input'), TRUE);
            $id = $params['idEdifice'];
            $edifice = $params['NameEdifice'];
            $photo = base64_decode($params['photo']);
            $idFloor = $params['idFloor'];
            $nameFloor = $params["NameFloor"];
            $numberFloor = $params["NumberFloor"];
            $data = $this->Edifice->insertEdifice($id, $edifice,$photo, $idFloor, $nameFloor, $numberFloor);
            header('Content-Type: application/json');
            $result = array('status' => 200, 'Registros' => $data);
            echo json_encode($result);
        }
        }
    }
    