package io.qusay.services.api;

import io.qusay.model.Building;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/*
Create and edit "location" field in JSON:
"location": {
    "x": 123,
    "y": 456
}
N.b. Can't just set x or y, have to set both at once
*/

@RepositoryRestResource(collectionResourceRel = "buildings", path = "buildings")
public interface BuildingRepository extends PagingAndSortingRepository<Building, Long> {
    @RestResource(path = "name", rel = "name")
    List<Building> findByName(String name);

    @RestResource(path = "venue", rel = "venue")
    List<Building> findByVenues_VenueId(Long id);
    @RestResource(path = "venueName", rel = "venueName")
    List<Building> findByVenues_Name(String name);

    @RestResource(path = "department", rel = "department")
    List<Building> findByDepartmentBuildings_DepartmentDepartmentId(Long id);
    // Note: the OR notation-style here does NOT work, returns TOO MANY results for ?name= queries: List<Building> findByDepartmentBuildings_DepartmentDepartmentId_OrDepartmentBuildings_DepartmentName(Long id, String name);
    // Thus, the separate departmentName API path is provided below
    @RestResource(path = "departmentName", rel = "departmentName")
    List<Building> findByDepartmentBuildings_DepartmentName(String name);
}
