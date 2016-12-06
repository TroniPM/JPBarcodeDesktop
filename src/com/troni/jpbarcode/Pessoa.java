package segRedes20162;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Pessoa  implements Serializable{
	private String nome;
	private String sexo;
	private int idade;

	public Pessoa(String nome, String sexo, int idade) {

		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
	}
	
	public Pessoa(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", sexo=" + sexo + ", idade=" + idade
				+ "]";
	}

	public String convertToString() {
		try {
			String str;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			byte[] objeto = baos.toByteArray();
			str = Base64.encode(objeto);
			oos.close();
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Pessoa convertFromString(String str) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(str));
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Pessoa) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
