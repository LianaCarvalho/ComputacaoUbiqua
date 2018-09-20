package great.ufc.br.awarenessclass;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.FenceClient;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.DetectedActivityFence;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.awareness.fence.HeadphoneFence;
import com.google.android.gms.awareness.state.HeadphoneState;

import great.ufc.br.awarenessclass.actions.NotificationAction;
import great.ufc.br.awarenessclass.actions.ToastAction;
import great.ufc.br.awarenessclass.actions.VibrateAction;

public class FenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fence);

        //Criar as AwarenessFences
        AwarenessFence test = DetectedActivityFence.during(DetectedActivityFence.WALKING);
        AwarenessFence headphone = HeadphoneFence.during(HeadphoneState.PLUGGED_IN);
        AwarenessFence multiple = AwarenessFence.and(test,headphone);

        //Filtros de Intent
        IntentFilter hp = new IntentFilter("headphone");
        //Registrar Receivers (actions) na pilha do Android
        registerReceiver(new ToastAction(), hp);
        registerReceiver(new VibrateAction(), hp);
        //Registrar PendingIntents getBroadcast com os filtros criados
        PendingIntent pi = PendingIntent.getBroadcast(this, 123, new Intent("headphone"), PendingIntent.FLAG_CANCEL_CURRENT);
        //Registro de Fences no Google Awareness API
        FenceClient fc = Awareness.getFenceClient(this);
        fc.updateFences(new FenceUpdateRequest.Builder().addFence("Headphone", headphone, pi).build());



        IntentFilter x = new IntentFilter("test");
        //Registrar Receivers (actions) na pilha do Android
        registerReceiver(new VibrateAction(), x);
        //Registrar PendingIntents getBroadcast com os filtros criados
        PendingIntent y = PendingIntent.getBroadcast(this, 123, new Intent("test"), PendingIntent.FLAG_CANCEL_CURRENT);
        //Registro de Fences no Google Awareness API
        FenceClient z = Awareness.getFenceClient(this);
        z.updateFences(new FenceUpdateRequest.Builder().addFence("test", test, y).build());


        IntentFilter a = new IntentFilter("multiple");
        //Registrar Receivers (actions) na pilha do Android
        registerReceiver(new NotificationAction(), a);
        //Registrar PendingIntents getBroadcast com os filtros criados
        PendingIntent b = PendingIntent.getBroadcast(this, 123, new Intent("multiple"), PendingIntent.FLAG_CANCEL_CURRENT);
        //Registro de Fences no Google Awareness API
        FenceClient c = Awareness.getFenceClient(this);
        c.updateFences(new FenceUpdateRequest.Builder().addFence("multiple", multiple, b).build());


    }
}
