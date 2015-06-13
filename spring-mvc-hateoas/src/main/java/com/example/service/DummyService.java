package com.example.service;

import java.util.List;

import com.example.model.Album;
import com.example.model.Artist;

public interface DummyService {
	
	List<Album> getAlbums();
	
	Album getAlbumById(String id);

	Artist getArtist(String id);

}
