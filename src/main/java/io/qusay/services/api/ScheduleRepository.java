package io.qusay.services.api;

import io.qusay.model.Schedule;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "schedules", path = "schedules")
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {
    @RestResource(path = "masterSchedule", rel = "masterSchedule")
    List<Schedule> findByIsMasterIsTrue();

    @RestResource(path = "creator", rel = "creator")
    List<Schedule> findByCreator_UserId(Long id);
    @RestResource(path = "creatorUsername", rel = "creatorUsername")
    List<Schedule> findByCreator_Username(String username);

    @RestResource(path = "creatorLatest", rel = "creatorLatest")
    List<Schedule> findTopByCreator_UserIdOrderByCreationDateDesc(Long id);

    @RestResource(path = "job", rel = "job")
    List<Schedule> findByJob_JobId(Long id);

    @RestResource(path = "hasRunningJob", rel = "hasRunningJob")
    List<Schedule> findByJobIsNotNull();
}
