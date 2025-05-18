package com.ttknpdev.springbootcrudmanytoonefourthtable.dao;

import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.Project;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.s_entity.Sale;
import com.ttknpdev.springbootcrudmanytoonefourthtable.log.Logging;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.ProjectRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.m_repo.MarketingRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.p_repo.ProgrammerRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.s_repo.SaleRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.ProjectService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectDaoCommon extends Logging implements ServiceCommon<Project> {

    private ProgrammerRepository programmerRepository;
    private SaleRepository saleRepository;
    private MarketingRepository marketingRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectDaoCommon(ProgrammerRepository programmerRepository, SaleRepository saleRepository, MarketingRepository marketingRepository, ProjectRepository projectRepository) {
        this.programmerRepository = programmerRepository;
        this.saleRepository = saleRepository;
        this.marketingRepository = marketingRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public Project create(Project obj) {
        return null;
    }

    @Override
    public <U1, U2, U3> Project create(U1 key1, U2 key2, U3 key3, Project obj) {
        Long p,s,m;
        p = Long.valueOf(key1.toString());
        s = Long.valueOf(key2.toString());
        m = Long.valueOf(key3.toString());
        return programmerRepository.findById(p).map(programmer->{

            Sale sale = saleRepository.findById(s).orElseThrow(()->{
                projectDao.warn("there is no sale id " + s);
                throw new RuntimeException();
            });
            Marketing marketing = marketingRepository.findById(m).orElseThrow(()->{
                projectDao.warn("there is no marketing id " + m);
                throw new RuntimeException();
            });

            obj.setProgrammer(programmer);
            obj.setSale(sale);
            obj.setMarketing(marketing);

            return projectRepository.save(obj);

        }).orElseThrow(()-> new RuntimeException("there is no programmer id " + p));
    }

    @Override
    public List<Project> reads() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        if (projects.isEmpty()) {
            throw new RuntimeException("nothing there in your table header_project");
        }
        return projects;
    }

    @Override
    public <U> Project read(U key) {
        String project_name = key.toString();
        return projectRepository.findById(project_name).orElseThrow(()-> new RuntimeException("there is no project name "+project_name));
    }

    @Override
    public <U> Project update(Project obj, U key) {
        String project_name = key.toString();
        return projectRepository.findById(project_name).map(project -> {
            int rows = projectRepository.update(obj.getProject_name(), obj.getProject_cost(), obj.getProject_status(),project_name);
            if (rows != 0) {
                project.setProject_status(obj.getProject_status());
                project.setProject_cost(obj.getProject_cost());
                project.setProject_name(obj.getProject_name());
                return project;
            }
            else {
                throw new RuntimeException("your modify query has the problem please , check at the update() method");
            }
        }).orElseThrow(()-> new RuntimeException("there is problem in update() method"));
    }

    @Override
    public <U> Map<String, U> delete(U key) {
        String project_name = key.toString();
        Map<String,Project> response = new HashMap<>();
        return (Map<String, U>) projectRepository.findById(project_name)
                .map(project -> {
                    projectRepository.deleteById(project_name);
                    response.put("deleted",project);
                    return response;
                }).orElseThrow(()-> new RuntimeException("not found name project "+project_name));
    }
}
