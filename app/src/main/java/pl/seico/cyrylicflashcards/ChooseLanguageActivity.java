package pl.seico.cyrylicflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import static pl.seico.cyrylicflashcards.common.Constants.IS_FRESH_START_NAME;
import static pl.seico.cyrylicflashcards.common.Constants.LANGUAGE_PARAM_NAME;
import static pl.seico.cyrylicflashcards.common.Constants.LANGUAGE_UA;
import static pl.seico.cyrylicflashcards.common.Constants.LANGUAGE_RU;

public class ChooseLanguageActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_choose_language, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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
