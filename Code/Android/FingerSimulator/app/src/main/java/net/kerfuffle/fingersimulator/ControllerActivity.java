package net.kerfuffle.fingersimulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 12664 on 5/12/2017.
 */

public class ControllerActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        final Main main = new Main(MainActivity.ip, MainActivity.port);

        Button leftButton = (Button) findViewById(R.id.left);
        Button rightButton = (Button) findViewById(R.id.right);
        Button letterSelectButton = (Button) findViewById(R.id.letter_select);
        Button spaceButton = (Button) findViewById(R.id.space);
        Button backspaceButton = (Button) findViewById(R.id.backspace);
        Button capsButton = (Button) findViewById(R.id.capslock);
        Button sentenceEnterButton = (Button) findViewById(R.id.sentence_enter);

        leftButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                main.sendCommand(Global.LEFT);
            }
        });
        capsButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                main.sendCommand(Global.CAPSLOCK);
            }
        });
        spaceButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                main.sendCommand(Global.SPACE);
            }
        });
        backspaceButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                main.sendCommand(Global.BACKSPACE);
            }
        });
        sentenceEnterButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                main.sendCommand(Global.SENTENCE_ENTER);
            }
        });
        letterSelectButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                main.sendCommand(Global.LETTER_ENTER);
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                main.sendCommand(Global.RIGHT);
            }
        });
    }

}
