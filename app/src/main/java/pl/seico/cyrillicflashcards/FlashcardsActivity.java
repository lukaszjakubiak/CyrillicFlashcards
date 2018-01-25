package pl.seico.cyrillicflashcards;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Map;
import java.util.Random;

import pl.seico.cyrillicflashcards.common.ImportResources;

import static pl.seico.cyrillicflashcards.common.Constants.IS_FRESH_START_NAME;
import static pl.seico.cyrillicflashcards.common.Constants.LANGUAGE_PARAM_NAME;
import static pl.seico.cyrillicflashcards.common.Constants.LANGUAGE_PL;
import static pl.seico.cyrillicflashcards.common.Constants.LANGUAGE_RU;
import static pl.seico.cyrillicflashcards.common.Constants.LANGUAGE_UA;

public class FlashcardsActivity extends AppCompatActivity {

    private Map.Entry<String, String>[] flashcards;

    private final String CHAR_IDX_NAME = "charIdx";
    private final String DISPLAY_CYRILLIC_NAME = "displayCyrillic";

    private int charIdx;
    private boolean displayCyrillic;

    private TextView flashcardBtn;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getNavigationContentDescription();

        String languageParam = getIntent().getStringExtra(LANGUAGE_PARAM_NAME);
        setToolbarTitle(toolbar, languageParam);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        flashcardBtn = findViewById(R.id.flashcardBtn);

        getFlashcards(languageParam);

        setInitialValues();

        displayFlashcard();
    }

    public void flashcardClick(View view) {
        displayCyrillic = !displayCyrillic;
        generateRandomIndex();
        displayFlashcard();
    }

    private void setInitialValues() {
        boolean isFreshStart = getIntent().getBooleanExtra(IS_FRESH_START_NAME, false);
        if (isFreshStart) {
            getIntent().putExtra(IS_FRESH_START_NAME, false);
            editor.clear();
            editor.commit();
            displayCyrillic = true;
            generateRandomIndex();
        } else {
            displayCyrillic = sharedPreferences.getBoolean(DISPLAY_CYRILLIC_NAME, true);
            charIdx = sharedPreferences.getInt(CHAR_IDX_NAME, 0);
        }
    }

    private void getFlashcards(String languageParam) {
        if (flashcards == null) {
            flashcards = ImportResources.importResources(getApplicationContext(), languageParam + "_" + LANGUAGE_PL);
        }
    }

    private void setToolbarTitle(Toolbar toolbar, String languageParam) {
        if (LANGUAGE_UA.equals(languageParam)) {
            toolbar.setTitle(R.string.chosenLanguageUkrainian);
        } else if (LANGUAGE_RU.equals(languageParam)) {
            toolbar.setTitle(R.string.chosenLanguageRussian);
        }
    }

    private void displayFlashcard() {
        displayLatin();
        displayCyrillic();
    }

    private void generateRandomIndex() {
        Random random = new Random();
        if (displayCyrillic) {
            charIdx = random.nextInt(flashcards.length);
        }
    }

    private void displayCyrillic() {
        if (displayCyrillic) {
            flashcardBtn.setText(flashcards[charIdx].getKey());
        }
    }

    private void displayLatin() {
        if (!displayCyrillic) {
            String latinStr = flashcards[charIdx].getValue();
            flashcardBtn.setText(latinStr);
        }
    }

    private void setSharedPreferences(){
        editor.putBoolean(DISPLAY_CYRILLIC_NAME, displayCyrillic);
        editor.putInt(CHAR_IDX_NAME, charIdx);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setSharedPreferences();
    }

}
