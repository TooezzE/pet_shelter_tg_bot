package pet.shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.shelter.model.UserStatus;

import java.util.Optional;
/**
 * Репозиторий класса UserStatus
 * Предоставляет доступ к данным для управления сущностями UserStatus в базе данных
 */
@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

    Optional<UserStatus> findByChatId(Long chatId);

}
