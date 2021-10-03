package spotify.murari.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spotify.murari.entity.AlbumCategory;
import spotify.murari.exception.AlbumCategoryNotFoundException;
import spotify.murari.payload.request.AlbumCategoryRequest;
import spotify.murari.payload.response.AlbumCategoryResponse;
import spotify.murari.repository.AlbumCategoryRepo;
import spotify.murari.service.IAlbumCategoryService;


@Service
public class AlbumCategoryServiceImpl implements IAlbumCategoryService{

	@Autowired
	private AlbumCategoryRepo albumCategoryRepo;
	
	public List<AlbumCategoryResponse> getAlbumCategory() {
		
		List< AlbumCategoryResponse> responseList=new ArrayList<AlbumCategoryResponse>();
		List<AlbumCategory> categoryList= albumCategoryRepo.findAll();
		if(categoryList.isEmpty())
			throw new AlbumCategoryNotFoundException("No Album Category Exist");
		categoryList.forEach(category->{
			AlbumCategoryResponse response=new AlbumCategoryResponse();
			BeanUtils.copyProperties(category, response);
			responseList.add(response);
		});
		return responseList;
	}
	
	public String saveCategory(AlbumCategoryRequest albumCategoryRequest) {
		
		AlbumCategory category=new AlbumCategory();
		BeanUtils.copyProperties(albumCategoryRequest, category);
		return albumCategoryRepo.save(category).getId();
	}

	@Override
	public void updateCategory(AlbumCategoryRequest albumCategoryRequest) {

		AlbumCategory category=new AlbumCategory();
		BeanUtils.copyProperties(albumCategoryRequest, category);
		category.setId(albumCategoryRequest.getId());
		albumCategoryRepo.save(category);
		
	}

	@Override
	public void deleteCategory(String categoryId) {
		
		albumCategoryRepo.deleteById(categoryId);
		
	}

	@Override
	public AlbumCategoryResponse getOneCategory(String categoryId) throws AlbumCategoryNotFoundException{
		Optional<AlbumCategory>optCategory=albumCategoryRepo.findById(categoryId);
		AlbumCategoryResponse response=new AlbumCategoryResponse();
		
		if(optCategory.isPresent()) {
			AlbumCategory category=optCategory.get();
			BeanUtils.copyProperties(category, response);
			return response;
		}
		else {
			throw new AlbumCategoryNotFoundException("Album category "+categoryId+" not exist");
		}
	}
	
}
