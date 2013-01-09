package at.ac.tuwien.sportmate;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class SlideToStartActivity {
	protected int startX;
	protected int startY;
	protected int width;
	protected int height;
	protected Rect sliderArea;

	protected Bitmap sliderKnob; // bitmap for the slider knob

	protected ArrayList<ISlideToStartActivity> listeners = new ArrayList<ISlideToStartActivity>(); // listener
																									// array

	private boolean wasContainedOnce;

	public SlideToStartActivity() {	}

	public void init(int x, int y, int width, int height, Bitmap sliderKnob) {
		startX = x;
		startY = y;
		this.width = width;
		this.height = height;
		wasContainedOnce = false;
		sliderArea = new Rect(x, y, x + width, y + height);
		this.sliderKnob = sliderKnob;
	}

	public void OnDrag(int x, int y) {
		int relX = x - startX;
		int relY = y - startY;

		if (sliderArea.contains(x, y)) {
			if (!wasContainedOnce) {
				// if the first time in, check if we grap the image
				if (relX <= sliderKnob.getWidth()) {
					wasContainedOnce = true;
				}
			} else {
				// check if we reached the end of the slider
				int halfWidth = sliderKnob.getWidth() / 2;

				// we assume that the slide has finished when the activity -
				// image
				// reached the end of the slider with it's right border
				if (relX >= width - halfWidth) {
					// notify attached listeners that the slide has been
					// performed
					for (ISlideToStartActivity listener : listeners) {
						listener.SlidePerformed();
					}
				}
			}
		} else {
			if (wasContainedOnce) {
				wasContainedOnce = false;
				// notify attached listeners that the slide has been performed
				for (ISlideToStartActivity listener : listeners) {
					listener.SlideCanceled();
				}
			}
		}
	}

	private void Draw(Canvas canvas, int relX, int relY) {
        canvas.drawBitmap(sliderKnob, startX, startY, null);
	}

}
