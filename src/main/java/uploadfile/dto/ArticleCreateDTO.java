package uploadfile.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ArticleCreateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String newsTitle;
	private String newsBody;
	private boolean deploy;
	private String imageName;
	

}
