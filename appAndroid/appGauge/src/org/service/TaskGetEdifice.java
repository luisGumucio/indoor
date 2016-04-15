/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.entity.Edifice;
import org.gauge.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Unkon
 */
public class TaskGetEdifice extends AsyncTask<Void, Void, Boolean> {

//    private final String HTTP_RESTFUL = "http://10.0.0.8/IndoorService/index.php/EdificeController/getEdifice";
    private final String HTTP_RESTFUL = "http://recordatorio.esy.es/index.php/EdificeController/getEdifice";
    private ListView lstEdifice;
    private ProgressDialog progressDialog;
    private Activity context;
    private Activity activity;
    private Edifice[] edifices;

    public TaskGetEdifice(ListView lstEdifice, Activity context, Activity activity) {
        this.lstEdifice = lstEdifice;
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean resul = getRestFul();
        progressDialog.dismiss();
        return resul;
    }

    /**
     * Antes de comenzar la tarea muestra el progressDialog
     *
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(
                context, "Por favor espere", "Procesando...");
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result) {
            AdapterEdifice adapter = new AdapterEdifice();
            lstEdifice.setAdapter(adapter);
        }
    }

    private Boolean getRestFul() {

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(HTTP_RESTFUL);
        
        Boolean strResultado = true;
        try {
            //ejecuta
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            BufferedHttpEntity buffer = new BufferedHttpEntity(entity);
            InputStream iStream = buffer.getContent();
                            
            String aux = "";
                    
            BufferedReader r = new BufferedReader(new InputStreamReader(iStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                aux += line;
            }
            JSONObject jsonObject = new JSONObject(aux);
            JSONArray array = jsonObject.getJSONArray("edifice");
            edifices = new Edifice[array.length()];
            // Recorremos el array con los elementos cities
            for(int i = 0; i < array.length(); i++) {
              JSONObject row = array.getJSONObject(i);
              String idEdifice = row.getString("idEdifice");
             String NameEdifice;
             NameEdifice = row.getString("NameEdifice");
             edifices[i] = new Edifice(idEdifice, NameEdifice);
            }
                return strResultado;
            
        } catch (ClientProtocolException e) {
            strResultado = false;
            e.printStackTrace();
        } catch (IOException e) {
            strResultado = false;
            e.printStackTrace();
        } catch (JSONException e) {
            strResultado = false;
            e.printStackTrace();
        }
        return strResultado;
    }

    private StringBuilder inputStreamToString(InputStream is) {
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

    class AdapterEdifice extends ArrayAdapter<Edifice> {

        AdapterEdifice() {
            super(context, R.layout.listitem_edifice, edifices);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            ViewHolder holder;

            if (item == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                item = inflater.inflate(R.layout.listitem_edifice, null);

                holder = new ViewHolder();
                holder.titulo = (TextView) item.findViewById(R.id.LblTitulo);
                holder.subtitulo = (TextView) item.findViewById(R.id.LblSubTitulo);

                item.setTag(holder);
            } else {
                holder = (ViewHolder) item.getTag();
            }

            holder.titulo.setText(edifices[position].getNameEdifice());
            holder.subtitulo.setText(edifices[position].getIdEdifice());

            return (item);
        }
    }

    static class ViewHolder {

        TextView titulo;
        TextView subtitulo;
    }

}
