package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Album;
import com.example.model.Artist;

@Service
public class DummyServiceImpl implements DummyService {

	List<Album> albums = new  ArrayList<Album>();
	List<Artist> artists = new  ArrayList<>();
	
	public DummyServiceImpl() {
		// TODO Auto-generated constructor stub
		
		Album album = new Album();
		album.setId("1");
		album.setTitle("Title 1");
		album.setPrice("90000");
		album.setArtist(new Artist("1", "Name Artis 1"));
		
		Album album2 = new Album();
		album2.setId("2");
		album2.setTitle("Title 2");
		album2.setPrice("90000");
		album2.setArtist(new Artist("2", "Name Artis 2"));
		
		Album album3 = new Album();
		album3.setId("3");
		album3.setTitle("Title 3");
		album3.setPrice("90000");
		album3.setArtist(new Artist("3", "Name Artis 3"));
		
		artists.add(new Artist("1", "Name Artis 1"));
		artists.add(new Artist("2", "Name Artis 2"));
		artists.add(new Artist("3", "Name Artis 3"));
		
		albums.add(album);
		albums.add(album2);
		albums.add(album3);
		
	}
	
	@Override
	public List<Album> getAlbums() {
		return albums;
	}
	
	@Override
	public Album getAlbumById(String id) {
		return albums.get(Integer.valueOf(id));
	}
	
	@Override
	public Artist getArtist(String id) {
		return artists.get(Integer.valueOf(id));
	}
}
