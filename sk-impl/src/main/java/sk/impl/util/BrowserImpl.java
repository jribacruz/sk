package sk.impl.util;

import java.awt.Desktop;
import java.net.URI;

import sk.api.util.Browser;
import sk.api.util.Colorize;

public class BrowserImpl implements Browser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void open(String url) {
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(url));
		} catch (Exception e) {
			System.out.println(Colorize.red("Erro ao abrir o navegador."));
		}
	}

}
