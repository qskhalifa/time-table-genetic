package io.qusay.services.api;

import io.qusay.model.Job;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "jobs", path = "jobs")
public interface JobRepository extends PagingAndSortingRepository<Job, Long> {
    @RestResource(path = "schedule", rel = "schedule")
    List<Job> findBySchedule_ScheduleId(Long id);

    // Find if a user has a job running (empty result if they do not)
    @RestResource(path = "user", rel = "user")
    List<Job> findBySchedule_Creator_UserId(Long id);
}
