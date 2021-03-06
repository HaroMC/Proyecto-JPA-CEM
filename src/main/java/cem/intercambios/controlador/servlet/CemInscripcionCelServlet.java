package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.InscripcionCelFacade;
import cem.intercambios.modelo.entidad.InscripcionCel;
import cem.intercambios.modelo.entidad.InscripcionCelPK;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CemInscripcionCelServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private InscripcionCelFacade icf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        sesion = req.getSession();
        String mensaje;
        List<InscripcionCel> inscripcionesCel = icf.findAll();
        if (inscripcionesCel != null) {
            sesion.setAttribute("inscripcionesCel", inscripcionesCel);
            mensaje = "Visualización correcta de todas las postulaciones de "
                    + "CEL.";
            LOGGER.info(mensaje);
        } else {
            mensaje = "No se encontraron registros.";
            sesion.setAttribute("mensajeEstado", mensaje);
            LOGGER.info(mensaje);
        }
        resp.sendRedirect("inscripciones_cel.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mensaje;
        String accion = ((req.getParameter("accion") == null)
                ? "" : req.getParameter("accion"));
        sesion = req.getSession();

        switch (accion) {

            case "modificar":
                InscripcionCel inscripcionEditar;
                String confirmarModificacion
                        = (req.getParameter("confirmar") == null
                        ? "" : req.getParameter("confirmar"));

                switch (confirmarModificacion) {

                    case "si":
                        inscripcionEditar = (InscripcionCel) sesion
                                .getAttribute("inscripcion");
                        try {
                            inscripcionEditar.setEstado(
                                    Short.parseShort(
                                            req.getParameter("estado")));
                        } catch (NumberFormatException ex) {

                        }
                        icf.edit(inscripcionEditar);
                        sesion.removeAttribute("inscripcion");
                        resp.sendRedirect("inscripciones_cel");
                        break;

                    default:
                        inscripcionEditar = icf.find(
                                new InscripcionCelPK(
                                        req.getParameter("rutCel"),
                                        req.getParameter("codigoPrograma"))
                        );
                        sesion.setAttribute("inscripcion", inscripcionEditar);
                        sesion.setAttribute("tipo", "CEL");
                        resp.sendRedirect("ver_detalles.jsp");
                }
                break;
        }
    }

}
