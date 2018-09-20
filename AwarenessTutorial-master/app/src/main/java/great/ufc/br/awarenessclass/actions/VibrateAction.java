package great.ufc.br.awarenessclass.actions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import com.google.android.gms.awareness.fence.FenceState;

public class VibrateAction extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int i = FenceState.extract(intent).getCurrentState();
        switch (i){
            case FenceState.TRUE:
                Toast.makeText(context, "andando", Toast.LENGTH_SHORT).show();
                vibrate(context);
                break;
            case FenceState.FALSE:
                Toast.makeText(context, "parado", Toast.LENGTH_SHORT).show();
                break;
            case FenceState.UNKNOWN:
                Toast.makeText(context, "?????", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void vibrate(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            //deprecated in API 26
            v.vibrate(500);
        }
    }
}
