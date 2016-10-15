#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package};

import java.io.IOException;


import sk.api.Application;
import sk.api.annotation.ApplicationConf;

/**
 * SK App!
 *
 */
@ApplicationConf(uuid = "${artifactId}", description = "${sk-app-description}")
public class App implements Application {

	@Override
	public void run() throws IOException {
		// TODO Application Code...
	}

}
