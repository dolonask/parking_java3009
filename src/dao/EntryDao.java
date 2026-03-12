package dao;

import dao.impl.EntryDaoImpl;
import model.Entry;
import model.enums.EntryStatus;

public interface EntryDao {

    EntryDao INSTANCE = new EntryDaoImpl();
    void saveEntry(Entry entry);
    boolean isCarInside(Integer carId);
    Entry findByCarIdAndStatus(Integer carId, EntryStatus status);
    void setEntry(Entry entry);
}
