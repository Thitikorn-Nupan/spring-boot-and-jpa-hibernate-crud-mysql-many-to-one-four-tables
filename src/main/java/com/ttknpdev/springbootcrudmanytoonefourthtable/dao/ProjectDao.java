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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectDao extends Logging implements ProjectService<Project> {

    private ProgrammerRepository programmerRepository;
    private SaleRepository saleRepository;
    private MarketingRepository marketingRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectDao(ProgrammerRepository programmerRepository, SaleRepository saleRepository, MarketingRepository marketingRepository, ProjectRepository projectRepository) {
        this.programmerRepository = programmerRepository;
        this.saleRepository = saleRepository;
        this.marketingRepository = marketingRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Long p, Long s, Long m, Project obj) {

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

        }).orElseThrow(()->{
            projectDao.warn("there is no programmer id " + p);
            throw new RuntimeException();
        });

    }

    @Override
    public List<Project> reads() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        if (projects.size() == 0) {
            projectDao.warn("nothing there in your table header_project");
            throw new RuntimeException();
        }
        return projects;
    }

    @Override
    public Project read(String project_name) {
        return projectRepository.findById(project_name).orElseThrow(()->{
            projectDao.warn("there is no project name "+project_name);
            throw new RuntimeException();
        });
    }

    @Override
    public Project update(Project obj , String project_name) {
        return projectRepository.findById(project_name).map(project -> {
            // projectDao.info(":{}"+obj);
            int rows = projectRepository.update(obj.getProject_name(), obj.getProject_cost(), obj.getProject_status(),project_name);
            if (rows != 0) {
                project.setProject_status(obj.getProject_status());
                project.setProject_cost(obj.getProject_cost());
                project.setProject_name(obj.getProject_name());
                return project;
            }
            else {
                projectDao.warn("your modify query has the problem please , check at the update() method");
                throw new RuntimeException();
            }
        }).orElseThrow(()->{
            projectDao.warn("there is problem in update() method");
            throw new RuntimeException();
        });
    }

    @Override
    public Map<String, Project> delete(String project_name) {
        Map<String,Project> response = new HashMap<>();
        return projectRepository.findById(project_name)
                .map(project -> {
/*                    projectDao.debug(project.getMarketing().getM_id());
                    if (project.getMarketing().getM_id() > 0) {
                        projectDao.debug("in this project has marketing id "+project.getMarketing().getM_id());
                        projectDao.debug("and you should destroy them before , Delete");
                        throw new RuntimeException();
                    }
                    if (project.getSale().getS_id()  > 0) {
                        projectDao.debug("in this project has sale id "+project.getSale().getS_id());
                        projectDao.debug("and you should destroy them before , Delete");
                        throw new RuntimeException();

                    }
                    if (project.getProgrammer().getP_id()  > 0) {
                        projectDao.debug("in this project has programmer id "+project.getProgrammer().getP_id());
                        projectDao.debug("and you should destroy them before , Delete");
                        throw new RuntimeException();
                    }*/
                    projectRepository.deleteById(project_name);
                    response.put("deleted",project);
                    return response;

                }).orElseThrow(()->{
                    projectDao.debug("not found name project "+project_name);
                    throw new RuntimeException();
                });
    }
}
