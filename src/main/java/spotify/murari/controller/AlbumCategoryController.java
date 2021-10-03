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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import spotify.murari.payload.request.AlbumCategoryRequest;
import spotify.murari.payload.response.AlbumCategoryResponse;
import spotify.murari.service.IAlbumCategoryService;

@RestController
@CrossOrigin
@RequestMapping("/spotify")

public class AlbumCategoryController {

	@Autowired
	private IAlbumCategoryService categoryService;
	
	@ApiOperation(value = "SAVE ALBUM CATEGORY")
	@PostMapping("/category")
	public ResponseEntity<?> saveAlbumCategory(@Valid @RequestBody AlbumCategoryRequest categoryRequest){
		ResponseEntity<String> resp=null;
		try {
			categoryService.saveCategory(categoryRequest);
			resp=new ResponseEntity<String>("Album category saved "+categoryRequest.getTitle(),HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			resp=new ResponseEntity<String>("Unable to process save",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return resp;
	} 
	
	@ApiOperation(value = "FETCH ALL ALBUM CATEGORY")
	@GetMapping("/category")
	public ResponseEntity<?> getAlbumCategory(){
		ResponseEntity<?> resp=null;
		try {
			List<AlbumCategoryResponse> categoryList=categoryService.getAlbumCategory();
			resp=new ResponseEntity<List<AlbumCategoryResponse>>(categoryList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp=new ResponseEntity<String>("Unable to fetch data",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
	@ApiOperation(value = "FETCH  ALBUM CATEGORY BY ID")
	@GetMapping("/category/{id}")
	public ResponseEntity<?> getOneCategory(@PathVariable String id){
		ResponseEntity<?> resp=null;
		try {
			AlbumCategoryResponse category=categoryService.getOneCategory(id);
			resp=new ResponseEntity<AlbumCategoryResponse>(category,HttpStatus.OK);
		} catch (Exception e) {
			throw e;
			
		}
		return resp;
	}
	
	@ApiOperation(value = "DELETE ALBUM CATEGORY BY ID")
	@DeleteMapping("/category/{id}")
	public ResponseEntity<String> removeOneCategory(@PathVariable String id){
		ResponseEntity<String> resp=null;
		try {
			categoryService.deleteCategory(id);
			resp=new ResponseEntity<String>("Category deleted",HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			resp=new ResponseEntity<String>("Unable to delete data",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
	
	@ApiOperation(value = "UPDATE ALBUM CATEGORY")
	@PutMapping("/category")
	public ResponseEntity<String> updateCategory(@RequestBody AlbumCategoryRequest req){
		ResponseEntity<String> resp=null;
		try {
			categoryService.updateCategory(req);
			resp=new ResponseEntity<String>("Category updated",HttpStatus.RESET_CONTENT);
		}
		catch (Exception e) {
			e.printStackTrace();
			resp=new ResponseEntity<String>("Unable to update data",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
	
	
}
