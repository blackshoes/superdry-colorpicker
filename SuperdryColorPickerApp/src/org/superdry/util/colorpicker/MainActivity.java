package org.superdry.util.colorpicker;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	private Paint mPaint;
	Button startBtn;
	View color;
	int changedColor;
	private static final int initColor = 0xFFFF0000;
	private static final int ACTION_GETCOLOR = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		changedColor = initColor;
		color = (View) findViewById(R.id.Color);
		color.setBackgroundColor(changedColor);
		startBtn = (Button) findViewById(R.id.StartButton);
		startBtn.setOnClickListener(this);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(changedColor);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(12);
		mPaint.setXfermode(null);
		mPaint.setAlpha(0xFF);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.StartButton:
			color.setBackgroundColor(changedColor);
			Intent intent = new Intent(this,
					org.superdry.util.colorpicker.lib.SuperdryColorPicker.class);
			intent.putExtra("SelectedColor", changedColor);
			startActivityForResult(intent, org.superdry.util.colorpicker.lib.SuperdryColorPicker.ACTION_GETCOLOR);
			break;
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == ACTION_GETCOLOR) {

			if (resultCode == RESULT_OK) {
				Bundle b = intent.getExtras();
				if (b != null) {
					changedColor = b.getInt("SelectedColor");
				}
			} else if (resultCode == RESULT_CANCELED) {
				changedColor = initColor;
			}
			mPaint.setColor(changedColor);
			color.setBackgroundColor(changedColor);
		}
	}
}