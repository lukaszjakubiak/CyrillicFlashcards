package pl.seico.cyrylicflashcards;

import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Test;
import org.mockito.Mockito;

import org.junit.runner.RunWith;

/**
 * Created by lukasz on 25.01.18.
 */

@RunWith(AndroidJUnit4.class)
public class ChooseLanguageActivitiTest {

    public void onCreateTest() throws Exception {
//        Bundle bundle = InstrumentationRegistry.getArguments();
        Bundle bundle = Mockito.mock(Bundle.class);

    }

    @Test
    public void testLanguageSelectOk() throws Exception {
//        //given
//        View view = Mockito.mock(View.class);
//        Mockito.when(view.getId()).thenReturn(R.id.russianLngBtn);
//        //when
//        ChooseLanguageActivity activity = new ChooseLanguageActivity();
//        activity.languageSelect(view);
//        Mockito.verify(activity.in)
    }

}
