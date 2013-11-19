package com.example.cardapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Questions {
	private int _mode;
	private List<QuestionEntity> _questions, _answers;

	public Questions(int mode){

		//modeをセット
		this._mode = mode;

		//問題，解答テキストファイルを読み込みEntityにセットしListに格納する
		try{
			_questions = new ArrayList<QuestionEntity>();
			File qustionfile = new File("/mnt/sdcard/card/questions.txt");
			File answerfile = new File("/mnt/sdcard/card/answers.txt");
			BufferedReader qustionBr = new BufferedReader(new FileReader(qustionfile));
			BufferedReader answerBr = new BufferedReader(new FileReader(answerfile));

			String question, answer;
			QuestionEntity entity;

			while(((question = qustionBr.readLine()) != null) && (answer = answerBr.readLine()) != null){
				//System.out.println(question + ":" + answer);
				//問題１セットごとにEntityに格納する
				entity = new QuestionEntity();
				entity.setQuestion(question);
				entity.setAnswer(answer);
				//Listに格納
				_questions.add(entity);
			}

			//解答群用にListをコピーする
			_answers = new ArrayList<QuestionEntity>(_questions);
			qustionBr.close();
			answerBr.close();

		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}
	}

	public QuestionEntity[] pickQuestion(){

		QuestionEntity[] entitys = new QuestionEntity[4];
		QuestionEntity entity;

		if(_questions.size() > 0){
			//問題群Listからひとつ問題を取得する（モードによって取得するメソッドをかえる）
			if(_mode == 0){//ランダム
				entitys[0] = randomPick();
			}else if(_mode == 1){//順番
				entitys[0] = nomalPick();
			}

			//残りの３つは適当に解答群から取得する
			int i = 1;
			while(i<4){
				int flag = 0;
				entity = _answers.get((int)(Math.random() * _answers.size() - 1));
				for(int j=i; j>0; j--){
					if(entitys[j-1] == entity)
						break;
					else if(j-1 == 0){
						entitys[i] = entity;
						i++;
					}
				}
			}

			for(int j=0; j<4; j++){
				Log.d("qs", entitys[j].getQuestion());
			}
			return entitys;

		}else{
			return null;
		}
	}

	public int getQuestionSize(){
		return _questions.size();
	}

	private QuestionEntity randomPick(){
		return _questions.remove((int)(Math.random() * _questions.size() - 1));
	}

	private QuestionEntity nomalPick(){
		return _questions.remove(0);
	}
}
