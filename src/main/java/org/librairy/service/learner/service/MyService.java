package org.librairy.service.learner.service;

import org.apache.avro.AvroRemoteException;
import org.librairy.service.learner.facade.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class MyService implements LearnerService {

    private static final Logger LOG = LoggerFactory.getLogger(MyService.class);

    @Value("#{environment['RESOURCE_FOLDER']?:'${resource.folder}'}")
    String resourceFolder;

    String model              ;

    @PostConstruct
    public void setup() throws IOException {

        //// Load resources
        //model              = Paths.get(resourceFolder,"resource.bin").toFile().getAbsolutePath();

        LOG.info("Service initialized");
    }


    @Override
    public String addDocument(Document document) throws AvroRemoteException {
        //TODO
        return "document added";
    }

    @Override
    public String reset() throws AvroRemoteException {
        //TODO
        return "documents deleted";
    }

    @Override
    public List<Topic> getTopics() throws AvroRemoteException {
        //TODO
        return Collections.emptyList();
    }

    @Override
    public List<Word> getWords(int i, int i1) throws AvroRemoteException {
        //TODO
        return Collections.emptyList();
    }

    @Override
    public String train(Map<String, String> map) throws AvroRemoteException {
        //TODO
        return "building a new model";
    }

    @Override
    public List<TopicDistribution> inference(String s) throws AvroRemoteException {
        return Collections.emptyList();
    }
}
