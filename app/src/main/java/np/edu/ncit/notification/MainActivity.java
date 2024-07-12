package np.edu.ncit.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
Button setnotification;
private static final String CHANNEL_ID="channel 100";
private static final int NOTE_ID=100;
    com.google.android.material.textfield.TextInputLayout txtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setnotification=(Button)findViewById(R.id.button);
        txtMessage=(com.google.android.material.textfield.TextInputLayout)findViewById(R.id.textInputLayout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification note;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                     note=new Notification.Builder(MainActivity.this)
                            .setSmallIcon(R.drawable.img)
//                             .setLargeIcon(R.drawable.img)
                            .setContentText(txtMessage.getEditText().getText())
                            .setSubText("Notification Test")
                            .setChannelId(CHANNEL_ID).build();
                    nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Channel 120",NotificationManager.IMPORTANCE_HIGH));
                }
                else {
                    note =new Notification.Builder(MainActivity.this)
                            .setSmallIcon(R.mipmap.logo)
                            .setContentText(txtMessage.getEditText().getText())
                            .setSubText("Notification Test")
                            .build();

                }
                MediaPlayer mp;
                mp= MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_NOTIFICATION_URI);
                mp.setLooping(true);
                mp.start();
nm.notify(NOTE_ID,note);
//                nm.notify(NOTE_ID,note);
            }
        });
txtMessage.getEditText().clearComposingText();
    }
}