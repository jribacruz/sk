package sk.api.reader;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import sk.api.Task;

/**
 * Leitor de opções do console.
 * 
 * @author jcruz
 *
 */
public interface Selector extends Serializable {

	/**
	 * Seleciona uma opção de uma lista de selecionáveis.
	 * 
	 * @param prompt
	 * @param selectableOptions
	 *            Lista de selecionáveis.
	 * @return Opção selecionada.
	 * @throws IOException
	 */
	<X, T extends Selectable<X>> T selectOne(String prompt, List<T> selectableOptions) throws IOException;

	/**
	 * 
	 * @param prompt
	 * @param contextKey
	 * @param selectableOptions
	 * @throws IOException
	 */
	<X, T extends Selectable<X>> void selectOne(String prompt, String contextKey, List<T> selectableOptions) throws IOException;

	/**
	 * Seleciona uma opção de uma lista de selecionáveis.
	 * 
	 * @param prompt
	 * @param selectableOptions
	 *            Lista de selecionáveis.
	 * @return Opção selecionada.
	 * @throws IOException
	 */
	<X, T extends Selectable<X>> T selectOne(String prompt, Iterable<T> selectableOptions) throws IOException;

	/**
	 * 
	 * @param prompt
	 * @param cotextKey
	 * @param selectableOptions
	 * @throws IOException
	 */
	<X, T extends Selectable<X>> void selectOne(String prompt, String cotextKey, Iterable<T> selectableOptions) throws IOException;

	/**
	 * Seleciona uma lista de opções de uma lista de selecionáveis.
	 * 
	 * @param prompt
	 * @param selectableOptions
	 *            Lista de selecionáveis.
	 * @return Lista de opções selecionadas.
	 * @throws IOException
	 */
	<X, T extends Selectable<X>> List<T> selectMany(String prompt, List<T> selectableOptions) throws IOException;

	/**
	 * 
	 * @param prompt
	 * @param contextKey
	 * @param selectableOptions
	 * @throws IOException
	 */
	<X, T extends Selectable<X>> void selectMany(String prompt, String contextKey, List<T> selectableOptions) throws IOException;

	/**
	 * Seleciona uma lista de opções de uma lista de selecionáveis.
	 * 
	 * @param selectableOptions
	 *            Lista de selecionáveis.
	 * @return Lista de opções selecionadas.
	 * @throws IOException
	 */
	<X, T extends Selectable<X>> List<T> selectMany(String prompt, Iterable<T> selectableOptions) throws IOException;

	/**
	 * 
	 * @param prompt
	 * @param contextKey
	 * @param selectableOptions
	 * @throws IOException
	 */
	<X, T extends Selectable<X>> void selectMany(String prompt, String contextKey, Iterable<T> selectableOptions) throws IOException;

	/**
	 * Seleciona e executa uma opção de uma lista de tarefas.
	 * 
	 * @param prompt
	 * @param taskOptions
	 *            Lista de tarefas.
	 * @throws IOException
	 */
	<T extends Task> void selectAndExecuteOne(String prompt, List<T> taskOptions) throws IOException;

	/**
	 * Seleciona e executa uma opção de uma lista de tarefas.
	 * 
	 * @param prompt
	 * @param taskOptions
	 *            Lista de tarefas.
	 * @throws IOException
	 */
	<T extends Task> void selectAndExecuteOne(String prompt, Iterable<T> taskOptions) throws IOException;

	/**
	 * Seleciona e executa uma lista de tarefas.
	 * 
	 * @param prompt
	 * @param taskOptions
	 *            Lista de tarefas.
	 * @throws IOException
	 */
	<T extends Task> void selectAndExecuteMany(String prompt, List<T> taskOptions) throws IOException;

	/**
	 * Seleciona e executa uma lista de tarefas.
	 * 
	 * @param prompt
	 * @param taskOptions
	 *            Lista de tarefas.
	 * @throws IOException
	 */
	<T extends Task> void selectAndExecuteMany(String prompt, Iterable<T> taskOptions) throws IOException;

	/**
	 * Confirma a execução de uma tarefa.
	 * 
	 * @param prompt
	 * @param taskOption
	 *            Opção executável.
	 * @throws IOException
	 */
	<T extends Task> void confirm(String prompt, T taskOption) throws IOException;

}
