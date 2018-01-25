package pl.seico.cyrillicflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static pl.seico.cyrillicflashcards.common.Constants.IS_FRESH_START_NAME;
import static pl.seico.cyrillicflashcards.common.Constants.LANGUAGE_PARAM_NAME;
import static pl.seico.cyrillicflashcards.common.Constants.LANGUAGE_UA;
import static pl.seico.cyrillicflashcards.common.Constants.LANGUAGE_RU;

public class ChooseLanguageActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void languageSelect(View view) {
        String chosenLanguage = "";
        switch (view.getId()) {
            case (R.id.russianLngBtn):
                chosenLanguage = LANGUAGE_RU;
                break;
            case (R.id.ukrainianLngBtn):
                chosenLanguage = LANGUAGE_UA;
                break;
        }

        intent = new Intent(this, FlashcardsActivity.class);
        intent.putExtra(IS_FRESH_START_NAME, Boolean.TRUE);
        intent.putExtra(LANGUAGE_PARAM_NAME, chosenLanguage);
        startActivity(intent);
    }
}
