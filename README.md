# Ways - Gestão de Transporte Escolar

> **Transformando a logística escolar com eficiência e estilo.**![Banner Ways](https://via.placeholder.com/1000x300/212121/FFFFFF?text=Ways+App)

## 📱 Descrição do App

O **Ways** (antigo Tio da Perua) é um aplicativo Android desenvolvido nativamente com Kotlin, focado na gestão de transporte escolar. O objetivo é facilitar a vida de condutores de vans escolares, permitindo o gerenciamento centralizado de alunos, responsáveis, escolas, turmas e condutores.

O aplicativo conta com um design moderno em **Dark Mode** inspirado no design do Uber, integração com API de CEPs e armazenamento local de dados.

---

## 📸 Telas Principais

| Login | Cadastro de Alunos | Integração ViaCEP |
|:-----------------:|:------------------:|:-----------------:|
| <img src="caminho/para/print_login.png" width="250"> | <img src="caminho/para/print_alunos.png" width="250"> | <img src="caminho/para/print_viacep.png" width="250"> |

| Lista de Turmas | Menu Principal | Dark Mode () UI |
|:---------------:|:--------------:|:------------:|
| <img src="caminho/para/print_turmas.png" width="250"> | <img src="caminho/para/print_menu.png" width="250"> | <img src="caminho/para/print_geral.png" width="250"> |

*(Substitua os caminhos acima pelos arquivos de imagem reais na pasta do seu projeto)*

---

## 🛠 Tecnologias Utilizadas

*   **Linguagem:** [Kotlin](https://kotlinlang.org/)
*   **IDE:** [Android Studio](https://developer.android.com/studio)
*   **Banco de Dados Local:** SQLite (com `SQLiteOpenHelper`)
*   **Comunicação de Rede (API):** [Retrofit 2](https://square.github.io/retrofit/) & [GSON](https://github.com/google/gson)
*   **Navegação:** Android Navigation Component
*   **Layout:** XML (ConstraintLayout, LinearLayout, CardView) com ViewBinding
*   **Design:** Material Design (Custom Dark Theme - Inspirado no design do Uber)

---

## 🚀 Passo a Passo para Instalar e Rodar

Siga este guia para configurar o ambiente de desenvolvimento do zero.

### 1. Instalação do GIT
Para baixar o projeto, você precisa do Git instalado.
1. Baixe o Git: [git-scm.com](https://git-scm.com/downloads).
2. Instale (pode seguir as opções padrão, "Next", "Next"...).
3. Abra o terminal (CMD ou PowerShell) e teste:
   ```bash
   git --version

### 2. Clonar o Projeto
Abra uma pasta no seu computador, clique com botão direito > "Git Bash Here" ou use o terminal:
  ```bash
  git clone <URL obtida no botão "Code" na Home do repositório>
  cd Ways
  ```
### 3. Instalação do Android Studio
1. Baixe a IDE oficial: [Android Studio Download](https://developer.android.com/studio).
2. Instale e abra o programa.
3. Na tela inicial, escolha **"Open"** e selecione a pasta onde você clonou o projeto `Ways`.
4. Aguarde o **Gradle** sincronizar (pode demorar alguns minutos na primeira vez).

### 4. Configurando o Celular (Depuração USB)
Se o seu computador for lento para rodar o Emulador, rodar direto no celular é a melhor opção.
1. No celular, vá em **Configurações > Sobre o telefone**.
2. Toque 7 vezes em **"Número da versão"** (ou "Número da compilação") até aparecer "Você agora é um desenvolvedor".
3. Volte, vá em **Sistema > Opções do Desenvolvedor**.
4. Ative a chave **"Depuração USB"**.

### 5. Instalando Drivers USB (Windows)
1. Conecte o celular ao PC via cabo USB.
2. Se o Android Studio não reconhecer o celular:
   * Baixe o [Driver USB Universal](https://developer.android.com/studio/run/win-usb) ou o driver específico da marca do seu celular (Samsung, Motorola, Xiaomi).
   * Instale o driver.
3. No celular, vai aparecer uma janela "Permitir depuração USB?". Clique em **"Permitir"**.

### 6. Rodando o App
1. No Android Studio, no topo direito, verifique se o seu celular aparece na lista de dispositivos.
2. Clique no botão verde ▶️ (**Run 'app'**).
3. O app será instalado e abrirá no seu celular.

---

## 🌐 Endpoints da API

O projeto utiliza a API pública do **ViaCEP** para preenchimento automático de endereços no cadastro de escolas.

*   **Base URL:** `https://viacep.com.br/ws/`
*   **Método:** `GET`
*   **Rota:** `/{cep}/json/`

**Exemplo de uso:**
Ao digitar 8 números no campo de CEP, o app dispara a requisição e preenche o Logradouro, Bairro, Cidade e UF automaticamente.

---

## 💾 Como funciona o CRUD (Banco de Dados)

O aplicativo utiliza o padrão **DAO (Data Access Object)** com **SQLite** nativo para persistência de dados. A lógica funciona da seguinte forma:

1.  **Model:** Classes de dados (ex: `Aluno`, `Escola`) representam as tabelas.
2.  **DatabaseHelper:** Classe responsável por criar (`onCreate`) e atualizar (`onUpgrade`) a estrutura das tabelas no banco de dados do celular (`tiodaperua.db`).
3.  **DAO:** Cada entidade tem sua classe DAO (ex: `UsuarioDAO`, `EscolaDAO`).
    *   **Create (C):** O método `.cadastrar()` recebe um objeto e usa `db.insert()` para salvar.
    *   **Read (R):** O método `.listar()` usa `db.rawQuery()` ou `db.query()` para retornar uma lista de objetos.
    *   **Update (U):** O método `.atualizar()` usa `db.update()` baseado no ID.
    *   **Delete (D):** O método `.excluir()` usa `db.delete()` baseado no ID.
4.  **UI:** Os Fragments chamam os métodos do DAO para exibir ou salvar as informações na tela.

---

## ✅ Funções Implementadas e Pendentes

### ✔️ Implementadas
- [x] Tela de Login e Cadastro de Usuário.
- [x] Dashboard com navegação intuitiva.
- [x] CRUD Completo de Alunos.
- [x] CRUD Completo de Responsáveis.
- [x] CRUD Completo de Turmas.
- [x] CRUD Completo de Escolas.
- [x] CRUD Completo de Condutores.
- [x] Integração com ViaCEP (Auto-preenchimento).
- [x] Design System Dark Mode Personalizado.

### 🚧 Pendentes (Próximos Passos)
- [ ] Integração com banco de dados na nuvem (Firebase).
- [ ] Otimização de rotas com Google Maps API.
- [ ] Geração de mensalidades e controle financeiro.
- [ ] Notificações Push para os pais (Chegada/Saída).

---

## 👥 Autores

Projeto desenvolvido com dedicação por:

*   **Arthur Gonçalves** - RA: 2404108
*   **Breno Gonçalves Renzi** - RA: 2403703
*   **Caliu Muriell** - RA: 2404012
*   **Eduardo Oliveira** - RA: 2501548
*   **Gustavo Silva** - RA: 2400891
*   **Nicole Moraes Ferreira** - RA: 2403651

---
*© 2024 Ways - Todos os direitos reservados.*
