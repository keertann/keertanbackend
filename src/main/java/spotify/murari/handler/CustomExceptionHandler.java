package spotify.murari.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import spotify.murari.exception.AlbumCategoryNotFoundException;
import spotify.murari.exception.AlbumNotFoundException;
import spotify.murari.exception.SongNotFoundException;
import spotify.murari.payload.ErrorData;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(AlbumCategoryNotFoundException.class)
	public ResponseEntity<ErrorData> handleAlbumCategoryNotFoundException(
			AlbumCategoryNotFoundException e) {
		return new ResponseEntity<ErrorData>(
				new ErrorData(System.currentTimeMillis(), "ALBUM-CATEGORY", e.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AlbumNotFoundException.class)
	public ResponseEntity<ErrorData> handleAlbumNotFoundException(
			AlbumNotFoundException e) {
		return new ResponseEntity<ErrorData>(
				new ErrorData(System.currentTimeMillis(), "ALBUM", e.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SongNotFoundException.class)
	public ResponseEntity<ErrorData> handleSongNotFoundException(
			SongNotFoundException e) {
		return new ResponseEntity<ErrorData>(
				new ErrorData(System.currentTimeMillis(), "SONG", e.getMessage()),
				HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
