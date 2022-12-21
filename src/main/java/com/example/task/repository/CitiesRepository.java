package com.example.task.repository;

import com.example.task.clients.model.City;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface CitiesRepository extends CrudRepository<City, Long> {
}
