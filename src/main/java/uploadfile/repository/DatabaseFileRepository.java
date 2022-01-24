package uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uploadfile.model.DatabaseFile;



@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, String> {

}