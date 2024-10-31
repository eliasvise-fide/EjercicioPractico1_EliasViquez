package EliasViquezExam.demo.service.impl;

import EliasViquezExam.demo.domain.Sugerencia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import EliasViquezExam.demo.dao.SugerenciaDao;
import EliasViquezExam.service.SugerenciaService;

/**
 *
 * @author Elias
 */
@Service
public class SugerenciaServiceImpl implements SugerenciaService {

    @Autowired
    private SugerenciaDao SugerenciaDao;

    @Transactional(readOnly = true)
    public List<Sugerencia> getSugerencias() {
        var lista = SugerenciaDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Sugerencia getSugerencia(Sugerencia sugerencia) {
        return SugerenciaDao.findById(sugerencia.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Sugerencia sugerencia) {
        SugerenciaDao.save(sugerencia);
    }

    @Override
    @Transactional
    public void delete(Sugerencia sugerencia) {
        SugerenciaDao.delete(sugerencia);
    }
}
