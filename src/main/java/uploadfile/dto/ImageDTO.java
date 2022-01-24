package uploadfile.dto;



import java.io.Serializable;

import lombok.Data;


@Data
public class ImageDTO  implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private String image;

	public ImageDTO(String image) {
		
		this.image = image;
	}
	
	
}
