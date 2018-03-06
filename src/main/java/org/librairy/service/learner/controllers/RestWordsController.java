package org.librairy.service.learner.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.avro.AvroRemoteException;
import org.librairy.service.learner.facade.model.LearnerService;
import org.librairy.service.learner.facade.rest.model.WordsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/words")
@Api(tags="/words", description="handle words per topic")
public class RestWordsController {

    private static final Logger LOG = LoggerFactory.getLogger(RestWordsController.class);

    @Autowired
    LearnerService service;

    @PostConstruct
    public void setup(){

    }

    @PreDestroy
    public void destroy(){

    }

    @ApiOperation(value = "read words of a given topic", nickname = "getWords", response=WordsResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = WordsResult.class),
    })
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<WordsResult> words(@RequestParam Integer topicId, @RequestParam Integer maxWords)  {
        try {
            return new ResponseEntity(new WordsResult(service.getWords(topicId,maxWords).stream().map(w -> new org.librairy.service.learner.facade.rest.model.Word(w)).collect(Collectors.toList())), HttpStatus.OK);
        } catch (AvroRemoteException e) {
            return new ResponseEntity("internal service seems down",HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            LOG.error("IO Error", e);
            return new ResponseEntity("IO error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
