package uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uploadfile.model.Album;
public interface AlbumRepository extends JpaRepository<Album, String> {

}
