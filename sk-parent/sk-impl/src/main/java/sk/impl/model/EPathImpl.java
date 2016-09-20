package sk.impl.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.SortedSet;

import sk.api.model.EPath;

public class EPathImpl implements EPath {

	private Path path;

	public EPathImpl(Path path) {
		super();
		this.path = path;
	}

	@Override
	public Path getPath() {
		return this.path;
	}

	@Override
	public Optional<EPath> getParent() {
		// return this.path.getParent() != null ?
		// Optional.of(fs.getEpath(this.path.getParent())) : Optional.empty();
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<EPath> getSibling(String name) throws IOException {
		if (path.getParent() != null) {
			for (EPath epath : getSiblings()) {
				if (epath.getPath().endsWith(name)) {
					return Optional.of(epath);
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public SortedSet<EPath> getSiblings() throws IOException {
		throw new UnsupportedOperationException();
		/*
		 * if (this.path.getParent() != null) { //@formatter:off return
		 * Files.walk(this.path.getParent(), 1) .filter(p ->
		 * p.toFile().isDirectory() && p.compareTo(this.path.getParent()) != 0
		 * && p.compareTo(this.path) != 0 ) .map(p -> fs.getEpath(p))
		 * .collect(Collectors.toCollection(TreeSet::new)); //@formatter:on }
		 * return new TreeSet<>();
		 */
	}

	@Override
	public String toString() {
		return "EPathImpl [path=" + path + "]";
	}

}
