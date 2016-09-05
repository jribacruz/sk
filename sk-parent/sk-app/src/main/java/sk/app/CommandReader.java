package sk.app;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import jline.console.ConsoleReader;
import sk.api.Context;
import sk.api.command.CommandContext;
import sk.impl.command.CommandContextImpl;

public class CommandReader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Context context;

	@Inject
	private CommandCompleter commandCompleter;

	public CommandContext read() throws IOException {
		ConsoleReader skReader = new ConsoleReader();
		skReader.setHandleUserInterrupt(true);
		skReader.addCompleter(commandCompleter);
		String uuid = skReader.readLine(context.get("PROMPT_ID") + "> ");
		skReader.close();
		return new CommandContextImpl(StringUtils.trim(uuid));
	}

}
