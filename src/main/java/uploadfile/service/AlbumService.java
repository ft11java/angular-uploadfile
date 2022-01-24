package uploadfile.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uploadfile.dto.AlbumAdminViewDTO;
import uploadfile.dto.AlbumCreateDTO;
import uploadfile.exception.NotFoundException;
import uploadfile.model.Album;
import uploadfile.repository.AlbumRepository;

@Service
@RequiredArgsConstructor
public class AlbumService {

	private final AlbumRepository albumRepository;
	private final ModelMapper modelMaper;

	public AlbumAdminViewDTO getAlbumById(String id) {
		final Album album = albumRepository.findById(id).orElseThrow(() -> new NotFoundException("Album Bulunamadı"));

		return modelMaper.map(album, AlbumAdminViewDTO.class);
	}
	
	public List<AlbumAdminViewDTO> getAllAlbum() {
		
		return albumRepository.findAll().stream().map(album->modelMaper.map(album, AlbumAdminViewDTO.class)).collect(Collectors.toList());
	}


	public AlbumAdminViewDTO createAlbum(AlbumCreateDTO albumCreateDTO) {

		final Album album = albumRepository.save(new Album(albumCreateDTO.getAlbumName(), LocalDate.now()));
		return modelMaper.map(album, AlbumAdminViewDTO.class);
	}

	public void albumAddImage(String albumId, String imageName) {
		final Album album = albumRepository.findById(albumId)
				.orElseThrow(() -> new NotFoundException("Album Bulunamadı"));
		album.getImageNames().add(imageName);
		albumRepository.save(album);
	}



}
