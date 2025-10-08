package Question;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question implements Parcelable {
    private String questionText;
    private List<String> options;
    @SerializedName("Answer")
    private String answer;
    private String audioFile;

    public Question(String questionText, List<String> options,
                    String answer, String audioFile) {
        this.questionText = questionText;
        this.options = options;
        this.answer = answer;
        this.audioFile = audioFile;
    }

    protected Question(Parcel in) {
        questionText = in.readString();
        options = in.createStringArrayList();
        answer = in.readString();
        audioFile = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionText);
        dest.writeStringList(options);
        dest.writeString(answer);
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

    //Get functions
    public String getQuestionText() {
        return questionText;
    }
    public List<String> getOptions() {
        return options;
    }
    public String getAnswer() { return answer; }
    public String getAudioFile() { return audioFile; }


    public boolean isCorrectText(String selectedText) {
        if (selectedText == null || answer == null) return false;
        return selectedText.trim().equalsIgnoreCase(answer.trim());
    }


    //i'm not sure this will be used but we never know !!!
    //i'm useless piece of text (Oui je te copie thomas)
    //thomas je t'aime
    //sort avec moi
    //je suis pas gay
    //juste je respecte ton travail
    //embauche moi stp
    //je suis cool, beau, et un peu intelligent (juste des fois)
    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                ", audioFile='" + audioFile + '\'' +
                '}';
    }
}

