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
                Collections.shuffle(quizModalArrayList); // xáo trộn vị trí các phần tử trong mảng
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
        quizModalArrayList.add(new QuizModal("Nhà Thanh có ao bèo 1.000m2, ngày hôm sau số lượng bèo sẽ nở gấp đôi ngày hôm trước, đến ngày thứ 18 thì bèo đã nở được nửa ao. Vậy đến ngày thứ bao nhiêu thì bèo sẽ nở đầy ao?", "Ngày thứ 1","Ngày thứ 19","Ngày thứ 36","Ngày thứ 42","Ngày thứ 19"));
        quizModalArrayList.add(new QuizModal("9 – 6 - 1\n27 – 1 - 2\n6 - 3 - ?", "2","3","4","5","3"));
        quizModalArrayList.add(new QuizModal("Số tiếp theo của dãy 19, 28, 37, 46, ... là số nào? ", "79","55","49","67","55"));
        quizModalArrayList.add(new QuizModal("Hãy tính dãy số sau đây: \n 1 + 2 + 3 + ..... + 99 = ...........", "4950","4500","4850","4650","4950"));
        quizModalArrayList.add(new QuizModal("Minh 4 tuổi, tuổi anh Minh gấp 3 lần tuổi Minh. Khi anh Minh bao nhiêu tuổi thì tuổi anh Minh chỉ còn gấp đôi tuổi Minh?", "20","18","14","16","16"));
        quizModalArrayList.add(new QuizModal("John có 10 đôi tất. Nếu anh ta mất 7 chiếc tất riêng lẻ thì số đôi nhiều nhất mà anh ta còn lại là bao nhiêu?", "6","7","3","5","6"));
        quizModalArrayList.add(new QuizModal("Một đội bóng rổ chơi được 2/3 trận đấu và đã thắng 17 bàn, thua 3 bàn. Trong suốt trận đấu còn lại đội bóng có thể thua nhiều nhất bao nhiêu mà vẫn thắng ít nhất 3/4 toàn trận đấu?", "5","4","3","7","4"));
        quizModalArrayList.add(new QuizModal("Các đồng xu được thả vào một cái hộp với tốc độ 2 fit khối/giờ. Nếu một hộp rỗng có kích thước là dài 4 fit, rộng 4 fit và sâu 3 fit, sẽ mất bao lâu để đôt đầy chiếc hộp đó?", "8","16","24","48","24"));
        quizModalArrayList.add(new QuizModal("Nếu x và y là các số nguyên tố thì các giá trị nào trong các giá trị sau không thể là tổng của x và y?", "9","13","16","23","23"));
        quizModalArrayList.add(new QuizModal("Tiền thuê 1 chỗ đậu xe trong gara là 10 đôla/tuần hoặc 30 đôla/tháng. Một người có thể tiết kiệm được bao nhiêu tiền trong 1 năm nếu thuê theo tháng?", "140","160","240","260","160"));
//        quizModalArrayList.add(new QuizModal("", "","","","",""));
        Collections.shuffle(quizModalArrayList); // xáo trộn vị trí các phần tử trong mảng

    }
}