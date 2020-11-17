package mx.gob.stps.portal.web.crm.servlet;

import mx.gob.stps.portal.persistencia.vo.CmrBannerVO;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
import mx.gob.stps.portal.web.crm.form.BannerForm;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class BannerImage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();


    public BannerImage() {
        super();
    }

    protected void doGet(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        // Not used in our simple example - see text.
        // String imageName = request.getParameter("imageName");

        // For this example, just create our input stream from our sample byte array:
        String idBanner = request.getParameter("idBanner");
        BannerForm bannerForm = new BannerForm();
        CmrBannerVO vo = new CmrBannerVO();
        bannerForm.setId(Long.parseLong(idBanner));
        try {
            vo = servicio.getBannerImage(Long.parseLong(idBanner));
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream iStream = new ByteArrayInputStream(vo.getImagen());

        // Determine the length of the content data.
        // In our simple example, I can get the length from the hard-coded byte array.
        // If you're getting your imaqe from a database or file,
        // you'll need to adjust this code to do what is appropriate:
        int length = vo.getImagen().length;

        // Hard-coded for a GIF image - see text.
        response.setContentType(vo.getTipoImagen());
        response.setContentLength(length);

        // Get the output stream from our response object, so we
        // can write our image data to the client:
        ServletOutputStream oStream = response.getOutputStream();

        // Now, loop through buffer reads of our content, and send it to the client:
        byte [] buffer = new byte[1024];
        int len;
        while ((len = iStream.read(buffer)) != -1) {
            oStream.write(buffer, 0, len);
        }

        // Now that we've sent the image data to the client, close down all the resources:
        iStream.close();

        oStream.flush();
        oStream.close();

        // And we're done. Just let the method return at this point.
    }

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        // Pass through to the doPost method:
        doGet(request, response);
    }
} // end of ImageServlet
