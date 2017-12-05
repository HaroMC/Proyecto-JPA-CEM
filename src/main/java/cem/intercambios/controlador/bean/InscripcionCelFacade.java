package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.InscripcionCel;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class InscripcionCelFacade extends AbstractFacade<InscripcionCel> {

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionCelFacade() {
        super(InscripcionCel.class);
    }

    public List<InscripcionCel> programasInscritosCel(String rutPersona) {
        return em.createNamedQuery("InscripcionCel.programasInscritosCel")
                .setParameter("rutPersona", rutPersona)
                .getResultList();
    }

    public BigDecimal codigoAutoIncremental() {
        try {
            return em.createNamedQuery("InscripcionCel.codigoAutoIncremental",
                    BigDecimal.class).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
