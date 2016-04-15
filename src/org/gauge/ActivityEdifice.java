/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gauge;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;

import org.service.TaskGetEdifice;

import org.service.TaskInsert;

/**
 *
 * @author Unkon
 */
public class ActivityEdifice extends Activity {

    private ImageView photo;
    private Bitmap photobmp;
    private Button button;
    private EditText nameEdifice;
    private EditText nameFloor;
    private EditText numberFloor;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edifice);
        photo = (ImageView) findViewById(R.id.photo);
        button = (Button) findViewById(R.id.btnUpload);
        nameEdifice = (EditText) findViewById(R.id.nameEdifice);
        nameFloor = (EditText) findViewById(R.id.nameFloor);
        numberFloor = (EditText) findViewById(R.id.numberFloor);
        photo.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
//                        Intent intent = new Intent();
//                        intent.setType("image/*");
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(Intent.createChooser(intent, "Complete la acción usando..."), 1);
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivityForResult(intent, 1);
                    }
                });
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        //Codifica la imagen con Base64
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        photobmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                        byte[] imageBytes = baos.toByteArray();
//                        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                        //Se ejecuta en segundo plano para no colgar la aplicacion
                        new TaskInsert(ActivityEdifice.this, nameEdifice, nameFloor, numberFloor).execute("iVBORw0KGgoAAAANSUhEUgAAAFAAAABkCAYAAAAR+rcWAAAABGdBTUEAALGPC\\/xhBQAAAAlwSFlzAAAOxAAADsQBlSsOGwAAFWlJREFUeF7t3HWMZcXWBfDmHxISQggWJBBIkCDB3XkPd3dncIcnuDvB3d0Gd3d314c83N31fO9XmdUpLt19e5iemTtfWEnlnFOnTsmqvXftknu7nn\\/++Sb4+eefh9w1zW+\\/\\/Vauv\\/76a\\/Pdd9+V+xNOOKH56aefStyGG27YfP\\/9982hhx7afPPNN93pwX0C+N59ff3ll19KkJc84frrr28uvPDCZr311ivP+b6T0ZZA12+\\/\\/bb54IMPmr333rvEIW6NNdYo75B33HHHNT\\/88EMhI3DvvbTw448\\/dndEUJdx7rnnNqnLyiuvXMgdFdCWQOQ99dRTzeOPP97sv\\/\\/+JQ4pO+ywQ0kjIO\\/YY48t8SFOyP3nn3\\/eTS5pQ6Z48O6CCy5o3nvvvZLPZ5991my++ebNaaed1p2mk9GWQJJw8cUXN3fddVez2267Nbfccktz5513FjI1GJCDPCQiPNITEgP3vhH\\/5ZdfNp988klzxRVXFPJIp\\/KlIYEHHnjg\\/w8CXQcPHty89tprRSqeeOKJ5pFHHilqG\\/UEDUceEmPjwPchzDVkw5lnntl8\\/PHH3R0BX3\\/9dbGvRx111B9UvhPRlkDqdv755zc333xzs9FGGzX33HNPtzSGnAA5Gl2TiBzxeS\\/O9cQTT2w++uijEq9cqo1k9yTQQMK+djr6ReDpp5\\/e3HHHHc0WW2zRPP3004VA95GcmkQERZ2\\/+uqrEkcygb1TxiGHHFJI9L3npAuWW265Zrvtthvy1NnolwqTthtuuKHZfvvtm\\/vuu6+57bbbmn\\/\\/+9\\/dagqtkoiUgw8+uHRAraIHHXRQt2T5JursPnZw8cUXb\\/bcc88S3+noeumll\\/5grxLEUy2Nvuaaa5rVV1+9efbZZ5urrrqq+ec\\/\\/9mdBklIy3eAGO\\/222+\\/QgrS5ANJ4xq1BmUhcbHFFuse8TsdbQnUIP6f0ZJtevLJJ5vLLrus2XHHHQsxjD4SkAiuVNa7EGvAMSggSL5AncF7AXzj20UXXbQ54IADSlynoy2Bwl577dVcd911xXmm8ldeeWWRwHyDGEB27F3sGqJIU9ySqLNvpBEXApWlQ\\/72t78V9R8V0JZAYANvuummZq211mpefPHFIo0hMFJGesA3iDSi\\/ve\\/\\/y3TP0DW7rvvXu4jia2DkHy++OKLZsEFF2wOP\\/zwEtfpaEug69Zbb91ce+21RYXNSrg12267bbfkkRqQFpBqRiEdIqOm0u+zzz4lTT2w1GV++umnzXzzzdccffTRQ952NvpF4MYbb1wIXHHFFQuB55xzTrPNNtsUqfMeEIQU1zjdJDFIOhLGJLS+S1l8w7nmmqs5\\/vjjh7ztbPRLhTm1bGAIPPvsswuBeU8C45o89thjZW4b94QkAuJCOKJJIklNWZHm999\\/v5ljjjmak046qTx3OtoSSKJWW221YveWWmqp5uGHHy6NGzRoUBkwqBxId\\/vttzf3339\\/d34h0bsgAwcSM7B4j0wwL55tttmaU045pTx3Orpefvnl3zWwRuK5FaTO7OCdd95p3nzzzeZf\\/\\/pXtx1ztcBw6623FiIQ6IpAUlcHkpYgjRlLgEx5L7nkkmUBIyN6J6MtgRpKAi+\\/\\/PJmjz32KPEGBlM5DUaSxQYLDMmHfYtKtkPtYAMTMeGEEzaHHXbYkJjORlsCqdw888zTXHTRRaVR4kjSLrvsUu7NUKwVZuYRVfTOM5KFViSe\\/WQGzFjglVde6SZ0lFhM6I8KzzzzzEXK2D7qSgK32mqrYg\\/ZPOmE2LxIZm\\/5Qk0soqiu0d0gdN55540S5EG\\/CFxooYXKIGI6Bq+++mqz8MILF6l55plnitRwsF3NVKzYkEr37FgdEJNA+t5+++1uP1Ie++67b1k6q92cTka\\/bODUU0\\/drcLU0v6Ie5LCWba2x+8TzCC4MQYBroql\\/wSrOQkGJMGcev311y8zG4PHOOOMU76HmINORr8kcKKJJmquvvrqMmLGRaF+pCTP7GLgXexfb6hVOKMtwizYvvvuu73WqdPQlkBEzDjjjMXHY9zFpcF5n+\\/ZvagecnrLF0JgOiBpuULUm+THpnYyup577rnuxpAAV9CgxI833nhlDTCjo5FZI+MHBtIipJY+1\\/iA4l3lXUsshEj2L7MX3yKy7ghxQvKwfxKoT\\/IB78UJ8kkdUjfwLNTtrdFTXI3fEaiQJE6G0NXVVXbjLEvVdsk9iXOtK5b8hLpBeQ\\/uI60ZREi2pTLTvpDW2\\/fSmvbZ2I8pqdNCnT4QJ29B+pq4ntL3hDqPPxAYeAl6DYFmGlmaUuG8ryFOHnqc+rny8eQtn+Sfq3zEZ3FVRxhAXEm5\\/LwHecizdo\\/EmfqBdPKLxKUOwwp5ykedlKFs5QZlT0SEUBOYRD6eeOKJm0svvbR7iSmNas2sJyACNAjkl\\/lwEEkAozqoeF9o\\/b4n9FQ3cQkpM0hcpDntbIU03uGr7aaSuJlmmqks42+22WbFDpJEqmaRVWD4SagRNNue4tgzg9R\\/\\/vOf4jtywEkkqfnwww+LCooTxLF9bC31rBuKTDa3rl8aKJDgkOHZu0hMO\\/iur5B8wkcNcV0vvPDCkMeeCbTxPdlkkxVi7MyZEzsAhMQjjjii+H18QluVluGtsNjPYC852jvvvHOz\\/PLLN0svvXSZElpttmBq582635xzzlmeF1hggWbeeectZc0666zN\\/PPPX5a1OPG+taG1zjrrNGuvvXaz5pprlu0FwcLupptuWvxM5dmCveSSS4rG6GS+qQ18Z2\\/4sgSBS+Yg04033lh2Gc2mHnrooebRRx8tez4mB0wb4aItOhSRkUySp1PZ7rYEiuMHTjDBBMUWjjHGGM0UU0zRzD333KXRVqmtEy677LLNMsssU\\/Z0Pa+66qqlgZxmZ12QKvzjH\\/8oS1VI987C6VlnnVWCqeKWW25ZnGqLuLY2OdnIsfpjZVywpapjNtlkk0KgfNzbcpBOGa677rprs8EGG5QDAZbf1MP3ceB32mmnolXilWuNU17eyV8+9mesRi2xxBLF0XdPCHT8LLPM0p5AcB\\/7SKydaRFHDU3FLHFRQSopeLYfYmU6vWkUJxErrLBCUXGSTMVJDGkxmyHViyyySKm4dGY60h1zzDFlRWiGGWYoHcWhJ2XiSWQ6B0kI0IHpRN+tssoqpWORQKJ1vjVHc\\/xJJpmk2HgrQIRk3HHHbcYee+xmrLHGasYcc8xm\\/PHHbyaddNJmuummKxpjCmvblQa5dmlkEBENeTWJfxbyyMjJHIw22mjdNk5ZSeM9kEJLZSSbilAvaqejQH2tTcoLEFnXt863v1C\\/119\\/\\/XeDKJVlm82KCIPFDlcL0ObsrvbIhzuBYARWIbZo9NFHLysukAYjT+Wls2BBzaikwYFtohnSaKgAp556aslzIAiUDw1KJ4Iy4wnU+blPOmlGCIGpAB+PSiEwlWgt6+STTy62ie1j7OvKxl8E0skUDASByiBtgTzEhY8MIOJ1dPIfYQSCvNhOJJppRJJShsoIJNAAwIhbQsuU0Tug\\/rmn2sNKYL6t1TedFtScSCdIo14jjMD0cL1UpRIpQ7kqxi2y2m0ENeiANCorDbfCFbgmA0Fg\\/T30RGDgPuncD3cCowqRHCoaCUIKpMLK41tyU4ymfLXMk6FWM1JsFB8IFU56nQrJT71oCi2IGuvkvIcRQmBdMVO1ECZeXKQRWQ4yUV8+INLtuWRxQeVDOgdZ3EBIYOqHqBrKE6fjDCgpP\\/krf4SocIgieWYxoDJBGuBqBmMQ4cxKQ2WpsgOepoT8SqvdBhTvB4LA2ONawiH1Vk46XRtyr5wu\\/lUqEETFBgpplMAGpoHiQx6IN9c2iJhhgDR9Bc72gw8+WObYpIN\\/+MYbbxS3hESTnIS4QdIlaGtfoR1GOoG1JIo3ozCVYwMhRPUWOLr2aLI4oT20yiIGh9fmVoID8q3B3LevYIOsrzDSCcw9uLcgYCpHhSFE9RZAJ5A29sq9+NjMdmjNb2jDSCewLlujndwyiNQHONuFvtAuXf2+p5B69xZGOoE1EOgQJunjC\\/YHaajge3Vn24zgpFKcUKcT+ot831voCALrK+IsX+UcTjtohPoajJJvIL\\/WMLToKY86dASBSADPRmADCFXuL3wXiXAvRBLz3BvyvrfQDh1BoOdUmO2z4Mmhbq3X0CD51UhcHUJ8b6EdRgiBJCH+npXnlCfeSCmkTP4fKbRdWUtQb+HPor\\/fx8kGvmT9DYJHCIHIi0vhBFbKi6evIinTWiA7eOSRR5bnWhp6Cr7\\/s8H37aBe6p7yoLa3I4RA+aVAc+GUJ75ujKv9CWpsVSYE9xXaIQ1P\\/q0hdestAE0Iea55FkYIgSqSCpvHpjwVCIkBCTQC+3VTvukrpCFCT+9r1PH5ph1a01HpECt+hA0ioGCbRGkE5KoyJM5ZFxJoE99zGtBbUNfepMj7nqDMfJ+61KGGOrB9Fhr4ltRZHCmEEUJgoHAb58prbZwyVXDdddcti6nWDfuDnhpdoyaqJ\\/T1bQ15xI6DDoLhTmCdN\\/H3gx1xqUDsXGBHzoKCDSiIhPUWWiWuJqzO17146Xyn3FYJzzet3\\/nxT5a6pAPxltRGKIFUwSqzuBBYuwbi7PM6acCdcRqipxWSOtQrI9YKBduNCRpvleatt94q64n2qK3M2O2zZSo+AReCfe0ER1Ts\\/9rolw\\/EtUFmV34oAzVxraT2hYg2FawlIVLiPZuRZfj0orTKSbmuNtSpcNyY\\/iBlZf8WCcqy1+vctnM5rggULH85UGApDNhlDj7zYifQYVKd53vHWTj1fmSUuqa9ngeUwHp4F5JHni036dEa0kQale8EAQL7+2PDdAIJsgbosBOCkENiqBkpJE3WCUmgMzF+YYBsdfaNX+Ej7d577y1nZO6+++6Sn3gmRceCtpJA7XE\\/IASmESEthAGpzF6DyqqQ9CENagIdJLKplLOI\\/QUCrURnkGIaoG5HNMEWQU6I2TJAmG8Rinjbrw4WId+xE+uTjnJk9HUNhpnAVsJca3IgjWGIqbByYsTBN\\/IRr6Kmctk0agdLVtQVgeyfTShEycv3dWNju8yGHKVjC9UJWQilslScejvLI57N9ntpEqhdITFlDAiBIRHkEQIVBBoIVIr6gG\\/yPt\\/7NoeL7PnW9ekL0mUvJBIOqZOrOB3pyt4Bu0i9EeZbWwAGIxKpo90zORx7J81qhJ8BUWFpk14PpeKMuYqwJyrONtUNrBECHZmzIm3GUktPX\\/Ad6aN6VJh01PWXT8okcVRY57GHkaikV4\\/cu9r186ssp8JIcDjKdcAIzDWVFxhq5\\/9sZWoAKWGoeyLGe+U7ZGlb08gYlesL6SxuCfhds++Qkk4JKQI1JVmIlNYoa3NJB1BZ53aotjTm7Y6XODfouBxbnkBymYEBITCN8E3ycFW5jGjAztiCTN41QSHQ2T1L+iQptrMvxBYpQ746S8c98MADZS+ZTdRpjiAbbb1XD3lz1qVjRyOhIR44\\/dwaK+QOb9acRAi6YhdqSJhM+gONSB4hUA85AMmGMMjAldC4VMQIXUMejvfyuwQjsZ+L+f2cDXdrhOJIhsYxB9wOdtV+MqKU5Z2ju6SN3RWQplwEKYcUcZWkSdmg3cjRuTrAYScmxUnWQNq0YZgJrNMhrz78QzWMpkY2caTPaAlIB2qQe+X+\\/e9\\/L+uBBpFIqG81Sl3FxUyAq3LrwUkaaeWtI+MjGijYPYHKOz7CLkPqIC8ku6q\\/VSGdmP+IgJqfASOwzkNBKsZYU0W2BVxJg\\/xr0uThGfnTTz99mQ+TXHmKl5\\/gWVrfBInPKo\\/7vE8HyCP1cy8eqQY43zlnSJLZPRpC1dWdVCPPwXmeQQiElDPMBPpWWuqo58D3nvWgSlI1ksAechVqEsS7Vzn3\\/EASyOaQEMtbCNUAm+5UyiF1WwOklJFHAl\\/NvTODrkgh8QYH9TCdMxPhLCPPYMH+6ej4gMo3kpNc0KEWdq0MMSMZsetOGmYCazWrpQQMUCqTwlSY9w8Ijg2MzSGdflqr5xHmhD3yqI+RmYONXFd2yWCTYyAWYsUJOXXv3iKAztARfDk\\/lXDvDLZjxP6RZKWVVioH0I207h1OHzRoUCmfSbGwoRztijRrk+swE1iLdSCOy2IU1vMkD5nURHydt7R5JgGzzz57qbAjvgYSPe9qwx2pfDLvcvLeTw\\/4jn6LwgWycuI3JgYjP0OYdtppmymnnLKYBlcd5MT95JNPXn6FOs0005Q46Um\\/PM06ECxvXoEO0YERhPBDGoeZQJCWFOkdV9+bR3KG\\/YmE1Q2FsTEksJZayAkC5CJQhama9+IFeSaIT1ngHpQZ+EbbqCi1JO2e1UN66pkBT+fyEAx26ptgoOETEgAjdq2+8nc\\/zAS2pg05KozAM844o1RQGraKvXEftYWUb+bihyx6Pr5aO4Q819wnv9bvlStN6pvy1cU33tXfpIMQzeYmf4jmdcksIT08NJBez6RSnqkiCbI+x1ll2FWQX6XS7l3TGD6ZOFLgV0CmTXF3OgFmLdYn1ZeWEAhEI3GYCazJS8\\/Lg1oiUS+SLODSRM2kSZmk1b10flqFQGR2AtKmTP+schvZPVPzYSYwBUDrt54RTB0BgdShFdIAqbPy6zd3cXA7DXgC7R4QCQSiLYRMrote4pDyx\\/LDGj5h0ik8ZYkDvpjRD4lUpROQhQN1jM1U7wG1gckwfp3CTJecXzb7yEF20y3kJaSsVMZ0i89mIGFnOgkRDjwxS9ooDDOBaTxEqgQFUlf3CpS\\/GUIqAt6JTx7sC8fWQFI74CMTsc86VN0zYoPrgNjAeiBxZWyJfvKTRkBgXYYr8qLCply8f85rXJ+RjdQtLo121PUaEBsYglJIIC92IwVamxOnUinHN4JnDqtpFmc6Pd4JCIk1eZHErvI0HJGOAcvj4DkVCUni+Ik21f3UP+87HR1FoCmfhUu\\/Jv+LwCEYGgKtAZoHWwTIN52OkU5gPYrzGy1DWSWRZlQgsSMIzHub2Nb1LD3VaToZI53AekQ2SlsLtIYHUe9ORkcRaK5sddkiJ\\/xF4P8wNATayEHgVFNNVZ5r+9ipGOkE5mo2Y9Pb\\/oc\\/uBlVMNIJzDuevQ1zBPqPrVEFHUMgVXbszChsk2hUwUgnMGDv7J\\/YdbMqDfmuk9ExBHq2Yc6RtqmUuE5HxxAI\\/sHNRrq9WfhrFP4fhoZAB3kQ6HQAZBmpk9Exgwj4X0AEOnUAfxFYAWH+gtPsop5hiA+Zzv8h0PmVOr6TMdwJrMnyk4EakcS\\/COwDNQmDBw8u11pta3X2v6pGYT84rOM7GcOdQEQIRlQ\\/L2DX6tE176GVwMR3Lprm\\/wAc8vyO0KiwfAAAAABJRU5ErkJggg==");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            String aaa = getRealPathFromURI(selectedImageUri);
            photobmp = BitmapFactory.decodeFile(aaa);
            photo.setImageBitmap(photobmp);
        }
    }

    /**
     * Obtiene la ruta del archivo de imagen en el dispositivo
     *
     * @param Uri
     * @return String
 *
     */
    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getApplicationContext().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
