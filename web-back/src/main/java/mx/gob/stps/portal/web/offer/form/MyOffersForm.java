package mx.gob.stps.portal.web.offer.form;

import java.util.List;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;

import mx.gob.stps.portal.utils.Catalogos;
import org.apache.struts.action.ActionForm;

public class MyOffersForm extends ActionForm {
	 
	private static final long serialVersionUID = 549535301611394086L;
	
	private String msg = "";
	private int action = ACTION_INIT;
	
	public static final int ACTION_INIT = 0;
	public static final int ACTION_REMOVE = 1;
	
	private List<OfertaEmpleoJB> myoffers;

    private String difTablaPager = "";

	private boolean neverBeenInTouchWithPpc = false;
	private boolean decidedNotToEnrollToPpc = false;
	private boolean activeToPpc = false;
	private boolean inactiveToPpc = false;
	private boolean notAnyMoreEnrolledToPpc = false;

	private int ofertasContratadoPpc = 0;

	public void setAction(int action) {
		this.action = action;
	}
	
	public int getAction() {
		return this.action;
	}
	
	public void setMessage(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return this.msg;
	}
	
	public void setListMyOffers(List<OfertaEmpleoJB> myoffers) {
		this.myoffers = myoffers;
	}

	public List<OfertaEmpleoJB> getMyOffersList() {
		return this.myoffers;
	}

    public String getDifTablaPager() {
        return difTablaPager;
    }

    public void setDifTablaPager(String difTablaPager) {
        this.difTablaPager = difTablaPager;
    }

	public boolean getNeverBeenInTouchWithPpc() {
		return neverBeenInTouchWithPpc;
	}

	public boolean getDecidedNotToEnrollToPpc() {
		return decidedNotToEnrollToPpc;
	}

	public boolean getActiveToPpc() {
		return activeToPpc;
	}

	public boolean getInactiveToPpc() {
		return inactiveToPpc;
	}

	public boolean getNotAnyMoreEnrolledToPpc() {
		return notAnyMoreEnrolledToPpc;
	}

	public void clear() {
		this.msg = "";
		this.myoffers = null;
        difTablaPager = "";

		neverBeenInTouchWithPpc = false;
		decidedNotToEnrollToPpc = false;
		activeToPpc = false;
		inactiveToPpc = false;
		notAnyMoreEnrolledToPpc = false;

		ofertasContratadoPpc = 0;
	}

	public void reassignPpcStatus(final int ppcEstatus) {
		neverBeenInTouchWithPpc = (ppcEstatus == 0);
		decidedNotToEnrollToPpc = (!neverBeenInTouchWithPpc) && (ppcEstatus == Catalogos.ESTATUS.NO_INSCRITO_PPC.getIdOpcion());
		activeToPpc = (!neverBeenInTouchWithPpc) && (ppcEstatus == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion());
		inactiveToPpc = (!neverBeenInTouchWithPpc) && (ppcEstatus == Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion());
		notAnyMoreEnrolledToPpc = (!neverBeenInTouchWithPpc) && (ppcEstatus == Catalogos.ESTATUS.FUERA_PPC.getIdOpcion());
	}

	public int getOfertasContratadoPpc() {
		return ofertasContratadoPpc;
	}

	public void setOfertasContratadoPpc(int ofertasContratadoPpc) {
		this.ofertasContratadoPpc = ofertasContratadoPpc;
	}
}