package nextspring;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class NextspringApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext( "file:src/beans.xml" );
		SkaiciaiTeksteTvarkymas stt = (SkaiciaiTeksteTvarkymas) context.getBean( "mainspring" );
		stt.ieskotiSkaiciu();
		stt.parodytiSkaicius();
		stt.ieskotiVienetu();
		stt.parodytiVienetus();	
		stt.surasytiVntIrAnti();
	}

}
