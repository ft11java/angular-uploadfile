package uploadfile.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AlbumCreateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String albumName;
	
}
