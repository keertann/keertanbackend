package spotify.murari.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {

	public String uploadSong(MultipartFile file);
	
	public byte[] downloadSong(String fileName);
	
	public String deleteSong(String fileName);
}
