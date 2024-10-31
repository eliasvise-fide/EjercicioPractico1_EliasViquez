package EliasViquezExam.demo.service.impl;

import EliasViquezExam.demo.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import EliasViquezExam.demo.dao.ProductoDao;
import EliasViquezExam.service.ProductoService;

/**
 *
 * @author Elias
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao ProductoDao;

    @Transactional(readOnly = true)
    public List<Producto> getProductos() {
        var lista = ProductoDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return ProductoDao.findById(producto.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        ProductoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        ProductoDao.delete(producto);
    }
}
