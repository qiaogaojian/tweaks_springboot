package com.mega.tweaks.model.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteDao extends MongoRepository<NoteEntity, String>
{

}
