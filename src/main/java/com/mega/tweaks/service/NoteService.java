package com.mega.tweaks.service;

import com.mega.tweaks.model.vo.NoteVO;
import com.mega.tweaks.mongodb.NoteDao;
import com.mega.tweaks.mongodb.NoteEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.mega.tweaks.consts.RedisKey.REDIS_NOTE_COUNT;

@Service
public class NoteService
{
    private final NoteDao             noteDao;
    private final StringRedisTemplate stringRedisTemplate0;

    @Autowired
    public NoteService(NoteDao noteDao,
                       @Qualifier("stringRedisTemplate0") StringRedisTemplate stringRedisTemplate0) {
        this.noteDao = noteDao;
        this.stringRedisTemplate0 = stringRedisTemplate0;
    }

    public void addNote(String note) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setNote_date(new Date());
        noteEntity.setNote_text(note);
        this.noteDao.save(noteEntity);

        this.stringRedisTemplate0.opsForValue().set(REDIS_NOTE_COUNT, this.noteDao.count() + "");
    }

    public NoteVO getAllNote() {
        List<NoteEntity> notes  = this.noteDao.findAll();
        NoteVO           noteVO = new NoteVO();
        noteVO.setNoteList(notes);
        return noteVO;
    }

    public Integer getNoteNum() {
        int noteNum = 0;
        if (this.stringRedisTemplate0.opsForValue().get(REDIS_NOTE_COUNT) != null) {
            noteNum = Integer.parseInt(this.stringRedisTemplate0.opsForValue().get(REDIS_NOTE_COUNT));
        }
        return noteNum;
    }
}
