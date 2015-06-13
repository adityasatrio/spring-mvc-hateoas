package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Album;
import com.example.model.Artist;
import com.example.model.Greeting;
import com.example.service.DummyService;

@RestController
@RequestMapping("/rest")
public class Controller   {
	
	@Autowired
	private DummyService dummyService;
	
	@RequestMapping("/greeting")
    @ResponseBody
	public HttpEntity<Greeting> greeting(@RequestParam(value = "name", required = false, defaultValue = "demoHateoas") String hai){
		
		Greeting greeting = new Greeting(hai);
		greeting.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(Controller.class).greeting(hai)).withSelfRel());
		
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/albums", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public List<Resource<Album>> getAlbums(){
		
		List<Album> listAlbum =  dummyService.getAlbums();
		
		List<Resource<Album>> listOfResourceAlbums = new ArrayList<>();
		for(Album album : listAlbum ){
			listOfResourceAlbums.add(getAlbumResources(album));
		}
		
		return listOfResourceAlbums;
	}

	private Resource<Album> getAlbumResources(Album album) {
		Resource<Album> resource = new Resource<Album>(album);
		if(album !=  null){
			// Link to Album
			resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(Controller.class).getAlbum(album.getId())).withSelfRel());
			// Link to Artist
			resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(Controller.class).getArtist(album.getArtist().getId())).withRel("artist"));

		}
		return resource;
	}
	
	@RequestMapping(value = "/album/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
	public Album getAlbum(@PathVariable(value = "id") String id){
		return dummyService.getAlbumById(id);
	}
	
	
	@RequestMapping(value = "/artist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
	public Artist getArtist(@PathVariable(value = "id") String id){
		return dummyService.getArtist(id);
	}

}
