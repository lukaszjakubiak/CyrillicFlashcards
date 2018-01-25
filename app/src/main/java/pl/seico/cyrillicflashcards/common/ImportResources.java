package pl.seico.cyrillicflashcards.common;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static pl.seico.cyrillicflashcards.common.Constants.RAW_RESOURCES_NAME;

/**
 * Created by lukasz on 24.01.18.
 */

public class ImportResources {

    private static String resPackageName;
    private static String resRawTypeName;

    private ImportResources() {

    }

    public static Map.Entry<String, String>[] importResources(Context context, String languageResource) {
        init(context);
        Map.Entry<String, String>[] flashcards;

        int resId = context.getResources().getIdentifier(resPackageName +":" + resRawTypeName + "/" + languageResource, null, null);
        InputStream inputStream = context.getResources().openRawResource(resId);
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        String line;
                Map<String, String> flashcardsMap = new HashMap<>();
        try {
            while ((line = br.readLine()) != null) {
                flashcardsMap.put(line, br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        flashcards = flashcardsMap.entrySet().toArray(new Map.Entry[flashcardsMap.size()]);

        return flashcards;
    }

    private static void init(Context context) {
        if (resPackageName == null) {
            resPackageName = context.getPackageName();
            resRawTypeName = RAW_RESOURCES_NAME;
        }
    }

}
