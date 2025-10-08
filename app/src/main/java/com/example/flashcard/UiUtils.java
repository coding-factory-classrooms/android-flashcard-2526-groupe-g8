package com.example.flashcard;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.TextView;

public class UiUtils {



    // Change the banner text
    public static void updateTitleName(Activity act, String titleText) {
        TextView title = act.findViewById(R.id.bannerTxt);
        title.setText(titleText);
    }

}
