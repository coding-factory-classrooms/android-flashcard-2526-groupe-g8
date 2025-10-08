package Question;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Question implements Parcelable {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;
    private String difficulty;
    private String audioFile;

    public Question(String questionText, List<String> options, int correctAnswerIndex,
                    String difficulty, String audioFile) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.difficulty = difficulty;
        this.audioFile = audioFile;
    }

    protected Question(Parcel in) {
        questionText = in.readString();
        options = in.createStringArrayList();
        correctAnswerIndex = in.readInt();
        difficulty = in.readString();
        audioFile = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionText);
        dest.writeStringList(options);
        dest.writeInt(correctAnswerIndex);
        dest.writeString(difficulty);
        dest.writeString(audioFile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public boolean isCorrect(int selectedIndex) {
        return selectedIndex == correctAnswerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", options=" + options +
                ", correctAnswerIndex=" + correctAnswerIndex +
                ", difficulty='" + difficulty + '\'' +
                ", audioFile='" + audioFile + '\'' +
                '}';
    }
}

