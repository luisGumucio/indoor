/**
 * 
 */
package org.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author Ortiz Pulido Andrea Patricia <andreaortiz@javeriana.eud.co>
 * @author Corredor Merch�n Jonnathan Silvestre
 *         <jonnathan.corredor@javeriana.edu.co>
 */
public abstract class ActivityCaller {
	public static void CallActivity(Context sender, Class receiver,
			Bundle bundle) {
		Intent intent = new Intent(sender, receiver);
		if (bundle != null)
			intent.putExtras(bundle);
		sender.startActivity(intent);
	}

	public static Bundle putInBundle(Bundle bundle, String name, Integer param) {
		bundle.putInt(name, param);
		return bundle;
	}

	public static Bundle putInBundle(Bundle bundle, String name, String param) {
		bundle.putString(name, param);
		return bundle;
	}
}
