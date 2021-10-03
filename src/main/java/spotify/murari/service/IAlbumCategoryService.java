package spotify.murari.service;

import java.util.List;

import spotify.murari.payload.request.AlbumCategoryRequest;
import spotify.murari.payload.response.AlbumCategoryResponse;

public interface IAlbumCategoryService {

	public String saveCategory(AlbumCategoryRequest albumCategoryRequest);
	
	public void updateCategory(AlbumCategoryRequest albumCategoryRequest);
	
	public void deleteCategory(String categoryId);
	
	public AlbumCategoryResponse getOneCategory(String categoryId);
	
	public List<AlbumCategoryResponse> getAlbumCategory();
	
	
}
