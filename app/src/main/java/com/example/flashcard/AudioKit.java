package com.example.flashcard;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Locale;

public final class AudioKit {

    private static MediaPlayer questionPlayer;
    private static MediaPlayer winSfx;
    private static MediaPlayer loseSfx;

    private AudioKit() { }

    // Appelle ça UNE FOIS (par ex. dans onCreate de ta 1ère Activity)
    public static void initSfx(Context ctx) {
        winSfx  = MediaPlayer.create(ctx, R.raw.oui);
        loseSfx = MediaPlayer.create(ctx, R.raw.nan);
    }

    // Prépare l'audio de la question depuis un nom de fichier "xxx.mp3" situé dans res/raw
    public static void prepareQuestion(Context ctx, String rawFileName) {
        releaseQuestion();
        int id = resolveRawId(ctx, rawFileName); // "joui_novak.mp3" -> R.raw.joui_novak
        questionPlayer = MediaPlayer.create(ctx, id);
    }

    // Rejoue la question depuis le début
    public static void replayQuestion() {
        if (questionPlayer == null) return;
        if (questionPlayer.isPlaying()) questionPlayer.pause();
        questionPlayer.seekTo(0);
        questionPlayer.start();
    }

    public static void playWin() {
        if (winSfx == null) return;
        if (winSfx.isPlaying()) winSfx.seekTo(0);
        winSfx.start();
    }

    public static void playLose() {
        if (loseSfx == null) return;
        if (loseSfx.isPlaying()) loseSfx.seekTo(0);
        loseSfx.start();
    }

    public static void releaseAll() {
        releaseQuestion();
        if (winSfx != null) { winSfx.release(); winSfx = null; }
        if (loseSfx != null) { loseSfx.release(); loseSfx = null; }
    }

    // --- privates ---

    private static void releaseQuestion() {
        if (questionPlayer != null) {
            questionPlayer.release();
            questionPlayer = null;
        }
    }

    private static int resolveRawId(Context ctx, String fileName) {
        String base = fileName;
        int dot = base.lastIndexOf('.');
        if (dot > 0) base = base.substring(0, dot);
        base = base.toLowerCase(Locale.ROOT); // res/raw doit être en minuscules/underscore
        return ctx.getResources().getIdentifier(base, "raw", ctx.getPackageName());
    }
}
