    package com.example.flashcard.Activity;

    import android.os.Bundle;
    import android.util.Log;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.flashcard.ListQuestionAdapter;
    import com.example.flashcard.Json.QuestionJSON;
    import com.example.flashcard.R;
    import com.example.flashcard.Theme;
    import com.example.flashcard.Utils.UiUtils;

    import java.util.ArrayList;

    import com.example.flashcard.Question;


    public class ListQuestionActivity extends AppCompatActivity {

        private ListQuestionAdapter adapter;
        private ArrayList<Question> questions;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_list_question);
            UiUtils.updateTitleName(this, "Liste Jouissements");




            questions = new ArrayList<>();

            QuestionJSON questionData = QuestionJSON.loadFromJSON(this, R.raw.json_joui);
            questions = questionData.getQuestions();

            adapter = new ListQuestionAdapter(questions);

            RecyclerView recyclerView = findViewById(R.id.ListeQuestions);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }