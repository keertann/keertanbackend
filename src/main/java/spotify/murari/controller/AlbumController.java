package spotify.murari.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import spotify.murari.exception.AlbumNotFoundException;
import spotify.murari.payload.request.AlbumRequest;
import spotify.murari.payload.response.AlbumResponse;
import spotify.murari.service.IAlbumService;

@RestController
@RequestMapping("/spotify")
public class AlbumController {

	@Autowired
	private IAlbumService albumService;

	@ApiOperation(value = "ADD NEW ALBUM")
	@PostMapping("/album")
	public ResponseEntity<?> saveAlbum(@Valid @RequestBody AlbumRequest albumRequest) throws AlbumNotFoundException {
		ResponseEntity<String> resp = null;
		try {
			albumService.addAlbum(albumRequest);
			resp = new ResponseEntity<String>("Album saved " + albumRequest.getAlbumName(), HttpStatus.CREATED);
		}
		catch (AlbumNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to process save", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;
	}

	@ApiOperation(value = "FETCH ALL ALBUM")
	@GetMapping("/album")
	public ResponseEntity<?> getAlbums() throws AlbumNotFoundException {
		ResponseEntity<?> resp = null;
		try {
			List<AlbumResponse> albumList = albumService.getAlbums();
			resp = new ResponseEntity<List<AlbumResponse>>(albumList, HttpStatus.OK);
		}
		catch (AlbumNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@ApiOperation(value = "FETCH  ALBUM BY ID")
	@GetMapping("/album/{id}")
	public ResponseEntity<?> getOneAlbum(@PathVariable String id) throws AlbumNotFoundException {
		ResponseEntity<?> resp = null;
		try {
			AlbumResponse album = albumService.getOneAlbum(id);
			resp = new ResponseEntity<AlbumResponse>(album, HttpStatus.OK);
		}
		catch (AlbumNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return resp;
	}

	@ApiOperation(value = "DELETE ALBUM BY ID")
	@DeleteMapping("/album/{id}")
	public ResponseEntity<String> removeOneAlbum(@PathVariable String id) {
		ResponseEntity<String> resp = null;
		try {
			albumService.delteAlbum(id);
			resp = new ResponseEntity<String>("Album deleted", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to delete data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@ApiOperation(value = "UPDATE ALBUM")
	@PutMapping("/album")
	public ResponseEntity<String> updateAlbum(@RequestBody AlbumRequest req) throws AlbumNotFoundException {
		ResponseEntity<String> resp = null;
		try {
			String id= albumService.updateAlbum(req);
			resp = new ResponseEntity<String>("Album updated"+id, HttpStatus.RESET_CONTENT);
		}
		catch (AlbumNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to update data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

}
