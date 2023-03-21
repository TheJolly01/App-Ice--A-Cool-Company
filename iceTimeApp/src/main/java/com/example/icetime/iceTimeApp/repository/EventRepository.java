package com.example.icetime.iceTimeApp.repository;

import com.example.icetime.iceTimeApp.entity.Event;

import org.springframework.data.repository.CrudRepository;;

public interface EventRepository extends CrudRepository<Event, Long> {

}
