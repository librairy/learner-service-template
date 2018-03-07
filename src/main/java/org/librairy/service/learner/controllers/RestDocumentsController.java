package org.librairy.service.learner.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.avro.AvroRemoteException;
import org.librairy.service.learner.facade.model.LearnerService;
import org.librairy.service.learner.facade.rest.model.Corpus;
import org.librairy.service.learner.facade.rest.model.Document;
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

@RestController
@RequestMapping("/documents")
@Api(tags="/documents", description="handle texts")
public class RestDocumentsController {

    private static final Logger LOG = LoggerFactory.getLogger(RestDocumentsController.class);

    @Autowired
    LearnerService service;

    @PostConstruct
    public void setup(){

    }

    @PreDestroy
    public void destroy(){

    }

    @ApiOperation(value = "add document to corpus", nickname = "postDocuments", response=String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = String.class),
    })
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> add(@RequestBody Document document)  {
        try {
            String result = service.addDocument(document);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (AvroRemoteException e) {
            return new ResponseEntity("internal service seems down",HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            LOG.error("IO Error", e);
            return new ResponseEntity("IO error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "remove all documents", nickname = "deleteDocuments", response=String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted", response = String.class),
    })
    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> removeAll()  {
        try {
            String result = service.reset();
            return new ResponseEntity(result, HttpStatus.ACCEPTED);
        } catch (AvroRemoteException e) {
            return new ResponseEntity("internal service seems down",HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            LOG.error("IO Error", e);
            return new ResponseEntity("IO error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "corpus statistics", nickname = "getDocuments", response=Corpus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Corpus.class),
    })
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Corpus> getCorpus()  {
        try {
            return new ResponseEntity(service.getCorpus(), HttpStatus.ACCEPTED);
        } catch (AvroRemoteException e) {
            return new ResponseEntity("internal service seems down",HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            LOG.error("IO Error", e);
            return new ResponseEntity("IO error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
