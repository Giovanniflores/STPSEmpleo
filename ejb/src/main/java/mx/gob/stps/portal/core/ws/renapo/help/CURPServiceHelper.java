/**
 * 
 Claves alfabéticas de Entidades Federativas
1	AGUASCALIENTES	AS
2	BAJA CALIFORNIA	BC
3	BAJA CALIFORNIA SUR	BS
4	CAMPECHE	CC
5	COAHUILA	CL
6	COLIMA	CM
7	CHIAPAS	CS
8	CHIHUHUA	CH
9	DISTRITO FEDERAL	DF
10	DURANGO	DG
11	GUANAJUATO	GT
12	GUERRERO	GR
13	HIDALGO	HG
14	JALISCO	JC
15	MEXICO	MC
16	MICHOACAN	MN
17	MORELOS	MS
18	NAYARIT	NT
19	NUEVO LEON	NL
20	OAXACA	OC
21	PUEBLA	PL
22	QUERETARO	QT
23	QUINTANA ROO	QR
24	SAN LUIS POTOSI	SP
25	SINALOA	SL
26	SONORA	SR
27	TABASCO	TC
28	TAMAULIPAS	TS
29	TLAXCALA	TL
30	VERACRUZ	VZ
31	YUCATAN	YN
32	ZACATECAS	ZS


 */
package mx.gob.stps.portal.core.ws.renapo.help;

/**
 * @author jose.hernandez
 *
 */
public class CURPServiceHelper {
	
	/** Genero Renapo 
	 * @param identidad
	 * @return String
	 */
	public static String getIdGeneroRenapo(int identidad){
		
		String cadena = "";
		
		switch (identidad) {
			case 1 : cadena = "H"; break;
			case 2 : cadena = "M"; break;
		}			
		return cadena;
	}
	
	/** Entidad Renapo
	 * @param identidad
	 * @return
	 */
	public static String getIdEntidadRenapo(int identidad){
	
	String cadena = "";
	
		switch (identidad) {
			case	1	:  cadena = "AS";  break;
			case	2	:  cadena = "BC";  break;
			case	3	:  cadena = "BS";  break;
			case	4	:  cadena = "CC";  break;
			case	5	:  cadena = "CL";  break;
			case	6	:  cadena = "CM";  break;
			case	7	:  cadena = "CS";  break;
			case	8	:  cadena = "CH";  break;
			case	9	:  cadena = "DF";  break;
			case	10	:  cadena = "DG";  break;
			case	11	:  cadena = "GT";  break;
			case	12	:  cadena = "GR";  break;
			case	13	:  cadena = "HG";  break;
			case	14	:  cadena = "JC";  break;
			case	15	:  cadena = "MC";  break;
			case	16	:  cadena = "MN";  break;
			case	17	:  cadena = "MS";  break;
			case	18	:  cadena = "NT";  break;
			case	19	:  cadena = "NL";  break;
			case	20	:  cadena = "OC";  break;
			case	21	:  cadena = "PL";  break;
			case	22	:  cadena = "QT";  break;
			case	23	:  cadena = "QR";  break;
			case	24	:  cadena = "SP";  break;
			case	25	:  cadena = "SL";  break;
			case	26	:  cadena = "SR";  break;
			case	27	:  cadena = "TC";  break;
			case	28	:  cadena = "TS";  break;
			case	29	:  cadena = "TL";  break;
			case	30	:  cadena = "VZ";  break;
			case	31	:  cadena = "YN";  break;
			case	32	:  cadena = "ZS";  break;
			case	33	:  cadena = "NE";  break;		
			}
		return cadena;
	}
	
	/** Otorga el valor numerido de Estado
	 * @param idEstadoRenapo
	 * @return
	 */
	public static Integer tipoEntidadFederativa(String idEstadoRenapo){
		Integer idEstadoCatalodo = 0;
			if("AS".equals(idEstadoRenapo)) idEstadoCatalodo =	1	;
			if("BC".equals(idEstadoRenapo)) idEstadoCatalodo =	2	;
			if("BS".equals(idEstadoRenapo)) idEstadoCatalodo =	3	;
			if("CC".equals(idEstadoRenapo)) idEstadoCatalodo =	4	;
			if("CL".equals(idEstadoRenapo)) idEstadoCatalodo =	5	;
			if("CM".equals(idEstadoRenapo)) idEstadoCatalodo =	6	;
			if("CS".equals(idEstadoRenapo)) idEstadoCatalodo =	7	;
			if("CH".equals(idEstadoRenapo)) idEstadoCatalodo =	8	;
			if("DF".equals(idEstadoRenapo)) idEstadoCatalodo =	9	;
			if("DG".equals(idEstadoRenapo)) idEstadoCatalodo =	10	;
			if("GT".equals(idEstadoRenapo)) idEstadoCatalodo =	11	;
			if("GR".equals(idEstadoRenapo)) idEstadoCatalodo =	12	;
			if("HG".equals(idEstadoRenapo)) idEstadoCatalodo =	13	;
			if("JC".equals(idEstadoRenapo)) idEstadoCatalodo =	14	;
			if("MC".equals(idEstadoRenapo)) idEstadoCatalodo =	15	;
			if("MN".equals(idEstadoRenapo)) idEstadoCatalodo =	16	;
			if("MS".equals(idEstadoRenapo)) idEstadoCatalodo =	17	;
			if("NT".equals(idEstadoRenapo)) idEstadoCatalodo =	18	;
			if("NL".equals(idEstadoRenapo)) idEstadoCatalodo =	19	;
			if("OC".equals(idEstadoRenapo)) idEstadoCatalodo =	20	;
			if("PL".equals(idEstadoRenapo)) idEstadoCatalodo =	21	;
			if("QT".equals(idEstadoRenapo)) idEstadoCatalodo =	22	;
			if("QR".equals(idEstadoRenapo)) idEstadoCatalodo =	23	;
			if("SP".equals(idEstadoRenapo)) idEstadoCatalodo =	24	;
			if("SL".equals(idEstadoRenapo)) idEstadoCatalodo =	25	;
			if("SR".equals(idEstadoRenapo)) idEstadoCatalodo =	26	;
			if("TC".equals(idEstadoRenapo)) idEstadoCatalodo =	27	;
			if("TS".equals(idEstadoRenapo)) idEstadoCatalodo =	28	;
			if("TL".equals(idEstadoRenapo)) idEstadoCatalodo =	29	;
			if("VZ".equals(idEstadoRenapo)) idEstadoCatalodo =	30	;
			if("YN".equals(idEstadoRenapo)) idEstadoCatalodo =	31	;
			if("ZS".equals(idEstadoRenapo)) idEstadoCatalodo =	32	;
			if("NE".equals(idEstadoRenapo)) idEstadoCatalodo =	33	;
			return idEstadoCatalodo;
	}

	
}
