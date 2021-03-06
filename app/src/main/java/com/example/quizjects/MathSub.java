package com.example.quizjects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;


public class MathSub extends AppCompatActivity {
    private TextView questionTV, questionNumberTV;
    private Button option1Btn, option2Btn, option3Btn, option4Btn;
    private ArrayList<QuizModal> quizModalArrayList;
    private ImageButton btn_back;
    int currentScore = 0, questionAttemped = 0, currentPos = 0;
    MediaPlayer correctEffects, wrongEffects, backEffects, resultEffects;

    private CountDownTimer countDownTimer;
    int timeValue = 20;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_sub);

        resultEffects = MediaPlayer.create(this, R.raw.result);
        backEffects = MediaPlayer.create(this, R.raw.close);
        correctEffects = MediaPlayer.create(this, R.raw.correct);
        wrongEffects = MediaPlayer.create(this, R.raw.wrong);
        questionTV = findViewById(R.id.idTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        option1Btn = findViewById(R.id.idBtnOption1);
        option2Btn = findViewById(R.id.idBtnOption2);
        option3Btn = findViewById(R.id.idBtnOption3);
        option4Btn = findViewById(R.id.idBtnOption4);
        btn_back = findViewById(R.id.btn_back);
        progress = (ProgressBar)findViewById(R.id.progress);

        quizModalArrayList = new ArrayList<>();
        getQuizQuestion(quizModalArrayList);
        setDataToViews(currentPos);
        backToMenu();

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())){
                    FancyToast.makeText(getApplicationContext(),"Correct, score +1",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                    correctEffects.start();
                    currentScore++;
                }
                else{
                    FancyToast.makeText(getApplicationContext(),"Incorrect",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                    wrongEffects.start();
                }
                countDownTimer.cancel();
                questionAttemped++;
                currentPos++;
                setDataToViews(currentPos);
            }
        });
        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())){
                    FancyToast.makeText(getApplicationContext(),"Correct, score +1",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                    correctEffects.start();
                    currentScore++;
                }
                else{
                    FancyToast.makeText(getApplicationContext(),"Incorrect",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                    wrongEffects.start();
                }
                countDownTimer.cancel();
                questionAttemped++;
                currentPos++;
                setDataToViews(currentPos);
            }
        });
        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())){
                    FancyToast.makeText(getApplicationContext(),"Correct, score +1",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                    correctEffects.start();
                    currentScore++;
                }
                else{
                    FancyToast.makeText(getApplicationContext(),"Incorrect",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                    wrongEffects.start();
                }
                countDownTimer.cancel();
                questionAttemped++;
                currentPos++;
                setDataToViews(currentPos);
            }
        });
        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())){
                    FancyToast.makeText(getApplicationContext(),"Correct, score +1",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                    correctEffects.start();
                    currentScore++;
                }
                else{
                    FancyToast.makeText(getApplicationContext(),"Incorrect",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                    wrongEffects.start();
                }
                countDownTimer.cancel();
                questionAttemped++;
                currentPos++;
                setDataToViews(currentPos);
            }
        });
    }
    private void showBottomSheet() {
        resultEffects.start();
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MathSub.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idTVScore);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        scoreTV.setText("Your score is \n" + currentScore + "/5");

        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionAttemped = 0;
                currentPos = 0;
                Collections.shuffle(quizModalArrayList); // x??o tr???n v??? tr?? c??c ph???n t??? trong m???ng
                setDataToViews(currentPos);
                currentScore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void backToMenu(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backEffects.start();
                countDownTimer.cancel();
                finish();
            }
        });
    }

    private void timer(){
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {
                timeValue -= 1;
                progress.setProgress(timeValue);
            }

            @Override
            public void onFinish() {
                FancyToast.makeText(getApplicationContext(),"Time's up",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                wrongEffects.start();
                questionAttemped++;
                currentPos++;
                setDataToViews(currentPos);
            }
        }.start();
    }

    private void setDataToViews(int currentPos) {
        timeValue = 20;
        questionNumberTV.setText("Number of questions: " + questionAttemped + "/5");
        if (questionAttemped == 5) {
            countDownTimer.cancel();
            showBottomSheet();
        }
        else{
            timer();
            questionTV.setText(quizModalArrayList.get(currentPos).getQuestion());
            option1Btn.setText(quizModalArrayList.get(currentPos).getOption1());
            option2Btn.setText(quizModalArrayList.get(currentPos).getOption2());
            option3Btn.setText(quizModalArrayList.get(currentPos).getOption3());
            option4Btn.setText(quizModalArrayList.get(currentPos).getOption4());
        }
    }

    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {
        quizModalArrayList.add(new QuizModal("Nh?? Thanh c?? ao b??o 1.000m2, ng??y h??m sau s??? l?????ng b??o s??? n??? g???p ????i ng??y h??m tr?????c, ?????n ng??y th??? 18 th?? b??o ???? n??? ???????c n???a ao. V???y ?????n ng??y th??? bao nhi??u th?? b??o s??? n??? ?????y ao?", "Ng??y th??? 1","Ng??y th??? 19","Ng??y th??? 36","Ng??y th??? 42","Ng??y th??? 19"));
        quizModalArrayList.add(new QuizModal("9 ??? 6 - 1\n27 ??? 1 - 2\n6 - 3 - ?", "2","3","4","5","3"));
        quizModalArrayList.add(new QuizModal("S??? ti???p theo c???a d??y 19, 28, 37, 46, ... l?? s??? n??o? ", "79","55","49","67","55"));
        quizModalArrayList.add(new QuizModal("H??y t??nh d??y s??? sau ????y: \n 1 + 2 + 3 + ..... + 99 = ...........", "4950","4500","4850","4650","4950"));
        quizModalArrayList.add(new QuizModal("Minh 4 tu???i, tu???i anh Minh g???p 3 l???n tu???i Minh. Khi anh Minh bao nhi??u tu???i th?? tu???i anh Minh ch??? c??n g???p ????i tu???i Minh?", "20","18","14","16","16"));
        quizModalArrayList.add(new QuizModal("John c?? 10 ????i t???t. N???u anh ta m???t 7 chi???c t???t ri??ng l??? th?? s??? ????i nhi???u nh???t m?? anh ta c??n l???i l?? bao nhi??u?", "6","7","3","5","6"));
        quizModalArrayList.add(new QuizModal("M???t ?????i b??ng r??? ch??i ???????c 2/3 tr???n ?????u v?? ???? th???ng 17 b??n, thua 3 b??n. Trong su???t tr???n ?????u c??n l???i ?????i b??ng c?? th??? thua nhi???u nh???t bao nhi??u m?? v???n th???ng ??t nh???t 3/4 to??n tr???n ?????u?", "5","4","3","7","4"));
        quizModalArrayList.add(new QuizModal("C??c ?????ng xu ???????c th??? v??o m???t c??i h???p v???i t???c ????? 2 fit kh???i/gi???. N???u m???t h???p r???ng c?? k??ch th?????c l?? d??i 4 fit, r???ng 4 fit v?? s??u 3 fit, s??? m???t bao l??u ????? ????t ?????y chi???c h???p ?????", "8","16","24","48","24"));
        quizModalArrayList.add(new QuizModal("N???u x v?? y l?? c??c s??? nguy??n t??? th?? c??c gi?? tr??? n??o trong c??c gi?? tr??? sau kh??ng th??? l?? t???ng c???a x v?? y?", "9","13","16","23","23"));
        quizModalArrayList.add(new QuizModal("Ti???n thu?? 1 ch??? ?????u xe trong gara l?? 10 ????la/tu???n ho???c 30 ????la/th??ng. M???t ng?????i c?? th??? ti???t ki???m ???????c bao nhi??u ti???n trong 1 n??m n???u thu?? theo th??ng?", "140","160","240","260","160"));
//        quizModalArrayList.add(new QuizModal("", "","","","",""));
        Collections.shuffle(quizModalArrayList); // x??o tr???n v??? tr?? c??c ph???n t??? trong m???ng

    }
}