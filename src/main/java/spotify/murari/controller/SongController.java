package spotify.murari.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import spotify.murari.exception.AlbumNotFoundException;
import spotify.murari.exception.SongNotFoundException;
import spotify.murari.payload.request.AlbumRequest;
import spotify.murari.payload.request.SongRequest;
import spotify.murari.payload.response.AlbumResponse;
import spotify.murari.payload.response.SongResponse;
import spotify.murari.service.IAlbumService;
import spotify.murari.service.ISongService;
import spotify.murari.service.IStorageService;

@RestController
@CrossOrigin
@RequestMapping("/spotify")
public class SongController {
	
	@Autowired
	private ISongService songService;
	
	@Autowired
	private IStorageService storageService;

	@ApiOperation(value = "ADD NEW SONG")
	@PostMapping("/song")
	public ResponseEntity<?> saveSong(@RequestParam("songRequest") String data,@RequestParam("file") MultipartFile file) throws SongNotFoundException {
		ResponseEntity<String> resp = null;
		try {
			//convert data to songRequest
			ObjectMapper mapper=new ObjectMapper();
			SongRequest songRequest=mapper.readValue(data, SongRequest.class);
			//upload song to s3
			String songUri= storageService.uploadSong(file);
			songRequest.setUri(songUri);
			songService.addSong(songRequest);
			resp = new ResponseEntity<String>("Song saved " + songRequest.getTitle(), HttpStatus.CREATED);
		}
		catch (SongNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to process save", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;
	}

	@ApiOperation(value = "FETCH ALL SONG")
	@GetMapping("/song")
	public ResponseEntity<?> getSongs() throws SongNotFoundException {
		ResponseEntity<?> resp = null;
		try {
			List<SongResponse> songList = songService.getSongs();
			resp = new ResponseEntity<List<SongResponse>>(songList, HttpStatus.OK);
		}
		catch (SongNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@ApiOperation(value = "FETCH SONG BY ID")
	@GetMapping("/song/{id}")
	public ResponseEntity<?> getOneSong(@PathVariable String id) throws SongNotFoundException {
		ResponseEntity<?> resp = null;
		try {
			SongResponse song = songService.getOneSong(id);
			resp = new ResponseEntity<SongResponse>(song, HttpStatus.OK);
		}
		catch (SongNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return resp;
	}

	@ApiOperation(value = "DELETE SONG BY ID")
	@DeleteMapping("/song/{id}")
	public ResponseEntity<String> removeOneSong(@PathVariable String id) {
		ResponseEntity<String> resp = null;
		try {
			songService.deleteSong(id);
			resp = new ResponseEntity<String>("Song deleted", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to delete data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@ApiOperation(value = "UPDATE SONG")
	@PutMapping("/song")
	public ResponseEntity<String> updateSong(@RequestBody SongRequest req) throws SongNotFoundException {
		ResponseEntity<String> resp = null;
		try {
			String id= songService.updateSong(req);
			resp = new ResponseEntity<String>("Song updated"+id, HttpStatus.RESET_CONTENT);
		}
		catch (SongNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to update data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

}
