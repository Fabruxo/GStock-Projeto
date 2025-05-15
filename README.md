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


A ser realizado


## Materiais e Métodos


### Modelagem do sistema:









### Tecnologias utilizadas:
Para o desenvolvimento do Gstock, optamos por utilizar tecnologias acessíveis, leves e adequadas para um sistema de controle de estoque com foco em agilidade e confiabilidade.  No backend, utilizamos a linguagem Java com o paradigma de programação orientada a objetos, o que nos permitiu estruturar o código de forma organizada, reutilizável e fácil de manter. A aplicação foi construída em camadas, com separação de responsabilidades entre os modelos, controladores e classes de acesso ao banco de dados (DAO).Como banco de dados, escolhemos o SQLite, por ser uma solução leve, de fácil integração e ideal para aplicações locais ou de pequeno porte. Ele nos permitiu armazenar todas as informações de peças, categorias e movimentações de estoque de forma eficiente e segura.Para a construção da interface gráfica, utilizamos HTML, CSS e JavaScript, desenvolvendo uma interface web simples e funcional, acessível por navegadores. O HTML foi utilizado para estruturar as páginas, o CSS para aplicar o estilo visual e o JavaScript para interatividade no lado do cliente.A comunicação entre o frontend e o backend foi feita por meio de uma API REST, que nós mesmos desenvolvemos em Java. Essa API expõe os endpoints necessários para realizar operações como cadastro, listagem e retirada de peças.Para o desenho dos modelos e documentação do projeto, utilizamos a ferramenta PlantUML para gerar os diagramas de classes, casos de uso e o modelo entidade-relacionamento (MER), o que nos ajudou a visualizar melhor a estrutura do sistema e garantir uma modelagem bem planejada.Essas tecnologias foram escolhidas levando em consideração a nossa familiaridade com elas, a facilidade de uso e a compatibilidade com os objetivos do projeto.











### Arquitetura do sistema: 
Segue abaixo o diagrama de arquitetura do sistema



## Resultados


### Protótipo:

Como ainda não possuímos um front-end para o projeto, estamos testando as principais funcionalidades junto ao nosso cliente usando uma interface no terminal.


A primeira tela acima é referente ao login do usuário administrador ou   usuário comum, destinado aos funcionários do cliente.



O menu acima é apresentado após o login bem-sucedido do usuário, onde o mesmo poderá realizar as operações de cadastro, listagem, ou retirada de algum produto ou estoque.












### Códigos das principais funcionalidades: Segue abaixo algumas das principais funções do sistema.

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

// FUNÇÃO PARA RETIRAR UM PRODUTO DE UM ESTOQUE PARA SER UTILIZADO \
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








## Conclusão


Impacto do sistema: <Dica: como o sistema impactou (alterou positivamente) o processo do cliente>
Melhorias Futuras: <Dica: elencar, pelo menos, uma melhoria que poderá ser realizada futuramente no sistema.>


## Homologação do MVP junto ao cliente


Após as entregas parciais, realizadas de acordo com os requisitos do sistema  e cronograma, o MVP foi apresentado em uma reunião, realizada entre o time de desenvolvedores e o cliente.

<Dica: inserir uma foto da homologação em cada linha do quadro abaixo. Serão 4 fotos (tiradas no momento da homologação) e, na linha debaixo, uma legenda para cada uma delas. A homologação, preferencialmente, deve ser presencial. Se não for viável, pode ser feita por videoconferência com prints da tela.>

<foto 1: foto do time e cliente com o primeiro slide de fundo>
<foto 2: foto de um integrante apresentando o MVP.>
Da esquerda para direita: <legenda 1: descreva quem está na foto>
<legenda 2: coloque o nome de quem está apresentando>
<foto 3: foto dos participantes assistindo a homologação>
<foto 4: foto do plano geral do local>
Participantes da homologação assistindo a apresentação
Participantes da homologação


Segue abaixo a lista de presentes na homologação do MVP.

Lista de presentes na Homologação
<Cole aqui a foto da lista de presentes na homologação.>


Ao final da apresentação, o sistema  foi homologado pelo cliente.

## Divulgação


Linkedin do Projeto
<A página do Linkedin do projeto deve ter o logo do LTD, o titulo do projeto, um breve resumo, o nome dos integrantes e o nome do professor-orientador. Insira também o link do repositório do projeto no GitHub. Neste perfil, deve ser postado a cada Sprint, os artefatos produzidos (diagramas, videos explicativos de códigos, artigo sobre determinado tema vinculado ao desenvolvimento do projeto). Promova engajamento e networking conectando-se a profissionais da área, compartilhamentos, comentários etc.
Insira o linnk deste perfil com o seu perfil pessoal do Linkedin.

<print da tela de perfil do Linkedin>
<link da pág do Linkedin>


Seminário de Projetos de Software

Vídeo da apresentação: <Grave sua apresentação, poste no Linkedin do projeto e insira aqui o link público (acesso sem login) do vídeo da apresentação>

<Na tabela abaixo, inserir uma foto da apresentação em cada linha. Serão 4 fotos (tiradas no momento da apresentação). Para cada foto, descreva uma legenda na linha de baixo.>

<foto 1: foto do time com o primeiro slide de fundo>
<foto 2: foto de um integrante apresentando o sistema.>
Da esquerda para direita: <legenda 1: descreva quem está na foto>
<legenda 2: coloque o nome de quem está apresentando>
<foto 3: foto plano geral da apresentação de frente para o fundo da sala>
<foto 4:  foto plano geral da apresentação do fundo para a frente da sala>
Participantes do evento assistindo a apresentação
Participantes do evento assistindo a apresentação


