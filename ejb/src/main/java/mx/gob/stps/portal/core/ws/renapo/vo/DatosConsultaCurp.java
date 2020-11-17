package mx.gob.stps.portal.core.ws.renapo.vo;

import java.io.Serializable;

/**
*
* Clase que actúa como Bean y encapsula los datos que se envían como parámetros para
* realizar el cambio especial
*
*/

public class DatosConsultaCurp implements Serializable {
	private static final long serialVersionUID = 8526819314438155120L;
	private String   direccionIp;
   private int      tipoTransaccion;
   private String   usuario;
   private String   password;
   private String   cveCurp;
   private String   cveEntidadEmisora;
   

   /**
    * Constructor
    * 
    */
   public DatosConsultaCurp() {
       super();
   }
   
   
   /**
    * Constructor sin entidad emisora
    * @param direccionIp
    * @param tipoTransaccion
    * @param usuario
    * @param password
    * @param cveCurp
    */
   public DatosConsultaCurp(String direccionIp, int tipoTransaccion,
           String usuario, String password, String cveCurp) {
       this.direccionIp = direccionIp;
       this.tipoTransaccion = tipoTransaccion;
       this.usuario = usuario;
       this.password = password;
       this.cveCurp = cveCurp;
   }
   
   
   /**
    * Constructor
    * @param direccionIp
    * @param tipoTransaccion
    * @param usuario
    * @param password
    * @param cveCurp
    * @param cveEntidadEmisora
    */
   public DatosConsultaCurp(String direccionIp, int tipoTransaccion,
           String usuario, String password, String cveCurp,
           String cveEntidadEmisora) {
       this.direccionIp = direccionIp;
       this.tipoTransaccion = tipoTransaccion;
       this.usuario = usuario;
       this.password = password;
       this.cveCurp = cveCurp;
       this.cveEntidadEmisora = cveEntidadEmisora;
   }
   /**
    * @return Obtiene el valor del atributo cveEntidadEmisora.
    */
   public String getCveEntidadEmisora() {
       return cveEntidadEmisora;
   }
   /**
    * @param cveEntidadEmisora Asigna valor al atributo cveEntidadEmisora.
    */
   public void setCveEntidadEmisora(String cveEntidadEmisora) {
       this.cveEntidadEmisora = cveEntidadEmisora;
   }
   /**
    * @return Obtiene el valor del atributo cveCurp.
    */
   public String getCveCurp() {
       return cveCurp;
   }
   /**
    * @param cveCurp Asigna valor al atributo cveCurp.
    */
   public void setCveCurp(String cveCurp) {
       this.cveCurp = cveCurp;
   }
   /**
    * @return Obtiene el valor del atributo direccionIp.
    */
   public String getDireccionIp() {
       return direccionIp;
   }
   /**
    * @param direccionIp Asigna valor al atributo direccionIp.
    */
   public void setDireccionIp(String direccionIp) {
       this.direccionIp = direccionIp;
   }
   /**
    * @return Obtiene el valor del atributo password.
    */
   public String getPassword() {
       return password;
   }
   /**
    * @param password Asigna valor al atributo password.
    */
   public void setPassword(String password) {
       this.password = password;
   }
   /**
    * @return Obtiene el valor del atributo tipoTransaccion.
    */
   public int getTipoTransaccion() {
       return tipoTransaccion;
   }
   /**
    * @param tipoTransaccion Asigna valor al atributo tipoTransaccion.
    */
   public void setTipoTransaccion(int tipoTransaccion) {
       this.tipoTransaccion = tipoTransaccion;
   }
   /**
    * @return Obtiene el valor del atributo usuario.
    */
   public String getUsuario() {
       return usuario;
   }
   /**
    * @param usuario Asigna valor al atributo usuario.
    */
   public void setUsuario(String usuario) {
       this.usuario = usuario;
   }
}