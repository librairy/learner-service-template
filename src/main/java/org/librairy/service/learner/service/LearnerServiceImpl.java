package org.librairy.service.learner.service;

import org.apache.avro.AvroRemoteException;
import org.librairy.service.learner.facade.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Component
public class LearnerServiceImpl implements org.librairy.service.learner.facade.model.LearnerService {

    private static final Logger LOG = LoggerFactory.getLogger(LearnerServiceImpl.class);

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
    public String train(Map<String, String> map) throws AvroRemoteException {
        //TODO
        return "building a new model";
    }

}
