package com.entidades;

public class Inventario {

	
	private int id;
	private String codigoProducto;
	private int cantidadProducto;
	private String areaAlmacen;
	private String seccionAlmacen;
	
	//private String fechaIngreso;
	
	private String fechaUltimoIngreso;
	private String fechaSalida;
	
	private int cantidadRecibida;
	private int cantidadSalida;
	
	private String nota;
	private String cliente;
	private String usuario;
	private String rol;
	
	
	
	public Inventario() {
		super();
	}



	public Inventario(int id, String codigoProducto, int cantidadProducto, String areaAlmacen, String seccionAlmacen,
			String fechaUltimoIngreso, String fechaSalida, String nota) {//para el inventario
		super();
		this.id = id;
		this.codigoProducto = codigoProducto;
		this.cantidadProducto = cantidadProducto;
		this.areaAlmacen = areaAlmacen;
		this.seccionAlmacen = seccionAlmacen;
		this.fechaUltimoIngreso = fechaUltimoIngreso;
		this.fechaSalida = fechaSalida;
		this.nota = nota;
	}



	public Inventario(int id, String codigoProducto, int cantidadRecibida, String areaAlmacen, String seccionAlmacen,
			String fechaUltimoIngreso, String nota, String usuario, String rol) {//para el respaldo de ingreso
		super();
		this.id = id;
		this.codigoProducto = codigoProducto;
		this.cantidadRecibida = cantidadRecibida;
		this.areaAlmacen = areaAlmacen;
		this.seccionAlmacen = seccionAlmacen;
		this.fechaUltimoIngreso = fechaUltimoIngreso;
		this.nota = nota;
		this.usuario = usuario;
		this.rol = rol;
	}



	public Inventario(int id, String codigoProducto, int cantidadSalida, String areaAlmacen, String seccionAlmacen,
			String fechaSalida, String nota, String cliente, String usuario, String rol) {//para la respaldo de la salida
		super();
		this.id = id;
		this.codigoProducto = codigoProducto;
		this.cantidadSalida = cantidadSalida;
		this.areaAlmacen = areaAlmacen;
		this.seccionAlmacen = seccionAlmacen;
		this.fechaSalida = fechaSalida;
		this.nota = nota;
		this.cliente = cliente;
		this.usuario = usuario;
		this.rol = rol;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCodigoProducto() {
		return codigoProducto;
	}



	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}



	public int getCantidadProducto() {
		return cantidadProducto;
	}



	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}



	public String getAreaAlmacen() {
		return areaAlmacen;
	}



	public void setAreaAlmacen(String areaAlmacen) {
		this.areaAlmacen = areaAlmacen;
	}



	public String getSeccionAlmacen() {
		return seccionAlmacen;
	}



	public void setSeccionAlmacen(String seccionAlmacen) {
		this.seccionAlmacen = seccionAlmacen;
	}



	public String getFechaUltimoIngreso() {
		return fechaUltimoIngreso;
	}



	public void setFechaUltimoIngreso(String fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}



	public String getFechaSalida() {
		return fechaSalida;
	}



	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}



	public int getCantidadRecibida() {
		return cantidadRecibida;
	}



	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}



	public int getCantidadSalida() {
		return cantidadSalida;
	}



	public void setCantidadSalida(int cantidadSalida) {
		this.cantidadSalida = cantidadSalida;
	}



	public String getNota() {
		return nota;
	}



	public void setNota(String nota) {
		this.nota = nota;
	}



	public String getCliente() {
		return cliente;
	}



	public void setCliente(String cliente) {
		this.cliente = cliente;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getRol() {
		return rol;
	}



	public void setRol(String rol) {
		this.rol = rol;
	}



	@Override
	public String toString() {
		return "Inventario [id=" + id + ", codigoProducto=" + codigoProducto + ", cantidadProducto=" + cantidadProducto
				+ ", areaAlmacen=" + areaAlmacen + ", seccionAlmacen=" + seccionAlmacen + ", fechaUltimoIngreso="
				+ fechaUltimoIngreso + ", fechaSalida=" + fechaSalida + ", cantidadRecibida=" + cantidadRecibida
				+ ", cantidadSalida=" + cantidadSalida + ", nota=" + nota + ", cliente=" + cliente + ", usuario="
				+ usuario + ", rol=" + rol + "]";
	}



	




	

	
	
	
	
	
	
	
	
	
}
