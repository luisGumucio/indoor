/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.service;

import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Unkon
 */
public class ApiRest {

//    private final String HTTP_EVENT = "http://10.0.0.8/IndoorService/index.php/EdificeController/insertEdifice";
    private final String HTTP_EVENT = "http://recordatorio.esy.es/index.php/EdificeController/insertEdifice";

    /**
     * Envia la imagen codificada al servidor
     *
     * @param String encodedImage Imagen codificada con Base64
     * @throws IOException
     * @throws ClientProtocolException
     * @throws JSONException
     *
     */
    public Boolean uploadPhoto(String encodedImage, EditText name,
            EditText nameFloor, EditText numberFloor) throws ClientProtocolException, IOException, JSONException {
        HttpClient httpclient = new DefaultHttpClient();
        //url y tipo de contenido
        HttpPost httppost = new HttpPost(HTTP_EVENT);
        httppost.addHeader("Content-Type", "application/json");
        //forma el JSON y tipo de contenido
        JSONObject jsonObject = new JSONObject();

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        jsonObject.put("idEdifice", randomUUIDString);
        jsonObject.put("NameEdifice", name.getText().toString());
        jsonObject.put("photo", encodedImage);
        jsonObject.put("idFloor", UUID.randomUUID().toString());
        jsonObject.put("NameFloor", nameFloor.getText().toString());
        jsonObject.put("NumberFloor", numberFloor.getText().toString());
        
        
        //
        StringEntity stringEntity = new StringEntity(jsonObject.toString());
       // stringEntity.setContentType((Header) new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httppost.setEntity(stringEntity);
        //ejecuta
        HttpResponse response = httpclient.execute(httppost);
        //obtiene la respuesta y transorma a objeto JSON 
        String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
        JSONObject object = new JSONObject(jsonResult);
        if (object.getString("status").equals("200")) {
            return true;
        }
        return false;
    }

    /**
     * Transforma el InputStream en un String
     *
     * @return StringBuilder
     *
     */
    private Object inputStreamToString(InputStream is) {
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder;
    }
}
