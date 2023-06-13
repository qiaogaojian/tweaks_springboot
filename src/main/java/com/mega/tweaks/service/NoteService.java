package com.mega.tweaks.service;

import com.mega.tweaks.model.mongodb.NoteDao;
import com.mega.tweaks.model.mongodb.NoteEntity;
import com.mega.tweaks.model.vo.NoteVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteService
{
    private final NoteDao noteDao;

    @Autowired
    public NoteService(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public void addNote(String note) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setNote_date(new Date());
        noteEntity.setNote_text(note);
        this.noteDao.save(noteEntity);
    }

    public NoteVO getAllNote() {
        List<NoteEntity> notes  = this.noteDao.findAll();
        NoteVO           noteVO = new NoteVO();
        noteVO.setNoteList(notes);
        return noteVO;
    }
}
