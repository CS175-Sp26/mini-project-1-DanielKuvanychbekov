package edu.sjsu.android.project1danielkuvan;

import android.widget.SeekBar;

public interface SeekBarListener extends SeekBar.OnSeekBarChangeListener
{
    @Override
    void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);

    @Override
    void onStartTrackingTouch(SeekBar seekBar);

    @Override
    void onStopTrackingTouch(SeekBar seekBar);
}
