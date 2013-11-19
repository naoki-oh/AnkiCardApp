package com.example.cardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button nomalStartButton;
	private Button randomStartButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//使用するレイアウトファイルの設定
		setContentView(R.layout.activity_main);

		//レイアウトの部品との紐付け
		getView();
		Questions qs = new Questions(0);
	}

	

	private void getView(){
		nomalStartButton = (Button)findViewById(R.id.nomalStartButton);
		randomStartButton = (Button)findViewById(R.id.randomStartButton);

		nomalStartButton.setOnClickListener(this);
		randomStartButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, GameActivity.class);
		if(v == nomalStartButton){//順番出題モード
			intent.putExtra("gameMode", 1);
		}else if(v == randomStartButton){//ランダム出題モード
			intent.putExtra("gameMode", 0);
		}
		startActivity(intent);
	}
}
