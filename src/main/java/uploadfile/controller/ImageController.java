package uploadfile.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import uploadfile.service.ImageService;
import uploadfiles.shared.GenericResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageController {

	private final ImageService imageService;

	@GetMapping("v1/images/{imageName}")
	public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) {

		byte[] image = imageService.getImageView(imageName);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}

	@PostMapping("v1/image")
	public ResponseEntity<?> uploadImage(@RequestParam("albumId") String albumId,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
			imageService.uploadImage(albumId, multipartFile);
		return ResponseEntity.ok(new GenericResponse("Image Uploaded..."));

	}

}
