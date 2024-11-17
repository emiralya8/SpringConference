package io.bcn.springConference.repository;

import io.bcn.springConference.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public interface SpeakerRepository extends JpaRepository<Speaker, UUID> {

    //@Query("SELECT a FROM SPEAKER a JOIN FETCH a.conferences WHERE a.id = :id")
    //public ArrayList<Speaker> findSpeakersWithConferences(@Param("id") UUID id);
}
