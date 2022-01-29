package uploadfile.dto;

import java.io.Serializable;


import lombok.Data;

@Data
public class AlbumAdminViewForSelectDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String albumName;
}

