package org.cloudfoundry.samples.music.web;

import javax.validation.Valid;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.repositories.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);
    
    @Autowired
    private AlbumRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Album> albums() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Album add(@RequestBody @Valid Album album) {
        logger.info("Adding new album '{}'", album.getTitle());
        return repository.save(album);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Album update(@RequestBody @Valid Album album) {
        logger.info("Updating album {}", album.getId());
        return repository.save(album);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Album getById(@PathVariable String id) {
        logger.info("Getting album {}", id);
        
        Album album = repository.findOne(id); 
        
        if (album == null) {
        	throw new ResourceNotFoundException();
        }
        
        return album;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String id) {
        logger.info("Deleting album {}", id);
        
        try{
        	repository.delete(id);
        } catch (EmptyResultDataAccessException e) {
        	; // If the id wasn't there for delete, then desired state is acheived.
        }
        
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;
    }
    
}