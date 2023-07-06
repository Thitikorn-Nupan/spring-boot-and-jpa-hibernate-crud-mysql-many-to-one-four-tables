package com.ttknpdev.springbootcrudmanytoonefourthtable.repositories;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends CrudRepository<Project,String> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update projects set `project_name` = :project_name ,`project_cost` = :project_cost ,`project_status` = :project_status  where `project_name`= :newProject_name" , nativeQuery = true)
    int update(@Param("project_name")String project_name , @Param("project_cost")Double project_cost ,@Param("project_status")Boolean project_status , @Param("newProject_name")String newProject_name);
}
