package mx.gob.stps.portal.web.crm.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin.vander on 14/12/2015.
 */
public class BannersForm extends BaseMenuForm {

    private static final long serialVersionUID = -3491637470205228033L;

    private List<BannerForm> bannerFormList = new ArrayList<BannerForm>();


    public List<BannerForm> getBannerFormList() {
        return bannerFormList;
    }

    public void setBannerFormList(List<BannerForm> bannerFormList) {
        this.bannerFormList = bannerFormList;
    }


    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
        try {
            request.setCharacterEncoding("ISO-8859-1");
            return this.validate(mapping, (HttpServletRequest)request);
        } catch (ClassCastException var4) {
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
