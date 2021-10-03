package spotify.murari.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {

	private Long timeStamp;
	private String module;
	private String message;
}
