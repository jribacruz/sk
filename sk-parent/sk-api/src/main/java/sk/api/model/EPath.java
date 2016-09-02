package sk.api.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.SortedSet;

import sk.api.reader.Selectable;

/**
 * Representa um caminho no sistema de arquivos.
 * 
 * @author jcruz
 *
 */
public interface EPath extends Selectable<EPath> {

	/**
	 * Objecto do tipo Path.
	 * 
	 * @return java.nio.file.Path.Path do java.
	 */
	Path getPath();

	/**
	 * 
	 * @return
	 */
	Optional<EPath> getParent();

	/**
	 * Retorna o caminho irmão do nó atual com o nome correspondente
	 * 
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	Optional<EPath> getSibling(String name) throws IOException;

	/**
	 * 
	 * Retorna os caminho irmãos do nó atual.
	 * 
	 * @return
	 * @throws IOException
	 */
	SortedSet<EPath> getSiblings() throws IOException;

	@Override
	default int compareTo(EPath o) {
		return getPath().compareTo(o.getPath());
	}

	@Override
	default String getConsoleLabel() {
		return getPath().toString();
	}

}
