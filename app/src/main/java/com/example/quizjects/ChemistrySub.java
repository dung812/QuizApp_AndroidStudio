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
        quizModalArrayList.add(new QuizModal("Tổng số đồng phân mang chức rượu ứng với công thức phân tử C4H10O là", "2","3","4","5","2"));
        quizModalArrayList.add(new QuizModal("Hợp chất (CH3)3C- OH là rượu bậc ba vì", "phân tử có 3 nhóm CH3","phân tử có nhóm OH liên kết với nguyên tử cacbon bậc ba"," phân tử có nhóm OH liên kết với một nguyên tử cacbon có hóa trị III","phân tử có 3 nhóm CH3 và có nguyên tử cacbon bậc ba","phân tử có 3 nhóm CH3"));
        quizModalArrayList.add(new QuizModal("Có các chất : X là CH3OH ; Y là CH3NH2 ; Z là HCOOH ; T là CH3Cl. Nhiệt độ sôi của các chất được sắp xếp theo trật tự sau:", "X < Y < Z < T","Y < Z < T < X"," Z < T < Y < X","T < Y < X < Z","X < Y < Z < T"));
        quizModalArrayList.add(new QuizModal("Đốt cháy hoàn toàn rượu X thu được CO2 và H2O. Nếu số mol H2O lớn hơn số mol CO2 có thể kết luận", "X là rượu thơm","X là rượu đơn chức","X là rượu no"," X là rượu không no","X là rượu thơm"));
        quizModalArrayList.add(new QuizModal("Rượu CH3- CH2- CHOH – CH3 có thể phản ứng được với tất cả các chất trong dãy", "Na, NaOH, HBr, O2","Na, NaCl, HBr, O2","Na, HBr, CuO, O2","Na, CaCO3 , CuO, O2","Na, NaOH, HBr, O2"));
        quizModalArrayList.add(new QuizModal("Có thể thu được kim loại nào trong số các kim loại sau: Cu, Ca, Na, Al bằng ba phương pháp điều chế kim loại phổ biến?", "Cu","Al"," Ca","Na","Al"));
        quizModalArrayList.add(new QuizModal("M là một kim loại trong số các kim loại: Cu, Ba, Zn, Mg. Dung dịch muối MCl2 phản ứng với Na2CO3 hoặc Na2SO4 tạo ra kết tủa, nhưng không tạo kết tủa khi phản ứng với NaOH. M là", "Cu","Ba","Zn","Mg","Ba"));
        quizModalArrayList.add(new QuizModal("100 ml dung dịch X chứa Na2SO4 0,1M và Na2CO3 0,1M tác dụng hết với dung dịch Ba(NO3)2 dư.(Cho C = 12; O = 16; S = 32; Ba = 137). Khối lượng kết tủa thu được là", "4,3 gam","3,4 gam","2,93 gam","2,39 gam","3,4 gam"));
        quizModalArrayList.add(new QuizModal("Trong các chất sau Fe, FeCl2, FeCl3, FeSO4, Fe2(SO4)3, FeO, Fe2O3. Những chất có cả tính oxi hoá và tính khử là:", "Fe, FeO, Fe2O3","Fe, FeCl2, FeCl3","Fe, FeSO4, Fe2(SO4)3","FeO, FeCl2 , FeSO4","Fe, FeSO4, Fe2(SO4)3"));
        quizModalArrayList.add(new QuizModal("Nhận định nào không đúng về ứng dụng của kim loại kiềm?", "Để điều chế một số kim loại hiếm bằng phương pháp nhiệt kim loại.","Dùng làm xúc tác trong nhiều phản ứng hữu cơ.","Để điều chế các hiđroxit và muối của chúng.","Để chế tạo hợp kim có nhiệt độ nóng chảy thấp.","Để điều chế các hiđroxit và muối của chúng."));
        quizModalArrayList.add(new QuizModal("Để phân biệt 4 chất rắn Na2CO3, CaCO3, Na2SO4, CaSO4.2H2O đựng trong 4 lọ riêng biệt, người ta đã sử dụng", "H2O và dung dịch HCl.","H2O và dung dịch NaOH","Dung dịch NaOH và dung dịch phenolphtalein","Giấy quỳ tím tẩm ướt và dung dịch BaCl2","Dung dịch NaOH và dung dịch phenolphtalein"));
        quizModalArrayList.add(new QuizModal("Nhận định nào không đúng về khả năng phản ứng của sắt với nước?", "Ở nhiệt độ thường, sắt không tác dụng với nước.","Ở nhiệt độ cao, sắt tác dụng với nước tạo ra Fe3O4 và H2","Ở nhiệt độ lớn hơn 570oC, sắt tác dụng với nước tạo ra FeO và H2","Ở nhiệt độ lớn hơn 1000oC, sắt tác dụng với nước tạo ra Fe(OH)3","Ở nhiệt độ lớn hơn 570oC, sắt tác dụng với nước tạo ra FeO và H2"));
        quizModalArrayList.add(new QuizModal("Có dãy chuyển hoá: Fe → FeCl2 → Fe(OH)2 → Fe(OH)3 → Fe2O3 → Fe → FeCl3 . Số phương trình phản ứng oxi hoá - khử trong dãy là", "3","4","5","6","5"));
        quizModalArrayList.add(new QuizModal("Cho a mol CO2 vào dung dịch chứa b mol NaOH, dung dịch thu được chứa muối Na2CO3 và NaHCO3. Giá trị của a và b tương ứng như sau:", "a = b","a > b","b > 2a","a < b < 2a","a < b < 2a"));
        quizModalArrayList.add(new QuizModal("Điện phân muối clorua của kim loại kiềm X nóng chảy, người ta thu được 1,344 lít khí (đktc) ở anôt và 2,76 gam kim loại ở catot. (Cho Li = 7; Na = 23; K = 39; Rb = 85). X là", "Li","Na","K","Rb","Rb"));
        quizModalArrayList.add(new QuizModal("Điện phân dung dịch CuCl2 (điện cực trơ), người ta thu được 0,672 lít khí (đktc) ở anôt và m gam Cu ở catôt. (Cho Cu = 64). Giá trị của m là", "1,29","1,92","3,84","2,58","2,58"));
        quizModalArrayList.add(new QuizModal("250 ml dung dịch HCl vừa đủ để hoà tan hỗn hợp Na2CO3 và Na2SO3 tạo ra muối duy nhất đồng thời thu được 2,8 lít khí (đktc). Nồng độ mol/lit của dung dịch HCl là", "1M","2M","2,5M","0,5M","0,5M"));
        quizModalArrayList.add(new QuizModal("Khi điện phân dung dịch KCl và dung dịch CuCl2 (điện cực trơ), ở điện cực dương đều xảy ra quá trình đầu tiên là:", "2H2O + 2e = H2 + 2OH","2H2O - 4e = O2 + 4H+","Cu2+ + 2e = Cu","2Cl- 2e = Cl2","2Cl- 2e = Cl2"));
        Collections.shuffle(quizModalArrayList); // xáo trộn vị trí các phần tử trong mảng

    }
}