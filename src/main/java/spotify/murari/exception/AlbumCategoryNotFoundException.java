package spotify.murari.exception;

public class AlbumCategoryNotFoundException extends RuntimeException{

	public AlbumCategoryNotFoundException() {
		super();
	}
	
	public AlbumCategoryNotFoundException(String message) {
		super(message);
	}
}
