package tw.idv.petradisespringboot.admin.service.impl;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(Integer id) {

    }

    public AdminNotFoundException(String message) {
        super(message);
    }

}
