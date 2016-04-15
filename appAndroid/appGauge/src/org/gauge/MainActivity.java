package org.gauge;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.Utils.ActivityCaller;
import org.entity.Edifice;
import org.service.TaskGetEdifice;

public class MainActivity extends Activity {

    private TextView lblMessage;
    private ListView lstEdifice;
    private Edifice[] edifices;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lblMessage = (TextView) findViewById(R.id.LblMensaje);
        lstEdifice = (ListView) findViewById(R.id.listEdifice);
        new TaskGetEdifice(lstEdifice, MainActivity.this, MainActivity.this).execute();

//        lstEdifice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//
//                //Alternativa 1:
//                String opcionSeleccionada
//                        = ((Edifice) a.getAdapter().getItem(position)).getNameEdifice();
//
//                //Alternativa 2:
//                //String opcionSeleccionada = 
//                //		((TextView)v.findViewById(R.id.LblTitulo))
//                //			.getText().toString();
//                lblMessage.setText("Opci�n seleccionada: " + opcionSeleccionada);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuAdd:
                ActivityCaller.CallActivity(MainActivity.this,
                        ActivityEdifice.class, null);
                return true;
            case R.id.MenuEdit:
                lblMessage.setText("Opcion 2 pulsada!");
                return true;
            case R.id.MenuDelete:
                lblMessage.setText("Opcion 3 pulsada!");
                return true;
            case R.id.MenuUpdate:
                new TaskGetEdifice(lstEdifice, MainActivity.this, MainActivity.this).execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
