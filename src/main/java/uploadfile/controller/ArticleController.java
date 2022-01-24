package uploadfile.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import uploadfile.dto.ArticleAdminViewDTO;
import uploadfile.dto.ArticleCreateDTO;
import uploadfile.service.ArticleService;
import uploadfiles.shared.GenericResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleController {
	
	private final ArticleService articleService;
	private final ObjectMapper objectMapper;
	
	
	private final String FILE_PATH_ROOT="article-photos/";
	
	@GetMapping("v1/image/{fileName}")
	public ResponseEntity<byte[]> getImage(@PathVariable("fileName")String fileName) throws IOException{
		byte[] image=new byte[0];
		try {
			image=FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT+fileName));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		
	}
	
	@GetMapping("v1/article/{id}")
	public ResponseEntity<ArticleAdminViewDTO> getArticleById(@PathVariable("id")String id){
		final ArticleAdminViewDTO article=articleService.getArticleById(id);
		
		return ResponseEntity.ok(article);
		
	}
	
	@GetMapping("v1/article")
	public ResponseEntity<List<ArticleAdminViewDTO>> getArticleAll(){
		final List<ArticleAdminViewDTO> article=articleService.getAllArticle();
		
		return ResponseEntity.ok(article);
	}
	
	@PostMapping("v1/article")
	public ResponseEntity<?> createNews(@RequestParam("article") String article,@RequestParam ("imageFile") MultipartFile multipartFile)
			throws IOException {
		ArticleCreateDTO articleCreateDTO = objectMapper.readValue(article,ArticleCreateDTO.class);
		articleService.articleCreate(articleCreateDTO,multipartFile);
		
		return ResponseEntity.ok(new GenericResponse("Article Created..."));

	}
	
	
}
