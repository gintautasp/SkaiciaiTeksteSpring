package nextspring;

import org.springframework.stereotype.Component;

@Component
public class SkaiciaiEiluteje {

	private String[] skaitmenys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private String[] dalys_skaiciaus = { ".", ",", "-", "e", "E", "+", "/", "%" };
	private String[] zodzio_pabaigos = { ".", ",", " ", "\n", ";", ":" };
	private String[] ne_zodzio_dalis = { ")", ",", ";", "." , "/" };
	
	public SkaiciaiEiluteje() {		
	}
	
	public static boolean priklausoAibei ( String simbolis, String[] aibe ) {
		
		boolean priklauso = false;
		
		for (int i = 0; i < aibe.length; i++ ) {
		
			if ( simbolis.equals ( aibe [ i ] ) ) {
			
				priklauso = true;
			}
		}
		return priklauso;
	}	
	
	public int ieskotiSkaiciu ( String eilute, String[] skaiciai, String[] vnt_po_skaiciu, int kiekis_skaiciu ) {
		
		boolean pries_tai_buvo_skaitmuo = false;
		
		boolean yra_zodis_po_skaitmens = false;	
		
		String simb;
		
		for ( int i = 0; i < eilute.length(); i++ ) {
			
			simb =  eilute.substring( i, i+1 );
		
			if ( 
					priklausoAibei ( simb, skaitmenys ) 
				|| 
					( 
							pries_tai_buvo_skaitmuo  
						&& 
							priklausoAibei ( simb, dalys_skaiciaus )
					) 
				||
					yra_zodis_po_skaitmens
			) {
				
				if ( pries_tai_buvo_skaitmuo || yra_zodis_po_skaitmens ) { 				//  ------
					
					if ( yra_zodis_po_skaitmens ) {
						
						vnt_po_skaiciu [ kiekis_skaiciu - 1 ] += simb;  
						
					} else {
				
						skaiciai [ kiekis_skaiciu - 1 ] += simb;
					}
					
				} else {
					
					skaiciai [ kiekis_skaiciu ] = simb;
					vnt_po_skaiciu [ kiekis_skaiciu ] = "";
					kiekis_skaiciu++;
				}
				
				if ( ! yra_zodis_po_skaitmens ) {
					
					pries_tai_buvo_skaitmuo = true;
					
				} else {
				
					if ( priklausoAibei ( simb, zodzio_pabaigos ) ) {
						
						yra_zodis_po_skaitmens = false;
					}
				}
				
			} else {
				
				if ( pries_tai_buvo_skaitmuo ) {
					
					if ( ! priklausoAibei ( simb, ne_zodzio_dalis ) ) {
				
						yra_zodis_po_skaitmens = true;
																	// skaiciai [ kiekis_skaiciu - 1 ] += simb;
						vnt_po_skaiciu [ kiekis_skaiciu - 1 ] += simb;
					}
				}
				pries_tai_buvo_skaitmuo = false;
			}
		}
		return kiekis_skaiciu;
	}	
	
}
