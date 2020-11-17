package mx.gob.stps.portal.movil.app.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.joda.time.DateTime;

@MappedSuperclass
//@Data
public abstract class Model implements Serializable {

    // ===============================
    // FIELDS
    // ===============================

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
//    @SequenceGenerator(name="AUTHOR_GEN", sequenceName="AUTHOR_SEQ", initialValue=1, allocationSize=1)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUTHOR_GEN")
//    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    private DateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private DateTime updatedAt;

    // ===============================
    // RELATIONSHIPS
    // ===============================

    // Do not have any

    // ===============================
    // TRIGGERS
    // ===============================

    @PrePersist
    public void onPrePersist() {
        createdAt = updatedAt = new DateTime();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = new DateTime();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
}
