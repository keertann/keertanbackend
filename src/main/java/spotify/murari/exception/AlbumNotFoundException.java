package spotify.murari.exception;

public class AlbumNotFoundException extends Exception {

	public AlbumNotFoundException() {
		super();
	}
	
	public AlbumNotFoundException(String message) {
		super(message);
	}
}
