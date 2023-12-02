package localcomida.pikda.dominio.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @NotNull
    @ManyToOne(optional = false)
    private Cliente cliente;

    @Valid
    @OneToMany(mappedBy = "clave.pedido", cascade = CascadeType.REMOVE)
    private List<LineaPedido> lineas;

    @Column(nullable = false)
    private Double total;

    private boolean despachado;


    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public boolean isDespachado() {
        return despachado;
    }

    public void setDespachado(boolean despachado) {
        this.despachado = despachado;
    }


    public Pedido() {
        this(null, null, null, null, false);
    }

    public Pedido(Long numero, LocalDateTime fechaHora, Cliente cliente, Double total, boolean despachado) {
        this.numero = numero;
        this.fechaHora = fechaHora;
        this.cliente = cliente;
        this.total = total;
        this.despachado = despachado;

        lineas = new ArrayList<>();
    }


    @Entity
    @Table(name = "lineas_pedido")
    public static class LineaPedido {

        @EmbeddedId
        private ClaveLineaPedido clave;

        @NotNull
        @Min(1)
        @Column(nullable = false)
        private Integer cantidad;

        @NotNull
        @ManyToOne(optional = false)
        private Producto producto;

        @Column(nullable = false)
        private Double importe;


        public ClaveLineaPedido getClave() {
            return clave;
        }

        public void setClave(ClaveLineaPedido clave) {
            this.clave = clave;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public Producto getProducto() {
            return producto;
        }

        public void setProducto(Producto producto) {
            this.producto = producto;
        }

        public Double getImporte() {
            return importe;
        }

        public void setImporte(Double importe) {
            this.importe = importe;
        }


        public LineaPedido() {
            this(null, null, null, null);
        }

        public LineaPedido(Integer cantidad, Producto producto, Double importe) {
            this(null, cantidad, producto, importe);
        }

        public LineaPedido(ClaveLineaPedido clave, Integer cantidad, Producto producto, Double importe) {
            this.clave = clave;
            this.cantidad = cantidad;
            this.producto = producto;
            this.importe = importe;
        }

    }


    @Embeddable
    public static class ClaveLineaPedido implements Serializable {

        private Integer numeroLinea;

        @ManyToOne(optional = false)
        private Pedido pedido;


        public Integer getNumeroLinea() {
            return numeroLinea;
        }

        public void setNumeroLinea(Integer numeroLinea) {
            this.numeroLinea = numeroLinea;
        }

        public Pedido getPedido() {
            return pedido;
        }

        public void setPedido(Pedido pedido) {
            this.pedido = pedido;
        }


        public ClaveLineaPedido() {
            this(null, null);
        }

        public ClaveLineaPedido(Integer numeroLinea, Pedido pedido) {
            this.numeroLinea = numeroLinea;
            this.pedido = pedido;
        }

    }
}
