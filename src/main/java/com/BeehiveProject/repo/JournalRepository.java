package com.BeehiveProject.repo;

import com.BeehiveProject.models.Journal;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal, Integer> {
}
