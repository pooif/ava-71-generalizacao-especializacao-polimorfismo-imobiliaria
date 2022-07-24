package model.imobiliaria;

public class Locacao {

  private final String endereco;
  private final int quartos, garagem;
  private double valor, extra;
  private int meses, prazo;
  private char tipo; // [C]omercial, [R]esidencial, [A]partamento
  private double ultimoValorPago;

  public Locacao(String endereco, int quartos, int garagem, double valor, double extra, char tipo) {
    if (garagem < 0) {
      throw new IllegalArgumentException();
    }
    if (quartos < 0) {
      throw new IllegalArgumentException();
    }
    if (valor < 0) {
      throw new IllegalArgumentException();
    }
    if (extra < 0) {
      throw new IllegalArgumentException();
    }
    if (tipo != 'A' && tipo != 'C' && tipo != 'R') {
      throw new IllegalArgumentException();
    }
    this.endereco = endereco;
    this.quartos = quartos;
    this.garagem = garagem;
    this.valor = valor;
    this.extra = extra;
    this.tipo = tipo;
  }

  public String getTipo() {
    switch (tipo) {
      case 'A': return "Apartamento";
      case 'C': return "Comercial";
      case 'R': return "Residencial";
    }
    throw new IllegalStateException();
  }

  public void renovar(double percentual) {
    if (tipo == 'C') {
      this.renovar(6, percentual);
    } else {
      this.renovar(12, percentual);
    }
  }

  public void renovar(double percentual, double extra) {
    if (tipo == 'C') this.renovar(6, percentual, extra);
    else this.renovar(12, percentual, extra);
  }

  public void renovar(int prazo, double percentual) {
    setPrazo(prazo);
    this.valor += percentual / 100.0 * valor;
  }

  public void renovar(int prazo, double percentual, double extra) {
    setPrazo(prazo);
    this.valor += percentual / 100.0 * valor;
    this.extra = extra;
  }

  public double pagar() {
    if (this.meses <= 0) return 0;
    double valorPago = this.getValorTotal();
    this.meses = this.meses - 1;
    ultimoValorPago = valorPago;
    return valorPago;
  }

  public double pagar(int atraso) {
    if (this.getMeses() <= 0) {
      return 0;
    }

    double valorPago = this.getValorTotal();

    if (this.tipo == 'C') {
      valorPago += 0.05 * this.getValorTotal()
          + (atraso * 0.01 * this.getValorTotal());
    }

    if (this.tipo == 'A') {
      valorPago += 0.03 * this.getValor() + (atraso * 0.01 * this.getValor());
      valorPago += 0.10 * this.getValorCondominio();
    }

    if (this.tipo == 'R') {
      valorPago += 0.03 * this.getValor() + (atraso * 0.01 * this.getValor());
    }

    this.meses = this.meses - 1;
    ultimoValorPago = valorPago;
    return valorPago;
  }

  public void setPrazo(int prazo) {
    if (tipo == 'C') {
      if (prazo < 6) {
        throw new IllegalArgumentException("prazo minimo 6 meses para comercial");
      }
    } else if (prazo < 12) {
      throw new IllegalArgumentException("prazo minimo 12 meses para residencial");
    }
    this.prazo = prazo;
    this.meses = prazo;
  }

  public int getMeses() {
    return this.meses;
  }

  public double getValor() {
    return this.valor;
  }

  public double getValorCondominio() {
    return this.extra;
  }

  public double getUltimoValorPago() {
    return ultimoValorPago;
  }

  public double getTaxaComercial() {
    return this.extra;
  }

  public double getValorTotal() {
    if (this.tipo == 'C') {
      // locações comerciais pagam a taxa
      // no primeiro pagamento
      if (this.getMeses() == prazo) {
        return this.getValor() + this.getTaxaComercial();
      }
    }
    // locações de apartamentos cobram condomínio
    if (this.tipo == 'A') return this.getValor() + this.getValorCondominio();
    return this.getValor();
  }

  public String getEndereco() {
    return endereco;
  }

  public double getExtra() {
    return extra;
  }

  public int getGaragem() {
    return garagem;
  }

  public int getQuartos() {
    return quartos;
  }

  @Override
  public String toString() {
    return this.getTipo() + " " + this.getValorTotal();
  }

}
