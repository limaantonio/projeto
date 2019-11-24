package model.entities;

import java.awt.Checkbox;
import java.io.Serializable;

import javafx.scene.control.CheckBox;

public class Associacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String endereco;
	private String distrito;
	
	private Checkbox selected;
	
	public Associacao() {
	}

	public Associacao(Integer id, String name, String endereco, String distrito) {
		this.id = id;
		this.name = name;
		this.endereco = endereco;
		this.distrito = distrito;
		this.selected = new Checkbox();
		
	}
	

	public Checkbox getSelected() {
		return selected;
	}

	public void setSelected(Checkbox selected) {
		this.selected = selected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	@Override
	public String toString() {
		return "Associacao [id=" + id + ", name=" + name + ", endereco=" + endereco + ", distrito=" + distrito + "]";
	}

	

	
	
	
	
}
