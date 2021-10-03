package spotify.murari.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spotify.murari.entity.Album;
import spotify.murari.entity.AlbumCategory;
import spotify.murari.exception.AlbumNotFoundException;
import spotify.murari.payload.request.AlbumRequest;
import spotify.murari.payload.response.AlbumResponse;
import spotify.murari.repository.AlbumCategoryRepo;
import spotify.murari.repository.AlbumRepo;
import spotify.murari.service.IAlbumService;

@Service
@Transactional
public class AlbumServiceImpl implements IAlbumService {

	@Autowired
	private AlbumRepo albumRepo;

	@Autowired
	private AlbumCategoryRepo categoryRepo;

	public String addAlbum(AlbumRequest albumRequest) throws AlbumNotFoundException {
		// convert AlbumRequest to Album
		Album album = new Album();
		BeanUtils.copyProperties(albumRequest, album, "categoryId");

		// get categoryId
		Optional<AlbumCategory> optCategory = categoryRepo.findById(albumRequest.getCategoryId());
		if (optCategory.isEmpty())
			throw new AlbumNotFoundException("Album does not exist");

		album.setCategory(optCategory.get());

		// save album to database
		return albumRepo.save(album).getId();
	}

	public String updateAlbum(AlbumRequest albumRequest) throws AlbumNotFoundException {
		// convert AlbumRequest to Album
		Album album = new Album();
		BeanUtils.copyProperties(albumRequest, album, "categoryId");

		// get categoryId
		Optional<AlbumCategory> optCategory = categoryRepo.findById(albumRequest.getCategoryId());
		if (optCategory.isEmpty())
			throw new AlbumNotFoundException("Album does not exist");

		album.setCategory(optCategory.get());

		// save album to database
		return albumRepo.save(album).getId();
	}

	public List<AlbumResponse> getAlbums() throws AlbumNotFoundException {
		
		List<AlbumResponse> albumResponseList=new ArrayList<>();
		List<Album> albumList=albumRepo.findAll();
		if(albumList.isEmpty())
			throw new AlbumNotFoundException("No any Album Found");
		
		albumList.forEach(album->{
			AlbumResponse albumResponse=new AlbumResponse();
			BeanUtils.copyProperties(album, albumResponse, "id","category");
			albumResponse.setAlbumId(album.getId());
			albumResponse.setCategoryId(album.getCategory().getId());
			albumResponseList.add(albumResponse);
		});
		return albumResponseList;
	}

	public AlbumResponse getOneAlbum(String albumId) throws AlbumNotFoundException {
		AlbumResponse albumResponse=new AlbumResponse();
		
		Optional<Album>optAlbum=albumRepo.findById(albumId);
		Album album=optAlbum.orElseThrow(()->new AlbumNotFoundException("Album not Found"));
		BeanUtils.copyProperties(album,albumResponse,"id" );
		albumResponse.setAlbumId(album.getId());
		return albumResponse;
	}

	public void delteAlbum(String albumId) {
		albumRepo.deleteById(albumId);
	}

}
