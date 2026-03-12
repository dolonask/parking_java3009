package service;

import model.dto.EntryDto;
import service.impl.EntryServiceImpl;

import java.util.List;

public interface EntryService {

    EntryService INSTANCE = new EntryServiceImpl();

    void in(String carNumber);
    void out(String carNumber);

    List<EntryDto> findEntryDtosInParking();
}
