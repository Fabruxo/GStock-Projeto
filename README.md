# Documentação do Sistema


## SUMÁRIO

Dados do Cliente........................................................................................................................................2 \
Equipe de Desenvolvimento.......................................................................................................................3 \
Introdução...................................................................................................................................................4 \
Objetivo.......................................................................................................................................................5 \
Escopo........................................................................................................................................................6 \
Backlogs do Produto...................................................................................................................................7 \
Cronograma................................................................................................................................................8 \
Materiais e Métodos....................................................................................................................................9 \
Resultados..................................................................................................................................................10 \
Conclusão...................................................................................................................................................11 \
Homologação do MVP junto ao cliente......................................................................................................12 \
Divulgação..................................................................................................................................................13 \
Carta de Apresentação...............................................................................................................................15 \
Carta de Autorização..................................................................................................................................16 \
Relato individual do processo.....................................................................................................................18 







## Dados do Cliente

Título do Projeto: Gstock: Solução para gerenciamento de estoque \
Cliente: Victor Hugo Brito Marião \
CNPJ/CPF: 59.353.373/0001-99 \
Contato: Victor Hugo \
Email do contato: victormariao2003@gmail.com

## Equipe de Desenvolvimento


| Nome Completo              | Curso                                 | Disciplina |
|----------------------------|---------------------------------------|------------|
| Fabrício Amorim dos Santos | Análise e Desenvolvimento de Sistemas | Padrões de Projeto de Software com Java|
| Heric Prestelo Pedro       | Análise e Desenvolvimento de Sistemas | Padrões de Projeto de Software com Java|
|Victor Hugo Brito Marião    | Análise e Desenvolvimento de Sistemas | Padrões de Projeto de Software com Java|


| Professor Orientador |
|----------------------|
| Kesede Rodrigues Julio |



## Introdução



Gstock foi idealizado por nós no momento que o nosso cliente e também integrante nos relatou dos problemas que sua empresa possuía em relação ao gerenciamento das peças automotivas em seu estoque.
O maior problema relatado pelo nosso cliente foi a falta de agilidade no processo de armazenagem e saque das peças, que por terem várias especificações diferentes, mas que podem ser aplicadas num veículo erroneamente, transformam o processo de estoque e saída dessas peças num processo tortuoso.

Para sanar a questão que nos foi proposta, iremos utilizar Java orientado a objetos no backend, SQLite como banco de dados, HTML, CSS e JavaScript para a interface Web e API Rest para comunicação do backend com o frontend.

Com a finalização do projeto, o cliente poderá realizar o estoque e a saída das peças com uma maior facilidade, transformando o processo em algo simples e rápido.


## Objetivo



O maior problema que nosso cliente enfrenta hoje, é a falta de agilidade na hora de contabilizar as entradas, saídas e peças quebradas no estoque que são feitas manualmente.
O nosso intuito é facilitar a vida dos estoquistas do nosso cliente criando um sistema de gerenciamento de estoque fácil e simples de ser assimilado por qualquer um.


## Escopo



Os principais requisitos que foram citados pelo cliente e que serão implementados são:

Cadastro de Produtos
Cadastro de Estoques
Controle de Entrada e Saída
Movimentações Internas



## Backlogs do Produto


Cadastro de novos usuários
Login dos usuários
Cadastro e Gestão de Itens e Produtos
Controle de Entrada
Controle de Saída
Movimentações Internas
Histórico de Movimentações


## Cronograma

### 21/03 a 03/04
* Criação do banco de dados SQLite
* Implementação da conexão com o banco (Database.java)
* Criação da entidade Usuario
* Implementação do UsuarioDAO com operações de cadastro e login
* Estrutura inicial do Main.java com menu de autenticação

### 04/04 a 17/04
* Criação da entidade Produto e implementação do ProdutoDAO com CRUD
* Criação da entidade Estoque e implementação do EstoqueDAO com CRUD
* Integração dos cadastros no menu principal
* Validação de entradas e IDs

### 18/04 a 01/05
* Criação da entidade ProdutoEstoque
* Implementação do ProdutoEstoqueDAO com suas funções
* Integração dessas funções no menu do sistema

### 02/05 a 15/05
* Implementação da função de retirar produto do estoque
* Criação da entidade HistoricoRetirada
* Implementação do HistoricoRetiradaDAO para registrar retiradas com data, quantidade e usuário
* Integração da retirada no menu principal
* Correção de erros e ajustes de fluxo

### 16/05 a 23/05
* Testes completos de todas as funcionalidades
* Organização final das classes e pacotes
* Refatoração leve para clareza e padronização de código

O Cronograma acima diz respeito apenas as tarefas de desenvolvimento do sistema.

## Materiais e Métodos

