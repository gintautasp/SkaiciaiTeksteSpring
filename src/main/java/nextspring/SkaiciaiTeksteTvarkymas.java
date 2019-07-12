package nextspring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class SkaiciaiTeksteTvarkymas {
	
	private String eilute_is_failo;
	
	private String[] skaiciai = new String [ 1000 ];
	private String[] vnt_po_skaiciu = new String [ 1000 ];
	private int kiekis_skaiciu = 0; 	
	
	private ArrayList<String> vnt; 
	private ArrayList<String> anti_vnt;
	private SkaiciaiEiluteje se;
	private SkaitymasIsFailo sf;
	private RasymasFaile rf;
	
	private String vardas_teksto_txt;
	private String vardas_failo_vnt;
	private String vardas_failo_anti_vnt;
	
	public SkaiciaiTeksteTvarkymas() {
	}
	
	public SkaiciaiTeksteTvarkymas(SkaiciaiEiluteje se, SkaitymasIsFailo sf, RasymasFaile rf, String vardas_teksto_txt, String vardas_failo_vnt, String vardas_failo_anti_vnt) {

		
		this.vardas_teksto_txt = vardas_teksto_txt;
		this.vardas_failo_vnt = vardas_failo_vnt;
		this.vardas_failo_anti_vnt = vardas_failo_anti_vnt;
		
		sf.setVardas_failo(vardas_failo_vnt);
		vnt = sf.iMasyva();
		sf.setVardas_failo(vardas_failo_anti_vnt);
		anti_vnt = sf.iMasyva();
			
		this.rf = rf;
		this.sf = sf;
		this.se = se;
	}	
	
	public void ieskotiSkaiciu() {
		
		
		sf.setVardas_failo(this.vardas_teksto_txt);
		sf.pradeti();
		
		System.out.println ( "Duomenų failas: "); 
		
		while ( sf.nuskaitytaEilute() ) {
			
			eilute_is_failo = sf.paimtiEilute();
			
			System.out.println ( eilute_is_failo );
			
			kiekis_skaiciu = se.ieskotiSkaiciu( eilute_is_failo, skaiciai, vnt_po_skaiciu, kiekis_skaiciu );
			
			sf.skaitytiEilute();
		}
	}
	
	public void ieskotiVienetu() {
		
		BufferedReader in_kb = new BufferedReader( new InputStreamReader ( System.in ) );		

		for ( int i = 0; i < kiekis_skaiciu; i++ ) {
			
			if ( 
					( vnt.indexOf ( vnt_po_skaiciu [ i ].trim() )  == -1 ) 
				&&  
					(  ! vnt_po_skaiciu [ i ].trim().equals( "" ) ) 
				&&
					( anti_vnt.indexOf ( vnt_po_skaiciu [ i ].trim() ) == -1 )
			) {
			
				System.out.println ( "  -- ??? " + skaiciai [ i ] + " " + vnt_po_skaiciu [ i ] + ": "  );
				System.out.println ( "įtraukti į mat. vnt.? " );
				
				String ats = "";
				
				try {
				
					ats = in_kb.readLine();
					
				} catch ( Exception e ) {
					
					System.err.format ( "IOException: %s%n", e );
				}
				
				if ( ats.equals( "" ) ) {
					
					vnt.add ( vnt_po_skaiciu [ i ].trim() );
					
				} else {
				
					anti_vnt.add ( vnt_po_skaiciu [ i ].trim() );
				}
			}
		}
	}	

	public void parodytiSkaicius() {
		
		for ( int i = 0; i < kiekis_skaiciu; i++ ) {
			
			System.out.println ( " " + skaiciai [ i ] + " " + vnt_po_skaiciu [ i ] );
		}
	}
	
	public void parodytiVienetus() {
		
		System.out.println ( "\nvienetai: \n" );
		
		for ( String value : vnt ) { 
			
			System.out.print( value ); 
			System.out.print( " " ); 
		}
	}
	
	public void surasytiVntIrAnti() {
		
		rf.setVardas_failo( vardas_failo_vnt );
		rf.iEilutes( vnt );
		
		rf.setVardas_failo( vardas_failo_anti_vnt );
		rf.iEilutes( anti_vnt );
	}	
}
