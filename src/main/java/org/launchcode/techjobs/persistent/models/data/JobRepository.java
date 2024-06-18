package org.launchcode.techjobs.persistent.models.data;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.hibernate.mapping.Value;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.launchcode.techjobs.persistent.models.Skill;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface JobRepository extends CrudRepository<Job, Integer> {
}

