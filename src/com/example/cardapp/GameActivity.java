package com.example.cardapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity implements  OnClickListener{

	private TextView _qustionTile;
	private Button[] _answerButtons ;

	private QuestionEntity[] _questions = new QuestionEntity[4];
	private Questions qs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		getView();
	}

	public void onStart(){
		super.onStart();
		qs = new Questions(0);
		_questions = qs.pickQuestion();
		setButtonName(_questions);
		//問題をセットする
		_qustionTile.setText(_questions[0].getQuestion());
	}

	private void getView(){
		_qustionTile = (TextView)findViewById(R.id.qustionTitle);

		_answerButtons = new Button[4];
		_answerButtons[0] = (Button)findViewById(R.id.answer1);
		_answerButtons[1] = (Button)findViewById(R.id.answer2);
		_answerButtons[2] = (Button)findViewById(R.id.answer3);
		_answerButtons[3] = (Button)findViewById(R.id.answer4);

		for(int i=0; i<4; i++)
			_answerButtons[i].setOnClickListener(this);
	}


	private void setButtonName(QuestionEntity entitys[]){
		int i = 0;
		while(i<4){
			//Log.d("loop", String.valueOf(i));
			//乱数を生成してそれを残りのボタンの数で割ったあまりを求め
			//ボタン配列のindexとして
			int ran = (int)(Math.random() * 100);
			if(_answerButtons[ran % 4].getText().equals("")){
				//Log.d("ran", String.valueOf(ran % 4));
				_answerButtons[ran % 4].setText(entitys[i].getAnswer());
				i++;
			}
		}
	}

	private void reset(){
		for(int i=0; i<4; i++){
			_answerButtons[i].setText("");
		}
		//Log.d("size", String.valueOf(qs.getQuestionSize()));
		if(qs.getQuestionSize()-1 == 0){
			finish();
		}
		_questions = qs.pickQuestion();
		setButtonName(_questions);
		//問題をセットする
		_qustionTile.setText(_questions[0].getQuestion());
	}



	@Override
	public void onClick(View v) {

		reset();
	}
}
