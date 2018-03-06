package org.librairy.service.learner.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.avro.AvroRemoteException;
import org.librairy.service.learner.facade.model.LearnerService;
import org.librairy.service.learner.facade.model.Topic;
import org.librairy.service.learner.facade.rest.model.Document;
import org.librairy.service.learner.facade.rest.model.TopicsResult;
import org.librairy.service.learner.facade.rest.model.TrainRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
@Api(tags="/topics", description="handle topics")
public class RestTopicsController {

    private static final Logger LOG = LoggerFactory.getLogger(RestTopicsController.class);

    @Autowired
    LearnerService service;

    @PostConstruct
    public void setup(){

    }

    @PreDestroy
    public void destroy(){

    }

    @ApiOperation(value = "read topics", nickname = "getTopics", response=TopicsResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TopicsResult.class),
    })
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TopicsResult> get()  {
        try {
            List<Topic> result = service.getTopics();
            return new ResponseEntity(new TopicsResult(result.stream().map(t -> new org.librairy.service.learner.facade.rest.model.Topic(t)).collect(Collectors.toList())), HttpStatus.OK);
        } catch (AvroRemoteException e) {
            return new ResponseEntity("internal service seems down",HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            LOG.error("IO Error", e);
            return new ResponseEntity("IO error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "create a new topic model", nickname = "postTopics", response=String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted", response = String.class),
    })
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> train(@RequestBody TrainRequest request)  {
        try {
            String result = service.train(request.getParameters());
            return new ResponseEntity(result, HttpStatus.ACCEPTED);
        } catch (AvroRemoteException e) {
            return new ResponseEntity("internal service seems down",HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            LOG.error("IO Error", e);
            return new ResponseEntity("IO error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
