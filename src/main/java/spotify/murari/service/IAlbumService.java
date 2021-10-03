package spotify.murari.service;

import java.util.List;

import spotify.murari.exception.AlbumNotFoundException;
import spotify.murari.payload.request.AlbumRequest;
import spotify.murari.payload.response.AlbumResponse;

public interface IAlbumService {

	public String addAlbum(AlbumRequest albumRequest) throws AlbumNotFoundException ;
	
	public String updateAlbum(AlbumRequest albumRequest) throws AlbumNotFoundException;
	
	public List<AlbumResponse> getAlbums() throws AlbumNotFoundException;
	
	public AlbumResponse getOneAlbum(String albumId) throws AlbumNotFoundException;
	
	public void delteAlbum(String albumId);
}
