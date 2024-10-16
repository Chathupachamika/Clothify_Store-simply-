package repository;

import model.Supplier;

import java.util.List;

public interface SupplierRepository {
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    boolean removeSupplier(int supplierId);
    Supplier searchSupplier(int supplierId);
    List<Supplier> getAllSupplier();
}
