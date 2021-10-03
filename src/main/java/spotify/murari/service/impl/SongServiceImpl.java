package spotify.murari.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spotify.murari.entity.Album;
import spotify.murari.entity.Song;
import spotify.murari.exception.SongNotFoundException;
import spotify.murari.payload.request.SongRequest;
import spotify.murari.payload.response.SongResponse;
import spotify.murari.repository.AlbumRepo;
import spotify.murari.repository.SongRepo;
import spotify.murari.service.ISongService;

@Service
@Transactional
public class SongServiceImpl implements ISongService {

	@Autowired
	private SongRepo songRepo;

	@Autowired
	private AlbumRepo albumRepo;

	public String addSong(SongRequest songRequest) throws SongNotFoundException {
		// convert SongRequest to Song
		Song song = new Song();
		BeanUtils.copyProperties(songRequest, song, "songId");

		// get albumId
		Optional<Album> optAlbum = albumRepo.findById(songRequest.getAlbumId());
		if (optAlbum.isEmpty())
			throw new SongNotFoundException("Song does not exist");

		song.setAlbum(optAlbum.get());

		// save album to database
		return songRepo.save(song).getId();
	}

	public String updateSong(SongRequest songRequest) throws SongNotFoundException {
		// convert SongRequest to Song
		Song song = new Song();
		BeanUtils.copyProperties(songRequest, song, "songId");

		// get albumId
		Optional<Album> optAlbum = albumRepo.findById(songRequest.getAlbumId());
		if (optAlbum.isEmpty())
			throw new SongNotFoundException("Song does not exist");

		song.setAlbum(optAlbum.get());

		// save album to database
		return songRepo.save(song).getId();
	}

	public List<SongResponse> getSongs() throws SongNotFoundException {
		List<SongResponse> songResponseList = new ArrayList<>();
		List<Song> songList = songRepo.findAll();
		if (songList.isEmpty())
			throw new SongNotFoundException("No any Song Found");

		songList.forEach(song -> {
			SongResponse songResponse = new SongResponse();
			BeanUtils.copyProperties(song, songResponse, "album");
			songResponse.setAlbumId(song.getAlbum().getId());
			songResponseList.add(songResponse);
		});
		return songResponseList;
	}

	public SongResponse getOneSong(String songId) throws SongNotFoundException {

		SongResponse songResponse = new SongResponse();

		Optional<Song> optSong = songRepo.findById(songId);
		Song song = optSong.orElseThrow(() -> new SongNotFoundException("Song not Found"));
		BeanUtils.copyProperties(song, songResponse);
		
		return songResponse;
	}

	public void deleteSong(String songId) {
		songRepo.deleteById(songId);
	}

}
