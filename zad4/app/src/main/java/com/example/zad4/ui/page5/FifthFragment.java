//Author: Joanna Walkiewicz, zad2

package com.example.zad4.ui.page5;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zad4.QuestionAnswer;
import com.example.zad4.databinding.FragmentFifthBinding;


public class FifthFragment extends Fragment implements View.OnClickListener {

    private FragmentFifthBinding binding;
    TextView totalQuestionTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;

    int currentQuestionIndex = 0;
    String selectedAnswer="";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        FifthViewModel homeViewModel = new ViewModelProvider(this).get(FifthViewModel.class);
        binding = FragmentFifthBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        totalQuestionTextView = binding.totalQuestions;
        questionTextView = binding.question;
        ansA = binding.ansA;
        ansB = binding.ansB;
        ansC = binding.ansC;
        ansD = binding.ansD;
        submitBtn = binding.submitBtn;

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionTextView.setText("Ilość pytań: "+totalQuestion);

        loadNewQuestion();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void onClick(View view){

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        Button clickedButton = (Button) view;
        if(clickedButton == submitBtn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        }else{
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.LTGRAY);
        }
    }
    public void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    public void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*60){
            passStatus = "Zaliczone";
        }
        else{
            passStatus="Niezaliczone";
        }

        new AlertDialog.Builder(this.getContext())
                .setTitle(passStatus)
                .setMessage("Wynik to "+ score + " z " + totalQuestion)
                .setPositiveButton("Powtórz quiz",(dialogInterface, i) -> restartQuiz()).setCancelable(false).show();
    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}
