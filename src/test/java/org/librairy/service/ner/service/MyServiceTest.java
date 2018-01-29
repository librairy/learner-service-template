package org.librairy.service.ner.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.librairy.service.learner.service.MyService;
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
@SpringBootTest(classes = MyService.class)
@WebAppConfiguration
public class MyServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(MyServiceTest.class);

    @Autowired
    MyService service;

    @Test
    public void annotation() throws IOException {


        String result = service.train("src/test/resources/corpus.csv", new HashMap<>(), new HashMap<>());

        LOG.info("Result: " + result);

//        Assert.assertEquals(2, annotations.size());
    }
}