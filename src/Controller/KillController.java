package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void listaProcessos() {
		String osNome = os();
		String ListaWindows = "TASKLIST /FO TABLE";
		String ListaLinux = "ps -ef";
		
		if(osNome.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec(ListaWindows);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(osNome.contains("Linux")) {
			try {
				Process p = Runtime.getRuntime().exec(ListaLinux);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void mataPid(String param) {
		
		String osNome = os();
		String PidWindows = "TASKKILL /PID";
		String PidLinux = "kill -9";
					
		StringBuffer buffer = new StringBuffer();
		
		if(osNome.contains("Windows")){
			try {
				int num_pid = Integer.parseInt(param);
				buffer.append(PidWindows);
				buffer.append(" ");
				buffer.append(num_pid);
			}catch(NumberFormatException e){
				mataNome(param);
			}
			try {
				Runtime.getRuntime().exec(buffer.toString());
				System.out.println("Processo encerrado!");
			} catch (IOException e) {
				mataNome(param);
			}
		}
		
		if(osNome.contains("Linux")){
			try {
				int num_pid = Integer.parseInt(param);
				buffer.append(PidLinux);
				buffer.append(" ");
				buffer.append(num_pid);
			}catch(Exception e){
				mataNome(param);
			}
		}
	}
	
	public void mataNome(String param) {
		String osNome = os();
		String NomeWindows = "TASKKILL /IM";
		String NomeLinux = "pkill -f";
		
		StringBuffer buffer = new StringBuffer();
		
		if(osNome.contains("Windows")){
			String num_pid = param;
			buffer.append(NomeWindows);
			buffer.append(" ");
			buffer.append(num_pid);
		}
		
		if(osNome.contains("Linux")){
			String num_pid = param;
			buffer.append(NomeLinux);
			buffer.append(" ");
			buffer.append(num_pid);
		}
		try {
			Runtime.getRuntime().exec(buffer.toString());
			System.out.println("Processo encerrado!");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

}
