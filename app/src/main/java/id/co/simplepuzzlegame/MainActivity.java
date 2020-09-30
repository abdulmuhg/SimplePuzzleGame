package id.co.simplepuzzlegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public GridLayout gridLayout;
    public int emptySpace;
    public ArrayList<String> answer;
    public ArrayList<String> correctAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineCorrectAnswer();
        startGame();
    }

    public void defineCorrectAnswer(){
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
        correctAnswer.add(" ");
        Log.i("Correct Answer", String.valueOf(correctAnswer));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void startGame(){
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();
        answer = new ArrayList<>();
        answer = (ArrayList<String>) correctAnswer.clone();
        Collections.shuffle(answer);

        for (int i = 0; i<16; i++) {
            final Button button = new Button(this);
            button.setBackground(getDrawable(R.drawable.bg_btn));
            button.setTextColor(getColor(R.color.white));
            button.setText(answer.get(i));
            final int  _i = i;
            if (answer.get(i).equals(" ")) {
                emptySpace = i;
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int es = answer.indexOf(" ");
                    int top =  _i - 4;
                    int bot =  _i + 4;
                    int left =  _i - 1;
                    int right =  _i + 1;

                    if (top >= 0 && top == es) {
                        switchButton( _i, es);
                    } else if (es == bot) {
                        switchButton( _i, es);
                    } else if (left >= 0 && left == es) {
                        switchButton( _i, es);
                    } else if (right == es) {
                        switchButton( _i, es);
                    }
                }
            });
            gridLayout.addView(button);
        }

    }

    public void switchButton(int index, int es){
        Collections.swap(answer, index, es);

        Button emptyBtn = (Button) gridLayout.getChildAt(es);
        emptyBtn.setText(answer.get(es));

        Button changeBtn = (Button) gridLayout.getChildAt(index);
        changeBtn.setText(answer.get(index));

        Log.i("Answer", String.valueOf(answer));
        if (check()){
            showSnackBar();
        }
    }

    public void showSnackBar(){
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.constrainLayout),
                "Selamat! Anda berhasil menyelesaikan puzzle", Snackbar.LENGTH_INDEFINITE);
        mySnackbar.setAction("Ulangi", new retryGame());
        mySnackbar.show();
    }

    public class retryGame implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startGame();
        }
    }

    public boolean check(){
        return correctAnswer.equals(answer);
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
            startGame();
        } else if (item.getItemId() == R.id.exit){
            finish();
        }
        return true;
    }
}