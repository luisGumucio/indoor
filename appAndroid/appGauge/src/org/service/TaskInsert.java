/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.service;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.EditText;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

/**
 *
 * @author Luis Gumucio
 */
public class TaskInsert extends AsyncTask<String, Void, Boolean> {

    private ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    private Context context;
    private EditText nameEdifice;
    private EditText nameFloor;
    private EditText numberFloor;

    public TaskInsert(Context context, EditText nameEdifice, EditText nameFloor, EditText numberFloor) {
        this.context = context;
        this.nameEdifice = nameEdifice;
        this.nameFloor = nameFloor;
        this.numberFloor = numberFloor;
        builder = new AlertDialog.Builder(context);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Boolean r = false;
        ApiRest apiRest = new ApiRest();
        try {
            r = apiRest.uploadPhoto(params[0], nameEdifice, nameFloor, numberFloor);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Antes de comenzar la tarea muestra el progressDialog
     *
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Por favor espere", "Subiendo...");
    }

    /**
     * Cuando se termina de ejecutar, cierra el progressDialog y avisa *
     */
    @Override
    protected void onPostExecute(Boolean resul) {
        progressDialog.dismiss();
        if (resul) {
            builder.setMessage("Imagen subida al servidor")
                    .setTitle("informe")
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        } else {
            builder.setMessage("No se pudo subir la imagen")
                    .setTitle("informe")
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
    }
}
