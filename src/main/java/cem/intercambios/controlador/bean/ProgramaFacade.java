package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Programa;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class ProgramaFacade extends AbstractFacade<Programa> {

    private static final Logger LOGGER
            = Logger.getLogger(ProgramaFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramaFacade() {
        super(Programa.class);
    }

    public BigDecimal codigoAutoIncremental() {
        try {
            return em.createNamedQuery(
                    "Programa.codigoAutoIncremental", BigDecimal.class)
                    .getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultado.", ex);
            return null;
        }
    }

    public List<Programa> listarProgramasPorEstado(String estado) {
        try {
            return em.createNamedQuery("Programa.findByEstado", Programa.class)
                    .setParameter("estado", estado)
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultado.", ex);
            return null;
        }
    }

    /*public Programa find(BigDecimal codigo) {
        try {
            return em.createNamedQuery("Programa.findByCodigo", Programa.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultado.", ex);
            return null;
        }
    }*/

}
