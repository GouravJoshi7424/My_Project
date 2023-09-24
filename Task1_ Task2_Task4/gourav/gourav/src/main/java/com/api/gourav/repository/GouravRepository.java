package com.api.gourav.repository;
import com.api.gourav.model.gourav;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GouravRepository extends MongoRepository<gourav, String> {

	public List findByName(String name);
}
