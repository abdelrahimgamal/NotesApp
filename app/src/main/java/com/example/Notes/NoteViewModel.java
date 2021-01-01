package com.example.Notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
    private LiveData<List<Note>> allNotes2;

    private MutableLiveData<String> filterText = new MutableLiveData<>();

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes2=repository.getAllNotes2();

        allNotes = Transformations.switchMap(filterText, new Function<String, LiveData<List<Note>>>() {
            @Override
            public LiveData<List<Note>> apply(String input) {
                if (input == null)
                    return repository.getAllNotes();
                else
                    return repository.filter(input);
            }
        });
    }

    public void setFilter(String query) {
        filterText.setValue(query);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }





    public void insert(Note note) {
        repository.insert(note);
    }
    public void update(Note note) {
        repository.update(note);
    }
    public void delete(Note note) {
        repository.delete(note);
    }

    public LiveData<List<Note>> getAllNotes2() {
        return allNotes2;
    }



}
