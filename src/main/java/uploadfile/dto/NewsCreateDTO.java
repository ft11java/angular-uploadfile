package uploadfile.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NewsCreateDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String newsTitle;
	private String newsBody;
	private boolean deploy;
	private MultipartFile multipartFile;
}
