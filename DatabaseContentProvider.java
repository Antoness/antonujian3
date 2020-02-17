package com.juara.ujiantigaandroid;

import com.activeandroid.Configuration;
import com.activeandroid.content.ContentProvider;
import com.juara.ujiantigaandroid.model.InputFilm;

public class DatabaseContentProvider extends ContentProvider {

    @Override
    protected Configuration getConfiguration() {
        Configuration.Builder builder = new Configuration.Builder(getContext());
        builder.addModelClass(InputFilm.class);

        return builder.create();
    }
}
