package net.kerfuffle.groupclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 12664 on 5/23/2017.
 */

public class ChatActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Main main = new Main(MainActivity.ip, MainActivity.port);
        main.start();

        if (main.hasNewSentence())
        {
            textview += main.getNewSentence();
        }
    }

}
