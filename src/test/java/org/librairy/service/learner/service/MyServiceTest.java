package org.librairy.service.learner.service;

import org.assertj.core.util.Strings;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.librairy.service.learner.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MyServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(MyServiceTest.class);

    @Autowired
    LearnerServiceImpl service;

    @Test
    public void train() throws IOException {


        String result = service.train(new HashMap<>());

        LOG.info("Result: " + result);

        Assert.assertFalse(Strings.isNullOrEmpty(result));
    }
}