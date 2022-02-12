package com.example.quizjects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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

public class BiologySub extends AppCompatActivity {
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
        setContentView(R.layout.activity_biology_sub);


        resultEffects = MediaPlayer.create(this, R.raw.result);
        correctEffects = MediaPlayer.create(this, R.raw.correct);
        wrongEffects = MediaPlayer.create(this, R.raw.wrong);
        backEffects = MediaPlayer.create(this, R.raw.close);
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
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BiologySub.this);
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
        quizModalArrayList.add(new QuizModal("Ở người bộ nhiễm sắc thể lưỡng bội 2n = 46. Số nhóm gen liên kết là", "23","46","92","69","23"));
        quizModalArrayList.add(new QuizModal("Ở người, bệnh mù màu (đỏ, lục) là do đột biến gen lặn nằm trên nhiễm sắc thể giới tính X gây nên (Xm). Nếu mẹ bình thường, bố bị mù màu thì con trai mù màu của họ đã nhận Xm từ", "mẹ","bố","bà nội","ông nội","mẹ"));
        quizModalArrayList.add(new QuizModal("Ở các loài sinh sản vô tính bộ nhiễm sắc thể ổn định và duy trì không đổi qua các thế hệ tế bào và thế hệ cơ thể là nhờ quá trình:", "thụ tinh","giảm phân","nguyên phân","nguyên phân và giảm phân.","thụ tinh"));
        quizModalArrayList.add(new QuizModal("Quần thể có những tính trạng chung về hình thái, sinh lý, có khu phân bố xác định, trong đó các cá thể có khả năng", "tác động qua lại","cách li địa lý","giao phối tự do","quan hệ qua lại","cách li địa lý"));
        quizModalArrayList.add(new QuizModal("Theo Lamác, cơ chế tiến hoá là sự di truyền các đặc tính", "của cha mẹ cho con cái.","thu được trong đời sống cá thể.","biến dị xác định","không xác định.","thu được trong đời sống cá thể."));
        quizModalArrayList.add(new QuizModal("Theo Đắc uyn, cơ chế tiến hoá là sự tích lũy các", "biến dị có lợi của cha mẹ cho con cái.","biến dị có lợi và đào thải các biến dị có hại","đặc tính của cha mẹ cho con cái."," biến dị có lợi và đào thải các biến dị không xác định.","biến dị có lợi và đào thải các biến dị có hại"));
        quizModalArrayList.add(new QuizModal("Tỉ lệ kiểu gen được tạo ra từ AAaa x AAaa là:", "1AAAA : 8 AAAa : 18 AAaa : 8Aaa : 1 aaaa.","1 AAAA : 34 AAaa : 1 aaaa.","11 AAAA : 1 aaaa.","3 AAAa : 1 Aaaa.","3 AAAa : 1 Aaaa."));
        quizModalArrayList.add(new QuizModal("Gen là một đoạn ADN", "mang thông tin cấu trúc của phân tử prôtêin.","mang thông tin mã hoá cho một sản phẩm xác định là chuỗi polipéptít hay ARN.","mang thông tin di truyền.","chứa các bộ 3 mã hoá các axit amin.\n","mang thông tin di truyền."));
        quizModalArrayList.add(new QuizModal("Cấu trúc nhiễm sắc thể ở sinh vật nhân sơ", " chỉ là phân tử ADN hoặc ARN trần","phân tử ADN dạng vòng.","phân tử ADN liên kết với prôtêin.","phân tử ARN liên kết với prôtêin.","phân tử ADN liên kết với prôtêin."));
        quizModalArrayList.add(new QuizModal("Trong nghiên cứu di truyền người, phương pháp có thể xác định một tính trạng nào đó có di truyền được hay không là", "phương pháp nghiên cứu trẻ đồng sinh.","phương pháp lai.","phương pháp nghiên cứu thể Barr","phương pháp nghiên cứu tế bào.","phương pháp nghiên cứu tế bào."));
        quizModalArrayList.add(new QuizModal("Mỗi loài sinh vật có bộ nhiễm sắc thể đặc trưng bởi", "số lượng, hình thái, cấu trúc nhiễm sắc thể."," số lượng , hình thái nhiễm sắc thể.","số lượng, cấu trúc nhiễm sắc thể.","số lượng không đổi.","số lượng không đổi."));
        quizModalArrayList.add(new QuizModal("Trong phương thức hình thành loài bằng con đường địa lí, điều kiện địa lí có vai trò là nhân tố", "chọn lọc những kiểu gen thích nghi.","gây ra những biến đổi tương ứng trên cơ thể sinh vật.","cung cấp nguồn nguyên liệu cho chọn lọc tự nhiên.","tạo nên sự đa dạng về kiểu gen và kiểu hình.","tạo nên sự đa dạng về kiểu gen và kiểu hình."));


        Collections.shuffle(quizModalArrayList); // xáo trộn vị trí các phần tử trong mảng
    }
}