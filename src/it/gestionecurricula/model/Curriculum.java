package it.gestionecurricula.model;

import java.util.Date;
import java.util.List;

public class Curriculum {
	private Long id;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String telefono;
	private String email;
	private List<Esperienza> esperienze;
	
	public Curriculum() {}
	public Curriculum(String nome, String cognome, Date dataNascita, String telefono, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.telefono = telefono;
		this.email = email;
	}
	public Curriculum(String nome, String cognome, Date dataNascita, String telefono, String email, List<Esperienza> esperienze) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.telefono = telefono;
		this.email = email;
		this.esperienze = esperienze;
	}
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getEmail() {
		return email;
	}
	public List<Esperienza> getEsperienze() {
		return esperienze;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEsperienze(List<Esperienza> esperienze) {
		this.esperienze = esperienze;
	}
	
	
	@Override
	public String toString() {
		return "Curriculum [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", telefono=" + telefono + ", email=" + email + ", esperienze=" + esperienze + "]";
	}
	
	
}
