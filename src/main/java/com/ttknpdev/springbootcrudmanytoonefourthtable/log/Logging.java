package com.ttknpdev.springbootcrudmanytoonefourthtable.log;


import com.ttknpdev.springbootcrudmanytoonefourthtable.dao.ProjectDao;
import com.ttknpdev.springbootcrudmanytoonefourthtable.dao.m_dao.MarketingDao;
import com.ttknpdev.springbootcrudmanytoonefourthtable.dao.p_dao.ProgrammerDao;
import com.ttknpdev.springbootcrudmanytoonefourthtable.dao.s_dao.SaleDao;
import org.apache.log4j.Logger;

public class Logging {
    public static final Logger programmerDao = Logger.getLogger(ProgrammerDao.class);
    public static final Logger marketingDao = Logger.getLogger(MarketingDao.class);
    public static final Logger saleDao = Logger.getLogger(SaleDao.class);
    public static final Logger projectDao = Logger.getLogger(ProjectDao.class);
}
