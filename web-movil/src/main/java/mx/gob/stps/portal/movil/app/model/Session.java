package mx.gob.stps.portal.movil.app.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "NLR_SESSIONS", schema = "RWRDS")
public class Session extends Model {

    // ===============================
    // FIELDS
    // ===============================

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String role;

    private String salt;
    private String token;

    @Lob
    private byte[] data;

    // ===============================
    // RELATIONSHIPS
    // ===============================

    public Session(){}

    public Session(String username, String role, String salt, String token) {
        this.username = username;
        this.role = role;
        this.salt = salt;
        this.token = token;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
    

//    public Session(String username, String salt, String token, String sessionId) {
//        this.username = username;
//        this.salt = salt;
//        this.token = token;
//        this.sessionId = sessionId;
//    }

}