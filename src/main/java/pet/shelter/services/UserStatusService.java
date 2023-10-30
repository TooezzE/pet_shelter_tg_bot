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

    private final UserStatusRepository repository;

    public UserStatusService(UserStatusRepository repository) {
        this.repository = repository;
    }

    public UserStatus saveUserStatus(UserStatus userStatus) {
        return repository.save(userStatus);
    }

    public Collection<UserStatus> getAll() {
        return repository.findAll();
    }

    public Optional<UserStatus> getByChatId(Long chatId) {
        return repository.findByChatId(chatId);
    }
}
