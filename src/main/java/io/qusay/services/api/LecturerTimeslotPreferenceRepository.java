package io.qusay.services.api;

import io.qusay.model.LecturerTimeslotPreference;
import io.qusay.model.LecturerTimeslotPreferencePK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "lecturerTimeslotPreferences", path = "lecturerTimeslotPreferences")
public interface LecturerTimeslotPreferenceRepository extends JpaRepository<LecturerTimeslotPreference, LecturerTimeslotPreferencePK> {
    @RestResource(path = "timeslot", rel = "timeslot")
    List<LecturerTimeslotPreference> findByTimeslot_TimeslotId(Long id);

    @RestResource(path = "lecturer", rel = "lecturer")
    List<LecturerTimeslotPreference> findByLecturer_LecturerId(Long id);
}
