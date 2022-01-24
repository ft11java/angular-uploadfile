package uploadfile.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class NewsCreateeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String newsTitle;
	private String newsBody;
	private boolean deploy;
}
