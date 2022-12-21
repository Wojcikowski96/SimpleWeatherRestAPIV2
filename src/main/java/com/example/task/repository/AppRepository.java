package com.example.task.repository;

import com.example.task.clients.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AppRepository extends CrudRepository<City, Long> {
}
