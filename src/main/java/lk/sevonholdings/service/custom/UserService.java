package lk.sevonholdings.service.custom;

import lk.sevonholdings.service.SuperService;

import java.util.Optional;

public interface UserService extends SuperService {
    Optional<String> validateToLoginManagerWindow(String username, String password);
}
