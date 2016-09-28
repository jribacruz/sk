package sk.impl.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.inject.Inject;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import sk.api.Context;
import sk.api.enums.MavenFolder;
import sk.api.model.EJavaPackage;
import sk.api.model.EJavaProject;
import sk.api.util.Colorize;
import sk.api.util.FS;

public class FSImpl implements FS {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EJavaProject eJavaProject;

	private Context context;

	@Inject
	public FSImpl(EJavaProject eJavaProject, Context context) {
		super();
		this.eJavaProject = eJavaProject;
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.util.FS#createFile(java.lang.String, sk.api.enums.MavenFolder)
	 */
	@Override
	public void createFile(String content, MavenFolder mf) {
		String finalPath = this.normalize(eJavaProject.getPathName()).concat(mf.getPath());
		this.write(content, finalPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.util.FS#createFile(java.lang.String, sk.api.enums.MavenFolder, java.lang.String)
	 */
	@Override
	public void createFile(String content, MavenFolder mf, String path) {
		String finalPath = this.normalize(eJavaProject.getPathName().concat(mf.getPath()).concat(context.replace(path)));
		this.write(content, finalPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.util.FS#createFile(java.lang.String, sk.api.enums.MavenFolder, sk.api.model.EJavaPackage, java.lang.String)
	 */
	@Override
	public void createFile(String content, MavenFolder mf, EJavaPackage eJavaPackage, String path) {
		String finalPath = this.normalize(
				eJavaProject.getPathName().concat(mf.getPath()).concat(eJavaPackage.getPathName()).concat(context.replace(path)));
		this.write(content, finalPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.util.FS#mkdir(sk.api.enums.MavenFolder, sk.api.model.EJavaPackage, java.lang.String)
	 */
	@Override
	public void mkdir(MavenFolder mf, EJavaPackage eJavaPackage, String path) {
		String finalPath = this.normalize(eJavaPackage.getPathName()).concat(context.replace(path));
		this.mkdir(mf, finalPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sk.api.util.FS#mkdir(sk.api.enums.MavenFolder, java.lang.String)
	 */
	@Override
	public void mkdir(MavenFolder mf, String path) {
		String finalPath = this.normalize(eJavaProject.getPathName().concat(mf.getPath()).concat(context.replace(path)));
		this.mkdir(finalPath);
	}

	private void mkdir(String finalPath) {
		if (Files.notExists(Paths.get(finalPath))) {
			try {
				Files.createDirectories(Paths.get(finalPath));
				System.out.println(Colorize.blue("[CREATE]"));
				System.out.println(Colorize.blue("\t> type: ") + "dir");
				System.out.println(Colorize.blue("\t> path: ") + finalPath);
			} catch (IOException e) {
				System.out.println(Colorize.yellow("[SKIP]"));
				System.out.println(Colorize.yellow("\t> path:") + finalPath);
				System.out.println(Colorize.yellow("\t> cause:") + e.getMessage());
			}
			return;
		}
		System.out.println(Colorize.yellow("[SKIP]"));
		System.out.println(Colorize.yellow("\t> path:") + finalPath);
		System.out.println(Colorize.yellow("\t> cause:") + "Diretório já existe.");
	}

	private void write(String content, String finalPath) {
		try {
			this.createParentDirsIfNotExists(finalPath);
			if (Files.notExists(Paths.get(finalPath))) {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(finalPath), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
				writer.write(content);
				writer.flush();
				writer.close();
				System.out.println(Colorize.blue("[CREATE]"));
				System.out.println(Colorize.blue("\t> type: ") + "file");
				System.out.println(Colorize.blue("\t> name: ") + FilenameUtils.getName(finalPath));
				System.out.println(Colorize.blue("\t> path: ") + FilenameUtils.getFullPath(finalPath));
				return;
			}
		} catch (FileExistsException e) {
			System.out.println(Colorize.yellow("[SKIP]"));
			System.out.println(Colorize.yellow("\t> path:") + finalPath);
			System.out.println(Colorize.yellow("\t> cause:") + e.getMessage());
		} catch (IOException e) {
			System.out.println(Colorize.yellow("[SKIP]"));
			System.out.println(Colorize.yellow("\t> path:") + finalPath);
			System.out.println(Colorize.yellow("\t> cause:") + e.getMessage());
		}
		System.out.println(Colorize.yellow("[SKIP]"));
		System.out.println(Colorize.yellow("\t> path:") + finalPath);
		System.out.println(Colorize.yellow("\t> cause:") + "Arquivo já existe.");
	}

	private void createParentDirsIfNotExists(String path) {
		if (StringUtils.isNotBlank(FilenameUtils.getExtension(path))) {
			String finalPath = FilenameUtils.getFullPath(path);
			this.mkdir(finalPath);
		}
	}

	private String normalize(String path) {
		return FilenameUtils.normalize(path);
	}

	@Override
	public void appendFile(String content, MavenFolder mf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void appendFile(String content, MavenFolder mf, String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void appendFile(String content, MavenFolder mf, EJavaPackage eJavaPackage, String path) {
		// TODO Auto-generated method stub

	}

}
