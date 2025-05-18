package com.ttknpdev.springbootcrudmanytoonefourthtable.testing;


import com.ttknpdev.springbootcrudmanytoonefourthtable.dao.ProjectDao;
import com.ttknpdev.springbootcrudmanytoonefourthtable.dao.m_dao.MarketingDao;
import com.ttknpdev.springbootcrudmanytoonefourthtable.dao.p_dao.ProgrammerDao;
import com.ttknpdev.springbootcrudmanytoonefourthtable.dao.s_dao.SaleDao;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.Project;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.s_entity.Sale;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.ProjectRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.m_repo.MarketingRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.p_repo.ProgrammerRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.repositories.s_repo.SaleRepository;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.MarketingService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.ProgrammerService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.ProjectService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.SaleService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@DataJpaTest
@Rollback(value = false)
@Transactional
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestLogic {
    @Autowired
    private ProgrammerRepository programmerRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private MarketingRepository marketingRepository;
    @Autowired
    private ProjectRepository projectRepository;

    private ProgrammerService programmerService;
    private SaleService saleService;
    private MarketingService marketingService;
    private ProjectService projectService;
    // @Autowired
    private void setService() {
        programmerService = new ProgrammerDao(programmerRepository);
        saleService = new SaleDao(saleRepository);
        marketingService = new MarketingDao(marketingRepository);
        projectService = new ProjectDao(programmerRepository,saleRepository,marketingRepository,projectRepository); // many to one
    }

    @Test
    public void createProgrammer() {
        Programmer programmer = new Programmer("Jack Nicholson",77000.0D,"middle");
        assertNotNull(programmerService.create(programmer));

    }

    @Test
    public void createSale() {
        Sale sale= new Sale("Humphrey Bogart",100000.0D,"senior");
        assertNotNull(saleService.create(sale));
    }
    @Test
    public void createMarketing() {
        Marketing marketing = new Marketing("Meryl Streep",66500.0D,"middle");
        assertNotNull(marketingService.create(marketing));
    }

    @Test
    public void createProject() {
        Project project = new Project("Cj Market-Shopping",150000.0,true);
        assertNotNull(projectService.create(3L,2L,2L,project)); // foreign key can have more than one in tables
    }
}
