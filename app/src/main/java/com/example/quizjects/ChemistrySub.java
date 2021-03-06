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

public class ChemistrySub extends AppCompatActivity {
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
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChemistrySub.this);
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
        quizModalArrayList.add(new QuizModal("T???ng s??? ?????ng ph??n mang ch???c r?????u ???ng v???i c??ng th???c ph??n t??? C4H10O l??", "2","3","4","5","2"));
        quizModalArrayList.add(new QuizModal("H???p ch???t (CH3)3C- OH l?? r?????u b???c ba v??", "ph??n t??? c?? 3 nh??m CH3","ph??n t??? c?? nh??m OH li??n k???t v???i nguy??n t??? cacbon b???c ba"," ph??n t??? c?? nh??m OH li??n k???t v???i m???t nguy??n t??? cacbon c?? h??a tr??? III","ph??n t??? c?? 3 nh??m CH3 v?? c?? nguy??n t??? cacbon b???c ba","ph??n t??? c?? 3 nh??m CH3"));
        quizModalArrayList.add(new QuizModal("C?? c??c ch???t : X l?? CH3OH ; Y l?? CH3NH2 ; Z l?? HCOOH ; T l?? CH3Cl. Nhi???t ????? s??i c???a c??c ch???t ???????c s???p x???p theo tr???t t??? sau:", "X < Y < Z < T","Y < Z < T < X"," Z < T < Y < X","T < Y < X < Z","X < Y < Z < T"));
        quizModalArrayList.add(new QuizModal("?????t ch??y ho??n to??n r?????u X thu ???????c CO2 v?? H2O. N???u s??? mol H2O l???n h??n s??? mol CO2 c?? th??? k???t lu???n", "X l?? r?????u th??m","X l?? r?????u ????n ch???c","X l?? r?????u no"," X l?? r?????u kh??ng no","X l?? r?????u th??m"));
        quizModalArrayList.add(new QuizModal("R?????u CH3- CH2- CHOH ??? CH3 c?? th??? ph???n ???ng ???????c v???i t???t c??? c??c ch???t trong d??y", "Na, NaOH, HBr, O2","Na, NaCl, HBr, O2","Na, HBr, CuO, O2","Na, CaCO3 , CuO, O2","Na, NaOH, HBr, O2"));
        quizModalArrayList.add(new QuizModal("C?? th??? thu ???????c kim lo???i n??o trong s??? c??c kim lo???i sau: Cu, Ca, Na, Al b???ng ba ph????ng ph??p ??i???u ch??? kim lo???i ph??? bi???n?", "Cu","Al"," Ca","Na","Al"));
        quizModalArrayList.add(new QuizModal("M l?? m???t kim lo???i trong s??? c??c kim lo???i: Cu, Ba, Zn, Mg. Dung d???ch mu???i MCl2 ph???n ???ng v???i Na2CO3 ho???c Na2SO4 t???o ra k???t t???a, nh??ng kh??ng t???o k???t t???a khi ph???n ???ng v???i NaOH. M l??", "Cu","Ba","Zn","Mg","Ba"));
        quizModalArrayList.add(new QuizModal("100 ml dung d???ch X ch???a Na2SO4 0,1M v?? Na2CO3 0,1M t??c d???ng h???t v???i dung d???ch Ba(NO3)2 d??.(Cho C = 12; O = 16; S = 32; Ba = 137). Kh???i l?????ng k???t t???a thu ???????c l??", "4,3 gam","3,4 gam","2,93 gam","2,39 gam","3,4 gam"));
        quizModalArrayList.add(new QuizModal("Trong c??c ch???t sau Fe, FeCl2, FeCl3, FeSO4, Fe2(SO4)3, FeO, Fe2O3. Nh???ng ch???t c?? c??? t??nh oxi ho?? v?? t??nh kh??? l??:", "Fe, FeO, Fe2O3","Fe, FeCl2, FeCl3","Fe, FeSO4, Fe2(SO4)3","FeO, FeCl2 , FeSO4","Fe, FeSO4, Fe2(SO4)3"));
        quizModalArrayList.add(new QuizModal("Nh???n ?????nh n??o kh??ng ????ng v??? ???ng d???ng c???a kim lo???i ki???m?", "????? ??i???u ch??? m???t s??? kim lo???i hi???m b???ng ph????ng ph??p nhi???t kim lo???i.","D??ng l??m x??c t??c trong nhi???u ph???n ???ng h???u c??.","????? ??i???u ch??? c??c hi??roxit v?? mu???i c???a ch??ng.","????? ch??? t???o h???p kim c?? nhi???t ????? n??ng ch???y th???p.","????? ??i???u ch??? c??c hi??roxit v?? mu???i c???a ch??ng."));
        quizModalArrayList.add(new QuizModal("????? ph??n bi???t 4 ch???t r???n Na2CO3, CaCO3, Na2SO4, CaSO4.2H2O ?????ng trong 4 l??? ri??ng bi???t, ng?????i ta ???? s??? d???ng", "H2O v?? dung d???ch HCl.","H2O v?? dung d???ch NaOH","Dung d???ch NaOH v?? dung d???ch phenolphtalein","Gi???y qu??? t??m t???m ?????t v?? dung d???ch BaCl2","Dung d???ch NaOH v?? dung d???ch phenolphtalein"));
        quizModalArrayList.add(new QuizModal("Nh???n ?????nh n??o kh??ng ????ng v??? kh??? n??ng ph???n ???ng c???a s???t v???i n?????c?", "??? nhi???t ????? th?????ng, s???t kh??ng t??c d???ng v???i n?????c.","??? nhi???t ????? cao, s???t t??c d???ng v???i n?????c t???o ra Fe3O4 v?? H2","??? nhi???t ????? l???n h??n 570oC, s???t t??c d???ng v???i n?????c t???o ra FeO v?? H2","??? nhi???t ????? l???n h??n 1000oC, s???t t??c d???ng v???i n?????c t???o ra Fe(OH)3","??? nhi???t ????? l???n h??n 570oC, s???t t??c d???ng v???i n?????c t???o ra FeO v?? H2"));
        quizModalArrayList.add(new QuizModal("C?? d??y chuy???n ho??: Fe ??? FeCl2 ??? Fe(OH)2 ??? Fe(OH)3 ??? Fe2O3 ??? Fe ??? FeCl3 . S??? ph????ng tr??nh ph???n ???ng oxi ho?? - kh??? trong d??y l??", "3","4","5","6","5"));
        quizModalArrayList.add(new QuizModal("Cho a mol CO2 v??o dung d???ch ch???a b mol NaOH, dung d???ch thu ???????c ch???a mu???i Na2CO3 v?? NaHCO3. Gi?? tr??? c???a a v?? b t????ng ???ng nh?? sau:", "a = b","a > b","b > 2a","a < b < 2a","a < b < 2a"));
        quizModalArrayList.add(new QuizModal("??i???n ph??n mu???i clorua c???a kim lo???i ki???m X n??ng ch???y, ng?????i ta thu ???????c 1,344 l??t kh?? (??ktc) ??? an??t v?? 2,76 gam kim lo???i ??? catot. (Cho Li = 7; Na = 23; K = 39; Rb = 85). X l??", "Li","Na","K","Rb","Rb"));
        quizModalArrayList.add(new QuizModal("??i???n ph??n dung d???ch CuCl2 (??i???n c???c tr??), ng?????i ta thu ???????c 0,672 l??t kh?? (??ktc) ??? an??t v?? m gam Cu ??? cat??t. (Cho Cu = 64). Gi?? tr??? c???a m l??", "1,29","1,92","3,84","2,58","2,58"));
        quizModalArrayList.add(new QuizModal("250 ml dung d???ch HCl v???a ????? ????? ho?? tan h???n h???p Na2CO3 v?? Na2SO3 t???o ra mu???i duy nh???t ?????ng th???i thu ???????c 2,8 l??t kh?? (??ktc). N???ng ????? mol/lit c???a dung d???ch HCl l??", "1M","2M","2,5M","0,5M","0,5M"));
        quizModalArrayList.add(new QuizModal("Khi ??i???n ph??n dung d???ch KCl v?? dung d???ch CuCl2 (??i???n c???c tr??), ??? ??i???n c???c d????ng ?????u x???y ra qu?? tr??nh ?????u ti??n l??:", "2H2O + 2e = H2 + 2OH","2H2O - 4e = O2 + 4H+","Cu2+ + 2e = Cu","2Cl- 2e = Cl2","2Cl- 2e = Cl2"));
        Collections.shuffle(quizModalArrayList); // x??o tr???n v??? tr?? c??c ph???n t??? trong m???ng

    }
}