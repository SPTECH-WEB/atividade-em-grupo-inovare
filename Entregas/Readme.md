# Sistema de Entregas

Este é um sistema de gerenciamento de pedidos e cálculo de frete, desenvolvido com **Spring Boot**, seguindo os princípios de design de software como o **Padrão de Observador** e o **Padrão de Estratégia**. Abaixo estão as instruções de uso e as explicações sobre os padrões implementados.

---

## **Instruções de Uso**

### 1. **Pré-requisitos**
- **Java 21** ou superior.
- **Maven 3.9+** para gerenciar dependências.
- Banco de dados configurado no arquivo `application.properties` (exemplo: H2, PostgreSQL, MySQL).
- Ferramenta para testes de API, como Postman, cURL ou qualquer HTTP client.
- **Swagger UI** habilitado (para documentação da API).

### 2. **Iniciando o Projeto**

1. Clone este repositório e navegue até o diretório do projeto:
```shell script
git clone https://github.com/SPTECH-WEB/atividade-em-grupo-inovare.git
   cd entregas
```

2. Compile e inicie o projeto utilizando **Maven**:
```shell script
mvn spring-boot:run
```

3. O serviço estará disponível no endereço padrão:
```
http://localhost:8080
```

4. Para acessar a documentação da API e testar os endpoints diretamente no navegador, use o Swagger UI:
```
http://localhost:8080/swagger-ui/index.html
```

---

## **Endpoints Disponíveis**

### **1. Calcular o Frete**
Calcula o valor do frete com base no peso do produto e no tipo de entrega.

- **URL:** `/pedidos/calcular-frete`
- **Método:** `GET`
- **Parâmetros:**
    - `peso` (obrigatório) - Peso em quilos (double).
    - `tipoFrete` (obrigatório) - Tipo da entrega (`economico`, `expresso`, `gratis`, `transportadora`).

- **Exemplo de Requisição:**
```
GET /pedidos/calcular-frete?peso=10.5&tipoFrete=expresso
```

- **Exemplo de Resposta (200 OK):**
```json
73.5
```

---

### **2. Salvar um Pedido**
Adiciona um novo pedido ao banco de dados e notifica os observadores.

- **URL:** `/pedidos`
- **Método:** `POST`
- **Corpo da Requisição (JSON):**
    - `cliente` (String): Nome do cliente.
    - `produto` (String): Nome do produto.
    - `peso` (double): Peso do produto.
    - `tipoEntrega` (String): Tipo do frete (`economico`, `expresso`, `gratis`, `transportadora`).

- **Exemplo de Requisição:**
```json
{
    "cliente": "João Silva",
    "produto": "Notebook",
    "peso": 2.5,
    "tipoEntrega": "economico"
  }
```

- **Exemplo de Resposta (200 OK):**
```json
{
    "id": 1,
    "cliente": "João Silva",
    "produto": "Notebook",
    "peso": 2.5,
    "tipoEntrega": "economico"
  }
```

---

### **3. Buscar Pedido pelo ID**
Busca os detalhes de um pedido cadastrado através do seu ID.

- **URL:** `/pedidos/{id}`
- **Método:** `GET`
- **Parâmetros:**
    - `id` (Path Variable - Long): Identificador do pedido.

- **Exemplo de Requisição:**
```
GET /pedidos/1
```

- **Exemplo de Resposta (200 OK):**
```json
{
    "id": 1,
    "cliente": "João Silva",
    "produto": "Notebook",
    "peso": 2.5,
    "tipoEntrega": "economico"
  }
```

---

## **Integração com Swagger**

O **Swagger UI** está configurado para facilitar a documentação e o teste dos endpoints diretamente no navegador.

### Como acessar:
- Acesse via:
```
http://localhost:8080/swagger-ui/index.html
```

No Swagger serão exibidos todos os endpoints documentados do sistema, com a possibilidade de testar cada um deles.

---

## **Padrões Utilizados**

### 1. **Padrão Strategy**
Utilizado para calcular o valor do frete com base em diferentes estratégias (econômico, expresso, grátis ou transportadora). Este padrão facilita a adição de novas formas de cálculo sem alterar as implementações existentes.

- **Interface:**
```java
public interface FreteStrategy {
        double calcularFrete(double peso);
    }
```

- **Implementações:**
    - `FreteEconomico`: Calcula frete a um custo mais baixo.
    - `FreteExpresso`: Calcula frete a um custo mais alto.
    - `FreteGratis`: Retorna custo zero.
    - `TransportadoraAdapter`: Usa um adaptador para cálculo com transportadora externa.

A escolha da estratégia no serviço de pedidos:
```java
switch (tipoFrete.toLowerCase()) {
    case "economico":
        freteStrategy = new FreteEconomico();
        break;
    case "expresso":
        freteStrategy = new FreteExpresso();
        break;
    case "gratis":
        freteStrategy = new FreteGratis();
        break;
    case "transportadora":
        freteStrategy = new TransportadoraAdapter();
        break;
    default:
        throw new IllegalArgumentException("Tipo de frete inválido");
}
return freteStrategy.calcularFrete(peso);
```

---

### 2. **Padrão Observer**
Notifica diferentes operações sempre que um pedido é salvo (ex.: e-mail, log, notificações).

- **Interface:**
```java
public interface PedidoObserver {
        void notificar(Pedido pedido);
    }
```

- **Implementações:**
    - `EmailObserver`: Envia um e-mail para o cliente.
    - `LogObserver`: Registra um log da operação.
    - `NotificacaoObserver`: Notifica o cliente.

Código para notificar os observadores:
```java
public void notificarTodos(Pedido pedido) {
    for (PedidoObserver observer : pedidoObservers) {
        observer.notificar(pedido);
    }
}
```

---

### 3. **Adapter**
Padroniza o cálculo de frete com serviços de transportadoras externas. Exemplo:
```java
@Override
public double calcularFrete(double peso) {
    double taxaFixa = 12.5;
    double frete = peso * 3 + taxaFixa;
    System.out.println("[Adapter] Frete calculado com transportadora externa: R$" + frete);
    return frete;
}
```

---

## **Testando o Sistema**

1. Suba a aplicação seguindo os passos em **2. Iniciando o Projeto**.
2. Use o **Swagger UI** para interagir com os endpoints.
3. Teste os serviços conforme descrito em **Endpoints Disponíveis**.

---

## **Tecnologias Utilizadas**

- **Java 21**
- **Spring Boot 3**
- **JPA/Hibernate**
- **Banco de Dados** (configuração via Spring Data JPA).
- **Lombok**
- **Swagger UI** (documentação automática da API).

---

Para dúvidas ou melhorias, entre em contato! 😊