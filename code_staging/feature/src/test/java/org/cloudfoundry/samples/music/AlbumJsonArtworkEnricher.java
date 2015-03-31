package org.cloudfoundry.samples.music;

import static org.cloudfoundry.samples.music.web.AlbumArtworkHelper.artworkUrlFor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import lombok.Data;

import org.cloudfoundry.samples.music.model.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.init.Jackson2ResourceReader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AlbumJsonArtworkEnricher {

	private static final Logger log = LoggerFactory
			.getLogger(AlbumJsonArtworkEnricher.class);

	
	public static void main(String[] args) {
		try {
			writeAlbums(toMarshalInfo(populateArtworkAndRating(readAlbums("albums.json"))),
					"src/main/resources/albums-with-artwork.json");
		} catch (Exception e) {
			log.error("{}", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static Collection<Album> readAlbums(String fromResource) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		Collection<Album> albums = (Collection<Album>) new Jackson2ResourceReader(mapper)
				.readFrom(new ClassPathResource(fromResource), Thread.currentThread().getContextClassLoader());

		return albums;
	}
	
	public static Collection<AlbumMarshalInfo> toMarshalInfo (Collection<Album> albums)
			throws Exception {
		
		List<AlbumMarshalInfo> albumsMarshallingInfo = new ArrayList<AlbumMarshalInfo>();
		
		for (Album album : albums) {
			AlbumMarshalInfo marshallInfo = new AlbumMarshalInfo();
			
			marshallInfo.setArtist(album.getArtist());
			marshallInfo.setTitle(album.getTitle());
			marshallInfo.setGenre(album.getGenre());
			marshallInfo.setReleaseYear(album.getReleaseYear());
			marshallInfo.setArtworkUrl(album.getArtworkUrl());
			marshallInfo.setRating(album.getRating());
			
			albumsMarshallingInfo.add(marshallInfo);
		}
		
		return albumsMarshallingInfo;
		
	}
	
	public static void writeAlbums(Collection<AlbumMarshalInfo> albums, String toResource)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(new File(toResource), albums);
	}

	public static Collection<Album> populateArtworkAndRating(Collection<Album> priorToArtwork)
			throws Exception {
		List<Album> afterArtwork = new ArrayList<Album>();
		Random random = new Random();

		for (Album album : priorToArtwork) {
			log.info("{}", album);
			
			album.setArtworkUrl(artworkUrlFor(album));
			album.setRating(random.nextInt(6));
			
			afterArtwork.add(album);
		}

		return afterArtwork;
	}

	@Data
	static class AlbumMarshalInfo {
		private final String _class = Album.class.getCanonicalName();
	    private String title;
	    private String artist;
	    private String releaseYear;
	    private String genre;
	    private String artworkUrl;
	    private Integer rating;
	}
}