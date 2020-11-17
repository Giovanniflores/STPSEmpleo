package mx.gob.stps.portal.web.admin.publisher;

import org.apache.struts.action.ActionForm;

public class AdminPublisherActivityForm extends ActionForm {

	private static final long serialVersionUID = -297362445391744605L;
	private String reporter;
	private long totalReview;
	private long totalAuthorized;
	private long totalRejected;
	private long totalCompanies;
	private long totalOffers;
	private long totalTestimonies;
	private long totalVideocv;
	/**
	 * @return the reporter
	 */
	public String getReporter() {
		return reporter;
	}
	/**
	 * @param reporter the reporter to set
	 */
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	/**
	 * @return the totalReview
	 */
	public long getTotalReview() {
		return totalReview;
	}
	/**
	 * @param totalReview the totalReview to set
	 */
	public void setTotalReview(long totalReview) {
		this.totalReview = totalReview;
	}
	/**
	 * @return the totalAuthorized
	 */
	public long getTotalAuthorized() {
		return totalAuthorized;
	}
	/**
	 * @param totalAuthorized the totalAuthorized to set
	 */
	public void setTotalAuthorized(long totalAuthorized) {
		this.totalAuthorized = totalAuthorized;
	}
	/**
	 * @return the totalRejected
	 */
	public long getTotalRejected() {
		return totalRejected;
	}
	/**
	 * @param totalRejected the totalRejected to set
	 */
	public void setTotalRejected(long totalRejected) {
		this.totalRejected = totalRejected;
	}
	/**
	 * @return the totalCompnies
	 */
	public long getTotalCompanies() {
		return totalCompanies;
	}
	/**
	 * @param totalCompnies the totalCompnies to set
	 */
	public void setTotalCompanies(long totalCompanies) {
		this.totalCompanies = totalCompanies;
	}
	/**
	 * @return the totalOffers
	 */
	public long getTotalOffers() {
		return totalOffers;
	}
	/**
	 * @param totalOffers the totalOffers to set
	 */
	public void setTotalOffers(long totalOffers) {
		this.totalOffers = totalOffers;
	}
	/**
	 * @return the totalTestimonies
	 */
	public long getTotalTestimonies() {
		return totalTestimonies;
	}
	/**
	 * @param totalTestimonies the totalTestimonies to set
	 */
	public void setTotalTestimonies(long totalTestimonies) {
		this.totalTestimonies = totalTestimonies;
	}
	/**
	 * @return the totalVideocv
	 */
	public long getTotalVideocv() {
		return totalVideocv;
	}
	/**
	 * @param totalVideocv the totalVideocv to set
	 */
	public void setTotalVideocv(long totalVideocv) {
		this.totalVideocv = totalVideocv;
	}
}
