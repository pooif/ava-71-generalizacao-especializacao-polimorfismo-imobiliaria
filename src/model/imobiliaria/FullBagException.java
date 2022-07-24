package model.imobiliaria;

class FullBagException extends RuntimeException {

  FullBagException(int cap) {
    super("Esta bolsa alcançou a capacidade máxima de " + cap + " objetos");
  }

}
