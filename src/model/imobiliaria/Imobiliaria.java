package model.imobiliaria;

public class Imobiliaria {

  private String nome;
  private Bag locacoes = new Bag(1000);
  private Bag cobrancas = new Bag(1000000);

  public Imobiliaria(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    String str = "Imobiliaria " + nome + "\n";
    str += "Locações: \n";
    for (Object o : this.getLocacoesAtivas()) {
      str += o.toString() + "\n";
    }
    str += "Cobranças: \n";
    for (Object o : this.cobrancas.toArray()) {
      str += o.toString() + "\n";
    }
    return str;
  }

  public Object[] getLocacoesAtivas() {
    Bag ativas = new Bag(locacoes.count());
    for (Object o : locacoes.toArray()) {
      Locacao l = (Locacao) o;
      if (l.getMeses() > 0) {
        ativas.add(l);
      }
    }
    return ativas.toArray();
  }

  public void contratar(Locacao locacao) {
    if (locacoes.has(locacao)) throw new IllegalStateException("locacao deve ser renovada");
    if (locacao.getTipo().equals("Residencial") || locacao.getTipo().equals("Apartamento")) {
      locacao.setPrazo(12); // mínimo não comercial
    } else if (locacao.getTipo().equals("Comercial")) {
      locacao.setPrazo(6); // mínimo comercial
    }
    locacoes.add(locacao);
  }


  public void renovar(Locacao locacao, double reajuste, double extra, int prazo) {
    if (locacao.getMeses() > 0) {
      throw new IllegalStateException("locacao em andamento");
    }
    if (locacao.getTipo().equals("Residencial") || locacao.getTipo().equals("Apartamento")) {
      if (prazo < 12) {
        throw new IllegalArgumentException(
          "prazo minimo de 12 meses para residencia e apartamento");
      }
      locacao.setPrazo(prazo);
      locacao.renovar(reajuste, extra);
    } else if (locacao.getTipo().equals("Comercial")) {
      if (prazo < 6) {
        throw new IllegalArgumentException("prazo minimo de 6 meses para comercial");
      }
      locacao.setPrazo(prazo);
      locacao.renovar(reajuste, extra);
    }
  }

  public Cobranca novaCobranca(int ano, int mes) {
    Cobranca cobranca = new Cobranca(this, ano, mes);
    cobrancas.add(cobranca);
    return cobranca;
  }

}
