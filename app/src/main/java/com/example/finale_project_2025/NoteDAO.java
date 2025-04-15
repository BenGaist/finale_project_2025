package com.example.finale_project_2025;
import android.provider.ContactsContract;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;
import androidx.room.Dao;

@Dao
public interface NoteDAO {
    @Insert
    void insert(Note note);
    @Update
    void update(Note note);
    @Delete
    void delete(Note note);


    @Query("SELECT * FROM notes")
    List<Note> getAllNotes();
}
