package com.mega.tweaks.model.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import lombok.Data;

@Data
@Document(collection = "notes")
public class NoteEntity implements Serializable
{
    @Id
    private String id;
    private Date   note_date;
    private String note_text;
}
