package com.a4ita.adeva.adverbproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class TestActivity extends AppCompatActivity{

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private Intent intent;

    Map<String, String> questions = new HashMap<String, String>();
    int questionNo = 1;
    TextView quest;
    TextView ans1;
    TextView ans2;
    TextView ans3;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        quest = (TextView) findViewById(R.id.question);
        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        results = (TextView) findViewById(R.id.result);

        questions.put("Question1","He plays the flute __________.");
        questions.put("Right1", "Beautifully");
        questions.put("WrongA1", "Monster");
        questions.put("WrongB1", "Swimming");

        questions.put("Question2","She __________ gave us the money.");
        questions.put("Right2", "Generously");
        questions.put("WrongA2", "Happy");
        questions.put("WrongB2", "Animal");

        questions.put("Question3","After the party, confetti was strewn _________________.");
        questions.put("Right3", "Everywhere");
        questions.put("WrongA3", "Later");
        questions.put("WrongB3", "Elephant");

        questions.put("Question4","It’s time to go __________.");
        questions.put("Right4", "Now");
        questions.put("WrongA4", "Yesterday");
        questions.put("WrongB4", "Before");

        questions.put("Question5","My grandmother always smiled ____________.");
        questions.put("Right5", "Cheerfully");
        questions.put("WrongA5", "Tomorrow");
        questions.put("WrongB5", "Never");

        setQuestion();

        dl = (DrawerLayout) findViewById(R.id.test_activity);
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                menuItem.setChecked(true);
                dl.closeDrawers();
                switch (id) {
                    case R.id.learn:
                        intent = new Intent(TestActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.test:
                        intent = new Intent(TestActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.addAdverbs:
                        intent = new Intent(TestActivity.this, AddAdverbsActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void setQuestion() {

        List currentAnswers = new ArrayList(3);
        currentAnswers.add(questions.get("Right" + questionNo).toString());
        currentAnswers.add(questions.get("WrongA" + questionNo).toString());
        currentAnswers.add(questions.get("WrongB" + questionNo).toString());
        Collections.shuffle(currentAnswers);

        quest.setText(questions.get("Question" + questionNo).toString());
        ans1.setText(currentAnswers.get(0).toString());
        ans2.setText(currentAnswers.get(1).toString());
        ans3.setText(currentAnswers.get(2).toString());

        if (ans1.getText() == questions.get("Right" + questionNo).toString()) {
            ans1.setTag("Correct");
        } else {
            ans1.setTag("Incorrect");
        }

        if (ans2.getText() == questions.get("Right" + questionNo).toString()) {
            ans2.setTag("Correct");
        } else {
            ans2.setTag("Incorrect");
        }

        if (ans3.getText() == questions.get("Right" + questionNo).toString()) {
            ans3.setTag("Correct");
        } else {
            ans3.setTag("Incorrect");
        }





        /** Random random = new Random();
        int x = random.nextInt(5);

        if (x == 0) {
            quest.setText(questions.get("Question" + questionNo));
            ans1.setText(questions.get("Right" + questionNo));
            ans1.setTag("Correct");
            ans2.setText(questions.get("WrongA" + questionNo));
            ans3.setText(questions.get("WrongB" + questionNo));
        } else if (x == 1) {
            quest.setText(questions.get("Question" + questionNo));
            ans2.setText(questions.get("Right" + questionNo));
            ans2.setTag("Correct");
            ans1.setText(questions.get("WrongA" + questionNo));
            ans3.setText(questions.get("WrongB" + questionNo));
        } else if (x == 2) {
            quest.setText(questions.get("Question" + questionNo));
            ans1.setText(questions.get("Right" + questionNo));
            ans1.setTag("Correct");
            ans3.setText(questions.get("WrongA" + questionNo));
            ans2.setText(questions.get("WrongB" + questionNo));
        } else if (x == 3) {
            quest.setText(questions.get("Question" + questionNo));
            ans3.setText(questions.get("Right" + questionNo));
            ans3.setTag("Correct");
            ans2.setText(questions.get("WrongA" + questionNo));
            ans1.setText(questions.get("WrongB" + questionNo));
        } else if (x == 4) {
            quest.setText(questions.get("Question" + questionNo));
            ans2.setText(questions.get("Right" + questionNo));
            ans2.setTag("Correct");
            ans1.setText(questions.get("WrongA" + questionNo));
            ans3.setText(questions.get("WrongB" + questionNo));
        } */
    }

    public void onClickAns1(View v) {
        if (ans1.getTag() == "Correct") {
            questionNo++;
            if ((questionNo * 4) > questions.size()) {
                Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
                intent = new Intent(TestActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show();
                setQuestion();
            }
        } else {
            Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickAns2(View v) {
        if (v.getTag() == "Correct") {
            questionNo++;
            if ((questionNo * 4) > questions.size()) {
                Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
                intent = new Intent(TestActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show();
                setQuestion();
            }
        } else {
            Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickAns3(View v) {
        if (v.getTag() == "Correct") {
            questionNo++;
            if ((questionNo * 4) > questions.size()) {
                Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
                intent = new Intent(TestActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show();
                setQuestion();
            }
        } else {
            Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show();
        }
    }
}
