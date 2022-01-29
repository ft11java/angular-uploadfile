package uploadfile.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import uploadfile.dto.AlbumAdminViewDTO;
import uploadfile.dto.AlbumAdminViewForSelectDTO;
import uploadfile.dto.AlbumCreateDTO;
import uploadfile.service.AlbumService;

import uploadfiles.shared.GenericResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AlbumController {

	private final AlbumService albumService;
	
	@GetMapping("v1/album/{id}")
	public ResponseEntity<AlbumAdminViewDTO> getAlbumById (@PathVariable("id")String id){
		final AlbumAdminViewDTO album=albumService.getAlbumById(id);
		
		return ResponseEntity.ok(album);
		
	}
	
	@GetMapping("v1/album")
	public ResponseEntity<List<AlbumAdminViewForSelectDTO>> getAllAlbum(){
		List<AlbumAdminViewForSelectDTO> album=albumService.getAllAlbum();		
		
		return ResponseEntity.ok(album);
		
	}
	
	
	@PostMapping("v1/album")
	public ResponseEntity<?> createAlbum(@RequestBody AlbumCreateDTO albumCreateDTO) {
		albumService.createAlbum(albumCreateDTO);

		return ResponseEntity.ok(new GenericResponse("Album Created..."));

	}

}
