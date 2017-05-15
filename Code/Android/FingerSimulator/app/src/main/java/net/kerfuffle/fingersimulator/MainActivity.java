package net.kerfuffle.fingersimulator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    public static InetAddress ip;
    public static int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        Button connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                TextView ipText = (TextView) findViewById(R.id.ip);
                TextView portText = (TextView) findViewById(R.id.port);

                try {
                    ip = InetAddress.getByName(ipText.getText().toString());
                }catch (UnknownHostException e){}
                port = Integer.parseInt(portText.getText().toString());


                Intent intent = new Intent(context, ControllerActivity.class);
                startActivity(intent);
            }
        });


    }
}