Segue abaixo a lista de presentes na apresentação.

Lista de presentes na Apresentação
<Faça uma lista de presença numa folha A4, contendo no alto da folha “Seminários de Projetos de Software”. A lista deve conter ra, nome e assinatura dos presentes. Cole aqui a foto desta lista.>


FENETEC: Feira de Negócios em Tecnologia

Apresentação do projeto: <Um vídeo deve ser produzido mostrando o time apresentando seu projeto para algum visitante. Importante que neste video tenha uma tomada do banner e dos integrantes. Insira aqui o link público deste vídeo.>

<Na tabela abaixo, inserir uma foto da apresentação em cada linha. Serão 4 fotos (tiradas do evento). Para cada foto, descreva uma legenda na linha de baixo.>

<foto 1: foto do time ao lado do poster>
<foto 2: foto de um integrante apresentando o sistema.>
Da esquerda para direita: <legenda 1: descreva quem está na foto>
<legenda 2: coloque o nome de quem está apresentando>
<foto 3: foto do público assistindo sua apresentação>
<foto 4:  foto plano geral da FENETEC>
Participantes do evento assistindo a apresentação
Estandes da FENETEC


Segue abaixo a lista de presentes na FENETEC.

Lista de presentes na Apresentação
<cole aqui a lista de presença dos visitantes da FENETEC com nome e email do visitante . Os próprios times farão um form contendo no cabeçalho: Lista de Visitantes FENETEC. Compartilhe a planilha gerada pelo form com todos os times.>















Carta de Apresentação

Vimos por desta apresentar o grupo de acadêmicos do Centro Universitário Unimetrocamp, localizada à Rua Sales de Oliveira, 1661 - Campinas - SP, a fim de convidá-lo a participar de uma atividade extensionista associada ao componente curricular <inserir o nome da disciplina>, sob responsabilidade do orientador Prof. Kesede Rodrigues Julio (profkesede64@gmail.com).
Em consonância ao Plano Nacional de Educação vigente, o Centro Universitário Unimetrocamp promove o Desenvolvimento de Software que, norteados pela metodologia de Gerenciamento Ágil Scrum, tem por princípios fundantes o diagnóstico dos problemas/demandas/necessidades, a participação ativa dos interessados/públicos participantes, a construção dialógica, coletiva e experiencial de conhecimentos, o planejamento de ações, o desenvolvimento e avaliação das ações, a sistematização dos conhecimentos, a avaliação das ações desenvolvidas.
Nesse contexto, a disciplina acima mencionada tem como principal escopo os temas relacionados à Programação Orientada à Objeto / Padrões de Projetos de Software, no que diz respeito ao desenvolvimento de um software utilizando Programação Orientada à Objeto.
Sendo assim, pedimos o apoio de <nome do cliente> para a realização das seguintes atividades: levantamento de requisitos, validação das entregas parciais, revalidação dos requisitos, homologação do MVP, ou qualquer outra intervenção que auxilie no desenvolvimento das competências de nossos acadêmicos e ao mesmo tempo possa contribuir para a comunidade em que estamos inseridos.
Aproveitamos a oportunidade para solicitarmos, em caso de aceite, que a parceria seja formalizada, mediante assinatura da Carta de Autorização, as atividades e informações que o(s) aluno(s) poderá(ão) ter acesso.
Em tempo, registramos ainda, o convite para a participação de todos os interessados no fórum semestral de acompanhamento e avaliação das atividades realizadas, que está previsto para o final deste semestre, e será comunicado previamente em convite específico.
Desde já nos colocamos à sua disposição para quaisquer esclarecimentos.
Atenciosamente,
Campinas, ____ de _________ de 202___.

____________________________________
Assinatura Direção Acadêmica da IES

____________________________________
Assinatura Docente


Carta de Autorização

Eu, (preencher com nome do responsável), (preencher com cargo ocupado), da (nome da empresa, organização, associação, escola, secretaria, etc., situada no endereço – inserir o endereço), autorizo a realização das seguintes atividades acadêmicas do componente extensionista <código e nome da disciplina>, do Centro Universitário Unimetrocamp, sob orientação do Prof. Kesede Rodrigues Julio.

Atividades:





Conforme combinado em contato prévio, as atividades acima descritas são autorizadas para os seguintes alunos:

Nome dos/das alunos/as
Curso
Matrícula




















Declaro que fui informado por meio da Carta de Apresentação sobre as características e objetivos das atividades que serão realizadas na organização/instituição/empresa a qual represento e afirmo estar ciente de tratar-se de uma atividade realizada com intuito exclusivo de ensino de alunos de graduação, sem a finalidade de exercício profissional.

Desta forma, autorizo, em caráter de confidencialidade:

o acesso a informações e dados que forem necessários à execução da atividade;
o registro de imagem por meio de fotografias;
outro: (especificar)


Campinas, ___ de ___________de 202_.

___________________________________________________________________
(Assinatura, nome completo do responsável, email de contato e com carimbo da empresa)


Relato individual do processo


<nome do aluno>
<um breve relato pessoal sobre o trabalho extensionista desenvolvido>


<nome do aluno>
<um breve relato pessoal sobre o trabalho extensionista desenvolvido>


<nome do aluno>
<um breve relato pessoal sobre o trabalho extensionista desenvolvido>


<nome do aluno>
<um breve relato pessoal sobre o trabalho extensionista desenvolvido>


<nome do aluno>
<um breve relato pessoal sobre o trabalho extensionista desenvolvido>



 

