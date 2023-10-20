package pet.shelter.services;

import org.springframework.stereotype.Service;
import pet.shelter.model.UserStatus;
import pet.shelter.repository.UserStatusRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * User status service
 */
@Service
public class UserStatusService {

    private UserStatusRepository userStatusRepository;

    public UserStatusService(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    public UserStatus saveUserStatus(UserStatus userStatus) {
        return userStatusRepository.save(userStatus);
    }

    public Collection<UserStatus> getAll() {
        return userStatusRepository.findAll();
    }

    public Optional<UserStatus> getByChatId(Long chatId) {
        return userStatusRepository.findByChatId(chatId);
    }
}
