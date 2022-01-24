package uploadfile.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
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
import uploadfile.dto.ImageDTO;
import uploadfile.dto.NewsAdminViewDTO;
import uploadfile.dto.NewsCreateeDTO;
import uploadfile.service.NewsService;
import uploadfiles.shared.GenericResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsController {

	private final NewsService newsService;
	private final ObjectMapper objectMapper;
    private final ServletContext contex;

	@GetMapping("v1/admin/news/{id}")

	public ResponseEntity<NewsAdminViewDTO> getNewsById(@PathVariable("id") String id) {
		final NewsAdminViewDTO news = newsService.getNewsById(id);

		return ResponseEntity.ok(news);
	}

	@GetMapping("v1/admin/news")
	public ResponseEntity<List<NewsAdminViewDTO>> getAllNews(){
		final List<NewsAdminViewDTO> newses=newsService.getAllNews();
		
		return ResponseEntity.ok(newses);
		
	}
	
	@PostMapping("v1/admin/news")

	public ResponseEntity<?> createNews(@RequestParam("news") String news,@RequestParam ("imageFile") MultipartFile multipartFile)
			throws IOException {
		NewsCreateeDTO newsCreateDTO = objectMapper.readValue(news,NewsCreateeDTO.class);
		
		newsService.createNews(newsCreateDTO, multipartFile);
		System.out.printf(newsCreateDTO.getNewsTitle());
		System.out.printf(newsCreateDTO.getNewsBody());
		System.out.println(newsCreateDTO.toString());
		System.out.println(multipartFile.getOriginalFilename());

		return ResponseEntity.ok(new GenericResponse("News Created..."));

	}
	
	 @GetMapping("/imgarticles")
	 public byte[] getPhoto() throws Exception{
		
		 return Files.readAllBytes(Paths.get(contex.getRealPath("/article-photos/")+"21d9f24e-8686-4179-a4fd-2fd3ccf1700b.jpg"));
	 }
	 
	 @GetMapping("/images/{imagename}")
	 public ResponseEntity<ImageDTO> getimage(@PathVariable("imagename") String imagename){
		String image=newsService.getimage(imagename);
		 ImageDTO imagess=new ImageDTO(image);
		
		 return ResponseEntity.ok(imagess);
				 
		 
	 }
	 
}
