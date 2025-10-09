package com.example.flashcard.Utils;

import android.app.Activity;
import android.widget.TextView;

import com.example.flashcard.R;

public class UiUtils {



    // Change the banner text
    public static void updateTitleName(Activity act, String titleText) {
        TextView title = act.findViewById(R.id.bannerTxt);
        title.setText(titleText);
    }

}
