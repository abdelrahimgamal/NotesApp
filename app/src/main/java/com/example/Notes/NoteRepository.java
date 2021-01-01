package com.example.Notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    private LiveData<List<Note>> allNotes2;


    public NoteRepository(Application application){
    NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
    noteDao=noteDatabase.noteDao();
    allNotes=noteDao.getAllNotes();
    allNotes2=noteDao.getAllNotes2();

    }

    public LiveData<List<Note>> filter(String input) {
        try {
            return new FilterNoteAsyncTask(noteDao).execute(input).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public LiveData<List<Note>> getAllNotes2() {
        return allNotes2;
    }

    private static class FilterNoteAsyncTask extends AsyncTask<String, Void, LiveData<List<Note>>> {
        private NoteDao notesDAO;

        private FilterNoteAsyncTask(NoteDao noteDao) {
            this.notesDAO = noteDao;
        }

        @Override
        protected LiveData<List<Note>> doInBackground(String... strings) {
            return notesDAO.filter(strings[0]);
        }
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }



    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    }