### Modelagem do sistema:
![Caso de Uso drawio](https://github.com/user-attachments/assets/7e14bb9b-ca03-40f2-b66c-d21834758a7f)

![Diagrama de Classes drawio](https://github.com/user-attachments/assets/cee68d50-af42-4465-b0ea-0d496919ba31)








### Tecnologias utilizadas:
Para o desenvolvimento do Gstock, optamos por utilizar tecnologias acessíveis, leves e adequadas para um sistema de controle de estoque com foco em agilidade e confiabilidade. \
No backend, utilizamos a linguagem Java com o paradigma de programação orientada a objetos, o que nos permitiu estruturar o código de forma organizada, reutilizável e fácil de manter. \
A aplicação foi construída em camadas, com separação de responsabilidades entre os modelos, controladores e classes de acesso ao banco de dados (DAO). \
Como banco de dados, escolhemos o SQLite, por ser uma solução leve, de fácil integração e ideal para aplicações locais ou de pequeno porte. Ele nos permitiu armazenar todas as informações de peças, categorias e movimentações de estoque de forma eficiente e segura. \
Para a construção da interface gráfica, iremos utilizar HTML, CSS e JavaScript, desenvolvendo uma interface web simples e funcional, acessível por navegadores. O HTML será utilizado para estruturar as páginas, o CSS para aplicar o estilo visual e o JavaScript para interatividade no lado do cliente. \
A comunicação entre o frontend e o backend foi feita por meio de uma API REST, que nós mesmos iremos desenvolver em Java. Essa API expõe os endpoints necessários para realizar operações como cadastro, listagem e retirada de peças. \
Para o desenho dos modelos e documentação do projeto, utilizamos a ferramenta Draw.Io para gerar os diagramas de classes, casos de uso e o modelo entidade-relacionamento (MER), o que nos ajudou a visualizar melhor a estrutura do sistema e garantir uma modelagem bem planejada. \
Utilizamos o ChatGPT 4.0 para tirar dúvidas e nos ajudar com o desenvolvimento do projeto. \
Essas tecnologias foram escolhidas levando em consideração a nossa familiaridade com elas, a facilidade de uso e a compatibilidade com os objetivos do projeto.


### Arquitetura do sistema: 

Segue abaixo o diagrama de arquitetura do sistema \
![Diagrama de Arquitetura drawio](https://github.com/user-attachments/assets/9ca7496d-81f6-4e60-9e2f-9f218b6960d9)



## Resultados


### Protótipo:

Como ainda não possuímos um front-end para o projeto, estamos testando as principais funcionalidades junto ao nosso cliente usando uma interface no terminal.

![7adabccf-c6d8-4c9c-8e34-4b2029be3dab](https://github.com/user-attachments/assets/7ccc922a-7dea-44fb-a956-0f60ac261987) \
A primeira tela acima é referente ao login do usuário administrador ou   usuário comum, destinado aos funcionários do cliente. 

![WhatsApp Image 2025-05-14 at 21 10 27](https://github.com/user-attachments/assets/73a238f0-0e07-41ce-83dd-a3249b8a7cd5) \
O menu acima é apresentado após o login bem-sucedido do usuário, onde o mesmo poderá realizar as operações de cadastro, listagem, ou retirada de algum produto ou estoque.












### Códigos das principais funcionalidades: 

Segue abaixo algumas das principais funções do sistema.

Segue abaixo o código da classe ProdutoEstoqueDAO:

        package dao;
        
        import model.*;
        import util.Database;
        
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;
        
        public class ProdutoEstoqueDAO {
        
        // FUNÇÃO PARA APRESENTAR UM DETERMINADO PRODUTO NUM DETERMINADO ESTOQUE
        
            public void inserirProdutoEmEstoque(ProdutoEstoque pe) {
                String sql = "INSERT INTO produtos_estoque (id_produto, id_estoque, corredor, bin, quantidade) VALUES (?, ?, ?, ?, ?)";
        
                try (Connection conn = Database.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
        
                    stmt.setLong(1, pe.getProduto().getId());
                    stmt.setLong(2, pe.getEstoque().getId());
                    stmt.setString(3, pe.getCorredor());
                    stmt.setString(4, pe.getBin());
                    stmt.setDouble(5, pe.getQuantidade());
        
                    stmt.executeUpdate();
                    System.out.println("Produto inserido no estoque com sucesso!");
        
                } catch (SQLException e) {
                    System.out.println("Erro ao inserir produto no estoque: " + e.getMessage());
                }
            }
        
        // FUNÇÃO PARA APRESENTAR OS PRODUTOS EM DETERMINADOS ESTOQUES
        
            public List<ProdutoEstoque> listarProdutosEmEstoque() {
                List<ProdutoEstoque> lista = new ArrayList<>();
                String sql = """
                    SELECT pe.id, p.id AS id_produto, p.nome, p.anoSuportadoInicio, p.anoSuportadoFim, p.modeloCarro,
                           e.id AS id_estoque, e.nome AS nome_estoque, e.posicao,
                           pe.corredor, pe.bin, pe.quantidade
                    FROM produtos_estoque pe
                    JOIN produtos p ON pe.id_produto = p.id
                    JOIN estoques e ON pe.id_estoque = e.id
                """;
        
                try (Connection conn = Database.getConnection();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {
        
                    while (rs.next()) {
                        Produto produto = new Produto(
                                rs.getLong("id_produto"),
                                rs.getString("nome"),
                                rs.getString("anoSuportadoInicio"),
                                rs.getString("anoSuportadoFim"),
                                rs.getString("modeloCarro")
                        );
        
                        Estoque estoque = new Estoque(
                                rs.getLong("id_estoque"),
                                rs.getString("nome_estoque"),
                                rs.getString("posicao")
                        );
        
                        ProdutoEstoque pe = new ProdutoEstoque(
                                rs.getLong("id"),
                                produto,
                                estoque,
                                rs.getString("corredor"),
                                rs.getString("bin"),
                                rs.getDouble("quantidade")
                        );
        
                        lista.add(pe);
                    }
        
                } catch (SQLException e) {
                    System.out.println("Erro ao listar produtos em estoque: " + e.getMessage());
                }
        
                return lista;
            }
        //FUNÇÃO PARA EDITAR UM PRODUTO EM ESTOQUE
        
            public void editarProdutoEmEstoque(ProdutoEstoque pe) {
                String sql = "UPDATE produtos_estoque SET corredor = ?, bin = ?, quantidade = ? WHERE id = ?";
        
                try (Connection conn = Database.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
        
                    stmt.setString(1, pe.getCorredor());
                    stmt.setString(2, pe.getBin());
                    stmt.setDouble(3, pe.getQuantidade());
                    stmt.setLong(4, pe.getId());
        
                    stmt.executeUpdate();
                    System.out.println("Produto em estoque atualizado com sucesso!");
                } catch (SQLException e) {
                    System.out.println("Erro ao editar produto em estoque: " + e.getMessage());
                }
            }
        
        // FUNÇÃO PARA DELETAR O PRODUTO DE ALGUM ESTOQUE PARA CASOS DE ADIÇÃO ERRONEA
        
            public void deletarProdutoEmEstoque(long id) {
                String sql = "DELETE FROM produtos_estoque WHERE id = ?";
        
                try (Connection conn = Database.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
        
                    stmt.setLong(1, id);
                    stmt.executeUpdate();
                    System.out.println("Produto removido do estoque com sucesso!");
                } catch (SQLException e) {
                    System.out.println("Erro ao deletar produto em estoque: " + e.getMessage());
                }
            }
        
        // FUNÇÃO PARA RETIRAR UM PRODUTO DE UM ESTOQUE PARA SER UTILIZADO
        
            public void retirarProdutoDoEstoque(long id, double quantidadeRetirada) {
                String selectSql = "SELECT quantidade FROM produtos_estoque WHERE id = ?";
                String updateSql = "UPDATE produtos_estoque SET quantidade = ? WHERE id = ?";
                HistoricoRetirada historicoParaRegistrar = null;
        
                try (Connection conn = Database.getConnection();
                     PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
        
                    selectStmt.setLong(1, id);
                    ResultSet rs = selectStmt.executeQuery();
        
                    if (rs.next()) {
                        double quantidadeAtual = rs.getDouble("quantidade");
        
                        if (quantidadeRetirada > quantidadeAtual) {
                            System.out.println("❌ Quantidade insuficiente no estoque.");
                            return;
                        }
        
                        double novaQuantidade = quantidadeAtual - quantidadeRetirada;
        
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                            updateStmt.setDouble(1, novaQuantidade);
                            updateStmt.setLong(2, id);
                            updateStmt.executeUpdate();
                            updateStmt.executeUpdate();
                            System.out.println("✅ Produto retirado com sucesso! Nova quantidade: " + novaQuantidade);
                        }
        
                        historicoParaRegistrar = new HistoricoRetirada(
                                id,
                                quantidadeRetirada,
                                java.time.LocalDateTime.now().toString(),
                                System.getProperty("user.name")
                        );
        
                    } else {
                        System.out.println("❌ ProdutoEstoque não encontrado.");
                    }
        
                } catch (SQLException e) {
                    System.out.println("Erro ao retirar produto: " + e.getMessage());
                }
                if (historicoParaRegistrar != null) {
                    HistoricoRetiradaDAO historicoDAO = new HistoricoRetiradaDAO();
                    historicoDAO.registrar(historicoParaRegistrar);
                }
            }
        
        }

        
        
### Conclusão


Impacto do sistema: o sistema auxiliou o cliente no controle e mapeamento do estoque e economia de tempo e gastos \
Melhorias Futuras: interface gráfica, controle de compra de produtos em falta, criar funções para usuários comuns e administrativos


### Homologação do MVP junto ao cliente


Após as entregas parciais, realizadas de acordo com os requisitos do sistema  e cronograma, o MVP foi apresentado em uma reunião, realizada entre o time de desenvolvedores e o cliente.

![adb94965-7077-4ffd-b53b-f5b3302f1b9e](https://github.com/user-attachments/assets/8e029642-9feb-40d8-982e-ef12736cc72c)
Da esquerda para direita: Heric controlando o sistema durante a apresentação, Victor à esquerda e Fabrício à direita.

![27398897-4e8a-4338-ba14-68f2537155bc](https://github.com/user-attachments/assets/5105268c-9008-4e10-8851-4d7ebb764fd0)
Victor Hugo, nosso integrante e cliente, realizando falando sobre a necessidade de seu cliente final.

![8cb78d4d-2554-42ed-86e3-128af6f795dc](https://github.com/user-attachments/assets/d1f073f0-6d94-4462-8f53-0ea15e6b7eea)
Fabrício apresentando alguma funções implementadas no GSock.





Segue abaixo a lista de presentes na homologação do MVP.

Lista de presentes na Homologação
| Nome                                      | Matrícula     |
|-------------------------------------------|---------------|
| Adriano de Sousa Ramos                    | 202302381219  |
| Aline Silveira Cordeiro                   | 202302376533  |
| André Lucas Martins Ezequie               | 202402810201  |
| Beatriz Colombo de Oliveira               | 202303878451  |
| Bianca Pessin Avelino                     | 202302377076  |
| Breno Zachello Oliveira                   | 202202455601  |
| Caick Bertin Viana                        | 202302773664  |
| Daniel Servino da Rocha                   | 202302381464  |
| Derek Bergesch                            | 202303321279  |
| Douglas Carlos de Castro                  | 202202771309  |
| Eduardo Alejandro Meli Aracena Bello      | 202303413572  |
| Emilly Araújo Marques                     | 202303801841  |
| Fabrício Amorim dos Santos                | 202302857957  |
| Felipe Orpheu Santoro Vasconcelos         | 202202389706  |
| Fernando Ferreira da Silva                | 202208700195  |
| Fernando Rodrigues de Sousa               |               |
| Gabriel Cangiani                          | 202304302723  |
| Gabriel de Oliveira Lima                  | 202302381261  |
| Gabriel Juliani Arroyo                    | 202302784471  |
| Guilherme Afonso da Silva Ferrari         | 202302468659  |
| Guilherme Martins Spiandorin              | 202302381359  |
| Heric Prestelo Pedro                      | 202302382479  |
| Jennifer de Oliveira                      | 202103014411  |
| João Alexandre Nunes Belchior             | 202303467079  |
| João Henrique Augait do Nascimento        | 202202782671  |
| João Pedro Dumbra Sturla                  | 202302424856  |
| João Victor Tourinho dos Santos           | 202308428489  |
| Leonardo Hideki Kuriki                    | 202302381431  |
| Lucas Ferreira Ascioni                    | 202108617806  |
| Lucas Guilherme Silva                     | 202303886241  |
| Lucas Guthierrez Oliver                   | 202208700292  |
| Lucas Ryan Rodrigues Barbosa              | 202303181493  |
| Lucas Silva do Carmo                      | 202202834181  |
| Lucas de Souza Pereira                    | 202303152035  |
| Luiz Gustavo de Lara Freschi              | 202303112581  |
| Marcos Vinicius Cardoso Correa            | 202204018292  |
| Matheus Azevedo Rosa                      | 202204156326  |
| Matheus Ramos Marcolino                   | 202302376819  |
| Michael de Souza da Silva                 | 202308396382  |
| Natalia Policeno                          | 202304086826  |
| Paulo Henrique Angelino Braga             | 202302380026  |
| Pedro Daniel Marques                      | 202302892523  |
| Pedro Henrique da Silva dos Santos        | 202403070049  |
| Sabrina Ribeiro Guimarães dos Santos      | 202203181076  |
| Samuel Bernardes                          | 202303459696  |
| Thiago Moscatini Carvalho                 | 202302382428  |
| Tonislau Domingos Quissanga               | 202208700519  |
| Victor Hugo Brito Marião                  | 202304086826  |
| Vinícius Teixeira Tamasaukas              | 202302381642  |
| Wellington José de Lima                   | 202302380921  |


Ao final da apresentação, o sistema  foi homologado pelo cliente.

