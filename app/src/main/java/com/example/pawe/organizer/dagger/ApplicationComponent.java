package com.example.pawe.organizer.dagger;

import com.example.pawe.organizer.flow.activities.MainActivity;
import com.example.pawe.organizer.base.MyApplication;
import com.example.pawe.organizer.dagger.module.ApplicationModule;
import com.example.pawe.organizer.flow.activities.SingleNoteActivity;
import com.example.pawe.organizer.flow.fragments.NotesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
    void inject(MainActivity mainActivity);
    void inject(SingleNoteActivity singleNoteActivity);
}
