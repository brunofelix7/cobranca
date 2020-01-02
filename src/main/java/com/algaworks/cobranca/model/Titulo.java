package com.algaworks.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.algaworks.cobranca.enumeration.TituloStatusEnum;

import javax.persistence.Id;

@Entity(name = "titulos")
public class Titulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long codigo;

	@NotEmpty(message = "Descrição é obrigatória")
	@Size(message = "A descrição não pode conter mais de 60 caracteres", max = 60)
	@Column(name = "descricao")
	private String descricao;

	@NotNull(message = "Date de vencimento é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy") // Formata a data
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento")
	private Date dataVencimento;

	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(message = "Valor não pode ser menor que 0,01", value = "0.01")
	@DecimalMax(message = "Valor não pode ser maior que 9.999.999,99", value = "9999999.99")
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "valor")
	private BigDecimal valor;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TituloStatusEnum status;

	public Titulo() {

	}

	public Titulo(Long codigo, String descricao, Date dataVencimento, BigDecimal valor, TituloStatusEnum status) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.status = status;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TituloStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TituloStatusEnum status) {
		this.status = status;
	}

	public boolean isPendente() {
		return TituloStatusEnum.PENDENTE.equals(this.status);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Titulo [codigo=" + codigo + ", descricao=" + descricao + ", dataVencimento=" + dataVencimento
				+ ", valor=" + valor + ", status=" + status + "]";
	}

}
