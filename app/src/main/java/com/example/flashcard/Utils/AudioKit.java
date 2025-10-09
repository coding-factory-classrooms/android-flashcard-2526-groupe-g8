package com.example.flashcard.Utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.flashcard.R;

public final class AudioKit {

    public enum Sfx {
        QUESTION,
        WIN,
        LOSE
    }

    private static MediaPlayer mpQuestion;   // question
    private static MediaPlayer mpWin;        // sfx win (
    private static MediaPlayer mpLose;       // sfx lose
    private static String currentFile;       // joui_novak.mp3

    private AudioKit() {}

    //PLAY | if question we need filename, otherwise nope
    public static void play(Context ctx, Sfx whichSfx, String fileName) {
        //get app ctx
        Context app = ctx.getApplicationContext();

        switch (whichSfx) {
            case QUESTION:
                // we cant spam it
                if (mpQuestion != null &&
                        fileName.equals(currentFile) &&
                        mpQuestion.isPlaying()) {
                    return;
                }
                // If same file, but not actually playing
                if (mpQuestion != null && fileName.equals(currentFile)) {
                    mpQuestion.seekTo(0); //reset
                    mpQuestion.start(); //START IT
                    return;
                }
                // If new file release at remove it
                if (mpQuestion != null) {
                    mpQuestion.release();
                    mpQuestion = null;
                }

                //get row ID with filname
                int qId = resolveRawId(app, fileName);
                //create the media player (with the ctx and the audio ID !)
                mpQuestion = MediaPlayer.create(app, qId);
                //stock it for later
                currentFile = fileName;
                //START IT (beacause its play a method remind it !)
                mpQuestion.start();
                break;

            case WIN:
                //if WIN
                //it' same logic but simply
                if (mpWin == null) mpWin = MediaPlayer.create(app, R.raw.oui);
                if (mpWin.isPlaying()) mpWin.seekTo(0);
                mpWin.start();
                break;

            case LOSE:
                //if LOSE
                if (mpLose == null) mpLose = MediaPlayer.create(app, R.raw.nan);
                if (mpLose.isPlaying()) mpLose.seekTo(0);
                mpLose.start();
                break;
        }
    }

    // helper for get raw id
    private static int resolveRawId(Context ctx, String fileName) {
        // "joui_novak.mp3" to R.raw.joui_novak
        String base = fileName;
        int dot = base.lastIndexOf('.');
        if (dot > 0) base = base.substring(0, dot);
        return ctx.getResources().getIdentifier(base, "raw", ctx.getPackageName());
    }
}
