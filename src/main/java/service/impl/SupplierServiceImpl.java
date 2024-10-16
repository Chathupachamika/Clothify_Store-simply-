package service.impl;

import model.Supplier;
import repository.SupplierRepository;
import repository.impl.SupplierRepositoryImpl;
import service.SupplierService;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository = new SupplierRepositoryImpl();
    @Override
    public boolean addSupplier(Supplier supplier) {
        return supplierRepository.addSupplier(supplier);
    }
    @Override
    public boolean updateSupplier(Supplier supplier) {
        return supplierRepository.updateSupplier(supplier);
    }
    @Override
    public boolean removeSupplier(int supplierId) {
        return supplierRepository.removeSupplier(supplierId);
    }
    @Override
    public Supplier searchSupplier(int supplierId) {
        return supplierRepository.searchSupplier(supplierId);
    }
    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepository.getAllSupplier();
    }
}
