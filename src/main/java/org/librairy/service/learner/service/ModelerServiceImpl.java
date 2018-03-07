package org.librairy.service.learner.service;

import org.apache.avro.AvroRemoteException;
import org.librairy.service.modeler.facade.model.Topic;
import org.librairy.service.modeler.facade.model.TopicDistribution;
import org.librairy.service.modeler.facade.model.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class ModelerServiceImpl implements org.librairy.service.modeler.facade.model.ModelerService{

    private static final Logger LOG = LoggerFactory.getLogger(ModelerServiceImpl.class);

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
    public List<TopicDistribution> inference(String s) throws AvroRemoteException {
        //TODO
        return Collections.emptyList();
    }

    @Override
    public List<Double> shape(String s) throws AvroRemoteException {
        //TODO
        return Collections.emptyList();
    }

    @Override
    public List<Topic> topics() throws AvroRemoteException {
        //TODO
        return Collections.emptyList();
    }

    @Override
    public List<Word> words(int i, int i1) throws AvroRemoteException {
        //TODO
        return Collections.emptyList();
    }

}
