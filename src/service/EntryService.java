package service;

import service.impl.EntryServiceImpl;

public interface EntryService {

    EntryService INSTANCE = new EntryServiceImpl();

    void in(String carNumber);
    void out(String carNumber);

}
