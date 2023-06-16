package lk.sevonholdings.service.custom.impl;

import lk.sevonholdings.dao.DaoFactory;
import lk.sevonholdings.dao.DaoTypes;
import lk.sevonholdings.dao.QueryDAO;
import lk.sevonholdings.db.DBConnection;
import lk.sevonholdings.service.custom.UserService;

import java.sql.Connection;
import java.util.Optional;

public class UserServiceimpl implements UserService {
    private final Connection connection;
    private final QueryDAO queryDAO;

    public UserServiceimpl() {
        connection= DBConnection.getDbConnection().getConnection();
        queryDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.QUERY );
    }

    @Override
    public Optional<String> validateToLoginManagerWindow(String username, String password) {
        return queryDAO.validateToLoginManagerWindow(username, password);
    }
}
