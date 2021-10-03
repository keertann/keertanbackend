package spotify.murari.service;

import java.util.List;

import spotify.murari.exception.SongNotFoundException;
import spotify.murari.payload.request.SongRequest;
import spotify.murari.payload.response.SongResponse;

public interface ISongService {

	public String addSong(SongRequest songRequest) throws SongNotFoundException ;
	
	public String updateSong(SongRequest songRequest) throws SongNotFoundException;
	
	public List<SongResponse> getSongs() throws SongNotFoundException;
	
	public SongResponse getOneSong(String songId) throws SongNotFoundException;
	
	public void deleteSong(String songId);
}
