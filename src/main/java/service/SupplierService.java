package service;

import model.Supplier;

import java.util.List;

public interface SupplierService {
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    boolean removeSupplier(int supplierId);
    Supplier searchSupplier(int supplierId);
    List<Supplier> getAllSupplier();
}
