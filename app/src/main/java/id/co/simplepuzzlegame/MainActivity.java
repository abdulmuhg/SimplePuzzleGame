package id.co.simplepuzzlegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public GridLayout gridLayout;
    public int emptySpace;
    public ArrayList<String> alphabet;
    public ArrayList<String> correctAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        gridLayout = findViewById(R.id.gridLayout);
        TextView helper = findViewById(R.id.helper);
        correctAnswer = new ArrayList<>();
        correctAnswer.add("A");
        correctAnswer.add("B");
        correctAnswer.add("C");
        correctAnswer.add("D");
        correctAnswer.add("E");
        correctAnswer.add("F");
        correctAnswer.add("G");
        correctAnswer.add("H");
        correctAnswer.add("I");
        correctAnswer.add("J");
        correctAnswer.add("K");
        correctAnswer.add("L");
        correctAnswer.add("M");
        correctAnswer.add("N");
        correctAnswer.add("O");
        correctAnswer.add("_");
        Log.i("Correct Answer", String.valueOf(correctAnswer));
        alphabet = new ArrayList<>();
        alphabet.add("A");
        alphabet.add("B");
        alphabet.add("C");
        alphabet.add("D");
        alphabet.add("E");
        alphabet.add("F");
        alphabet.add("G");
        alphabet.add("H");
        alphabet.add("I");
        alphabet.add("J");
        alphabet.add("K");
        alphabet.add("L");
        alphabet.add("M");
        alphabet.add("N");
        alphabet.add("O");
        alphabet.add("_");
        Collections.shuffle(alphabet);
        for (int i = 0; i<16; i++) {
            final Button button = new Button(this);
            button.getId();
            button.setText(alphabet.get(i));
            final int _i = i;
            if (alphabet.get(i).equals("_")) {
                emptySpace = i;
                String sEmpty = String.valueOf(emptySpace);
                helper.setText(sEmpty);
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(MainActivity.this, ""+_i, Toast.LENGTH_SHORT).show();
                    int es = alphabet.indexOf("_");
                    int top = _i - 4;
                    int bot = _i + 4;
                    int left = _i - 1;
                    int right = _i + 1;

                    if (top >= 0 && top == es) {
                        switchButton(_i, es);
                        Toast.makeText(MainActivity.this, "Move top", Toast.LENGTH_SHORT).show();
                    } else if (es == bot) {
                        switchButton(_i, es);
                        Toast.makeText(MainActivity.this, "Move Bot", Toast.LENGTH_SHORT).show();
                    } else if (left >= 0 && left == es) {
                        switchButton(_i, es);
                        Toast.makeText(MainActivity.this, "Move Left" , Toast.LENGTH_SHORT).show();
                    } else if (right == es) {
                        switchButton(_i, es);
                        Toast.makeText(MainActivity.this, "Move Right", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            gridLayout.addView(button);
        }

    }

    public void switchButton(int index, int es){
        Collections.swap(alphabet, index, es);

        Button emptyBtn = (Button) gridLayout.getChildAt(es);
        emptyBtn.setText(alphabet.get(es));

        Button changeBtn = (Button) gridLayout.getChildAt(index);
        changeBtn.setText(alphabet.get(index));

        Log.i("Alphabets", String.valueOf(alphabet));
        check();
    }

    public boolean check(){
        if (correctAnswer.equals(alphabet)){
            Toast.makeText(MainActivity.this, "Congratulation, You Finished the Puzzle!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.retry){

        } else if (item.getItemId() == R.id.exit){

        }
        return true;
    }
}