package com.temofey.k.android.loftmoney.activities.main.balance;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.temofey.k.android.loftmoney.R;

public class DiagramView extends View {

    private float outcomes = 100;
    private float incomes = 300;

    private Paint outcomePaint = new Paint();
    private Paint incomePaint = new Paint();

    public DiagramView(Context context) {
        super(context);

        // default colors
        outcomePaint.setColor(ContextCompat.getColor(getContext(), R.color.dark_sky_blue));
        incomePaint.setColor(ContextCompat.getColor(getContext(), R.color.apple_green));

    }

    public DiagramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleRes, defStyleRes);
    }

    public void update(int outcomes, int incomes) {
        this.outcomes = outcomes;
        this.incomes = incomes;

        invalidate();
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        final TypedArray styledAttributes = context.obtainStyledAttributes(attrs,
                R.styleable.DiagramView,
                defStyleAttr,
                defStyleRes);
        try {
            outcomePaint.setColor(styledAttributes.getColor(
                    R.styleable.DiagramView_outcomeFillColor,
                    ContextCompat.getColor(getContext(),
                            R.color.dark_sky_blue)));
            incomePaint.setColor(styledAttributes.getColor(
                    R.styleable.DiagramView_incomeFillColor,
                    ContextCompat.getColor(getContext(),
                            R.color.apple_green)));
        } finally {
            styledAttributes.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float total = outcomes + incomes;

        float outcomesAngle = 360f * outcomes / total;
        float incomesAngle = 360f * incomes / total;

        int space = 10;
        int size = Math.min(getWidth(), getHeight()) - space * 2;
        int xMargin = (getWidth() - size) / 2;
        int yMargin = (getHeight() - size) / 2;

        canvas.drawArc(xMargin - space, yMargin,
                getWidth() - xMargin - space,
                getHeight() - yMargin,
                180 - outcomesAngle / 2, outcomesAngle,
                true, outcomePaint);

        canvas.drawArc(xMargin + space, yMargin, getWidth() - xMargin + space,
                getHeight() - yMargin,
                360 - incomesAngle / 2, incomesAngle,
                true, incomePaint);
    }
}
