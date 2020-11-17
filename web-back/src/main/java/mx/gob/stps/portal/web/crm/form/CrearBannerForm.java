package mx.gob.stps.portal.web.crm.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin.vander on 21/12/2015.
 */
public class CrearBannerForm extends BannerForm {

    private Long id;

    private BannerForm bannerForm = new BannerForm();

    private List<BannerForm> bannersForm = new ArrayList<BannerForm>();

    public BannerForm getBannerForm() {
        return bannerForm;
    }

    public void setBannerForm(BannerForm bannerForm) {
        this.bannerForm = bannerForm;
    }

    public List<BannerForm> getBannersForm() {
        return bannersForm;
    }

    public void setBannersForm(List<BannerForm> bannersForm) {
        this.bannersForm = bannersForm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
