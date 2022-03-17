package com.iucosoft.beautysalon.dao;

import com.iucosoft.beautysalon.models.Customer;
import com.iucosoft.beautysalon.models.Operation;
import com.iucosoft.beautysalon.models.OperationStatus;
import com.iucosoft.beautysalon.models.OperationType;
import com.iucosoft.beautysalon.models.User;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Rusanovschi
 */
public interface RegistrationDaoIntf {

    void inregistrare(Operation o) throws SQLException;

    void modificare(Operation o) throws SQLException;

    List<Operation> getOperations() throws SQLException;

    List<Operation> getOperations(LocalDate date) throws SQLException;

    List<Operation> getOperation(LocalDate d1, LocalDate d2,
            OperationType oType, OperationStatus oStatus) throws SQLException;

    Operation findById(int id) throws SQLException;

}
