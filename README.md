# 7.1 // Generalização, Especialização e Polimorfismo // Imobiliária

Use este link do GitHub Classroom para ter sua cópia alterável deste repositório: <https://classroom.github.com/a/D4Y-cDmB>

Implementar respeitando os fundamentos de Orientação a Objetos.

**Tópicos desta atividade:** generalização, super classes, classes abstratas, especialização, classes concretas e polimorfismo.

---

Refatorar o projeto Imobiliária para generalização/especialização e polimorfismo. Considere a classe [App](src/App.java) e as instruções que verificam o funcionamento do sistema imobiliária, suas tarefas são:

0. **Entender**: entender as regras da imobiliária com base na leitura dos comentários e observação do seu funcionamento, rode o projeto, se possível usando um _breakpoint_ passo-a-passo,
1. **Generalizar**: criar uma hierarquia de classes de forma que a [Locação](src/model/imobiliaria/Locacao.java) não precise do atributo de `tipo` e que [Cobrança](src/model/imobiliaria/Cobranca.java) não tenha menção aos detalhes dos tipos de imóveis, tratando sempre pelo tipo abstrato/genérico,
2. **Testar**: converter os _prints_ do [App](src/App.java) para a forma de _Casos de Teste_ (assertivas),
3. **Refinar**: resolver os _warnings_ do _Check Style_, adicionar os JavaDocs e formatar o código conforme exigido,
4. **Tipar**: substituir as exceções de `IllegalArgumentException` e `IllegalStateException` por exceções personalizadas de acordo com o caso especial tratado (subtipar `RuntimeException` ou `Exception`), e
5. **Usar a `Bag`**: mover as classes [Bag](src/model/imobiliaria/Bag.java) e [FullBagException](src/model/imobiliaria/FullBagException.java) para o pacote [util](src/util/) e garantir que onde tenham coleções de objetos seja usada a classe [Bag](src/model/imobiliaria/Bag.java) (Bolsa) e não `ArrayList`, _arrays_ simples ou outras embutidas do Java.

