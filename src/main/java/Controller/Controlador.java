package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controlador {
	
	private final static Logger logger = LoggerFactory.getLogger(Controlador.class);
	
	@RequestMapping("/")
	public String Main(Model model) {
		logger.info("prueba1");
		String datos= null;
		String ruta = "src/main/resources/static/noticias.txt";
		ArrayList<String> news = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			
			while((datos = br.readLine()) != null) {
				String[] split = datos.split("@@");
				for(String noticia : split) {
					news.add(noticia);
				}
			}
			br.close();
			fr.close();
			for(int i = 0; i<news.size(); i++) {
				model.addAttribute("noticia" + i, news.get(i));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("No se ha completado la lectura del archivo:" + ruta);
		
	}
		return "index";
}
}